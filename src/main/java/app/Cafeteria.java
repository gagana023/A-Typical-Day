package app;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Cafeteria extends JoinGroupHere{
   public Scene buildPrototypeCafeteria(Stage stage) {
       stage.setTitle("Cafeteria");
       Line l1 = new Line(0, 0, 150, 50);
       Line l2 = new Line(800, 0, 650, 50);
       Line l3 = new Line(0, 450, 150, 400);
       Line l4 = new Line(800, 450, 650, 400);
       Line l5 = new Line(150, 50, 650, 50);
       Line l6 = new Line(150, 50, 150, 400);
       Line l7 = new Line(650, 50, 650, 400);
       Line l8 = new Line(650, 400, 150, 400);
       Rectangle table1 = new Rectangle(25, 450, 200, 50);
       Rectangle tableLegs1 = new Rectangle(115, 450, 20, 75);
       Rectangle table2 = new Rectangle(375, 400, 200, 50);
       Rectangle tableLegs2 = new Rectangle(465, 400, 20, 75);
       Button back = new Button("Back");
       back.setLayoutX(20);
       back.setLayoutY(20);

        back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
        });
       Group group = new Group(l1, l2, l3, l4, l5, l6, l7, l8, table1, tableLegs1, table2, tableLegs2, back);
       Scene scene = new Scene(group, 800, 600);



       return scene;
   }


  
}
