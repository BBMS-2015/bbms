/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rajagopal
 */
public class AddDonorController implements Initializable {
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;

    private HomeController objHomeController;
    private DSEController objDSEController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        objHomeController = HomeController.getInstance();
        objDSEController = DSEController.getInstance();

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
    
    @FXML
    private void onSubmit() {
        objDSEController.populateDDEPage();
        objDSEController.clearAllFields();
        objHomeController.showDDEPage();        
        ((Stage) btnSubmit.getScene().getWindow()).close();
    }
    
    @FXML
    private void onCancel() {
        objDSEController.clearAllFields();
        ((Stage) btnCancel.getScene().getWindow()).close();
    }
}
