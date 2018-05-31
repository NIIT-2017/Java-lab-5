package Avtomat.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class AvtomatGUIController {


    @FXML
    private GridPane menuGridPane;

    public void addRowsMenuGridPane(AnchorPane pane,int col,int row) {
        menuGridPane.add(pane,col,row);
        menuGridPane.getRowConstraints().add(new RowConstraints());
    }
}
