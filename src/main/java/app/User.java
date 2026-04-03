package app;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class User extends Person{
    
    private double playerPosX = 350.0, playerPosY = 300.0;
    private final double SPEED = 5;
    private boolean wPressed, aPressed, sPressed, dPressed;
    private Canvas topL, botL, topR, botR;
    private Stage stage;

    private Sprite sprite;
    private Canvas canvas;
    private AnimationTimer timer;
    private boolean canEnter = true;
    private boolean enteringOffice = false;
    private boolean enteringCafeteria = false;
    private boolean enteringClassroom = false;
    private boolean enteringLibrary = false;
    private final int FRAME_DELAY = 8;
    private int frameTick = 0;
    private double health = 1.0;
    private double social = 1.0;

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
        enteringCafeteria = false;
        enteringClassroom = false;
        enteringLibrary = false;
        enteringOffice = false;
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
                    stayInBoundaries(scene);

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

    

    public void changeStats(int optionType) {
        System.out.println("Health: " + health + " Social: " + social);
        switch (optionType) {
            
            case 1:
                social += 0.1;
                health -= 0.05;
                break;
            case 2:
                social -= 0.05;
                health -= 0.05;
                break;
            case 3:
                social -= 0.1;
                health -= 0.1;
                break;
        }

        // clamp values between 0 and 1
        health = Math.max(0, Math.min(1, health));
        social = Math.max(0, Math.min(1, social));
    }

    public double getHealth() {
        return health;
    }

    public double getSocial() {
        return social;
    }


    public boolean isColliding(Canvas box){
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

    public void setRooms(NPC topL2, NPC botL2, NPC topR2, NPC botR2, Stage stage){
        this.topL = topL2;
        this.topR = topR2;
        this.botR = botR2;
        this.botL = botL2;
        this.stage = stage;
    }

    public void stopMovement(){
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
    }

    private void enterOffice(){
        if (enteringOffice) return;   
        enteringOffice = true;    
        stop();
        Office office = new Office();
        Scene officeScene = new Scene(office.getRoot(stage), 800,600);
        stage.setScene(officeScene);
    }

    private void enterClassroom(){
        if (enteringClassroom) return;
        enteringClassroom = true;
        stop();
        Classroom classroom = new Classroom();
        Scene classroomScene = new Scene(classroom.getRoot(stage), 800,600);
        stage.setScene(classroomScene);


    }

    private void enterLibrary(){
        if (enteringLibrary) return;
        enteringLibrary = true;
        stop();
        Library library = new Library();
        stage.setScene(library.buildPrototypeLibrary(stage));

    }

    private void enterCafeteria(){
        if (enteringCafeteria) return;
        enteringCafeteria = true;
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

    public void setCoordinates(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setX(double x) { playerPosX = x; }

    public void setY(double y) { playerPosY = y; }

    public void stayInBoundaries(Scene scene) {
        double maxX = scene.getWidth() - canvas.getWidth();
        double maxY = scene.getHeight() - canvas.getHeight();
        playerPosX = Math.max(0, Math.min(playerPosX, maxX));
        playerPosY = Math.max(0, Math.min(playerPosY, maxY));
    }
}
