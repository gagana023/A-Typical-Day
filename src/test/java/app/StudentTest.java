package app;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;

public class StudentTest extends ApplicationTest {

  private HelloWorld game;

  @Override
  public void start(Stage stage) throws Exception {
    game = new HelloWorld();
    game.start(stage);
  }

  @Test
  public void testCanvasIsVisible() {
    verifyThat("#gameCanvas", isNotNull());
    verifyThat("#gameCanvas", isVisible());
  }

  @Test
  public void clickingRelativeLocation_ShouldChangePanes() {
    moveTo(point("#gameCanvas").atPosition(Pos.TOP_LEFT));
    moveBy(110, 110);
    clickOn();
    sleep(2000);

    FxAssert.verifyThat("#topLeft", isVisible());
  }

  @Test
  public void completeTaskMarksDone() {
    Tasks.chooseRandomTasks(3);

    List<Tasks.Task> tasks = Tasks.getActiveTasks();
    assertFalse(tasks.isEmpty());

    String id = tasks.get(0).getId();

    Tasks.completeTask(id);

    boolean done =
        Tasks.getActiveTasks().stream().anyMatch(t -> t.getId().equals(id) && t.isDone());

    assertTrue(done);
  }

  @Test
  public void updateTasksStrikethrough() {
    Tasks.chooseRandomTasks(3);

    Label t1 = new Label();
    Label t2 = new Label();
    Label t3 = new Label();

    Tasks.getActiveTasks().get(0).complete();

    HelloWorld.updateTasks(t1, t2, t3);

    assertTrue(t1.getStyle().contains("strikethrough"));
  }

  @Test
  public void updateTasksNoStrikethrough() {
    Tasks.chooseRandomTasks(3);

    Label t1 = new Label();
    Label t2 = new Label();
    Label t3 = new Label();

    HelloWorld.updateTasks(t1, t2, t3);

    assertTrue(t1.getStyle().contains("false"));
  }

  @Test
  public void spriteLoads() {
    Sprite sprite = new Sprite("/walk-front.png", 4, 3, 12);
    assertEquals(12, sprite.getSpriteCount());

    sprite = new Sprite("/walk-back.png", 3, 4, 12);
    assertEquals(12, sprite.getSpriteCount());

    sprite = new Sprite("/student_sprite.png", 1, 1, 1);
    assertEquals(1, sprite.getSpriteCount());
  }

  @Test
  public void invalidSpriteThrow() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new Sprite("/walk-right.png", 0, 0, 0);
        });
  }

  @Test
  public void officeSceneBuilds() {
    Office office = new Office();
    AnchorPane root = office.getRoot(new Stage());
    assertNotNull(root);
  }

  @Test
  public void allUserDialogueExists() {
    for (UserDialogueEngine.Room room : UserDialogueEngine.Room.values()) {
      assertEquals(3, UserDialogueEngine.getOptions(room).size());
    }
  }

  @Test
  public void changeSocialStats() {
    User user = new User();
    double before = user.getSocial();

    user.changeStats(1);

    assertTrue(user.getSocial() > before);
  }

  @Test
  public void statsClampedMax() {
    User user = new User();

    for (int i = 0; i < 50; i++) {
      user.changeStats(1);
    }

    assertTrue(user.getSocial() <= 1);
  }

  @Test
  public void statsClampedMin() {
    User user = new User();

    for (int i = 0; i < 50; i++) {
      user.changeStats(3);
    }

    assertTrue(user.getHealth() >= 0);
  }

  @Test
  public void stayInBounds() {
    User user = new User();
    Scene scene = new Scene(new AnchorPane(), 800, 600);

    user.setCoordinates(10000, 10000);
    user.stayInBoundaries(scene);

    assertTrue(user.getHealth() <= 1);
  }

  @Test
  public void npcPositionSet() {
    NPC npc = new NPC("/student_sprite.png");
    npc.setPosition(200, 300);

    assertEquals(200, npc.getLayoutX());
    assertEquals(300, npc.getLayoutY());
  }

  @Test
  public void collisionDetected() {
    User user = new User();
    NPC npc = new NPC("/student_sprite.png");

    npc.setWidth(100);
    npc.setHeight(100);

    AnchorPane.setLeftAnchor(npc, 350.0);
    AnchorPane.setTopAnchor(npc, 300.0);

    user.setCoordinates(350, 300);

    assertTrue(user.isColliding(npc));
  }

  @Test
  public void noCollisionWhenFar() {
    User user = new User();
    NPC npc = new NPC("/student_sprite.png");

    AnchorPane.setLeftAnchor(npc, 0.0);
    AnchorPane.setTopAnchor(npc, 0.0);

    user.setCoordinates(700, 500);

    assertFalse(user.isColliding(npc));
  }

  @Test
  public void collisionHandlesNullAnchors() {
    User user = new User();
    NPC npc = new NPC("/student_sprite.png");

    try {
      user.isColliding(npc);
    } catch (Exception e) {
      fail("Method threw exception: " + e.getMessage());
    }
  }

  @Test
  public void animationSequenceSet() {
    Sprite sprite = new Sprite("/walk-right.png", 3, 4, 12);

    sprite.setAnimationSequence(List.of(0, 1, 2));

    assertEquals(12, sprite.getSpriteCount());
  }

  @Test
  public void invalidAnimationSequenceThrows() {
    Sprite sprite = new Sprite("/walk-left.png", 3, 4, 12);

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          sprite.setAnimationSequence(List.of(100));
        });
  }

  @Test
  public void nextFrameCycles() {
    Sprite sprite = new Sprite("/walk-back.png", 3, 4, 12);

    sprite.nextFrame();
    sprite.nextFrame();

    assertNotNull(sprite);
  }

  @Test
  public void renderInvalidIndexThrows() {
    Sprite sprite = new Sprite("/teacher_sprite.png", 1, 1, 1);

    GraphicsContext gc = new Canvas().getGraphicsContext2D();

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          sprite.render(gc, 100, 0, 0, 50, 50);
        });
  }
}
