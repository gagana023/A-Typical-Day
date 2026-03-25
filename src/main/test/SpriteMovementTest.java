
import app.HelloWorld;
import app.User;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteMovementTest {
    @Test
    // FIXME - stopped working after pulled changes
    void collideChecks() {
        User u = new User();
//        Canvas box = new Canvas
        u.setCoordinates(100, 100);
//        assertTrue(u.isColliding(topL));
        u.setCoordinates(550, 100);
//        assertTrue(u.isColliding(topR));
        u.setCoordinates(100, 400);
//        assertTrue(u.isColliding(botL));
        u.setCoordinates(550, 400);
//        assertTrue(u.isColliding(botR));
        
    }

}
