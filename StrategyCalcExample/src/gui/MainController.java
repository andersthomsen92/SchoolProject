package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDivide;
    @FXML
    private Button btnEight;
    @FXML
    private Button btnFive;
    @FXML
    private Button btnFour;
    @FXML
    private Button btnNine;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnSeven;
    @FXML
    private Button btnSix;
    @FXML
    private Button btnThree;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnZero;
    @FXML
    private Label lblResult;
    @FXML
    private TextField txtInput;
    @FXML
    private List<Button> numberButtons;


    @FXML
    private void handleNumberButtonClicked(ActionEvent actionEvent){
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonText = clickedButton.getText();
        txtInput.appendText(buttonText);
    }

    @FXML
    public void initialize() {
        numberButtons = new ArrayList<>();

        // Define the names of buttons that are considered number buttons
        String[] numberButtonNames = {"btnZero", "btnOne", "btnTwo", "btnThree", "btnFour", "btnFive", "btnSix", "btnSeven", "btnEight", "btnNine"};

        // Loop through the button names and add them to the numberButtons list
        for (String buttonName : numberButtonNames) {
            try {
                // Use the button name to retrieve the button from the controller
                Button button = (Button) getClass().getDeclaredField(buttonName).get(this);
                numberButtons.add(button);
                button.setOnAction(this::handleNumberButtonClicked);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exceptions (e.g., log an error, show a message)
                e.printStackTrace();
            }
        }
    }

}
