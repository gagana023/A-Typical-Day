package app;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class NPC extends Canvas {
    
    private Sprite sprite;

    public NPC(String spritePath)
    {
        sprite = new Sprite(spritePath, 1,1,1);
        
        sprite.setFrameSize(100, 160);

        setWidth(sprite.getFrameWidth());
        setHeight(sprite.getFrameHeight());

        sprite.setPosition(0, 0);
        GraphicsContext gc = getGraphicsContext2D();
        sprite.renderCurrent(gc);
    }
 
    public void setPosition(double x, double y)
    {
        setLayoutX(x);
        setLayoutY(y);
    }

}
