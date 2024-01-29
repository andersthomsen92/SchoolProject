package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;

public class ChartsController {

    @FXML
    private BorderPane borderPane;

    public void initialize(){
        borderPane.setCenter(buildBarChart());
    }


    @FXML
    void onShowBarChart(ActionEvent event) {
        borderPane.setCenter(buildBarChart());
    }

    private BarChart buildBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity Sold");

        BarChart barChart = new BarChart(xAxis,yAxis);

        XYChart.Series barChartData = new XYChart.Series();
        barChartData.setName("Products Sold");

        // Provide the data
        barChartData.getData().add(new XYChart.Data("Product A", 3000));
        barChartData.getData().add(new XYChart.Data("Product B", 2400));
        barChartData.getData().add(new XYChart.Data("Product C", 4200));

        barChart.getData().add(barChartData);
        barChart.setLegendVisible(false);

        // Add Bar chart to borderpane
        // borderPane.setCenter(barChart);
        return barChart;
    }

    @FXML
    void onShowPieChart(ActionEvent event) {

        borderPane.setCenter(buildPieChart());

    }

    private PieChart buildPieChart(){
        // Create Data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Product A",3000),
                new PieChart.Data("Product B", 2400),
                new PieChart.Data("Product C", 4200)
        );

        // Create Piechart Object
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Products Sold");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        // Create the context menu and the menu item
        ContextMenu contextMenu = new ContextMenu();
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        // Display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY){
                    contextMenu.show(pieChart,event.getScreenX(),event.getScreenY());
                }
            }
        });

        miSwitchToBarChart.setOnAction((ActionEvent actionEvent) -> {
            borderPane.setCenter(buildBarChart());
        });
        return pieChart;
    }

    @FXML
    void onUpdateData(ActionEvent event) {
        Node node = borderPane.getCenter();

        if (node instanceof PieChart) {
            PieChart pieChart = (PieChart) node;

            pieChart.getData().get(0).setPieValue(2000);
        }
    }

    @FXML
    void onClose(ActionEvent event) {
        System.exit(0);
    }


}
