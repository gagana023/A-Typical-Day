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

public class User extends Person{
    
    private double playerPosX = 250.0, playerPosY = 250.0;
    private final double SPEED = 5;
    private boolean wPressed, aPressed, sPressed, dPressed;
    private Rectangle topL, botL, topR, botR;
    private Stage stage;

    private Sprite sprite;
    private Canvas canvas;
    private AnimationTimer timer;
    private boolean canEnter = true;
    private final int FRAME_DELAY = 8;
    private int frameTick = 0;

    public User()
    {
        sprite = new Sprite("/blinkdrop.png", 3,3,9);
        canvas = new Canvas(sprite.getFrameWidth(), sprite.getFrameHeight());
        renderFrame();
    }

    private void renderFrame()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        sprite.setPosition(0, 0);
        sprite.renderCurrent(gc);
    }

    public Canvas getInAddAllForm() {
        return canvas;
    }

    public void resume(Scene scene){
        stopMovement();
        playerPosX = 350;
        playerPosY = 250;
        AnchorPane.setLeftAnchor(canvas, playerPosX);
        AnchorPane.setTopAnchor(canvas, playerPosY);
        start();
        canEnter = true;
        scene.getRoot().requestFocus();
    }

    public void connect(Scene scene)
    {
        AnchorPane.setLeftAnchor(canvas, playerPosX);
        AnchorPane.setTopAnchor(canvas, playerPosY);

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

                boolean moving = dx != 0 || dy != 0;
                if (moving)
                {
                    playerPosX += dx * SPEED;
                    playerPosY += dy * SPEED;
                    double maxX = scene.getWidth() - canvas.getWidth();
                    double maxY = scene.getHeight() - canvas.getHeight();
                    playerPosX = Math.max(0, Math.min(playerPosX, maxX));
                    playerPosY = Math.max(0, Math.min(playerPosY, maxY));

                    frameTick++;
                    if (frameTick >= FRAME_DELAY)
                    {
                        sprite.nextFrame();
                        frameTick = 0;
                    }
                }
                else
                {
                    frameTick = 0;
                }
                renderFrame();

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
                AnchorPane.setLeftAnchor(canvas, playerPosX);
                AnchorPane.setTopAnchor(canvas, playerPosY);
            }
        };
    }
    public boolean isColliding(Rectangle box){
        double playerX = playerPosX;
        double playerY = playerPosY;
        double playerW = canvas.getWidth();
        double playerH = canvas.getHeight();

        Double boxXObj = AnchorPane.getLeftAnchor(box);
        Double boxYObj = AnchorPane.getTopAnchor(box);

        double boxX = (boxXObj == null) ? 0 : boxXObj;
        double boxY = (boxYObj == null) ? 0 : boxYObj;

        double boxW = box.getWidth();
        double boxH = box.getHeight();

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

    public void stopMovement(){
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
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
