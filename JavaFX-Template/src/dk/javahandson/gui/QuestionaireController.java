package dk.javahandson.gui;

import dk.javahandson.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.control.Label;


public class QuestionaireController
{

    private MainController mainController;
    private int score = 0;

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblScore;
    private String userName;

    public void setUserName(String userName)
    {
        lblUsername.setText(userName);
//        this.userName = userName;
//        lblUsername.setText(userName);
    }
    public void setParentController(MainController controller)
    {
        mainController = controller;
    }



    @FXML
    private ToggleGroup gr1,gr2,gr3,gr4,gr5,gr6,gr7,gr8,gr9;
    @FXML
    private void goBack(javafx.event.ActionEvent actionEvent)
    {
        // Need to implement the go back button here.
    }
    @FXML
    public int calculateScore(ActionEvent actionEvent)
    {
        ToggleGroup[] questions = {gr1,gr2,gr3,gr4,gr5,gr6,gr7,gr8,gr9};

            for (ToggleGroup question : questions)
            {
                RadioButton selectedRadioButton = (RadioButton) question.getSelectedToggle();
                if(selectedRadioButton != null) {
                    String ToggleGroupValue = selectedRadioButton.getText();
                    if (ToggleGroupValue.equals("Disagree"))
                        score--;
                    else if (ToggleGroupValue.equals("Agree"))
                        score++;
                }

            }
        mainController.setScore(score);
        lblScore.setText("Score: " + score);
        return score;
    }


}