package app;


import javafx.scene.Scene;


public class MovementController {


    private final double SPEED = 5;
    private PlayerInput input;


    public MovementController(PlayerInput input) {
        this.input = input;
    }


    public boolean update(Player player, Scene scene) {
        double dx = input.getHorizontalMovement();
        double dy = input.getVerticalMovement();


        boolean moving = dx != 0 || dy != 0;


        if (moving) {
            player.getPosition().move(dx, dy, SPEED);
            player.getPosition().stayInBoundaries(
                scene,
                player.getCanvas().getWidth(),
                player.getCanvas().getHeight()
            );
        }


        return moving;
    }


    public void stop() {
        input.stopMovement();
    }
}