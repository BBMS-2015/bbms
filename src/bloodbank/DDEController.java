/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rajagopal
 */
public class DDEController implements Initializable {

    private static DDEController instance;
    
    /*Navin code */
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDOB;
    @FXML
    private ComboBox cobxGender;
    @FXML
    private ComboBox cobxBloodGroup;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfSpouseName;
    @FXML
    private TextField tfEducation;
    @FXML
    private TextField tfOccupation;
    @FXML
    private TextField tfResLandlineStdCode;
    @FXML
    private TextField tfResLandlineNumber;
    @FXML
    private TextField tfResEmail;
    @FXML
    private TextField tfResMobileNumber;
    @FXML
    private TextArea taResidenceAddress;
    @FXML
    private TextField tfResidencePincode;
    @FXML
    private TextField tfOffLandlineStdCode;
    @FXML
    private TextField tfOffLandlineNumber;
    @FXML
    private TextField tfOffEmail;
    @FXML
    private TextField tfOffMobileNumber;
    @FXML
    private TextArea taOfficeAddress;
    @FXML
    private TextField tfOfficePincode;
    @FXML
    private TextField tfBirthday;
    @FXML
    private CheckBox chbxBirthday;
    @FXML
    private CheckBox chbxPeriodic;
    @FXML
    private ComboBox cobxPeriodicity;
    @FXML
    private CheckBox chbxWedding;
    @FXML
    private TextField tfWedding;
    @FXML
    private TextField tfOther;
    @FXML
    private CheckBox chbxOther;
    @FXML
    private TextField tfRegistrationDate;
    @FXML
    private TextField tfSuggestedNextDonationDate;
    @FXML
    private CheckBox chbxWB;
    @FXML
    private CheckBox chbxAph;
    @FXML
    private Label lbMessage;
    
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnReset;

    /*Navin code */
    private String[] formData = new String[25];
    private int check;
    protected boolean willingnessChoice = false;
    /* WB to be selected by default */
    private boolean bWB = true;
    private boolean bAph = false;

    private boolean bNameValidate;
    private boolean bDOBValidate;
    private boolean bGenderValidate;
    private boolean bBloodGroupValidate;
    private boolean bSpouseNameValidate;
    private boolean bRegistrationDateValidate;
    private boolean bSuggestedNextDonationDateValidate;
    private boolean bResidenceAddressValidate;
    private boolean bResidencePincodeValidate;
    private boolean bOfficeAddressValidate;
    private boolean bOfficePincodeValidate;
    private boolean bOtherValidate;
    private boolean bWeddingValidate;
    private boolean bResLandlineNumberValidate;
    private boolean bResMobileNumberValidate;
    private boolean bOffLandlineNumberValidate;
    private boolean bOffMobileNumberValidate;
    private boolean bResLandlineStdCodeValidate;
    private boolean bOffLandlineStdCodeValidate;
    private boolean bEducationValidate;
    private boolean bOccupationValidate;
    private boolean bDonorTypeValidate;
    private boolean bDonorTypeWBValidate;
    private boolean bDonorTypeAphValidate;
    private boolean bOffEmailValidate;
    private boolean bResEmailValidate;

    static String[] abca = new String[23];

    private ValidationBean validation;

    public DDEController() {
        check = 0;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;

        validation = new ValidationBean();
        validation.addPropertyChangeListener(new DDEController.MyPropertyChangeListener());
        
        bNameValidate = false;
        bDOBValidate = false;
        bGenderValidate = false;
        bBloodGroupValidate = false;
        bSpouseNameValidate = false;
        bRegistrationDateValidate = false;
        bSuggestedNextDonationDateValidate = false;
        bResidenceAddressValidate = false;
        bResidencePincodeValidate = false;
        bOfficeAddressValidate = false;
        bOtherValidate = false;
        bWeddingValidate = false;
        bResLandlineNumberValidate = false;
        bResMobileNumberValidate = false;
        bOffLandlineNumberValidate = false;
        bOffMobileNumberValidate = false;
        bResLandlineStdCodeValidate = false;
        bOffLandlineStdCodeValidate = false;
        bEducationValidate = false;
        bOccupationValidate = false;
        bDonorTypeValidate = false;
        /* WB to be selected by default */
        bDonorTypeWBValidate = false;
        bDonorTypeAphValidate = false;
        bOffEmailValidate = false;
        bResEmailValidate = false;
        
        populate_donorentry();
    }    

// static method to get instance of view
    public static DDEController getInstance() {
        return instance;
    }
    
