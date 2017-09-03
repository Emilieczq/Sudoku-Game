package pl.com.viewproject;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.slf4j.LoggerFactory;
import pl.comp.Game;
import pl.comp.Levels;

public class HomePageController implements Initializable {

    String language = "en";
    String country = "US";
    Locale currentLocale = new Locale(language, country);
    ResourceBundle bundle = ResourceBundle.getBundle("i18n.MyBundle", currentLocale);
    Game game = new Game();
    TextField[][] list = new TextField[9][9];
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Game.class);
    private String saveName;

    @FXML
    private Label label;
    @FXML
    private Button easy;
    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private Button check;
    @FXML
    private Button restart;
    @FXML
    private GridPane pizzle;
    @FXML
    ChoiceBox<String> cb;
    @FXML
    private AnchorPane MenuBar;
    @FXML
    private MenuItem about;
    @FXML
    private Menu file;
    @FXML
    private MenuItem newGame;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem exit;
    @FXML
    private Menu help;

    public HomePageController() {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws CloneNotSupportedException {
        if (event.getSource() == check) {
            if (game.getBoard().checkAnswer()) {
                label.setText(bundle.getString("right"));
                LOG.info(bundle.getString("checkInfo"), bundle.getString("right"));
            } else {
                label.setText(bundle.getString("wrong"));
                LOG.info(bundle.getString("checkInfo"), bundle.getString("wrong"));
            }

        } else {
            if (event.getSource() == easy) {
                Node node = pizzle.getChildren().get(0);
                pizzle.getChildren().clear();
                pizzle.getChildren().add(0, node);
                game.setLevel(Levels.EASY);
                game.makePizzle();
                LOG.info(bundle.getString("level"), bundle.getString("easy"));
            } else if (event.getSource() == medium) {
                Node node = pizzle.getChildren().get(0);
                pizzle.getChildren().clear();
                pizzle.getChildren().add(0, node);
                game.setLevel(Levels.MEDIUM);
                game.makePizzle();
                LOG.info(bundle.getString("level"), bundle.getString("medium"));
            } else if (event.getSource() == hard) {
                Node node = pizzle.getChildren().get(0);
                pizzle.getChildren().clear();
                pizzle.getChildren().add(0, node);
                game.setLevel(Levels.HARD);
                game.makePizzle();
                LOG.info(bundle.getString("level"), bundle.getString("hard"));
            } else if (event.getSource() == restart) {
                Node node = pizzle.getChildren().get(0);
                pizzle.getChildren().clear();
                pizzle.getChildren().add(0, node);
                if (null != game.getLevel()) {
                    switch (game.getLevel()) {
                        case EASY:
                            label.setText(bundle.getString("easy"));
                            break;
                        case MEDIUM:
                            label.setText(bundle.getString("medium"));
                            break;
                        case HARD:
                            label.setText(bundle.getString("hard"));
                            break;
                        default:
                            break;
                    }
                }
                LOG.info(bundle.getString("restartInfo"));
                game.restart();
            }

            StringConverter integerStringConverter = new IntegerStringConverter();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    try {
                        TextField field = new TextField();
                        field.setAlignment(Pos.CENTER);
                        field.setPrefHeight(43);
                        field.setBackground(Background.EMPTY);
                        JavaBeanIntegerProperty integerproperty = JavaBeanIntegerPropertyBuilder.create().
                                bean(game.getBoard().getField(i, j)).name("value").build();

                        field.textProperty().bindBidirectional(integerproperty, integerStringConverter);
                        if (field.getText().equals("0")) {
                            field.setText("");
                            field.setStyle("-fx-text-fill: blue;-fx-font-size: 20");
                        } else {
                            field.setStyle("-fx-font-size: 20");
                            field.setEditable(false);
                        }
                        list[i][j] = field;
                        pizzle.add(field, j, i);
                    } catch (NoSuchMethodException ex) {
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cb.setTooltip(new Tooltip("Select the language"));

        cb.getItems().addAll("English", "简体中文");

        final String[] countries = new String[]{"US", "CN"};
        final String[] languages = new String[]{"en", "zh"};

        cb.setValue("English"); // if reload, remove this line
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov,
                    Number value, Number newValue) {
                country = countries[newValue.intValue()];
                language = languages[newValue.intValue()];

                currentLocale = new Locale(language, country);
                bundle = ResourceBundle.getBundle("i18n.MyBundle", currentLocale);
                easy.setText(bundle.getString("easy"));
                medium.setText(bundle.getString("medium"));
                hard.setText(bundle.getString("hard"));
                check.setText(bundle.getString("check"));
                restart.setText(bundle.getString("restart"));
                file.setText(bundle.getString("file"));
                save.setText(bundle.getString("save"));
                newGame.setText(bundle.getString("newGame"));
                exit.setText(bundle.getString("exit"));
                help.setText(bundle.getString("help"));
                about.setText(bundle.getString("about"));
//                try {
//                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"), bundle);
//                    Scene scene = new Scene(root);
//                    scene.getStylesheets().add("/styles/Styles.css");
//                    Stage stage = (Stage) easy.getScene().getWindow();
//                    stage.setScene(scene);
//
//                } catch (IOException ex) {
//                }

            }
        });
//        if (language == "en") {
//            cb.setValue("Language");
//        } else if (language == "zh") {
//            cb.setValue("语言");
//        }

    }

    @FXML
    public void doExit() {
        Platform.exit();
    }

    @FXML
    public void newGame() throws CloneNotSupportedException {
        Node node = pizzle.getChildren().get(0);
        pizzle.getChildren().clear();
        pizzle.getChildren().add(0, node);
    }

    @FXML
    private void about(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/About.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            AboutController controller = (AboutController) fxmlLoader.getController();
            controller.setLanguageCountry(language, country);
            controller.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOG.error(bundle.getString("Error"));
        }
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Save.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            SaveController controller = (SaveController) fxmlLoader.getController();
            controller.setLanguageCountry(language, country);
            controller.setBoard(game.getBoard());
            controller.setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOG.error(bundle.getString("error"));
        }

    }

}
