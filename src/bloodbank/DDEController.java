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
    private TextField tfResidenceLandlineStdCode;
    @FXML
    private TextField tfResidenceLandline;
    @FXML
    private TextField tfResidenceEmail;
    @FXML
    private TextField tfResidenceMobile;
    @FXML
    private Label lResidenceDoorNoAndStreetOrRoad;
    @FXML
    private TextField tfResidenceDoorNoAndStreetOrRoad;
    @FXML
    private TextField tfResidenceBuildingName;
    @FXML
    private TextField tfResidenceArea;
    @FXML
    private Label lResidenceVillageOrTownOrCity;
    @FXML
    private TextField tfResidenceVillageOrTownOrCity;
    @FXML
    private TextField tfResidenceTaluk;
    @FXML
    private TextField tfResidenceDistrict;
    @FXML
    private Label lResidencePincode;
    @FXML
    private TextField tfResidencePincode;
    @FXML
    private TextField tfOfficeLandlineStdCode;
    @FXML
    private TextField tfOfficeLandline;
    @FXML
    private TextField tfOfficeEmail;
    @FXML
    private TextField tfOfficeMobile;
    @FXML
    private Label lOfficeDoorNoAndStreetOrRoad;
    @FXML
    private TextField tfOfficeDoorNoAndStreetOrRoad;
    @FXML
    private TextField tfOfficeBuildingName;
    @FXML
    private TextField tfOfficeArea;
    @FXML
    private Label lOfficeVillageOrTownOrCity;
    @FXML
    private TextField tfOfficeVillageOrTownOrCity;
    @FXML
    private TextField tfOfficeTaluk;
    @FXML
    private TextField tfOfficeDistrict;    
    @FXML
    private Label lOfficePincode;
    @FXML
    private TextField tfOfficePincode;
    @FXML
    private TextField tfRegistrationDate;
    @FXML
    private TextField tfSuggestedNextWBDonationDate;
    @FXML
    private TextField tfSuggestedNextAphDonationDate;
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
    private String[] formData = new String[38];
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
    private boolean bSuggestedNextAphDonationDateValidate;
    private boolean bSuggestedNextWBDonationDateValidate;
    private boolean bResidenceDoorNoAndStreetOrRoadValidate;
    private boolean bResidenceBuildingNameValidate;
    private boolean bResidenceAreaValidate;
    private boolean bResidenceVillageOrTownOrCityValidate;
    private boolean bResidenceTalukValidate;
    private boolean bResidenceDistrictValidate;
    private boolean bResidencePincodeValidate;
    private boolean bOfficeDoorNoAndStreetOrRoadValidate;
    private boolean bOfficeBuildingNameValidate;
    private boolean bOfficeAreaValidate;
    private boolean bOfficeVillageOrTownOrCityValidate;
    private boolean bOfficeTalukValidate;
    private boolean bOfficeDistrictValidate;
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
        bSuggestedNextAphDonationDateValidate = false;
        bSuggestedNextWBDonationDateValidate = false;
        bResidenceDoorNoAndStreetOrRoadValidate = false;
        bResidenceBuildingNameValidate = false;
        bResidenceAreaValidate = false;
        bResidenceVillageOrTownOrCityValidate = false;
        bResidenceTalukValidate = false;
        bResidenceDistrictValidate = false;
        bResidencePincodeValidate = false;
        bOfficeDoorNoAndStreetOrRoadValidate = false;
        bOfficeBuildingNameValidate = false;
        bOfficeAreaValidate = false;
        bOfficeVillageOrTownOrCityValidate = false;
        bOfficeTalukValidate = false;
        bOfficeDistrictValidate = false;
        bOfficePincodeValidate = false;
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

                    if (bWB) {
                        tfSuggestedNextWBDonationDate.setText(calculateNextWBDonationDate(tfRegistrationDate.getText(), newPropertyValue));
                    }
                    
                    if (bAph) {
                        tfSuggestedNextAphDonationDate.setText(calculateNextAphDonationDate(tfRegistrationDate.getText(), newPropertyValue));                        
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

        tfResidenceLandlineStdCode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(4));
        tfResidenceLandlineStdCode.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResidenceLandlineStdCode.getStyleClass();
                if (tfResidenceLandlineStdCode.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResLandlineStdCodeValidate = false;
                    callValidation();
                } else if (tfResidenceLandlineStdCode.getText().length() < 3) {
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
        
        tfResidenceLandline.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(8));
        tfResidenceLandline.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResidenceLandline.getStyleClass();
                if (tfResidenceLandline.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResLandlineNumberValidate = false;
                    callValidation();
                } else if (tfResidenceLandline.getText().length() < 6) {
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

        tfResidenceMobile.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(10));
        tfResidenceMobile.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfResidenceMobile.getStyleClass();
                if (tfResidenceMobile.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResMobileNumberValidate = false;
                    callValidation();
                } else if (tfResidenceMobile.getText().length() < 10) {
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
        
        tfOfficeLandlineStdCode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(4));
        tfOfficeLandlineStdCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOfficeLandlineStdCode.getStyleClass();
                if (tfOfficeLandlineStdCode.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffLandlineStdCodeValidate = false;
                    callValidation();
                } else if (tfOfficeLandlineStdCode.getText().length() < 3) {
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
        
        tfOfficeLandline.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(8));
        tfOfficeLandline.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOfficeLandline.getStyleClass();
                if (tfOfficeLandline.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffLandlineNumberValidate = false;
                    callValidation();
                } else if (tfOfficeLandline.getText().length() < 6) {
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

        tfOfficeMobile.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(10));
        tfOfficeMobile.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfOfficeMobile.getStyleClass();
                if (tfOfficeMobile.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffMobileNumberValidate = false;
                    callValidation();
                } else if (tfOfficeMobile.getText().length() < 10) {
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

        chbxWB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {

                bWB = new_val;
                bDonorTypeWBValidate = new_val;

                if (new_val) {
                    tfSuggestedNextWBDonationDate.setText(calculateNextWBDonationDate(tfRegistrationDate.getText(), cobxGender.getSelectionModel().getSelectedItem().toString()));
                } else {
                    tfSuggestedNextWBDonationDate.clear();
                }
                tfSuggestedNextWBDonationDate.setEditable(new_val);

                callValidation();
            }
        });
        
        chbxAph.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {

                bAph = new_val;
                bDonorTypeAphValidate = new_val;

                if (new_val) {
                    tfSuggestedNextAphDonationDate.setText(calculateNextAphDonationDate(tfRegistrationDate.getText(), cobxGender.getSelectionModel().getSelectedItem().toString()));
                } else {
                    tfSuggestedNextAphDonationDate.clear();
                }
                tfSuggestedNextAphDonationDate.setEditable(new_val);

                callValidation();
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
                        String gen = cobxGender.getSelectionModel().getSelectedItem().toString();

                        if (bWB) {
                            tfSuggestedNextWBDonationDate.setText(calculateNextWBDonationDate(dt, gen));
                        }
                        
                        if (bAph) {
                            tfSuggestedNextAphDonationDate.setText(calculateNextAphDonationDate(dt, gen));
                        }
                    }
                }
            }
        });

        tfSuggestedNextWBDonationDate.promptTextProperty().setValue("DD/MM/YYYY");
        tfSuggestedNextWBDonationDate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfSuggestedNextWBDonationDate.getStyleClass();
                if (tfSuggestedNextWBDonationDate.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bSuggestedNextWBDonationDateValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfSuggestedNextWBDonationDate.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        bSuggestedNextWBDonationDateValidate = false;
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    bSuggestedNextWBDonationDateValidate = true;
                    callValidation();
                }
            }
        });

        tfSuggestedNextAphDonationDate.promptTextProperty().setValue("DD/MM/YYYY");
        tfSuggestedNextAphDonationDate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfSuggestedNextAphDonationDate.getStyleClass();
                if (tfSuggestedNextAphDonationDate.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bSuggestedNextAphDonationDateValidate = false;
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfSuggestedNextAphDonationDate.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        bSuggestedNextAphDonationDateValidate = false;
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    bSuggestedNextAphDonationDateValidate = true;
                    callValidation();
                }
            }
        });
        
        tfResidenceDoorNoAndStreetOrRoad.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.length_Validation(100));

        tfResidenceDoorNoAndStreetOrRoad.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceDoorNoAndStreetOrRoad.getText().trim().length() > 0) {
                        tfResidenceDoorNoAndStreetOrRoad.setText(capitalizeFirstLetterOfEachWord(tfResidenceDoorNoAndStreetOrRoad.getText().trim()));
                    }
                }
            }
        });

        tfResidenceDoorNoAndStreetOrRoad.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceDoorNoAndStreetOrRoad.getText().trim().length() == 0) {
                    if (bResidenceDoorNoAndStreetOrRoadValidate) {
                        bResidenceDoorNoAndStreetOrRoadValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bResidenceDoorNoAndStreetOrRoadValidate) {
                        bResidenceDoorNoAndStreetOrRoadValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfResidenceBuildingName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.length_Validation(50));

        tfResidenceBuildingName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceBuildingName.getText().trim().length() > 0) {
                        tfResidenceBuildingName.setText(capitalizeFirstLetterOfEachWord(tfResidenceBuildingName.getText().trim()));
                    }
                }
            }
        });

        tfResidenceBuildingName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceBuildingName.getText().trim().length() == 0) {
                    if (bResidenceBuildingNameValidate) {
                        bResidenceBuildingNameValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bResidenceBuildingNameValidate) {
                        bResidenceBuildingNameValidate = true;
                        callValidation();
                    }
                }
            }
        });

        tfResidenceArea.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfResidenceArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceArea.getText().trim().length() > 0) {
                        tfResidenceArea.setText(capitalizeFirstLetterOfEachWord(tfResidenceArea.getText().trim()));
                    }
                }
            }
        });

        tfResidenceArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceArea.getText().trim().length() == 0) {
                    bResidenceAreaValidate = false;
                    callValidation();
                } else {
                    if (!bResidenceAreaValidate) {
                        bResidenceAreaValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfResidenceVillageOrTownOrCity.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfResidenceVillageOrTownOrCity.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceVillageOrTownOrCity.getText().trim().length() > 0) {
                        tfResidenceVillageOrTownOrCity.setText(capitalizeFirstLetterOfEachWord(tfResidenceVillageOrTownOrCity.getText().trim()));
                    }
                }
            }
        });

        tfResidenceVillageOrTownOrCity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceVillageOrTownOrCity.getText().trim().length() == 0) {
                    bResidenceVillageOrTownOrCityValidate = false;
                    callValidation();
                } else {
                    if (!bResidenceVillageOrTownOrCityValidate) {
                        bResidenceVillageOrTownOrCityValidate = true;
                        callValidation();                        
                    }
                }
            }
        });
        
        tfResidenceTaluk.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfResidenceTaluk.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceTaluk.getText().trim().length() > 0) {
                        tfResidenceTaluk.setText(capitalizeFirstLetterOfEachWord(tfResidenceTaluk.getText().trim()));
                    }
                }
            }
        });

        tfResidenceTaluk.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceTaluk.getText().trim().length() == 0) {
                    bResidenceTalukValidate = false;
                    callValidation();
                } else {
                    if (!bResidenceTalukValidate) {
                        bResidenceTalukValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfResidenceDistrict.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfResidenceDistrict.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceDistrict.getText().trim().length() > 0) {
                        tfResidenceDistrict.setText(capitalizeFirstLetterOfEachWord(tfResidenceDistrict.getText().trim()));
                    }
                }
            }
        });

        tfResidenceDistrict.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfResidenceDistrict.getText().trim().length() == 0) {
                    bResidenceDistrictValidate = false;
                    callValidation();
                } else {
                    if (!bResidenceDistrictValidate) {
                        bResidenceDistrictValidate = true;
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
        
        tfResidenceEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfResidenceEmail.getText().trim().length() > 0) {
                        tfResidenceEmail.getText().trim().toLowerCase();
                    }
                }
            }
        });

        tfResidenceEmail.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfResidenceEmail.getStyleClass();
                if (tfResidenceEmail.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bResEmailValidate = false;
                    callValidation();
                } else if (ProjectUtils.emailValidation(tfResidenceEmail.getText())) {
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
        
        tfOfficeDoorNoAndStreetOrRoad.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.length_Validation(100));

        tfOfficeDoorNoAndStreetOrRoad.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeDoorNoAndStreetOrRoad.getText().trim().length() > 0) {
                        tfOfficeDoorNoAndStreetOrRoad.setText(capitalizeFirstLetterOfEachWord(tfOfficeDoorNoAndStreetOrRoad.getText().trim()));
                    }
                }
            }
        });

        tfOfficeDoorNoAndStreetOrRoad.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfOfficeDoorNoAndStreetOrRoad.getStyleClass();
                if (tfOfficeDoorNoAndStreetOrRoad.getText().trim().length() == 0) {
                    if (bOfficeDoorNoAndStreetOrRoadValidate) {
                        bOfficeDoorNoAndStreetOrRoadValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bOfficeDoorNoAndStreetOrRoadValidate) {
                        bOfficeDoorNoAndStreetOrRoadValidate = true;
                        callValidation();
                    }
                }
            }

        });

        tfOfficeBuildingName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.length_Validation(50));

        tfOfficeBuildingName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeBuildingName.getText().trim().length() > 0) {
                        tfOfficeBuildingName.setText(capitalizeFirstLetterOfEachWord(tfOfficeBuildingName.getText().trim()));
                    }
                }
            }
        });

        tfOfficeBuildingName.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfOfficeBuildingName.getText().trim().length() == 0) {
                    bOfficeBuildingNameValidate = false;
                    callValidation();
                } else {
                    if (!bOfficeBuildingNameValidate) {
                        bOfficeBuildingNameValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfOfficeArea.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfOfficeArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeArea.getText().trim().length() > 0) {
                        tfOfficeArea.setText(capitalizeFirstLetterOfEachWord(tfOfficeArea.getText().trim()));
                    }
                }
            }
        });

        tfOfficeArea.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfOfficeArea.getText().trim().length() == 0) {
                    bOfficeAreaValidate = false;
                    callValidation();
                } else {
                    if (!bOfficeAreaValidate) {
                        bOfficeAreaValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfOfficeVillageOrTownOrCity.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfOfficeVillageOrTownOrCity.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeVillageOrTownOrCity.getText().trim().length() > 0) {
                        tfOfficeVillageOrTownOrCity.setText(capitalizeFirstLetterOfEachWord(tfOfficeVillageOrTownOrCity.getText().trim()));
                    }
                }
            }
        });

        tfOfficeVillageOrTownOrCity.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfOfficeVillageOrTownOrCity.getText().trim().length() == 0) {
                    bOfficeVillageOrTownOrCityValidate = false;
                    callValidation();
                } else {
                    if (!bOfficeVillageOrTownOrCityValidate) {
                        bOfficeVillageOrTownOrCityValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfOfficeTaluk.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfOfficeTaluk.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeTaluk.getText().trim().length() > 0) {
                        tfOfficeTaluk.setText(capitalizeFirstLetterOfEachWord(tfOfficeTaluk.getText().trim()));
                    }
                }
            }
        });

        tfOfficeTaluk.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfOfficeTaluk.getText().trim().length() == 0) {
                    bOfficeTalukValidate = false;
                    callValidation();
                } else {
                    if (!bOfficeTalukValidate) {
                        bOfficeTalukValidate = true;
                        callValidation();                        
                    }
                }
            }
        });

        tfOfficeDistrict.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));

        tfOfficeDistrict.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeDistrict.getText().trim().length() > 0) {
                        tfOfficeDistrict.setText(capitalizeFirstLetterOfEachWord(tfOfficeDistrict.getText().trim()));
                    }
                }
            }
        });

        tfOfficeDistrict.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (tfOfficeDistrict.getText().trim().length() == 0) {
                    bOfficeDistrictValidate = false;
                    callValidation();
                } else {
                    if (!bOfficeDistrictValidate) {
                        bResidenceDistrictValidate = true;
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

        tfOfficeEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if (tfOfficeEmail.getText().trim().length() > 0) {
                        tfOfficeEmail.getText().trim().toLowerCase();
                    }
                }
            }
        });

        tfOfficeEmail.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfOfficeEmail.getStyleClass();
                if (tfOfficeEmail.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bOffEmailValidate = false;
                    callValidation();
                } else if (ProjectUtils.emailValidation(tfOfficeEmail.getText())) {
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
         
        cobxBloodGroup.getSelectionModel().selectFirst();
        
        chbxWB.setSelected(true);
        
        tfRegistrationDate.setText(ProjectUtils.getCurrentDate());
    }

    private String calculateNextWBDonationDate(String strRegistrationDate, String strGender) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            if (!strRegistrationDate.isEmpty()) {
                c.setTime(sdf.parse(strRegistrationDate));
            }
        } catch (ParseException ex) {
            Logger.getLogger(DDEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (strGender.equals("Male")) {
            c.add(Calendar.MONTH, 3);  // number of days to add
        } else {
            c.add(Calendar.MONTH, 4);
        }

        return sdf.format(c.getTime());  // dt is now the new date
    }
    
    private String calculateNextAphDonationDate(String strRegistrationDate, String strGender) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            if (!strRegistrationDate.isEmpty()) {
                c.setTime(sdf.parse(strRegistrationDate));
            }
        } catch (ParseException ex) {
            Logger.getLogger(DDEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (strGender.equals("Male")) {
            c.add(Calendar.MONTH, 3);  // number of days to add
        } else {
            c.add(Calendar.MONTH, 4);
        }

        return sdf.format(c.getTime());  // dt is now the new date
    }
    
    private void callValidation() {
        boolean dobValidate = !ProjectUtils.dateValidation(tfDOB.getText().trim());           
        int iDoorNoAndStreetOrRoadLength, iVillageOrTownOrCityLength, iPincodeLength;
        int iLandlineLength, iLandlineStdCodeLength;
        int iMobileLength;
        
        boolean resAddrValidate = false;
        iDoorNoAndStreetOrRoadLength = tfResidenceDoorNoAndStreetOrRoad.getText().trim().length();
        iVillageOrTownOrCityLength = tfResidenceVillageOrTownOrCity.getText().trim().length();
        iPincodeLength = tfResidencePincode.getText().length();

        if (iDoorNoAndStreetOrRoadLength == 0 && iVillageOrTownOrCityLength == 0 && iPincodeLength == 0) {
            resAddrValidate = true;

            lResidenceDoorNoAndStreetOrRoad.setText(lResidenceDoorNoAndStreetOrRoad.getText().replace('*', '\b'));
            lResidenceVillageOrTownOrCity.setText(lResidenceVillageOrTownOrCity.getText().replace('*', '\b'));
            lResidencePincode.setText(lResidencePincode.getText().replace('*', '\b'));
        } else if (iDoorNoAndStreetOrRoadLength > 0 && iVillageOrTownOrCityLength > 0 && iPincodeLength == 6) {
            resAddrValidate = true;
        } else {
            /* Dynamically mark the mandatory fields with "*"*/
            
            if (!lResidenceDoorNoAndStreetOrRoad.getText().endsWith("*")) {
                lResidenceDoorNoAndStreetOrRoad.setText(lResidenceDoorNoAndStreetOrRoad.getText().concat("*"));
            }
            
            if (!lResidenceVillageOrTownOrCity.getText().endsWith("*")) {
                lResidenceVillageOrTownOrCity.setText(lResidenceVillageOrTownOrCity.getText().concat("*"));
            }
            
            if (!lResidencePincode.getText().endsWith("*")) {
                lResidencePincode.setText(lResidencePincode.getText().concat("*"));
            }            
        }
        
        
        boolean offAddrValidate = false;
        iDoorNoAndStreetOrRoadLength = tfOfficeDoorNoAndStreetOrRoad.getText().trim().length();
        iVillageOrTownOrCityLength = tfOfficeVillageOrTownOrCity.getText().trim().length();
        iPincodeLength = tfOfficePincode.getText().length();
        
        if (iDoorNoAndStreetOrRoadLength == 0 && iVillageOrTownOrCityLength == 0 && iPincodeLength == 0) {
            offAddrValidate = true;

            lOfficeDoorNoAndStreetOrRoad.setText(lOfficeDoorNoAndStreetOrRoad.getText().replace('*', '\b'));
            lOfficeVillageOrTownOrCity.setText(lOfficeVillageOrTownOrCity.getText().replace('*', '\b'));
            lOfficePincode.setText(lOfficePincode.getText().replace('*', '\b'));            
        } else if (iDoorNoAndStreetOrRoadLength > 0 && iVillageOrTownOrCityLength > 0 && iPincodeLength == 6) {
            offAddrValidate = true;
        } else {
            /* Dynamically mark the mandatory fields with "*"*/

            if (!lOfficeDoorNoAndStreetOrRoad.getText().endsWith("*")) {
                lOfficeDoorNoAndStreetOrRoad.setText(lOfficeDoorNoAndStreetOrRoad.getText().concat("*"));
            }
            
            if (!lOfficeVillageOrTownOrCity.getText().endsWith("*")) {
                lOfficeVillageOrTownOrCity.setText(lOfficeVillageOrTownOrCity.getText().concat("*"));
            }
            
            if (!lOfficePincode.getText().endsWith("*")) {
                lOfficePincode.setText(lOfficePincode.getText().concat("*"));
            }            
        }

        boolean resPhoneValidate = false;
        iLandlineLength = tfResidenceLandline.getText().trim().length();
        iLandlineStdCodeLength = tfResidenceLandlineStdCode.getText().trim().length();
        
        if ((iLandlineLength == 0) && (iLandlineStdCodeLength == 0)) {
            resPhoneValidate = true;
        } else if ((iLandlineLength >= 6) && (iLandlineStdCodeLength >= 3)) {
            resPhoneValidate = true;
        }

        boolean offPhoneValidate = false;
        iLandlineLength = tfOfficeLandline.getText().trim().length();
        iLandlineStdCodeLength = tfOfficeLandlineStdCode.getText().trim().length();
        
        if ((iLandlineLength == 0) && (iLandlineStdCodeLength == 0)) {
            offPhoneValidate = true;
        } else if ((iLandlineLength >= 6) && (iLandlineStdCodeLength >= 3)) {
            offPhoneValidate = true;            
        }
        
        boolean resMobileValidate = false;
        iMobileLength = tfResidenceMobile.getText().length();
        
        if (iMobileLength == 0 || iMobileLength == 10) {
            resMobileValidate = true;
        }
        
        boolean offMobileValidate = false;
        iMobileLength = tfOfficeMobile.getText().length();
        
        if (iMobileLength == 0 || iMobileLength == 10) {
            offMobileValidate = true;
        }
        
        boolean resEmailValidate = false;
        if (tfResidenceEmail.getText().trim().length() == 0 || bResEmailValidate) {
            resEmailValidate = true;
        }
        
        boolean offEmailValidate = false;
        if (tfOfficeEmail.getText().trim().length() == 0 || bOffEmailValidate) {
            offEmailValidate = true;
        }
        
        ObservableList<String> styleClass = chbxAph.getStyleClass();
        ObservableList<String> styleClass1 = chbxWB.getStyleClass();

        if (bDonorTypeWBValidate || bDonorTypeAphValidate) {
            bDonorTypeValidate = true;
            styleClass1.removeAll(Collections.singleton("error"));
        } else {
            bDonorTypeValidate = false;
            styleClass1.add("error");
        }

        if (bSuggestedNextWBDonationDateValidate || bSuggestedNextAphDonationDateValidate) {
            bSuggestedNextDonationDateValidate = true;
            styleClass1.removeAll(Collections.singleton("error"));
        } else {
            bDonorTypeValidate = false;
            styleClass1.add("error");            
        }
        
        boolean mandatoryFieldsValidate = bNameValidate && bDOBValidate && bBloodGroupValidate && bSpouseNameValidate && bEducationValidate && bOccupationValidate && bDonorTypeValidate && bRegistrationDateValidate && bSuggestedNextDonationDateValidate;
        boolean donorContactAddressValidate = ((bResidenceDoorNoAndStreetOrRoadValidate && bResidenceVillageOrTownOrCityValidate && bResidencePincodeValidate) || (bOfficeDoorNoAndStreetOrRoadValidate && bOfficeVillageOrTownOrCityValidate && bOfficePincodeValidate));
        boolean bDonorContactAddressIncompleteValidate = resAddrValidate && offAddrValidate;
        boolean donorContactsValidate = (bResLandlineStdCodeValidate && bResLandlineNumberValidate) || bResMobileNumberValidate || bResEmailValidate || (bOffLandlineStdCodeValidate && bOffLandlineNumberValidate) || bOffMobileNumberValidate || bOffEmailValidate;
        boolean bDonorContactsIncompleteValidate = resPhoneValidate && offPhoneValidate && resMobileValidate && offMobileValidate && resEmailValidate && offEmailValidate;

        StringBuilder s = new StringBuilder("");
        if (mandatoryFieldsValidate && donorContactAddressValidate && bDonorContactAddressIncompleteValidate && donorContactsValidate && bDonorContactsIncompleteValidate) {
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
            } else if (!donorContactAddressValidate || !bDonorContactAddressIncompleteValidate) {
                if (!resAddrValidate) {
                    s.append("Mandatory residence address fields(*) not filled");
                } else if (!offAddrValidate) {
                    s.append("Mandatory office address fields(*) not filled");
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
        int iIndex;

        donorType = findDonorType();

        String bloodgroup, gender, will_term = "";
        String name = tfName.getText(),
                dob = tfDOB.getText(),
                age = tfAge.getText();

        bloodgroup = cobxBloodGroup.getSelectionModel().getSelectedItem().toString();

        gender = cobxGender.getSelectionModel().getSelectedItem().toString();

        String spousename = tfSpouseName.getText(),
                education = tfEducation.getText(),
                occupation = tfOccupation.getText(),
                resdoornoandstreetorroad = tfResidenceDoorNoAndStreetOrRoad.getText().trim(),
                resbuildingname = tfResidenceBuildingName.getText().trim(),
                /*
                 * Till we remove the field from the table
                 * we need to pass default value for this
                 * field, an empty string. Otherwise, DB insert
                 * would fail.
                 */
                resaddress = "",
                resarea = tfResidenceArea.getText().trim(),
                resvillageortownorcity = tfResidenceVillageOrTownOrCity.getText().trim(),
                restaluk = tfResidenceTaluk.getText().trim(),
                resdistrict = tfResidenceDistrict.getText().trim(),
                respincode = tfResidencePincode.getText(),
                resstd = tfResidenceLandlineStdCode.getText(),
                resphone = tfResidenceLandline.getText(),
                resmob = tfResidenceMobile.getText(),
                resemail = tfResidenceEmail.getText(),
                offdoornoandstreetorroad = tfOfficeDoorNoAndStreetOrRoad.getText().trim(),
                offbuildingname = tfOfficeBuildingName.getText().trim(),
                /*
                 * Till we remove the field from the table
                 * we need to pass default value for this
                 * field, an empty string. Otherwise, DB insert
                 * would fail.
                 */
                offaddr = "",
                offarea = tfOfficeArea.getText().trim(),
                offvillageortownorcity = tfOfficeVillageOrTownOrCity.getText().trim(),
                offtaluk = tfOfficeTaluk.getText().trim(),
                offdistrict = tfOfficeDistrict.getText().trim(),
                offpincode = tfOfficePincode.getText(),
                offstd = tfOfficeLandlineStdCode.getText(),
                offphone = tfOfficeLandline.getText(),
                offmob = tfOfficeMobile.getText(),
                offemail = tfOfficeEmail.getText(),
                dod = tfRegistrationDate.getText(),
                nsdod = tfSuggestedNextWBDonationDate.getText(),
                nsdodaph = tfSuggestedNextAphDonationDate.getText(),
                will = donorType,
                will_bday = "",
                will_wed_day = "",
                will_oth_day = "";

        if (resphone.length() > 0) {
            resphone = resstd + "-" + resphone;
        }
        
        if (resmob.length() > 0) {
            /* Do not add ISD prefix when mobile not entered ! */
            resmob = "+91" + resmob;            
        }
        
        if (offphone.length() > 0) {
            offphone = offstd + "-" + offphone;
        }
        
        if (offmob.length() > 0) {
            /* Do not add ISD prefix when mobile not entered ! */
            offmob = "+91" + offmob;            
        }
        
        iIndex = 0;
        //ab[0] = donorid;
        formData[iIndex++] = name;
        formData[iIndex++] = dob;
        formData[iIndex++] = age;
        formData[iIndex++] = bloodgroup;
        formData[iIndex++] = gender;
        formData[iIndex++] = spousename;
        formData[iIndex++] = education;
        formData[iIndex++] = occupation;
        formData[iIndex++] = resaddress;
        formData[iIndex++] = resdoornoandstreetorroad;
        formData[iIndex++] = resbuildingname;
        formData[iIndex++] = resarea;
        formData[iIndex++] = resvillageortownorcity;
        formData[iIndex++] = restaluk;
        formData[iIndex++] = resdistrict;
        formData[iIndex++] = respincode;
        formData[iIndex++] = resphone;
        formData[iIndex++] = resmob;
        formData[iIndex++] = resemail;
        formData[iIndex++] = offaddr;
        formData[iIndex++] = offdoornoandstreetorroad;
        formData[iIndex++] = offbuildingname;
        formData[iIndex++] = offarea;
        formData[iIndex++] = offvillageortownorcity;
        formData[iIndex++] = offtaluk;
        formData[iIndex++] = offdistrict;
        formData[iIndex++] = offpincode;
        formData[iIndex++] = offphone;
        formData[iIndex++] = offmob;
        formData[iIndex++] = offemail;

        formData[iIndex++] = dod;
        formData[iIndex++] = nsdod;
        formData[iIndex++] = nsdodaph;

        formData[iIndex++] = will;
        formData[iIndex++] = will_bday;
        formData[iIndex++] = will_wed_day;
        formData[iIndex++] = will_oth_day;
        formData[iIndex++] = will_term;
        
        for (int mm = 0; mm < formData.length; mm++) {

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
                
                tfResidenceLandlineStdCode.setDisable(false);
                tfResidenceLandlineStdCode.clear();
                
                tfResidenceLandline.setDisable(false);
                tfResidenceLandline.clear();
                
                tfResidenceMobile.setDisable(false);
                tfResidenceMobile.clear();
                
                tfOfficePincode.setDisable(false);
                tfOfficePincode.clear();
                
                tfOfficeLandlineStdCode.setDisable(false);
                tfOfficeLandlineStdCode.clear();
                
                tfOfficeLandline.setDisable(false);
                tfOfficeLandline.clear();
                
                tfOfficeMobile.setDisable(false);
                tfOfficeMobile.clear();
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

                if (!tfResidenceLandlineStdCode.isDisabled()) {
                    tfResidenceLandlineStdCode.clear();
                }

                if (!tfResidenceLandline.isDisabled()) {
                    tfResidenceLandline.clear();
                }

                if (!tfResidenceMobile.isDisabled()) {
                    tfResidenceMobile.clear();
                }

                if (!tfOfficePincode.isDisabled()) {
                    tfOfficePincode.clear();
                }

                if (!tfOfficeLandlineStdCode.isDisabled()) {
                    tfOfficeLandlineStdCode.clear();
                }

                if (!tfOfficeLandline.isDisabled()) {
                    tfOfficeLandline.clear();
                }

                if (!tfOfficeMobile.isDisabled()) {
                    tfOfficeMobile.clear();
                }    
            }// ~else
    }
    
    private void clearNonSearchFields() {
        
        tfOccupation.clear();
        tfEducation.clear();
        
        tfResidenceDoorNoAndStreetOrRoad.clear();
        tfResidenceBuildingName.clear();
        tfResidenceArea.clear();
        tfResidenceVillageOrTownOrCity.clear();
        tfResidenceTaluk.clear();
        tfResidenceDistrict.clear();
        tfResidenceEmail.clear();
        
        tfOfficeDoorNoAndStreetOrRoad.clear();
        tfOfficeBuildingName.clear();
        tfOfficeArea.clear();
        tfOfficeVillageOrTownOrCity.clear();
        tfOfficeTaluk.clear();
        tfOfficeDistrict.clear();
        tfOfficeEmail.clear();
        
//        tfRegistrationDate.clear();
//        tfSuggestedNextWBDonationDate.clear();

        cobxGender.getSelectionModel().selectFirst();

        cobxBloodGroup.getSelectionModel().selectFirst();
        
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
    
    public void setResidenceDoorNoAndStreetOrRoad(String strResDoorNoAndStreetOrRoad) {
        tfResidenceDoorNoAndStreetOrRoad.setText(strResDoorNoAndStreetOrRoad);
    }
    
    public void setResidenceBuildingName(String strResBuildingName) {
        tfResidenceBuildingName.setText(strResBuildingName);
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
            tfResidenceMobile.setText(strResMobile);
            tfResidenceMobile.setDisable(true);
        } else {
            tfResidenceMobile.clear();
            tfResidenceMobile.setDisable(false);
        }
    }
    
    public void setResidenceLandlineStdCode(String strResLandlineStdCode) {
        if (!strResLandlineStdCode.trim().isEmpty()) {
            tfResidenceLandlineStdCode.setText(strResLandlineStdCode);
            tfResidenceLandlineStdCode.setDisable(true);
        } else {
            tfResidenceLandlineStdCode.clear();
            tfResidenceLandlineStdCode.setDisable(false);
        }
    }
    
    public void setResidenceLandlineNumber(String strResLandlineNumber) {
        if (!strResLandlineNumber.trim().isEmpty()) {
            tfResidenceLandline.setText(strResLandlineNumber);
            tfResidenceLandline.setDisable(true);
        } else {
            tfResidenceLandline.clear();
            tfResidenceLandline.setDisable(false);            
        }
    }
    
    public void setResidenceEmail(String strResEmail) {
        tfResidenceEmail.setText(strResEmail);
    }
    
    public void setOfficeDoorNoAndStreetOrRoad(String strOffDoorNoAndStreetOrRoad) {
        tfOfficeDoorNoAndStreetOrRoad.setText(strOffDoorNoAndStreetOrRoad);
    }
    
    public void setOfficeBuildingName(String strOffBuildingName) {
        tfOfficeBuildingName.setText(strOffBuildingName);
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
            tfOfficeMobile.setText(strOffMobile);
            tfOfficeMobile.setDisable(true);
        } else {
            tfOfficeMobile.clear();
            tfOfficeMobile.setDisable(false);
        }
    }
    
    public void setOfficeLandlineStdCode(String strOffLandlineStdCode) {
        if (strOffLandlineStdCode.trim().length() > 0) {
            tfOfficeLandlineStdCode.setText(strOffLandlineStdCode);
            tfOfficeLandlineStdCode.setDisable(true);
        } else {
            tfOfficeLandlineStdCode.clear();
            tfOfficeLandlineStdCode.setDisable(false);
        }
    }
    
    public void setOfficeLandlineNumber(String strOffLandlineNumber) {
        if (strOffLandlineNumber.trim().length() > 0) {
            tfOfficeLandline.setText(strOffLandlineNumber);
            tfOfficeLandline.setDisable(true);
        } else {
            tfOfficeLandline.clear();
            tfOfficeLandline.setDisable(false);            
        }
    }
    
    public void setOfficeEmail(String strOffEmail) {
        tfOfficeEmail.setText(strOffEmail);
    }
    
    public void setPeriodicity(String strPeriodicity) {
    }
    
    public void setWillingOnBirthday(String strBirthday) {
    }
    
    public void setWillingOnWeddingDay(String strWeddingDay) {
    }
    
    public void setWillingOnOtherDay(String strOther) {
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
        tfSuggestedNextWBDonationDate.setText(strSuggestedNextDonationDate);
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
