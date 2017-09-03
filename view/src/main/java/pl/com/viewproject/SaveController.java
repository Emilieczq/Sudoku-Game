package pl.com.viewproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.comp.SudokuBoard;
import pl.comp.dao.DaoException;
import pl.comp.dao.FileSudokuBoardDao;
import pl.comp.dao.JdbcSudokuBoardDao;

/**
 * FXML Controller class
 *
 * @author Zhenqi
 */
public class SaveController implements Initializable {

    Stage stage;
    ResourceBundle bundle;
    SudokuBoard board;

    @FXML
    private TextField saveName;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    @FXML
    private Label name;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBoard(SudokuBoard board) {
        this.board = board;
    }

    public void setLanguageCountry(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("pl.com.i18n.save", locale);
        name.setText(bundle.getString("name")+":");
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font-weight: bold;-fx-font-size: 16");
        save.setText(bundle.getString("save"));
        save.setAlignment(Pos.CENTER);
        cancel.setText(bundle.getString("cancel"));
        cancel.setAlignment(Pos.CENTER);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws DaoException, IOException {
        if (event.getSource() == cancel) {
            stage.close();
        }
        if (event.getSource() == save) {
            if (saveName.getText() == "") {
                label.setText(bundle.getString("information1"));
                label.setAlignment(Pos.CENTER);
            } else if (saveName.getText().length() > 15) {
                label.setText(bundle.getString("information2"));
                label.setAlignment(Pos.CENTER);
            } else {
                String fileName = saveName.getText();
                /* Database */
                JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao();
                if (dao.gameExists(fileName)) {
                    label.setText(bundle.getString("information3"));
                    label.setAlignment(Pos.CENTER);
                } else {
                    dao.setGameName(fileName);
                    dao.write(board);
                    stage.close();
                }
                
                /* File */
                FileSudokuBoardDao fileDao = new FileSudokuBoardDao(fileName);
                fileDao.write(board);
            }
        }
    }
}
