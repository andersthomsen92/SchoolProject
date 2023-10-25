package dk.easv.gui;

import dk.easv.be.History;
import dk.easv.bll.CalculatorLogic;
import dk.easv.bll.HistoryLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainController {

    @FXML
    private TextField txtValue;

    @FXML
    private TextField txtVat;

    @FXML
    private Label lblResult;

    private final CalculatorLogic logic = new CalculatorLogic();
    private final HistoryLogic historyLogic = new HistoryLogic();

    public void btnOnCalculate(ActionEvent actionEvent) {
        /*In this section we convert the values from the two text fields to int. Just so it is ready for our logical layer */
        double value = Double.parseDouble(txtValue.getText());
        double vat = Double.parseDouble(txtVat.getText());

        /* We call the logical layer, for it to do the calculation for us.  */
        double result = logic.CalculateNewValueAddedPercentage(value, vat);

        /* The calculation from our logic layer is presented in the GUI. */
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        lblResult.setText(df.format(result));

        /* Informs our logical layer about the new history to be added */
        historyLogic.AddHistory(value, vat, result);
    }

    public void onClickRestore(ActionEvent actionEvent)
    {
        if(historyLogic.HasAny())
        {
            /* Gets the latest history  */
            History latestHistory = historyLogic.GetLatestHistory();

            /* Prepares a decimal formatter, so our numbers is presented as expected in the GUI. */
            DecimalFormat df = new DecimalFormat("#");
            DecimalFormat df1 = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.FLOOR);

            /* Fill up the TextFields and Label with the information from the last calculation */
            txtValue.setText(df.format(latestHistory.GetValue()));
            txtVat.setText(df.format(latestHistory.GetVat()));
            lblResult.setText(df1.format(latestHistory.GetResult()));
        }
    }
}
