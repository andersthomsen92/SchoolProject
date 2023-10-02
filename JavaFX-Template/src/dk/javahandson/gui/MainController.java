package dk.javahandson.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    private int score = 0;

    @FXML
    private Label lblName,lblScore, lblText;
    @FXML
    private TextField textUsername;
    private String userName;
    @FXML
    private Label LblScore;

    @FXML
    private void clickBtn(ActionEvent actionEvent) throws IOException {
    // This open the next window(The Questionaire window)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuestionaireWindow.fxml")); // Creating an instance of the FXML Loader, that can load FXML Files.
        Parent scene = loader.load();  // Loads the FXML content into a parent object.
        Stage stage = new Stage();  // Creates a new stage object. this is used to display the content loaded from the FXML file in a new window.

        QuestionaireController controller = loader.getController();
        controller.setParentController(this);

        stage.setTitle("Fill Questionaire");
        stage.setScene(new Scene(scene));
        stage.show();

        String userName = textUsername.getText();
        controller.setUserName(userName);

    }
    public void setScore(int score)
    {
        this.score = score;
        lblScore.setText(Integer.toString(score));
    }

}
