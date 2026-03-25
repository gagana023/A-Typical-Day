
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
    void collideChecks() {
        User u = new User();
        Rectangle topL = new Rectangle(100, 100 + 30, Color.BURLYWOOD);
        Rectangle topR = new Rectangle(100, 100 + 30, Color.BURLYWOOD);
        Rectangle botL = new Rectangle(100, 100 + 30, Color.BURLYWOOD);
        Rectangle botR = new Rectangle(100, 100 + 30, Color.BURLYWOOD);
        AnchorPane.setTopAnchor(topL, 100.0);
        AnchorPane.setLeftAnchor(topL, 100.0);

        AnchorPane.setLeftAnchor(topR, 550.0);
        AnchorPane.setTopAnchor(topR, 100.0);

        AnchorPane.setLeftAnchor(botL, 100.0);
        AnchorPane.setTopAnchor(botL, 400.0);

        AnchorPane.setLeftAnchor(botR, 550.0);
        AnchorPane.setTopAnchor(botR, 400.0);
        u.setCoordinates(100, 100);
        assertTrue(u.isColliding(topL));
        u.setCoordinates(550, 100);
        assertTrue(u.isColliding(topR));
        u.setCoordinates(100, 400);
        assertTrue(u.isColliding(botL));
        u.setCoordinates(550, 400);
        assertTrue(u.isColliding(botR));
        
    }

}
