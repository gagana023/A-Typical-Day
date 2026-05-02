package app;


import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class RoomTransitioner {
    private Canvas topL, topR, botL, botR;
    private RoomManager roomManager;
    private boolean canEnter = true;
    private CollisionChecker collisionChecker = new CollisionChecker();


    public RoomTransitioner(Canvas topL, Canvas topR, Canvas botL, Canvas botR, Stage stage) {
      this.topL = topL;
      this.topR = topR;
      this.botL = botL;
      this.botR = botR;
      this.roomManager = new RoomManager(stage);
    }


    public void check(User user)
    {
      if (!canEnter) return;


      if (isColliding(user, topL)) {
        enter("office");
      } else if (isColliding(user, topR)) {
        enter("library");
      } else if (isColliding(user, botL)) {
        enter("classroom");
      } else if (isColliding(user, botR)) {
        enter("cafeteria");
      }
    }


    private boolean isColliding(User user, Canvas box)
    {
      return collisionChecker.isColliding(user.getPlayer().getCanvas(),box, user.getPlayer().getPosition().getX(),user.getPlayer().getPosition().getY());
    }


    private void enter(String roomName)
    {
      canEnter = false;


      switch (roomName) {
        case "office" -> roomManager.enterOffice();
        case "library" -> roomManager.enterLibrary();
        case "classroom" -> roomManager.enterClassroom();
        case "cafeteria" -> roomManager.enterCafeteria();
      }
    }


    public void reset() {
      canEnter = true;
    }


}