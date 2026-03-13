package app;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class Library extends JoinGroupHere{
   public Scene buildPrototypeLibrary() {
       Line l1 = new Line(0, 100, 300, 150);
       Line l2 = new Line(800, 100, 300, 150);
       Line l3 = new Line(0, 500, 300, 450);
       Line l4 = new Line(800, 500, 300, 450);
       Line l5 = new Line(300, 150, 300, 450);
       Ellipse table = new Ellipse(250, 450, 100, 50);
       Rectangle tableLegs = new Rectangle(225, 450, 50, 75);
       Group group = new Group(l1, l2, l3, l4, l5, table, tableLegs);
       Scene scene = new Scene(group, 800, 600);
       return scene;
   }
}
