package app;


import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * A simple help/instructions pane that shows scrollable text
 * for the animation project. The text is read-only at runtime,
 * but students can update the default content in code as the
 * project evolves.
 *
 * This pane is not animated. It is intended to be used as
 * a "Help" tab alongside the animated panes.
 */
public class Help extends MenuScreen {


    private final TextArea helpArea;


    /**
     * Constructs a HelpPane with default project directions.
     * Students can update the DEFAULT_TEXT constant or use
     * setHelpText(...) in code to customize instructions.
     */
    public Help() {
        this.helpArea = new TextArea();


        helpArea.setEditable(false);
        helpArea.setWrapText(true);
        helpArea.setStyle("-fx-font-family: 'Consolas', 'Monospaced'; -fx-font-size: 12;");


        helpArea.setText(DEFAULT_TEXT.trim());


        ScrollPane scrollPane = new ScrollPane(helpArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        this.setFillWidth(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        helpArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        // setPadding(new Insets(10));
        getChildren().add(scrollPane);
    }


    /**
     * Replace the entire help text with new content.
     *
     * @param text the text to display in the help pane
     */
    public void setHelpText(String text) {
        helpArea.setText(text == null ? "" : text);
        helpArea.positionCaret(0); // scroll to top
    }


    /**
     * Append additional text to the end of the help area.
     *
     * @param text text to append (will start on a new line)
     */
    public void appendHelpText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        if (!helpArea.getText().isEmpty()) {
            helpArea.appendText("\n");
        }
        helpArea.appendText(text);
    }


    /**
     * Default instructions text. Students can update this
     * as we add requirements for each pane.
     */
    private static final String DEFAULT_TEXT = """
            JavaFX Animation Project - Help


            This tab is for project directions and notes.


            BallPane
            --------


            My ball pane added the following two requirements:
                1. Bounces off the walls
                2. Changes color and size when bounce
                3. New Balls are added when you click


            Fill Pane:
                1. When Clicked, shape fills in
                2. Background Fills diagonally


            Sprite Pane:
                1. Sprite moves (blooming flower)
                2. Moves from left to right side from side to side.


            SparkPane
                1. When you click on the screen, a spark appears. This spark is multiple different colors.
                2. Spark Lasts for few seconds.


            Custom Pane
                1. When you click on the screen, the water bucket tips to water flower
                2. When the flower blooms, rain falls
                3. When flower blooms, number of flowers bloomed increases


                Required features: Text Animation, 2 sprites that do different things from sprite pane,
                    balls that do different things from ball pane. Random Colored balls vs blue rain represented by balls


                    User interaction: When click mouse, flower is watered


                    Attributes: Sprite Sheet Animation and Movement and Physics for Rain Balls
            """
            ;


        public Scene getHelp(Stage stage){
            return new Scene(this, 800, 600);
        }

}



