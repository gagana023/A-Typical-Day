package app;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;


public class Library extends JoinGroupHere{
   public Scene buildPrototypeLibrary(Stage stage) {
      stage.setTitle("Library");
      Line l1 = new Line(0, 100, 300, 150);
      Line l2 = new Line(800, 100, 300, 150);
      Line l3 = new Line(0, 500, 300, 450);
      Line l4 = new Line(800, 500, 300, 450);
      Line l5 = new Line(300, 150, 300, 450);
      Ellipse table = new Ellipse(250, 450, 100, 50);
      Rectangle tableLegs = new Rectangle(225, 450, 50, 75);
      Button back = new Button("Back");
      back.setLayoutX(20);
      back.setLayoutY(20);

      NPC npc = new NPC("/student_sprite.png");
      // Random rand = new Random();
      //   double minX = 100;
      //   double maxX = 700;
      //   double minY = 150;
      //   double maxY = 500;

      //   double randomX = minX + rand.nextDouble() * (maxX - minX);
      //   double randomY = minY + rand.nextDouble() * (maxY - minY);
      //   npc.setPosition(randomX,randomY);
      //   AnchorPane.setLeftAnchor(npc, randomX);
      //   AnchorPane.setTopAnchor(npc, randomY);

       npc.setPosition(320, 310);
        AnchorPane.setLeftAnchor(npc, 320.0);
        AnchorPane.setTopAnchor(npc, 310.0);


      back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
      });       
        
        
      Group group = new Group(l1, l2, l3, l4, l5, table, tableLegs, back, npc);
      Scene scene = new Scene(group, 800, 600);
      return scene;
   }
}
