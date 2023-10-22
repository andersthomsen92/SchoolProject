package Questions.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class QuestionController {
    public void setParentController(MainController controller) {
        mainController = controller;
    }

    private MainController mainController;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblUsername;
    private String userName;

    public void setUsername(String userName) {
        lblUsername.setText(userName);
    }

    @FXML
    private ToggleGroup gr1, gr2, gr3, gr4, gr5, gr6, gr7, gr8, gr9;

    @FXML
    public void calculateScore() {
        ToggleGroup[] questions = {gr1, gr2, gr3, gr4, gr5, gr6, gr7, gr8, gr9};
        int score = 0;

        for (ToggleGroup question : questions) {
            RadioButton selectedRadioButton = (RadioButton) question.getSelectedToggle();
            if (selectedRadioButton != null) {
                String toggleGroupValue = selectedRadioButton.getText();
                if (toggleGroupValue.equals("Disagree"))
                    score--;
                else if (toggleGroupValue.equals("Agree"))
                    score++;
            }
        }
        lblScore.setText("Score: " + score);
    }

  /*  @FXML
    public void sendScore() {
        ToggleGroup[] questions = {gr1, gr2, gr3, gr4, gr5, gr6, gr7, gr8, gr9};
        int score = 0;

        for (ToggleGroup question : questions) {
            RadioButton selectedRadioButton = (RadioButton) question.getSelectedToggle();
            if (selectedRadioButton != null) {
                String toggleGroupValue = selectedRadioButton.getText();
                if (toggleGroupValue.equals("Disagree"))
                    score--;
                else if (toggleGroupValue.equals("Agree"))
                    score++;
            }
        }

        String userName = lblUsername.getText();
        MainController.Participant participant = new MainController.Participant(userName, score);
        mainController.addParticipant(participant);

        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.close();
    }*/


   /* public void sendScore() {
        ToggleGroup[] questions = {gr1, gr2, gr3, gr4, gr5, gr6, gr7, gr8, gr9};
        int score = 0;

        for (ToggleGroup question : questions) {
            RadioButton selectedRadioButton = (RadioButton) question.getSelectedToggle();
            if (selectedRadioButton != null) {
                String toggleGroupValue = selectedRadioButton.getText();
                if (toggleGroupValue.equals("Disagree"))
                    score--;
                else if (toggleGroupValue.equals("Agree"))
                    score++;
            }
        }

        String userName = lblUsername.getText();
        MainController.Participant participant = new MainController.Participant(userName, score);
        mainController.addParticipant(participant);

        mainController.updateParticipantsList(); // Update the ListView

        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.close();
    }*/

}