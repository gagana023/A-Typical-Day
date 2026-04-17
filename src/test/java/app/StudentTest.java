package app;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;

/** TODO: Replace the test code in this file with your own tests 
 * 
 * For the final project
 * 
*/

public class StudentTest extends ApplicationTest {

    private HelloWorld game;

    // @Override
    public void start(Stage stage) throws Exception {
        // Initialize and start the game instance
        game = new HelloWorld();
        game.start(stage);
    }

    @Test
    public void testCanvasIsVisible() {
        // 1. Verify the canvas exists by its FX ID ("gameCanvas")
        // 2. Verify that it is actually visible to the user
        // verifyThat("#gameCanvas", isNotNull());
        verifyThat("#gameCanvas", isVisible());
    }

}