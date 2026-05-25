package app;

import javafx.scene.Scene;

public class Bully extends NPC{
    
    private double dx;
    private double dy;
    private double rememberX;
    private double rememberY;

    public Bully()
    {
      super("/student1.png");
      dx = Math.random() * 6 - 3;
      dy = Math.random() * 6 - 3;

      rememberX = dx;
      rememberY = dy;
    }

    public void move(Scene scene)
    {
      setLayoutX(getLayoutX() + dx);
      setLayoutY(getLayoutY() + dy);

      double width = scene.getWidth();
      double height = scene.getHeight();

      if (getLayoutX() <= 0 || getLayoutX() >= width - getWidth()) 
      {
            dx *= -1;
      }

      if (getLayoutY() <= 0 || getLayoutY() >= height - getHeight()) 
      {
            dy *= -1;
      }
    }

    public void stop()
    {
      dx = 0;
      dy = 0;
    }

    public void resume()
    {
      dx = rememberX;
      dy = rememberY;
    }
}
