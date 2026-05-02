package app;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class User {

  private RoomManager roomManager;
  private PlayerPosition position = new PlayerPosition(350.0, 300.0);
  private final double SPEED = 5;
  private PlayerInput playerInput = new PlayerInput();
  private Canvas topL, botL, topR, botR;

  private Canvas canvas;
  private AnimationTimer timer;
  private boolean canEnter = true;

  private PlayerStats stats = new PlayerStats();
  private CollisionChecker collisionChecker = new CollisionChecker();
  private PlayerAnimation playerAnimation;

  public User() {
    playerAnimation = new PlayerAnimation("/blinkdrop.png", 3, 3, 9);
    // sprite = new Sprite("/blinkdrop.png", 3, 3, 9);
    canvas = playerAnimation.getCanvas();
    canvas.setId("gameCanvas");

    // renderFrame();
  }

  public Canvas getInAddAllForm() {
    return canvas;
  }

  public void resume(Scene scene) {
    stopMovement();
    position.setCoordinates(350, 250);
    updateCanvasPosition();
    start();
    canEnter = true;
    roomManager.resetEntries();
    scene.getRoot().requestFocus();
  }

  public void connect(Scene scene) {
    updateCanvasPosition();
    playerInput.connect(scene);

    timer =
        new AnimationTimer() {
          public void handle(long now) {
            boolean moving = updateMovement(scene);
            playerAnimation.updateAnimation(moving);
            playerAnimation.renderFrame();
            checkRoomEntries();
            updateCanvasPosition();
          }
        };
  }

  public void changeStats(int optionType) {
    System.out.println("Stats Changed");
    stats.changeStats(optionType);
  }

  public double getHealth() {
    return stats.getHealth();
  }

  private boolean updateMovement(Scene scene) {
    double dx = playerInput.getHorizontalMovement();
    double dy = playerInput.getVerticalMovement();

    boolean moving = dx != 0 || dy != 0;

    if (moving) {
      position.move(dx, dy, SPEED);
      position.stayInBoundaries(scene, canvas.getWidth(), canvas.getHeight());
    }

    return moving;
  }

  private void checkRoomEntries() {
    if (!canEnter) {
      return;
    }

    if (isColliding(topL)) {
      enterRoom("office");
    } else if (isColliding(topR)) {
      enterRoom("library");
    } else if (isColliding(botL)) {
      enterRoom("classroom");
    } else if (isColliding(botR)) {
      enterRoom("cafeteria");
    }
  }

  private void enterRoom(String roomName) {
    canEnter = false;

    switch (roomName) {
      case "office" -> roomManager.enterOffice(this);
      case "library" -> roomManager.enterLibrary(this);
      case "classroom" -> roomManager.enterClassroom(this);
      case "cafeteria" -> roomManager.enterCafeteria(this);
      default -> {}
    }
  }

  private void updateCanvasPosition() {
    AnchorPane.setLeftAnchor(canvas, position.getX());
    AnchorPane.setTopAnchor(canvas, position.getY());
  }

  public double getSocial() {
    return stats.getSocial();
  }

  public boolean isColliding(Canvas box) {
    return collisionChecker.isColliding(canvas, box, position.getX(), position.getY());
  }

  public void setRooms(NPC topL2, NPC botL2, NPC topR2, NPC botR2, Stage stage) {
    this.topL = topL2;
    this.topR = topR2;
    this.botR = botR2;
    this.botL = botL2;
    this.roomManager = new RoomManager(stage);
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

  public void setCoordinates(double x, double y) {
    position.setCoordinates(x, y);
  }

  public void setX(double x) {
    position.setX(x);
  }

  public void setY(double y) {
    position.setY(y);
  }

  public void stayInBoundaries(Scene scene) {
    position.stayInBoundaries(scene, canvas.getWidth(), canvas.getHeight());
  }
}
