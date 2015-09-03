/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rajagopal
 */
public class DDUController implements Initializable {
    @FXML
    private AnchorPane primary;
    @FXML
    private Group gBasicProfile;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDOB;
    @FXML
    private ComboBox<?> cobxGender;
    @FXML
    private ComboBox<?> cobxBloodGroup;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfSpouseName;
    @FXML
    private TextField tfEducation;
    @FXML
    private TextField tfOccupation;
    @FXML
    private Label lBasicProfileError;
    @FXML
    private Group gResidence;
    @FXML
    private TextArea taResidenceAddress;
    @FXML
    private TextField tfResidencePincode;
    @FXML
    private TextField tfResMobileNumber;
    @FXML
    private TextField tfResLandlineStdCode;
    @FXML
    private TextField tfResLandlineNumber;
    @FXML
    private TextField tfResEmail;
    @FXML
    private Label lResidenceError;
    @FXML
    private Group gOffice;
    @FXML
    private TextArea taOfficeAddress;
    @FXML
    private TextField tfOfficePincode;
    @FXML
    private TextField tfOffMobileNumber;
    @FXML
    private TextField tfOffLandlineStdCode;
    @FXML
    private TextField tfOffLandlineNumber;
    @FXML
    private TextField tfOffEmail;
    @FXML
    private Label lOfficeError;
    @FXML
    private Group gBloodDonation;
    @FXML
    private CheckBox chbxPeriodic;
    @FXML
    private ComboBox<?> cobxPeriodicity;
    @FXML
    private CheckBox chbxBirthday;
    @FXML
    private TextField tfBirthday;
    @FXML
    private CheckBox chbxWedding;
    @FXML
    private TextField tfWedding;
    @FXML
    private CheckBox chbxOther;
    @FXML
    private TextField tfOther;
    @FXML
    private CheckBox chbxWB;
    @FXML
    private CheckBox chbxAph;
    @FXML
    private TextField tfRegistrationDate;
    @FXML
    private TextField tfSuggestedNextDonationDate;
    @FXML
    private Label lBloodDonationError;
    @FXML
    private Label lbMessage;
    @FXML
    private ComboBox<?> cmbbxAction;
    @FXML
    private Button btnGo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
