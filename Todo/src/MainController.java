import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class MainController {

    @FXML
    private TextField txtAdd;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnProgress; // From To Do to In Progress
    @FXML
    private Button btnTodo; // From In Progress to To Do
    @FXML
    private Button btnCompleted; // From In Progress to Completed
    @FXML
    private Button btnUnprogress; // From Completed back to In Progress
    @FXML
    private ListView<String> listTodo;
    @FXML
    private ListView<String> listInProgress;
    @FXML
    private ListView<String> listCompleted;
    @FXML
    private Button btnRemove;

    @FXML
    void addTask(ActionEvent actionEvent) {
        if (!txtAdd.getText().isEmpty()) {
            listTodo.getItems().add(txtAdd.getText());
            txtAdd.clear();
            txtAdd.requestFocus();
        }
    }
    @FXML
    void removeTask(ActionEvent actionEvent) {
        String selectedItem = listTodo.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listTodo.getItems().remove(selectedItem);
        }
    }
    @FXML
    void removeProgress(ActionEvent actionEvent) {
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listInProgress.getItems().remove(selectedItem);
        }
    }
    @FXML
    void removeCompleted(ActionEvent actionEvent) {
        String selectedItem = listCompleted.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            listCompleted.getItems().remove(selectedItem);
        }
    }
    @FXML
    void toProgress(ActionEvent actionEvent){
        String selectedItem = listTodo.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            listInProgress.getItems().add(selectedItem);
            listTodo.getItems().remove(selectedItem);
        }
    }
    @FXML
    void toTodo(ActionEvent actionEvent){
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            listTodo.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }
    @FXML
    void toCompleted(ActionEvent actionEvent){
        String selectedItem = listInProgress.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            listCompleted.getItems().add(selectedItem);
            listInProgress.getItems().remove(selectedItem);
        }
    }
    @FXML
    void toUnprogress(ActionEvent actionEvent){
        String selectedItem = listCompleted.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            listInProgress.getItems().add(selectedItem);
            listCompleted.getItems().remove(selectedItem);
        }
    }






}
