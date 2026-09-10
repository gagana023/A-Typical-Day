package app;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Represents the player user and controls player movement, animation, stats, and room transitions.
 *
 * <p>This class connects keyboard input, updates the player through an animation timer, checks room
 * collisions, and manages returning to the main hallway scene.
 */
public class User {
  /** The room manager used to open different room scenes. */
  private RoomManager roomManager;

  /** The input handler used to track player keyboard movement. */
  private PlayerInput playerInput = new PlayerInput();

  /**
   * The canvas representing the top-left, bottom-left, top-right, and bottom-right room entrance.
   */
  private Canvas topL, botL, topR, botR;

  /** The controller used to update player movement. */
  private MovementController movementController;

  /** The canvas used to display the player. */
  private Canvas canvas;

  /** The timer used to repeatedly update movement, animation, and collision checks. */
  private AnimationTimer timer;

  /** Tracks whether the player is currently allowed to enter a room. */
  private boolean canEnter = true;

  /** The transitioner used to check and handle room entrances. */
  private RoomTransitioner roomTransitioner;

  /** The player's social battery and social standing stats. */
  private PlayerStats stats = new PlayerStats();

  /** The collision checker used to detect overlap with objects. */
  private CollisionChecker collisionChecker = new CollisionChecker();

  /** The animation object used to draw and update the player sprite. */
  private PlayerAnimation playerAnimation;

  /** The player object that stores the player's canvas, position, and stats. */
  private Player player;

  private long lastBullyHitTime = 0;
  private final long bullyImmunityTime = 2_000_000_000L;
  private boolean secondBullyAdded = false;

  private List<Bully> bullies = new ArrayList<>();
  private Stage stage;
  private boolean paused = false;

  /** Creates a user with a player animation, canvas, and player object. */
  public User() {
    playerAnimation = new PlayerAnimation("/walk-front.png", 4, 3, 12);
    canvas = playerAnimation.getCanvas();
    player = new Player(canvas);
  }

  /**
   * Returns the canvas used to display the player.
   *
   * @return the player's canvas
   */
  public Canvas getInAddAllForm() {
    return canvas;
  }

  /**
   * Resumes the user in the given scene.
   *
   * <p>This method stops movement, resets the player position, starts the animation timer, allows
   * room entry again, resets the room transitioner, and requests focus for keyboard input.
   *
   * @param scene the scene where the user resumes movement
   */
  public void resume(Scene scene) {
    paused = false;
    stopMovement();

    player.setCoordinates(350, 250);
    player.updateCanvasPosition();

    if (roomTransitioner != null) {
      roomTransitioner.reset();
    }

    start();

    for (int i = 0; i < bullies.size(); i++) {
      Bully b = bullies.get(i);
      b.spawnRandomly(scene);
      b.resume();

      if (i == 1 && !secondBullyAdded) {
        b.setVisible(false);
      }
    }

    start();

    Platform.runLater(
        () -> {
          scene.getRoot().requestFocus();
        });

    System.out.println("resumed");
  }

  /**
   * Connects the user controls and animation timer to the given scene.
   *
   * <p>This method sets up keyboard input, movement control, animation updates, room transition
   * checks, and player canvas position updates.
   *
   * @param scene the scene that receives keyboard input and updates
   */
  public void connect(Scene scene) {
    player.updateCanvasPosition();
    playerInput.connect(scene);
    movementController = new MovementController(playerInput);

    timer =
        new AnimationTimer() {
          /**
           * Runs each frame of the animation timer.
           *
           * @param now the current timestamp in nanoseconds
           */
          public void handle(long now) {
            if (paused || timer == null) {
              return;
            }

            boolean moving = movementController.update(player, scene);
            playerAnimation.updateAnimation(moving);
            playerAnimation.renderFrame();
            roomTransitioner.check(User.this);
            player.updateCanvasPosition();

            if (!secondBullyAdded && getHealth() < 0.5 && getSocial() < 0.5 && bullies.size() > 1) {
              Bully secondBully = bullies.get(1);
              secondBully.setVisible(true);
              secondBully.spawnRandomly(scene);
              secondBullyAdded = true;
            }

            for (Bully bully : bullies) {
              if (!bully.isVisible()) {
                continue;
              }

              bully.moveTowardPlayer(scene, player);

              if (collisionChecker.isCollidingWithPadding(canvas, bully, 15, 5)) {
                if (now - lastBullyHitTime < bullyImmunityTime) {
                  continue;
                }

                lastBullyHitTime = now;

                bully.spawnRandomly(scene);

                boolean isOver = bullyHit();

                Platform.runLater(
                    () -> {
                      stop();

                      if (isOver) {
                        stage.setScene(new GameOver().getScene(stage));
                      } else {
                        stage.setScene(new BullyScene().getScene(stage));
                      }
                    });
              }
            }
          }
        };
    // timer.start();
  }

