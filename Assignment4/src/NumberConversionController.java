import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class NumberConversionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtNumberInput;
    @FXML
    private Label lblResult;


    private NumberConversionModel ncModel = new NumberConversionModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Text Input Dialog");
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            NumberConversionModel ncmodel;
            String message = ncModel.getGreetingsMessage(result.get());
            label.setText(message);
        }
    }


    @FXML
    private void onClick(ActionEvent actionEvent)
   {
       double txtFieldValue = Double.parseDouble(txtNumberInput.getText());
       double result = ncModel.getMilesFromKilometers(txtFieldValue);
       String resultAsString = String.valueOf(result);
       lblResult.setText(resultAsString);
   }


}
