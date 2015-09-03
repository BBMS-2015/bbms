/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import database.DBApi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author anirudhnegi
 */
public class ConfirmController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label confirm_label;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
  /*  @FXML
    private RadioButton rbr1;
    @FXML
    private RadioButton rbr2;*/
    private DDEController objDDEController =DDEController.getInstance();
   // ToggleGroup tgr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnSubmit.setOnAction((ActionEvent event) -> {
            /*Navin code*/
            if(objDDEController.getCheck()==0)
                check();
            else
                check1();
            /*Navin code*/
        });
        
        btnCancel.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
       
        Platform.runLater(() -> {

            /* Keyboard shortcut for firing Submit button */
            btnSubmit.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER), 
                () -> {
                    if (!btnCancel.isFocused()) {
                        btnSubmit.fire();
                    } else {
                        btnCancel.fire();
                    }
                }
            );

            /* Keyboard shortcut for firing Cancel button */
            btnCancel.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ESCAPE),
                () -> {
                    btnCancel.fire();
                }
            );
        });       
    }

    public void check() {
        System.out.println("check");
        String[] x = objDDEController.x();
        DBApi db = new DBApi();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        if (db.insert(x)) {
            new AlertDialog(stage, "Record entered successfully.", AlertDialog.ICON_INFO).showAndWait();            
        } else {
            new AlertDialog(stage, "Record entry failed !", AlertDialog.ICON_ERROR).showAndWait();
        }

        stage.close();

    }
    /*Navin code*/
        public void check1() {
        System.out.println("check1");
        String[] x = objDDEController.getFormData();
        DBApi db = new DBApi();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        if (db.insert1(x)) {
            new AlertDialog(stage, "Record entered successfully.", AlertDialog.ICON_INFO).showAndWait();
            HomeController.getInstance().showDSEPage();
        } else {
            new AlertDialog(stage, "Record entry failed !", AlertDialog.ICON_ERROR).showAndWait();
        }

        stage.close();        
    }
}