  public void setBullies(List<Bully> bullyList) {
    this.bullies = bullyList;
  }

  /**
   * Changes the user's stats based on the selected dialogue option.
   *
   * <p>This method updates the player's social battery and social standing, refreshes the stats
   * bars, and returns whether the game should end.
   *
   * @param optionType the selected dialogue option number
   * @return true if the stat change causes a game-over condition, false otherwise
   */
  public boolean changeStats(int optionType) {
    System.out.println("Stats Changed");
    boolean over = stats.changeStats(optionType);
    HelloWorld.updateStatsBars();
    return over;
  }

  /**
   * Returns the user's social battery value.
   *
   * @return the current social battery value
   */
  public double getHealth() {
    return stats.getHealth();
  }

  /**
   * Returns the user's social standing value.
   *
   * @return the current social standing value
   */
  public double getSocial() {
    return stats.getSocial();
  }

  /**
   * Checks whether the player is colliding with the given canvas object.
   *
   * @param box the canvas object being checked for collision
   * @return true if the player is colliding with the canvas, false otherwise
   */
  public boolean isColliding(Canvas box) {
    return collisionChecker.isColliding(
        canvas, box /*,player.getPosition().getX(), player.getPosition().getY()*/);
  }

  public boolean bullyHit() {
    boolean over = stats.bullyHit();
    HelloWorld.updateStatsBars();
    return over;
  }

  /**
   * Sets the room entrance canvases used for room transitions.
   *
   * @param topL2 the top-left room entrance canvas
   * @param botL2 the bottom-left room entrance canvas
   * @param topR2 the top-right room entrance canvas
   * @param botR2 the bottom-right room entrance canvas
   * @param stage the main stage used to display room scenes
   */
  public void setRooms(NPC topL2, NPC botL2, NPC topR2, NPC botR2, Stage stage) {
    this.topL = topL2;
    this.topR = topR2;
    this.botR = botR2;
    this.botL = botL2;
    this.stage = stage;
    this.roomManager = new RoomManager(stage);
    this.roomTransitioner = new RoomTransitioner(topL, topR, botL, botR, stage);
  }

  /** Stops all current player movement input. */
  public void stopMovement() {
    playerInput.stopMovement();
  }

  /** Starts the user's animation timer. */
  public void start() {
    if (timer == null) {
      return;
    }
    paused = false;
    timer.start();
  }

  /** Stops the user's animation timer. */
  public void stop() {
    paused = true;
    if (timer != null) {
      timer.stop();
    }
    for (Bully b : bullies) {
      b.stop();
    }
    System.out.println("stopped");
  }

  /**
   * Returns the player object connected to this user.
   *
   * @return the player object
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the player's coordinates.
   *
   * @param x the new x-coordinate
   * @param y the new y-coordinate
   */
  public void setCoordinates(double x, double y) {
    player.setCoordinates(x, y);
  }

  /**
   * Keeps the player inside the boundaries of the given scene.
   *
   * @param scene the scene used to check the movement boundaries
   */
  public void stayInBoundaries(Scene scene) {
    player.stayInBoundaries(scene);
  }
}
