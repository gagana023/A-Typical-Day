package app;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

public class StudentTest extends ApplicationTest {

  private HelloWorld game;

  @Override
  public void start(Stage stage) throws Exception {
    // Initialize and start the game instance
    game = new HelloWorld();
    game.start(stage);
  }

  @Test
  public void testCanvasIsVisible() {
    // 1. Verify the canvas exists by its FX ID ("gameCanvas")
    // 2. Verify that it is actually visible to the user
    verifyThat("#gameCanvas", isNotNull());
    verifyThat("#gameCanvas", isVisible());
  }

  @Test
  public void clickingRelativeLocation_ShouldChangePanes() {
    // 1. Move to the node, then move by a specific offset (x: 10, y: 10 from center)
    // By default, clickOn targets the center of the node.
    moveTo(
        point("#gameCanvas")
            .atPosition(Pos.TOP_LEFT)); // default is center, so we move to top-left first
    moveBy(110, 110);
    clickOn();
    sleep(10000);
    // 2. Verify the Alert window is actually visible
    // We look for the window by its title or just check if a window is focused
    //FxAssert.verifyThat(window("Message"), WindowMatchers.isShowing());
    FxAssert.verifyThat("#topLeft", isVisible());

    // 3. Close the dialog to clean up the test state
    //clickOn("OK");
  }

    @Test
public void testCollisionTopLeft() {
    User u = new User();

    Canvas box = new Canvas(100, 160);
    AnchorPane.setLeftAnchor(box, 100.0);
    AnchorPane.setTopAnchor(box, 100.0);

    u.setCoordinates(100, 100);

    assertTrue(u.isColliding(box));
}



}