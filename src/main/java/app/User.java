package app;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class User extends Person{
    
    private double playerPosX = 250.0, playerPosY = 250.0;
    private final double SPEED = 5;
    private boolean wPressed, aPressed, sPressed, dPressed;
    private Rectangle topL, botL, topR, botR;
    private Stage stage;

    private Rectangle sprite;
    private AnimationTimer timer;
    private boolean canEnter = true;

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
                double minX = 0;
                double minY = 0;
                double maxX = scene.getWidth() - sprite.getWidth();
                double maxY = scene.getHeight() - sprite.getHeight();

                double newX = sprite.getLayoutX() + dx;
                double newY = sprite.getLayoutY() + dy;
                playerPosX = Math.max(minX, Math.min(playerPosX, maxX));
                playerPosY = Math.max(minY, Math.min(playerPosY, maxY));

                if(canEnter && isColliding(topL)){
                    canEnter = false;
                    enterOffice();
                }
                else if(canEnter && isColliding(topR)){
                    canEnter = false;
                    enterLibrary();
                }
                else if(canEnter && isColliding(botL)){
                    canEnter = false;
                    enterClassroom();
                }
                else if(canEnter && isColliding(botR)){
                    canEnter = false;
                    enterCafeteria();
                }
                AnchorPane.setLeftAnchor(sprite, playerPosX);
                AnchorPane.setTopAnchor(sprite, playerPosY);
            }
        };
    }
    public boolean isColliding(Rectangle box){
        double playerX = playerPosX;
        double playerY = playerPosY;
        double playerW = sprite.getWidth();
        double playerH = sprite.getHeight();

        Double boxXObj = AnchorPane.getLeftAnchor(box);
        Double boxYObj = AnchorPane.getTopAnchor(box);

        double boxX = (boxXObj == null) ? 0 : boxXObj;
        double boxY = (boxYObj == null) ? 0 : boxYObj;

        double boxW = box.getWidth();
        double boxH = box.getHeight();

        System.out.println("topR: " + AnchorPane.getLeftAnchor(topR) + ", " + AnchorPane.getTopAnchor(topR));
        return (playerX < boxX + boxW &&
                playerX + playerW > boxX &&
                playerY < boxY + boxH &&
                playerY + playerH > boxY);
    }

    public void setRooms(Rectangle topL, Rectangle botL, Rectangle topR, Rectangle botR, Stage stage){
        this.topL = topL;
        this.topR = topR;
        this.botR = botR;
        this.botL = botL;
        this.stage = stage;
    }

    private void enterOffice(){
        stop();
        Office office = new Office();
        Scene officeScene = new Scene(office.getRoot(stage), 800,600);
        stage.setScene(officeScene);
    }

    private void enterClassroom(){
        stop();
        Classroom classroom = new Classroom();
        Scene classroomScene = new Scene(classroom.getRoot(stage), 800,600);
        stage.setScene(classroomScene);


    }

    private void enterLibrary(){
        stop();
        Library library = new Library();
        stage.setScene(library.buildPrototypeLibrary(stage));

    }

    private void enterCafeteria(){
        stop();
        Cafeteria cafeteria = new Cafeteria();
        stage.setScene(cafeteria.buildPrototypeCafeteria(stage));
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
