package app;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class User extends Person{
    
    private double playerPosX = 250.0, playerPosY = 250.0;
    private final double SPEED = 5;
    private boolean wPressed, aPressed, sPressed, dPressed;

    private Rectangle sprite;
    private AnimationTimer timer;

    public User()
    {
        sprite = new Rectangle(150, 100, Color.BLACK);
    }

    public Rectangle getInAddAllForm() {
        return sprite;
    }

    public void connect(Scene scene)
    {
        AnchorPane.setLeftAnchor(sprite, playerPosX);
        AnchorPane.setTopAnchor(sprite, playerPosY);

        scene.setOnKeyPressed(e->
        {
            switch (e.getCode())
            {
                case W -> wPressed = true;
                case A -> aPressed = true;
                case S -> sPressed = true;
                case D -> dPressed = true;
            }
        });

        scene.setOnKeyReleased(e->
        {
            switch (e.getCode())
            {
                case W -> wPressed = false;
                case A -> aPressed = false;
                case S -> sPressed = false;
                case D -> dPressed = false;
            }
        });

        timer = new AnimationTimer() {
            public void handle(long now)
            {
                double dx = 0;
                double dy = 0;

                if (wPressed)
                {
                    dy -=1;
                }
                if (sPressed)
                {
                    dy +=1;
                }
                if (aPressed)
                {
                    dx -=1;
                }
                if (dPressed)
                {
                    dx +=1;
                }

                playerPosX += dx * SPEED;
                playerPosY += dy * SPEED;

                AnchorPane.setLeftAnchor(sprite, playerPosX);
                AnchorPane.setTopAnchor(sprite, playerPosY);
            }
        };
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
