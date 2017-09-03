/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.viewproject;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Zhenqi & Danyang
 */
public class AboutController implements Initializable {

    Stage stage;

    @FXML
    private Label overview;
    @FXML
    private Label information;
    @FXML
    private Label contact;
    @FXML
    private Label email;
    @FXML
    private Button close;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == close) {
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLanguageCountry(String language, String country) {
        Locale locale = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle("pl.com.i18n.author", locale);
        overview.setText(bundle.getString("overview"));
        overview.setAlignment(Pos.CENTER);
        overview.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        information.setText(bundle.getString("information"));
        information.setAlignment(Pos.TOP_LEFT);
        information.setWrapText(true);
        contact.setText(bundle.getString("contact"));
        contact.setAlignment(Pos.CENTER);
        contact.setStyle("-fx-font-weight: bold;-fx-font-size: 20");
        email.setText(bundle.getString("email"));
        email.setAlignment(Pos.TOP_LEFT);
        email.setWrapText(true);
        close.setText(bundle.getString("close"));
    }
}
