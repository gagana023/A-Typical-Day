package app;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class User {


  private RoomManager roomManager;
  private PlayerInput playerInput = new PlayerInput();
  private Canvas topL, botL, topR, botR;
  private MovementController movementController;


  private Canvas canvas;
  private AnimationTimer timer;
  private boolean canEnter = true;
  private RoomTransitioner roomTransitioner;


  private PlayerStats stats = new PlayerStats();
  private CollisionChecker collisionChecker = new CollisionChecker();
  private PlayerAnimation playerAnimation;
  private Player player;


  public User() {
    playerAnimation = new PlayerAnimation("/blinkdrop.png", 3, 3, 9);
    // sprite = new Sprite("/blinkdrop.png", 3, 3, 9);
    canvas = playerAnimation.getCanvas();
    player = new Player(canvas);
    // renderFrame();
  }


  public Canvas getInAddAllForm() {
    return canvas;
  }


  public void resume(Scene scene) {
    stopMovement();
    player.setCoordinates(350, 250);
    player.updateCanvasPosition();
    start();
    canEnter = true;
    //roomManager.resetEntries();
    roomTransitioner.reset();
    scene.getRoot().requestFocus();
  }


  public void connect(Scene scene) {
    player.updateCanvasPosition();
    playerInput.connect(scene);
    movementController = new MovementController(playerInput);


    timer =
        new AnimationTimer() {
          public void handle(long now) {
            boolean moving = movementController.update(player, scene);
            playerAnimation.updateAnimation(moving);
            playerAnimation.renderFrame();
            roomTransitioner.check(User.this);
            player.updateCanvasPosition();
          }
        };
  }


  public void changeStats(int optionType) {
    System.out.println("Stats Changed");
    stats.changeStats(optionType);
    HelloWorld.updateStatsBars();
  }


  public double getHealth() {
    return stats.getHealth();
  }


  public double getSocial() {
    return stats.getSocial();
  }


  public boolean isColliding(Canvas box) {
    return collisionChecker.isColliding(canvas, box, player.getPosition().getX(), player.getPosition().getY());
  }


  public void setRooms(NPC topL2, NPC botL2, NPC topR2, NPC botR2, Stage stage) {
    this.topL = topL2;
    this.topR = topR2;
    this.botR = botR2;
    this.botL = botL2;
    this.roomManager = new RoomManager(stage);
    this.roomTransitioner = new RoomTransitioner(topL, topR, botL, botR, stage);
  }


  public void stopMovement() {
    playerInput.stopMovement();
  }


  public void start() {
    timer.start();
  }


  public void stop() {
    timer.stop();
  }


  public Player getPlayer() {
    return player;
  }


    public void setCoordinates(double x, double y) {
    player.setCoordinates(x, y);
  }


  public void stayInBoundaries(Scene scene) {
    player.stayInBoundaries(scene);
  }
}