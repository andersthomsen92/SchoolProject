package visualizealgorithms;

//Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Søren Spangsberg
 */
public class VisualizeAlgorithms extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/visualizealgorithms/gui/view/MainWindow.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/visualizealgorithms/gui/css/styles.css").toExternalForm());
        stage.setTitle("Time Complexity");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
