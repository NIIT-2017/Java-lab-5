package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Controller {

Automata automata;
    @FXML
    private Button btnEspresso;

    @FXML
    private Label lattePrice = new Label();

    @FXML
    private Label espressoPrice = new Label();

    @FXML
    private ProgressBar progBar = new ProgressBar(1);
    @FXML
    private ProgressIndicator progressIndicator = new ProgressIndicator(1);
    @FXML
    Label pbLabel = new Label();

    @FXML
    private Label capuccinoPrice = new Label();

    @FXML
    private Label americanoPrice = new Label();


    @FXML
    private TextArea textArea = new TextArea();

    @FXML
    private TextField tfInputMoney=new TextField();


    @FXML
    private Button btnCapuccino;

    @FXML
    private Button btnAmericano;

    @FXML
    private Button btnLatte;

    @FXML
    private Button btnOff;

    @FXML
    private Button btnOn;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSetView;

    @FXML
    Task copyWorker;

    @FXML
    ImageView image;


    public String getTfInputMoney() {
        return tfInputMoney.getText();
    }

    public void setTfInputMoney(String tfInputMoney) {
        this.tfInputMoney.setText(tfInputMoney);
    }

    public String getTextArea() {
        return textArea.getText();
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public ProgressBar getProgBar() {
        return progBar;
    }

    public void setProgBar(ProgressBar progBar) {
        this.progBar = progBar;
    }

    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }


    @FXML
    ObservableList observableList = FXCollections.observableArrayList("capuccino: ", "latte: ", "espresso: ", "americano: ");


    public void initialize() {
image.setFitHeight(20);
image.setFitWidth(20);
        image=new ImageView(getClass().getResource("black-coffee.png").toExternalForm());
        americanoPrice.setText("70");
        capuccinoPrice.setText("120");
        espressoPrice.setText("60");
        lattePrice.setText("100");
         automata=new Automata();
        OutputStream outputStream=new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                writeToTextArea(String.valueOf((char)b));
            }
        };
        System.setOut(new PrintStream(outputStream,true));

    }

    public void OnSetView(){
        btnSetView.setOnMouseClicked(event ->new Button("setView1!!",image) );
    }

    public void onClick() {
        btnOn.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                automata.on();

            }
        });
    }

    public void onAccept() {

        btnAccept.setOnMouseClicked(event ->  {

                automata.setCash(Integer.parseInt(getTfInputMoney()));
                automata.coin(automata.getCash());
                setTfInputMoney("");

        });

    }

    public void writeToTextArea(String text) {
        Platform.runLater(() -> this.textArea.appendText(text));
    }
    public void writeToTextField(String text) {
        Platform.runLater(() -> this.tfInputMoney.appendText(text));
    }
    public void onCancel() {
        btnCancel.setOnMouseClicked(event-> {
                automata.cancel(automata.getSavedCash());
                textArea.setText("");
        });

    }

    public void onStart() {
        btnStart.setOnMouseClicked(event-> {


                progBar.setProgress(0.0);
                progressIndicator.setProgress(0.0);

                copyWorker = createWorker();

                progBar.progressProperty().unbind();
                progressIndicator.progressProperty().unbind();

                progBar.progressProperty().bind(copyWorker.progressProperty());
                progressIndicator.progressProperty().bind(copyWorker.progressProperty());

                copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        System.out.println(newValue);
                        pbLabel.setText(newValue);
                    }
                });
                new Thread(copyWorker).start();

        });
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    updateMessage("Preparing is completed : " + ((i * 10) + 10) + "%");
                    updateProgress(i + 1, 10);
                }
                textArea.setText("\nEnjoy your drink!\n");
                return true;
            }


        };
    }

    public void onCapuccinoBtn() {

        btnCapuccino.setOnMouseClicked(event ->  {
                automata.setPrice(Integer.parseInt(capuccinoPrice.getText()));
                try {
                    automata.choice(automata.getPrice());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });

    }

    public void onEspresso() {
        btnEspresso.setOnMouseClicked(event -> {
            automata.setPrice(Integer.parseInt(espressoPrice.getText()));
            try {
                automata.choice(automata.getPrice());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

    }

    public void onAmericano() {
        btnAmericano.setOnMouseClicked(event -> {
                    automata.setPrice(Integer.parseInt(americanoPrice.getText()));
            try {
                automata.choice(automata.getPrice());
            } catch (InterruptedException e) {
                e.printStackTrace();
}

        });

    }
        public void onLatte () {
            btnLatte.setOnMouseClicked(event-> {
                    automata.setPrice(Integer.parseInt(lattePrice.getText()));
                    try {
                        automata.choice(automata.getPrice());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
        }
        public void onOff(){
        btnOff.setOnMouseClicked(event-> {
            textArea.setText("");
            automata.off();

        });
        }

    }






