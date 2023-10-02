package Questions.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TextField txtUsername;

    @FXML
    private void clickBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuestionWindow.fxml"));
        Parent scene = loader.load();
        Stage stage = new Stage();

        QuestionController controller = loader.getController();
        controller.setParentController(this);
        stage.setTitle("Questions");
        stage.setScene(new Scene(scene));
        stage.show();

        String userName = txtUsername.getText();
        controller.setUsername(userName);
    }

    private String name;
    private int score;

    public static class Participant {
        private String name;
        private int score;

        public Participant(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }

    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}