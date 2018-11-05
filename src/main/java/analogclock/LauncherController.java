package analogclock;

import analogclock.clocks.Clock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LauncherController {

    @FXML
    private StackPane clocksPane;

    public Clock registerClock(FXMLLoader loader, String name) {
        try {
            Pane clockPane = loader.load();
            clockPane.setVisible(false);
            clocksPane.getChildren().add(clockPane);
            Clock clock = loader.getController();
            clock.setName(name);
            return clock;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to create clock: " + name);
        }
    }

}
