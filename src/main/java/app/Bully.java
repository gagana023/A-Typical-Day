package app;

import javafx.scene.Scene;

public class Bully extends NPC {

  private double speed = 1.3;

  public Bully() {
    super("/student11.png", 50, 85);
  }

  public void spawnRandomly(Scene scene) {
    double width = scene.getWidth();
    double height = scene.getHeight();

    int side = (int) (Math.random() * 4);

    if (side == 0) {
      // top
      setLayoutX(Math.random() * (width - getWidth()));
      setLayoutY(0);
    } else if (side == 1) {
      // bottom
      setLayoutX(Math.random() * (width - getWidth()));
      setLayoutY(height - getHeight());
    } else if (side == 2) {
      // left
      setLayoutX(0);
      setLayoutY(Math.random() * (height - getHeight()));
    } else {
      // right
      setLayoutX(width - getWidth());
      setLayoutY(Math.random() * (height - getHeight()));
    }
  }

  public void moveTowardPlayer(Scene scene, Player player) {
    double playerX = player.getPosition().getX();
    double playerY = player.getPosition().getY();

    double bullyX = getLayoutX();
    double bullyY = getLayoutY();

    double dx = playerX - bullyX;
    double dy = playerY - bullyY;

    double distance = Math.sqrt(dx * dx + dy * dy);

    if (distance != 0) {
      setLayoutX(bullyX + (dx / distance) * speed);
      setLayoutY(bullyY + (dy / distance) * speed);
    }

    stayInBounds(scene);
  }

  private void stayInBounds(Scene scene) {
    double width = scene.getWidth();
    double height = scene.getHeight();

    if (getLayoutX() < 0) {
      setLayoutX(0);
    }

    if (getLayoutY() < 0) {
      setLayoutY(0);
    }

    if (getLayoutX() > width - getWidth()) {
      setLayoutX(width - getWidth());
    }

    if (getLayoutY() > height - getHeight()) {
      setLayoutY(height - getHeight());
    }
  }

  public void stop() {
    // No dx/dy anymore, so this can stay empty.
  }

  public void resume() {
    // No dx/dy anymore, so this can stay empty.
  }
}
