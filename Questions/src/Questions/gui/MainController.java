package Questions.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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
    /*@FXML
    private ListView<String> listParticipants;

    private ObservableList<Participant> participantsList = FXCollections.observableArrayList();

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

    public void updateParticipantsList() {
        listParticipants.getItems().clear();

        for (Participant participant : participantsList) {
            String participantData = participant.getName() + " - Score: " + participant.getScore();
            listParticipants.getItems().add(participantData);
        }
    }*/



}

