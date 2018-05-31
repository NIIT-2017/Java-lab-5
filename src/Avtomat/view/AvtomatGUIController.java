package Avtomat.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class AvtomatGUIController {

    @FXML
    private Button coin1Button;

    @FXML
    private Button coin2Button;

    @FXML
    private Button coin5Button;

    @FXML
    private Button coin10Button;

    @FXML
    private Button coin50Button;

    @FXML
    private Label cashLabel;

    @FXML
    private Label moneyReturnLabel;

    @FXML
    private Label displayLabel;

    @FXML
    private GridPane menuGridPane;

    public void addRowsMenuGridPane(AnchorPane pane,int col,int row) {
        menuGridPane.add(pane,col,row);
        menuGridPane.getRowConstraints().add(new RowConstraints());
    }
}