    public void populate_donorentry() {
        tfName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));
        tfName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                ObservableList<String> styleClass = tfName.getStyleClass();
                if (!newPropertyValue) {
                    if (tfName.getText().trim().length() > 0) {
                        tfName.setText(capitalizeFirstLetterOfEachWord(tfName.getText().trim()));
                    }
                }
            }
        });
        tfName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfName.getStyleClass();
                if (tfName.getText().trim().length() == 0) {
                    if (bNameValidate) {
                        bNameValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bNameValidate) {
                        bNameValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfDOB.promptTextProperty().setValue("DD/MM/YYYY");
        tfDOB.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfDOB.getStyleClass();
                
                if (tfDOB.getText().length() == 0) {
                    bDOBValidate = false;
                    styleClass.removeAll(Collections.singleton("error"));
                    tfAge.clear();
                } else if (ProjectUtils.dateValidation(tfDOB.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bDOBValidate) {
                            bDOBValidate = false;
                            tfAge.clear();
                        }
                        callValidation();
                    }
                } else {
                    int y = Integer.parseInt(tfDOB.getText().substring(6));
                    int curr = Calendar.getInstance().get(Calendar.YEAR);
                    int diff = curr - y;
                    
                    if (diff >= 18 && diff < 60) {
                        tfAge.setText(String.valueOf(diff));
                        if (chbxBirthday.isSelected()) {
                            tfBirthday.setText(tfDOB.getText());
                        }
                        styleClass.removeAll(Collections.singleton("error"));
                        if (!bDOBValidate) {
                            bDOBValidate = true;
                            callValidation();
                        }                        
                    } else {
                        if (bDOBValidate) {
                            bDOBValidate = false;
                        }
                        callValidation();
                    }
                }
            }
        });

        Object[] gen = {"Male", "Female", "TransGender"};
        cobxGender.getItems().addAll(gen);
        
        cobxGender.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldPropertyValue, String newPropertyValue) {
                bGenderValidate = true;
                
                if (bRegistrationDateValidate) {

                    String dt = tfRegistrationDate.getText();  // Start date
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        if (!dt.isEmpty()) {
                            c.setTime(sdf.parse(dt));
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        String gen = cobxGender.getSelectionModel().getSelectedItem().toString();
                        if (gen.equals("Female") || gen.equals("TransGender")) {
                            c.add(Calendar.MONTH, 4);  // number of days to add

                        } else {
                            c.add(Calendar.MONTH, 3);
                        }

                        dt = sdf.format(c.getTime());  // dt is now the new date
                        tfSuggestedNextDonationDate.setText(dt);//Navin no need for incremented show
                    } catch (NullPointerException e) {

                    }
                }
                callValidation();
            }
        });
        
        tfEducation.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(50));
        tfEducation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfEducation.getText().trim().length() > 0) {
                        tfEducation.setText(capitalizeFirstLetterOfEachWord(tfEducation.getText().trim()));
                    }
                }
            }
        });
        tfEducation.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfEducation.getStyleClass();
                if (tfEducation.getText().trim().length() == 0) {
                    if (bEducationValidate) {
                        bEducationValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bEducationValidate) {
                        bEducationValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfOccupation.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(50));
        tfOccupation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOccupation.getText().trim().length() > 0) {
                        tfOccupation.setText(capitalizeFirstLetterOfEachWord(tfOccupation.getText().trim()));
                    }
                }
            }
        });
        tfOccupation.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfOccupation.getStyleClass();
                if (tfOccupation.getText().trim().length() == 0) {
                    if (bOccupationValidate) {
                        bOccupationValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bOccupationValidate) {
                        bOccupationValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfResLandlineStdCode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(4));
        tfResLandlineStdCode.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResLandlineStdCode.getStyleClass();
                if (tfResLandlineStdCode.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResLandlineStdCodeValidate = false;
                    callValidation();
                } else if (tfResLandlineStdCode.getText().length() < 3) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bResLandlineStdCodeValidate) {
                            bResLandlineStdCodeValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bResLandlineStdCodeValidate) {
                        bResLandlineStdCodeValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfResLandlineNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(8));
        tfResLandlineNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResLandlineNumber.getStyleClass();
                if (tfResLandlineNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResLandlineNumberValidate = false;
                    callValidation();
                } else if (tfResLandlineNumber.getText().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bResLandlineNumberValidate) {
                            bResLandlineNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bResLandlineNumberValidate) {
                        bResLandlineNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfResMobileNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(10));
        tfResMobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResMobileNumber.getStyleClass();
                if (tfResMobileNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResMobileNumberValidate = false;
                    callValidation();
                } else if (tfResMobileNumber.getText().length() < 10) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bResMobileNumberValidate) {
                            bResMobileNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bResMobileNumberValidate) {
                        bResMobileNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfOffLandlineStdCode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(4));
        tfOffLandlineStdCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOffLandlineStdCode.getStyleClass();
                if (tfOffLandlineStdCode.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffLandlineStdCodeValidate = false;
                    callValidation();
                } else if (tfOffLandlineStdCode.getText().length() < 3) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOffLandlineStdCodeValidate) {
                            bOffLandlineStdCodeValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bOffLandlineStdCodeValidate) {
                        bOffLandlineStdCodeValidate = true;
                        callValidation();
                    }

                }
            }
        });
        
        tfOffLandlineNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(8));
        tfOffLandlineNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOffLandlineNumber.getStyleClass();
                if (tfOffLandlineNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffLandlineNumberValidate = false;
                    callValidation();
                } else if (tfOffLandlineNumber.getText().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOffLandlineNumberValidate) {
                            bOffLandlineNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bOffLandlineNumberValidate) {
                        bOffLandlineNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfOffMobileNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(10));
        tfOffMobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOffMobileNumber.getStyleClass();
                if (tfOffMobileNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffMobileNumberValidate = false;
                    callValidation();
                } else if (tfOffMobileNumber.getText().length() < 10) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOffMobileNumberValidate) {
                            bOffMobileNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bOffMobileNumberValidate) {
                        bOffMobileNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfBirthday.setEditable(false);

        chbxBirthday.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (chbxBirthday.isSelected()) {
                    tfBirthday.setText(tfDOB.getText());

                } else {
                    tfBirthday.clear();
                }
            }
        });

        tfWedding.setEditable(false);
        tfWedding.promptTextProperty().setValue("DD/MM/YYYY");
        tfWedding.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfWedding.getStyleClass();
                if (tfWedding.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bWeddingValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfWedding.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bWeddingValidate) {
                            bWeddingValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bWeddingValidate) {
                        bWeddingValidate = true;
                        callValidation();
                    }

                }
            }
        });
        
        chbxWedding.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                ObservableList<String> styleClass = tfWedding.getStyleClass();
                if (chbxWedding.isSelected()) {
                    tfWedding.setEditable(true);
                    if (bWeddingValidate) {
                        bWeddingValidate = false;
                        //styleClass.add("error");
                        callValidation();
                    }
                } else {
                    tfWedding.clear();
                    tfWedding.setEditable(false);
                    if (!bWeddingValidate) {
                        bWeddingValidate = true;
                        styleClass.removeAll(Collections.singleton("error"));
                        callValidation();
                    }
                }
            }
        });

        tfOther.setEditable(false);
        tfOther.promptTextProperty().setValue("DD/MM/YYYY");
        tfOther.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOther.getStyleClass();
                if (tfOther.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOtherValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfOther.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOtherValidate) {
                            bOtherValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    
                    if (!bOtherValidate) {
                        bOtherValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        chbxOther.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                ObservableList<String> styleClass = tfOther.getStyleClass();
                if (chbxOther.isSelected()) {
                    tfOther.setEditable(true);
                    if (bOtherValidate) {
                        //styleClass.add("error");
                        bOtherValidate = false;
                        callValidation();
                    }
                } else {
                    tfOther.clear();
                    tfOther.setEditable(false);
                    styleClass.removeAll(Collections.singleton("error"));
                    if (!bOtherValidate) {
                        bOtherValidate = true;
                        callValidation();
                    }
                }
            }
        });

        chbxPeriodic.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (chbxPeriodic.isSelected()) {
                    cobxPeriodicity.setDisable(false);
                } else {
                    cobxPeriodicity.setDisable(true);
                }
            }
        });
        Object[] will = {"Quaterly", "Half Yearly", "Annually"};
        cobxPeriodicity.getItems().addAll(will);

        chbxWB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {

                if (chbxWB.isSelected()) {

                    bDonorTypeWBValidate = true;
                    callValidation();
                } else {

                    bDonorTypeWBValidate = false;
                    callValidation();
                }
            }

        });
        
        chbxAph.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {

                if (chbxAph.isSelected()) {

                    bDonorTypeAphValidate = true;
                    callValidation();
                } else {

                    bDonorTypeAphValidate = false;
                    callValidation();
                }
            }

        });

        Object[] bloodGroup = {"--Select--", "A1+", "A1-", "A2+", "A2-", "B+", "B-", "O+", "O-", "A1B+", "A1B-", "A2B+", "A2B-", "BOMBAY GROUP"};
        cobxBloodGroup.getItems().addAll(bloodGroup);

        cobxBloodGroup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldPropertyValue, String newPropertyValue) {
                if (cobxBloodGroup.getSelectionModel().isSelected(0)) {
                    bBloodGroupValidate = false;
                    callValidation();
                } else if (!bBloodGroupValidate){
                    bBloodGroupValidate = true;
                    callValidation();
                }
            }
        });
            
        tfSpouseName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));
        tfSpouseName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfSpouseName.getText().trim().length() > 0) {
                        tfSpouseName.setText(capitalizeFirstLetterOfEachWord(tfSpouseName.getText().trim()));
                    }
                }
            }
        });
        tfSpouseName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfSpouseName.getText().trim().length() == 0) {
                    if (bSpouseNameValidate) {
                        bSpouseNameValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bSpouseNameValidate) {
                        bSpouseNameValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfRegistrationDate.promptTextProperty().setValue("DD/MM/YYYY");
        tfRegistrationDate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfRegistrationDate.getStyleClass();
                if (tfRegistrationDate.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bRegistrationDateValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfRegistrationDate.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bRegistrationDateValidate) {
                            bRegistrationDateValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bRegistrationDateValidate) {
                        bRegistrationDateValidate = true;
                        callValidation();
                    }
                    if (bGenderValidate) {
                        String dt = tfRegistrationDate.getText();  // Start date
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar c = Calendar.getInstance();
                        try {
                            if (!dt.isEmpty()) {
                                c.setTime(sdf.parse(dt));
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String gen = cobxGender.getSelectionModel().getSelectedItem().toString();
                        if (gen.equals("Female")) {
                            c.add(Calendar.MONTH, 4);  // number of days to add
                        } else {
                            c.add(Calendar.MONTH, 3);
                        }

                        dt = sdf.format(c.getTime());  // dt is now the new date
                        tfSuggestedNextDonationDate.setText(dt);//Navin no need for incremented show
                    }
                }
            }
        });

        tfSuggestedNextDonationDate.promptTextProperty().setValue("DD/MM/YYYY");
        tfSuggestedNextDonationDate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfSuggestedNextDonationDate.getStyleClass();
                if (tfSuggestedNextDonationDate.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bSuggestedNextDonationDateValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfSuggestedNextDonationDate.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bSuggestedNextDonationDateValidate) {
                            bSuggestedNextDonationDateValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    if (!bSuggestedNextDonationDateValidate) {
                        bSuggestedNextDonationDateValidate = true;
                        callValidation();
                    }
                }
            }
        });

        chbxWB.selectedProperty().addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (chbxWB.isSelected()) {
                    bWB = true;
                } else {
                    bWB = false;
                }
            }
        });

        chbxAph.selectedProperty().addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (chbxAph.isSelected()) {
                    bAph = true;
                } else {
                    bAph = false;
                }
            }
        });
        
        taResidenceAddress.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent objKeyEvent) {
                if (objKeyEvent.getCode() == KeyCode.TAB) {
                    TextAreaSkin objTextAreaSkin = (TextAreaSkin) taResidenceAddress.getSkin();
                    if (objTextAreaSkin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior objTextAreaBehavior = (TextAreaBehavior) objTextAreaSkin.getBehavior();
                        if (objKeyEvent.isControlDown()) {
                            objTextAreaBehavior.callAction("InsertTab");
                        } else if (objKeyEvent.isShiftDown()) {
                            objTextAreaBehavior.callAction("TraversePrevious");                            
                        } else {
                            objTextAreaBehavior.callAction("TraverseNext");
                        }
                        objKeyEvent.consume();
                    }
                }
            }
        });
        
        taResidenceAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (taResidenceAddress.getText().trim().length() > 0) {
                        taResidenceAddress.setText(capitalizeFirstLetterOfEachWord(taResidenceAddress.getText().trim()));
                    }
                }
            }
        });

        taResidenceAddress.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (taResidenceAddress.getText().trim().length() == 0) {
                    if (bResidenceAddressValidate) {
                        bResidenceAddressValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bResidenceAddressValidate) {
                        bResidenceAddressValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfResidencePincode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(6));
        tfResidencePincode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfResidencePincode.getStyleClass();
                if (tfResidencePincode.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResidencePincodeValidate = false;
                    callValidation();
                } else if (tfResidencePincode.getText().trim().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bResidencePincodeValidate) {
                            bResidencePincodeValidate = false;
                        }
                        callValidation();                            
                    }                    
                } else {
                    styleClass.removeAll(Collections.singleton("error"));                    
                    
                    if (!bResidencePincodeValidate) {
                        bResidencePincodeValidate = true;
                        callValidation();                        
                    }
                }
            }
        });
        
        tfResEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResEmail.getText().trim().length() > 0) {
                        tfResEmail.getText().trim().toLowerCase();
                    }
                }
            }
        });

        tfResEmail.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfResEmail.getStyleClass();
                if (tfResEmail.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResEmailValidate = false;
                    callValidation();
                } else if (ProjectUtils.emailValidation(tfResEmail.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bResEmailValidate) {
                            bResEmailValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    // remove all occurrences:
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bResEmailValidate) {
                        bResEmailValidate = true;
                        callValidation();
                    }
                }
            }

        });
        
        taOfficeAddress.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent objKeyEvent) {
                if (objKeyEvent.getCode() == KeyCode.TAB) {
                    TextAreaSkin objTextAreaSkin = (TextAreaSkin) taOfficeAddress.getSkin();
                    if (objTextAreaSkin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior objTextAreaBehavior = (TextAreaBehavior) objTextAreaSkin.getBehavior();
                        if (objKeyEvent.isControlDown()) {
                            objTextAreaBehavior.callAction("InsertTab");
                        } else if (objKeyEvent.isShiftDown()) {
                            objTextAreaBehavior.callAction("TraversePrevious");                            
                        } else {
                            objTextAreaBehavior.callAction("TraverseNext");
                        }
                        objKeyEvent.consume();
                    }
                }
            }
        });

        taOfficeAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (taOfficeAddress.getText().trim().length() > 0) {
                        taOfficeAddress.setText(capitalizeFirstLetterOfEachWord(taOfficeAddress.getText().trim()));
                    }
                }
            }
        });

        taOfficeAddress.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = taOfficeAddress.getStyleClass();
                if (taOfficeAddress.getText().trim().length() == 0) {
                    if (bOfficeAddressValidate) {
                        bOfficeAddressValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bOfficeAddressValidate) {
                        bOfficeAddressValidate = true;
                        callValidation();
                    }
                }
            }

        });
        
        tfOfficePincode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(6));
        tfOfficePincode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfOfficePincode.getStyleClass();
                if (tfOfficePincode.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOfficePincodeValidate = false;
                    callValidation();
                } else if (tfOfficePincode.getText().trim().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOfficePincodeValidate) {
                            bOfficePincodeValidate = false;
                        }
                        callValidation();                            
                    }
                    
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    
                    if (!bOfficePincodeValidate) {
                        bOfficePincodeValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfOffEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOffEmail.getText().trim().length() > 0) {
                        tfOffEmail.getText().trim().toLowerCase();
                    }
                }
            }
        });

        tfOffEmail.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfOffEmail.getStyleClass();
                if (tfOffEmail.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffEmailValidate = false;
                    callValidation();
                } else if (ProjectUtils.emailValidation(tfOffEmail.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bOffEmailValidate) {
                            bOffEmailValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    // remove all occurrences:
                    styleClass.removeAll(Collections.singleton("error"));
                    
                    if (!bOffEmailValidate) {
                        bOffEmailValidate = true;
                        callValidation();
                    }
                }
            }

        });
        
        tfAge.setDisable(true);
        
        cobxGender.getSelectionModel().selectFirst();
        
        chbxPeriodic.setSelected(true);
        cobxPeriodicity.getSelectionModel().selectFirst();
        
        cobxBloodGroup.getSelectionModel().selectFirst();
        
        chbxWB.setSelected(true);
        
        tfRegistrationDate.setText(ProjectUtils.getCurrentDate());
    }

    private void callValidation() {
        boolean dobValidate = !ProjectUtils.dateValidation(tfDOB.getText().trim());           
        boolean chbxOtherValidate = chbxOther.isSelected();
        boolean chbxWeddingValidate = chbxWedding.isSelected();
        int iAddressLength, iPincodeLength;
        int iLandlineLength, iLandlineStdCodeLength;
        int iMobileLength;
        
        boolean resAddrValidate = false;
        iAddressLength = taResidenceAddress.getText().trim().length();
        iPincodeLength = tfResidencePincode.getText().length();
        
        if (iAddressLength == 0 && iPincodeLength == 0) {
            resAddrValidate = true;
        } else if (iAddressLength > 0 && iPincodeLength == 6) {
            resAddrValidate = true;
        }
        
        boolean offAddrValidate = false;
        iAddressLength = taOfficeAddress.getText().trim().length();
        iPincodeLength = tfOfficePincode.getText().length();
        
        if (iAddressLength == 0 && iPincodeLength == 0) {
            offAddrValidate = true;
        } else if (iAddressLength > 0 && iPincodeLength == 6) {
            offAddrValidate = true;
        }

        boolean resPhoneValidate = false;
        iLandlineLength = tfResLandlineNumber.getText().trim().length();
        iLandlineStdCodeLength = tfResLandlineStdCode.getText().trim().length();
        
        if ((iLandlineLength == 0) && (iLandlineStdCodeLength == 0)) {
            resPhoneValidate = true;
        } else if ((iLandlineLength >= 6) && (iLandlineStdCodeLength >= 3)) {
            resPhoneValidate = true;
        }

        boolean offPhoneValidate = false;
        iLandlineLength = tfOffLandlineNumber.getText().trim().length();
        iLandlineStdCodeLength = tfOffLandlineStdCode.getText().trim().length();
        
        if ((iLandlineLength == 0) && (iLandlineStdCodeLength == 0)) {
            offPhoneValidate = true;
        } else if ((iLandlineLength >= 6) && (iLandlineStdCodeLength >= 3)) {
            offPhoneValidate = true;            
        }
        
        boolean resMobileValidate = false;
        iMobileLength = tfResMobileNumber.getText().length();
        
        if (iMobileLength == 0 || iMobileLength == 10) {
            resMobileValidate = true;
        }
        
        boolean offMobileValidate = false;
        iMobileLength = tfOffMobileNumber.getText().length();
        
        if (iMobileLength == 0 || iMobileLength == 10) {
            offMobileValidate = true;
        }
        
        boolean resEmailValidate = false;
        if (tfResEmail.getText().trim().length() == 0 || bResEmailValidate) {
            resEmailValidate = true;
        }
        
        boolean offEmailValidate = false;
        if (tfOffEmail.getText().trim().length() == 0 || bOffEmailValidate) {
            offEmailValidate = true;
        }
        
        ObservableList<String> styleClass = chbxAph.getStyleClass();
        ObservableList<String> styleClass1 = chbxWB.getStyleClass();

        if (bDonorTypeWBValidate || bDonorTypeAphValidate) {
            bDonorTypeValidate = true;
            styleClass.removeAll(Collections.singleton("error"));
            styleClass1.removeAll(Collections.singleton("error"));
        } else {
            bDonorTypeValidate = false;
            //styleClass.add("error");
            styleClass1.add("error");
        }
        
        boolean mandatoryFieldsValidate = bNameValidate && bDOBValidate && bBloodGroupValidate && bSpouseNameValidate && bEducationValidate && bOccupationValidate && bDonorTypeValidate && bRegistrationDateValidate && bSuggestedNextDonationDateValidate;
        boolean donorContactAddressValidate = ((bResidenceAddressValidate && bResidencePincodeValidate) || (bOfficeAddressValidate && bOfficePincodeValidate));
        boolean bDonorContactAddressIncompleteValidate = resAddrValidate && offAddrValidate;
        boolean donorContactsValidate = (bResLandlineStdCodeValidate && bResLandlineNumberValidate) || bResMobileNumberValidate || bResEmailValidate || (bOffLandlineStdCodeValidate && bOffLandlineNumberValidate) || bOffMobileNumberValidate || bOffEmailValidate;
        boolean bDonorContactsIncompleteValidate = resPhoneValidate && offPhoneValidate && resMobileValidate && offMobileValidate && resEmailValidate && offEmailValidate;
        boolean donorWillingness = (bOtherValidate || !chbxOtherValidate) && (bWeddingValidate || !chbxWeddingValidate);

        StringBuilder s = new StringBuilder("");
        if (mandatoryFieldsValidate && donorWillingness && donorContactAddressValidate && bDonorContactAddressIncompleteValidate && donorContactsValidate && bDonorContactsIncompleteValidate) {
            validation.setProperty(1);
            lbMessage.setText("You can Add Donor Detail");
            lbMessage.setStyle("-fx-text-alignment: center; -fx-text-fill: green");
        } else {
            s.delete(0, s.length());
            if (!mandatoryFieldsValidate) {
                if (!bDOBValidate && dobValidate) {
                    s.append("Donor age should be between 18 and 60");
                } else {
                    s.append("Mandatory fields(*) not filled");
                }
            } else if (!donorWillingness) {
                s.append("Invalid wedding/other date fields");
            } else if (!donorContactAddressValidate || !bDonorContactAddressIncompleteValidate) {
                if (!resAddrValidate) {
                    s.append("Please enter both address & pincode for residence");
                } else if (!offAddrValidate) {
                    s.append("Please enter both address & pincode for office");
                } else {
                    s.append("Either residence or office address must be entered");
                }
            } else if (!donorContactsValidate || !bDonorContactsIncompleteValidate) {
                if (!resPhoneValidate) {
                    s.append("Invalid residence land line number");
                } else if (!offPhoneValidate) {
                    s.append("Invalid office land line number");
                } else if (!resMobileValidate) {
                    s.append("Invalid residence mobile number");
                } else if (!offMobileValidate) {
                    s.append("Invalid office mobile number");
                } else if (!resEmailValidate) {
                    s.append("Invalid residence email");
                } else if (!offEmailValidate) {
                    s.append("Invalid office email");
                } else {
                    s.append("Please enter landline or mobile or email for EITHER office or residence");
                }
            }

            lbMessage.setText(s.toString());
            lbMessage.setStyle("-fx-text-alignment: center; -fx-text-fill: red");
            validation.setProperty(0);
        }
    }

    /*Navin code*/
    public void fetchFormData() {

        String donorType;

        donorType = findDonorType();

        String bloodgroup, gender, will_term = "";
        String name = tfName.getText(),
                dob = tfDOB.getText(),
                age = tfAge.getText();

        bloodgroup = cobxBloodGroup.getSelectionModel().getSelectedItem().toString();

        gender = cobxGender.getSelectionModel().getSelectedItem().toString();

        if (chbxPeriodic.isSelected()) {
            will_term = cobxPeriodicity.getSelectionModel().getSelectedItem().toString();
        }

        String spousename = tfSpouseName.getText(),
                education = tfEducation.getText(),
                occupation = tfOccupation.getText(),
                resaddress = taResidenceAddress.getText().trim(),
                respincode = tfResidencePincode.getText(),
                resstd = tfResLandlineStdCode.getText(),
                resphone = tfResLandlineNumber.getText(),
                resmob = tfResMobileNumber.getText(),
                resemail = tfResEmail.getText(),
                offaddr = taOfficeAddress.getText().trim(),
                offpincode = tfOfficePincode.getText(),
                offstd = tfOffLandlineStdCode.getText(),
                offphone = tfOffLandlineNumber.getText(),
                offmob = tfOffMobileNumber.getText(),
                offemail = tfOffEmail.getText(),
                dod = tfRegistrationDate.getText(),
                nsdod = tfSuggestedNextDonationDate.getText(),
                will = donorType,
                will_bday = tfBirthday.getText(),
                will_wed_day = tfWedding.getText(),
                will_oth_day = tfOther.getText();

        if (resphone.length() > 0) {
            resphone = resstd + "-" + resphone;
        } else {
            resphone = "";
        }
        
        if (resmob.length() > 0) {
            /* Do not add ISD prefix when mobile not entered ! */
            resmob = "+91" + resmob;            
        } else {
            resmob = "";
        }
        
        if (resemail.length() == 0) {
            resemail = "";
        }
        
        if (offphone.length() > 0) {
            offphone = offstd + "-" + offphone;
        } else {
            offphone = "";
        }
        
        if (offmob.length() > 0) {
            /* Do not add ISD prefix when mobile not entered ! */
            offmob = "+91" + offmob;            
        } else {
            offmob = "";
        }
        
        if (offemail.length() == 0) {
            offemail = "";
        }

        if (respincode.length() == 0) {
            respincode = "";
        }
        
        if (offpincode.length() == 0) {
            offpincode = "";
        }
        
        //ab[0] = donorid;
        formData[0] = name;
        formData[1] = dob;
        formData[2] = age;
        formData[3] = bloodgroup;
        formData[4] = gender;
        formData[5] = spousename;
        formData[6] = education;
        formData[7] = occupation;
        formData[8] = resaddress;
        formData[9] = respincode;
        formData[10] = resphone;
        formData[11] = resmob;
        formData[12] = resemail;
        formData[13] = offaddr;
        formData[14] = offpincode;
        formData[15] = offphone;
        formData[16] = offmob;
        formData[17] = offemail;

        formData[18] = dod;
        formData[19] = nsdod;

        formData[20] = will;
        formData[21] = will_bday;
        formData[22] = will_wed_day;
        formData[23] = will_oth_day;
        formData[24] = will_term;
        
        for (int mm = 0; mm < 25; mm++) {

            System.out.print(formData[mm] + "\t");
        }
        check = 1;
        System.out.println("check " + check);
    }

    private String findDonorType() {
        String donorType = "";
        if (bWB && bAph)//3
        {
            donorType = "WB,Aph";
        } else if (bWB && !bAph)//2
        {
            donorType = "WB";
        } else if (!bWB && bAph)//1
        {
            donorType = "Aph";
        } else {
            donorType = "Unknown";
        }
        return donorType;
    }

    /*Navin code*/
    public String[] getFormData() {
        return formData;
    }

    /*Navin code*/
    public String[] x() {
        return abca;
    }

    /*Navin code*/
    public int getCheck() {
        System.out.println("check =" + check);
        return check;
    }

    private void clearSearchFields(boolean bClearAllFields) {
    
            if (bClearAllFields) {
                tfName.setDisable(false);
                tfName.clear();
                
                tfDOB.setDisable(false);
                tfDOB.clear();
                
                tfSpouseName.setDisable(false);
                tfSpouseName.clear();
                
                tfResidencePincode.setDisable(false);
                tfResidencePincode.clear();
                
                tfResLandlineStdCode.setDisable(false);
                tfResLandlineStdCode.clear();
                
                tfResLandlineNumber.setDisable(false);
                tfResLandlineNumber.clear();
                
                tfResMobileNumber.setDisable(false);
                tfResMobileNumber.clear();
                
                tfOfficePincode.setDisable(false);
                tfOfficePincode.clear();
                
                tfOffLandlineStdCode.setDisable(false);
                tfOffLandlineStdCode.clear();
                
                tfOffLandlineNumber.setDisable(false);
                tfOffLandlineNumber.clear();
                
                tfOffMobileNumber.setDisable(false);
                tfOffMobileNumber.clear();
            } else {
                if (!tfName.isDisabled()) {
                    tfName.clear();
                }

                if (!tfDOB.isDisabled()) {
                    tfDOB.clear();
                }

                if (!tfSpouseName.isDisabled()) {
                    tfSpouseName.clear();
                }

                if (!tfResidencePincode.isDisabled()) {
                    tfResidencePincode.clear();
                }

                if (!tfResLandlineStdCode.isDisabled()) {
                    tfResLandlineStdCode.clear();
                }

                if (!tfResLandlineNumber.isDisabled()) {
                    tfResLandlineNumber.clear();
                }

                if (!tfResMobileNumber.isDisabled()) {
                    tfResMobileNumber.clear();
                }

                if (!tfOfficePincode.isDisabled()) {
                    tfOfficePincode.clear();
                }

                if (!tfOffLandlineStdCode.isDisabled()) {
                    tfOffLandlineStdCode.clear();
                }

                if (!tfOffLandlineNumber.isDisabled()) {
                    tfOffLandlineNumber.clear();
                }

                if (!tfOffMobileNumber.isDisabled()) {
                    tfOffMobileNumber.clear();
                }    
            }// ~else
    }
    
    private void clearNonSearchFields() {
        
        tfOccupation.clear();
        tfEducation.clear();
        taResidenceAddress.clear();
        
        tfResEmail.clear();
        taOfficeAddress.clear();
        
        tfOffEmail.clear();
        tfBirthday.clear();
        tfWedding.clear();
        tfOther.clear();
//        tfRegistrationDate.clear();
//        tfSuggestedNextDonationDate.clear();

        cobxGender.getSelectionModel().selectFirst();

        cobxBloodGroup.getSelectionModel().selectFirst();
        
        chbxPeriodic.setSelected(true);
        cobxPeriodicity.getSelectionModel().selectFirst();
        
        chbxBirthday.setSelected(false);
        chbxWedding.setSelected(false);
        chbxOther.setSelected(false);
        
        chbxWB.setSelected(true);
        chbxAph.setSelected(false);

        //tfRegistrationDate.setText(ProjectUtils.getCurrentDate());        
    }
    
    /*Navin code*/
    public void clearAllFields() {
        /* Clear even disabled fields */
        boolean bClearDisabledFields = true;
        
        clearSearchFields(bClearDisabledFields);
        clearNonSearchFields();
    }
    
    private String capitalizeFirstLetterOfEachWord(String strUnformattedString) {
        char cStringCharacters[] = strUnformattedString.toLowerCase().toCharArray();
        boolean bNewWord = true;
        
        for (int iIndex=0; iIndex<cStringCharacters.length; iIndex++) {
            if (bNewWord && Character.isLetter(cStringCharacters[iIndex])) {
                cStringCharacters[iIndex] = Character.toUpperCase(cStringCharacters[iIndex]);
                bNewWord = false;
            } else if (cStringCharacters[iIndex] == ' ' ||
                       cStringCharacters[iIndex] == ',' ||
                       cStringCharacters[iIndex] == '.' ||
                       cStringCharacters[iIndex] == '/' ||
                       cStringCharacters[iIndex] == '-' ||
                       cStringCharacters[iIndex] == '\'' ||
                       cStringCharacters[iIndex] == '\t' ||
                       cStringCharacters[iIndex] == '\n') {
                bNewWord = true;
            } else if (Character.isDigit(cStringCharacters[iIndex])) {
                bNewWord = false;
            }
        }
        
        return String.valueOf(cStringCharacters);
    }
    
    @FXML
    private void onReset() {
        /* Clear only enabled fields */
        boolean bClearDisabledFields = false;
        
        clearSearchFields(bClearDisabledFields);
        clearNonSearchFields();
        
        initFocus();
    }


    @FXML
    private void submitToDB(ActionEvent event) {
        fetchFormData();
        submitToConfirm(event);
    }

    private void submitToConfirm(ActionEvent event) {
        Stage stage = new Stage();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/bloodbank/Confirm.fxml"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initFocus() {
        
        /* Set focus to Name field */
        if (!tfName.isDisabled()) {
            tfName.requestFocus();
        } else if (!tfDOB.isDisabled()) {
            tfDOB.requestFocus();
        } else {
            cobxGender.requestFocus();
        }

        /* Keyboard shortcut for firing ADD button */
        btnAdd.getScene().getAccelerators().put(
            new KeyCodeCombination(KeyCode.ENTER, KeyCombination.SHORTCUT_DOWN),
            () -> {
                System.out.println("Ctrl + ENTER pressed for Add");
                btnAdd.fire();
            }
        );

        /* Keyboard shortcut for firing Reset button */
        btnReset.getScene().getAccelerators().put(
            new KeyCodeCombination(KeyCode.ESCAPE),
            () -> {
                btnReset.fire();
            }
        );

        /* Handle ENTER key press when a button is focused */
        btnAdd.getScene().getAccelerators().put(
            new KeyCodeCombination(KeyCode.ENTER), 
            () -> {
                if (btnAdd.isFocused()) {
                    btnAdd.fire();
                } else if (btnReset.isFocused()) {
                    btnReset.fire();
                }
            }
        );
    }
    
    public void setName(String strName) {
        if (!strName.trim().isEmpty()) {
            tfName.setText(strName);
            tfName.setDisable(true);
        } else {
            tfName.clear();
            tfName.setDisable(false);
        }
    }
    
    public void setDOB(String strDOB) {
        if (!strDOB.trim().isEmpty()) {
            tfDOB.setText(strDOB);
            tfDOB.setDisable(true);
        } else {
            tfDOB.clear();
            tfDOB.setDisable(false);
        }
    }
    
    public void setGender(String strGender) {
        cobxGender.getSelectionModel().select(strGender);        
    }
    
    public void setBloodGroup(String strBloodGroup) {
        cobxBloodGroup.getSelectionModel().select(strBloodGroup);        
    }
    
    public void setFatherOrSpouseName(String strFatherOrSpouseName) {
        if (!strFatherOrSpouseName.trim().isEmpty()) {
            tfSpouseName.setText(strFatherOrSpouseName);
            tfSpouseName.setDisable(true);
        } else {
            tfSpouseName.clear();
            tfSpouseName.setDisable(false);
        }
    }
    
    public void setEducation(String strEducation) {
        tfEducation.setText(strEducation);
    }
    
    public void setOccupation(String strOccupation) {
        tfOccupation.setText(strOccupation);
    }
    
    public void setResidenceAddress(String strResAddress) {
        taResidenceAddress.setText(strResAddress);
    }
    
    public void setResidencePincode(String strResPincode) {
        if (!strResPincode.trim().isEmpty()) {
            tfResidencePincode.setText(strResPincode);
            tfResidencePincode.setDisable(true);
        } else {
            tfResidencePincode.clear();
            tfResidencePincode.setDisable(false);
        }
    }
    
    public void setResidenceMobile(String strResMobile) {
        if (!strResMobile.trim().isEmpty()) {
            tfResMobileNumber.setText(strResMobile);
            tfResMobileNumber.setDisable(true);
        } else {
            tfResMobileNumber.clear();
            tfResMobileNumber.setDisable(false);
        }
    }
    
    public void setResidenceLandlineStdCode(String strResLandlineStdCode) {
        if (!strResLandlineStdCode.trim().isEmpty()) {
            tfResLandlineStdCode.setText(strResLandlineStdCode);
            tfResLandlineStdCode.setDisable(true);
        } else {
            tfResLandlineStdCode.clear();
            tfResLandlineStdCode.setDisable(false);
        }
    }
    
    public void setResidenceLandlineNumber(String strResLandlineNumber) {
        if (!strResLandlineNumber.trim().isEmpty()) {
            tfResLandlineNumber.setText(strResLandlineNumber);
            tfResLandlineNumber.setDisable(true);
        } else {
            tfResLandlineNumber.clear();
            tfResLandlineNumber.setDisable(false);            
        }
    }
    
    public void setResidenceEmail(String strResEmail) {
        tfResEmail.setText(strResEmail);
    }
    
    public void setOfficeAddress(String strOffAddress) {
        taOfficeAddress.setText(strOffAddress);
    }
    
    public void setOfficePincode(String strOffPincode) {
        if (strOffPincode.trim().length() > 0) {
            tfOfficePincode.setText(strOffPincode);
            tfOfficePincode.setDisable(true);
        } else {
            tfOfficePincode.clear();
            tfOfficePincode.setDisable(false);
        }
    }
    
    public void setOfficeMobile(String strOffMobile) {
        if (strOffMobile.trim().length() > 0) {
            tfOffMobileNumber.setText(strOffMobile);
            tfOffMobileNumber.setDisable(true);
        } else {
            tfOffMobileNumber.clear();
            tfOffMobileNumber.setDisable(false);
        }
    }
    
    public void setOfficeLandlineStdCode(String strOffLandlineStdCode) {
        if (strOffLandlineStdCode.trim().length() > 0) {
            tfOffLandlineStdCode.setText(strOffLandlineStdCode);
            tfOffLandlineStdCode.setDisable(true);
        } else {
            tfOffLandlineStdCode.clear();
            tfOffLandlineStdCode.setDisable(false);
        }
    }
    
    public void setOfficeLandlineNumber(String strOffLandlineNumber) {
        if (strOffLandlineNumber.trim().length() > 0) {
            tfOffLandlineNumber.setText(strOffLandlineNumber);
            tfOffLandlineNumber.setDisable(true);
        } else {
            tfOffLandlineNumber.clear();
            tfOffLandlineNumber.setDisable(false);            
        }
    }
    
    public void setOfficeEmail(String strOffEmail) {
        tfOffEmail.setText(strOffEmail);
    }
    
    public void setPeriodicity(String strPeriodicity) {
        if (strPeriodicity.length() > 0) {
            chbxPeriodic.setSelected(true);
            cobxPeriodicity.getSelectionModel().select(strPeriodicity);            
        } else {
            cobxPeriodicity.getSelectionModel().clearSelection();
            chbxPeriodic.setSelected(false);
        }
    }
    
    public void setWillingOnBirthday(String strBirthday) {
        if (strBirthday.length() > 0) {
            chbxBirthday.setSelected(true);
            tfBirthday.setText(strBirthday);            
        } else {
            tfBirthday.clear();
            chbxBirthday.setSelected(false);
        }
    }
    
    public void setWillingOnWeddingDay(String strWeddingDay) {
        if (strWeddingDay.length() > 0) {
            chbxWedding.setSelected(true);
            tfWedding.setText(strWeddingDay);
        } else {
            tfWedding.clear();
            chbxWedding.setSelected(false);
        }
    }
    
    public void setWillingOnOtherDay(String strOther) {
        if (strOther.length() > 0) {
            chbxOther.setSelected(true);
            tfOther.setText(strOther);
        } else {
            tfOther.clear();
            chbxOther.setSelected(false);
        }
    }
    
    public void setWholeBloodDonor(boolean bWBDonor) {
        chbxWB.setSelected(bWBDonor);
    }
    
    public void setApheresisDonor(boolean bAphDonor) {
        chbxAph.setSelected(bAphDonor);
    }
    
    public void setRegistrationDate(String strRegistrationDate) {
        tfRegistrationDate.setText(strRegistrationDate);
    }
    
    public void setSuggestedNextDonationDate(String strSuggestedNextDonationDate) {
        tfSuggestedNextDonationDate.setText(strSuggestedNextDonationDate);
    }
    
    class MyPropertyChangeListener implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {

            if (validation.getProperty() == 0) {
                btnAdd.setDisable(false);
            } else {
                btnAdd.setDisable(true);
            }
        }

    }
}
