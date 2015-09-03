/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import database.DBApi;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rajagopal
 */
public class DSEController implements Initializable {

    static DSEController objDSEController;
    
    @FXML
    private TextField tfName;
    
    @FXML
    private TextField tfFatherOrSpouseName;
    
    @FXML
    private TextField tfDOB;
    
    @FXML
    private TextField tfMobileNumber;
    
    @FXML
    private ComboBox cobxMobileType;
    
    @FXML
    private TextField tfLandlineStdCode;
    
    @FXML
    private TextField tfLandlineNumber;
    
    @FXML
    private ComboBox cobxLandlineType;

    @FXML
    private TextField tfPincode;
    
    @FXML
    private ComboBox cobxPincodeType;
    
    @FXML
    private TextField tfDonorId;
    
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnReset;
    
    @FXML
    private Label lMessage;
    
    @FXML
    private TableView tvSearchResultsTable;
    
    private boolean bDonorIdValidate;
    
    private boolean bNameValidate;
    private boolean bFatherOrSpouseNameValidate;
    private boolean bDOBValidate;
    private boolean bMobileNumberValidate;
    private boolean bLandlineStdCodeValidate;
    private boolean bLandlineNumberValidate;
    private boolean bPincodeValidate;
    
    private String[] searchParameters = new String[7];

    DBApi objDBApi;

    private ObservableList<ObservableList> olData;
    ObservableList<String> olRow;

    public DSEController() {
        objDSEController = this;
    }
    
    public static DSEController getInstance() {
        return objDSEController;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        objDBApi = HomeController.getDBApi();
        
        olData = FXCollections.observableArrayList();
        initializeFields();

        bDonorIdValidate = false;
        
        bNameValidate = false;
        bFatherOrSpouseNameValidate = false;
        bDOBValidate = false;
        bMobileNumberValidate = false;
        bLandlineStdCodeValidate = false;
        bLandlineNumberValidate = false;
        bPincodeValidate = false;
        
        callValidation();

        initFocus();
    }    
    
    private void initializeFields() {
        
        tvSearchResultsTable.setPlaceholder(new Label(""));
        
        tfName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));
        
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
         
        tfFatherOrSpouseName.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.char_Validation(100));
        
        tfFatherOrSpouseName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfFatherOrSpouseName.getStyleClass();
                if (tfFatherOrSpouseName.getText().trim().length() == 0) {
                    if (bFatherOrSpouseNameValidate) {
                        bFatherOrSpouseNameValidate = false;
                        callValidation();
                    }
                } else {
                    if (!bFatherOrSpouseNameValidate) {
                        bFatherOrSpouseNameValidate = true;
                        callValidation();
                    }
                }
            }
        });
        
        tfDOB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfDOB.getStyleClass();
                
                if (tfDOB.getText().length() == 0) {
                    bDOBValidate = false; 
                    styleClass.removeAll(Collections.singleton("error"));
                    callValidation();
                } else if (ProjectUtils.dateValidation(tfDOB.getText())) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bDOBValidate) {
                            bDOBValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));
                    if (!bDOBValidate) {
                        bDOBValidate = true;
                        callValidation();
                    }                        
                }
            }
        });
        
        tfMobileNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(10));
        tfMobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfMobileNumber.getStyleClass();
                if (tfMobileNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bMobileNumberValidate = false;
                    callValidation();
                } else if (tfMobileNumber.getText().length() < 10) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bMobileNumberValidate) {
                            bMobileNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bMobileNumberValidate) {
                        bMobileNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });

        cobxMobileType.getItems().addAll("Residence", "Office");
        cobxMobileType.getSelectionModel().selectFirst();
                
        tfLandlineStdCode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(4));
        tfLandlineStdCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfLandlineStdCode.getStyleClass();
                if (tfLandlineStdCode.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bLandlineStdCodeValidate = false;
                    callValidation();
                } else if (tfLandlineStdCode.getText().length() < 3) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bLandlineStdCodeValidate) {
                            bLandlineStdCodeValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bLandlineStdCodeValidate) {
                        bLandlineStdCodeValidate = true;
                        callValidation();
                    }

                }
            }
        });
        
        tfLandlineNumber.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(8));
        tfLandlineNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String sOldValue, String sNewValue) {
                ObservableList<String> styleClass = tfLandlineNumber.getStyleClass();
                if (tfLandlineNumber.getText().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bLandlineNumberValidate = false;
                    callValidation();
                } else if (tfLandlineNumber.getText().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bLandlineNumberValidate) {
                            bLandlineNumberValidate = false;
                        }
                        callValidation();
                    }
                } else {
                    styleClass.removeAll(Collections.singleton("error"));

                    if (!bLandlineNumberValidate) {
                        bLandlineNumberValidate = true;
                        callValidation();
                    }
                }
            }
        });

        cobxLandlineType.getItems().addAll("Residence", "Office");
        cobxLandlineType.getSelectionModel().selectFirst();

        tfPincode.addEventFilter(KeyEvent.KEY_TYPED, ProjectUtils.numeric_Validation(6));
        tfPincode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ObservableList<String> styleClass = tfPincode.getStyleClass();
                if (tfPincode.getText().trim().length() == 0) {
                    styleClass.removeAll(Collections.singleton("error"));
                    bPincodeValidate = false;
                    callValidation();
                } else if (tfPincode.getText().trim().length() < 6) {
                    if (!styleClass.contains("error")) {
                        styleClass.add("error");
                        if (bPincodeValidate) {
                            bPincodeValidate = false;
                        }
                        callValidation();                            
                    }        
                } else {
                    styleClass.removeAll(Collections.singleton("error"));                    
                    
                    if (!bPincodeValidate) {
                        bPincodeValidate = true;
                        callValidation();                        
                    }
                }
            }
        });        

        cobxPincodeType.getItems().addAll("Residence", "Office");
        cobxPincodeType.getSelectionModel().selectFirst();
    }
    
    public void initFocus() {
        
        clearAllFields();
        
        Platform.runLater(() -> {
            /* Set focus to Mobile Number field */
            tfMobileNumber.requestFocus();

            /* Keyboard shortcut for firing Search button */
            btnSearch.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER, KeyCombination.SHORTCUT_DOWN), 
                () -> {
                    btnSearch.fire();                                                
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
            btnSearch.getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER), 
                () -> {
                    if (btnSearch.isFocused()) {
                        btnSearch.fire();
                    } else if (btnReset.isFocused()) {
                        btnReset.fire();
                    }
                }
            );
        });
    }

    private void fetchSearchParameters() {
        
        if (tfDonorId.getText().trim().length() > 0) {
            searchParameters[0] = tfDonorId.getText().trim();
        } else {
            searchParameters[0] = null;
        }

        if (tfMobileNumber.getText().trim().length() > 0) {
            searchParameters[1] = "+91" + tfMobileNumber.getText().trim();
        } else {
            searchParameters[1] = null;
        }
        
        if (tfDOB.getText().trim().length() > 0) {
            searchParameters[2] = tfDOB.getText().trim();
        } else {
            searchParameters[2] = null;
        }
        
        if (tfLandlineNumber.getText().trim().length() > 0) {
            searchParameters[3] = tfLandlineStdCode.getText() + "-" + tfLandlineNumber.getText();
        } else {
            searchParameters[3] = null;
        }
        
        if (tfPincode.getText().length() > 0) {
            searchParameters[4] = tfPincode.getText();
        } else {
            searchParameters[4] = null;
        }
        
        if (tfName.getText().trim().length() > 0) {
            searchParameters[5] = tfName.getText().trim();
        } else {
            searchParameters[5] = null;
        }
        
        if (tfFatherOrSpouseName.getText().trim().length() > 0) {
            searchParameters[6] = tfFatherOrSpouseName.getText().trim();
        } else {
            searchParameters[6] = null;
        }
    }
    
    @FXML
    private void searchInDB(ActionEvent event) {

        int iRowCount, iColumnCount;

        olData.clear();
        olData.removeAll(olData);
        tvSearchResultsTable.getColumns().clear();

        fetchSearchParameters();
        try {
            objDBApi.dseDonorSearch(searchParameters[0],
                                    searchParameters[1],
                                    searchParameters[2],
                                    searchParameters[3],
                                    searchParameters[4],
                                    searchParameters[5],
                                    searchParameters[6]);
            
            iRowCount = objDBApi.dseRowCount();
            iColumnCount = objDBApi.dseColumnCount();
            
            if (iRowCount > 0) {
                populateSearchResultsTable(iRowCount, iColumnCount);
            } else {
                Stage stage = new Stage();
                Parent parent;

                System.out.println("Empty");
                
                try {
                    parent = FXMLLoader.load(getClass().getResource("/bloodbank/AddDonor.fxml"));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(btnSearch.getScene().getWindow());
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception objException) {
            System.out.println(Arrays.toString(objException.getStackTrace()));
        }
        
    }
    
    public void populateSearchResultsTable(int iRowCount, int iColumnCount) {

        try {
            if (iRowCount > 0) {
                olRow = null;
                for (int k = 0; k < iRowCount; k++) {
                    olRow = FXCollections.observableArrayList();
                    for (int i = 0; i < iColumnCount; i++) {
                        olRow.add(objDBApi.dseColumnValue(k, i));
                    }
                    olData.add(olRow);
                }                

                for (int i = 0; i < iColumnCount; i++) {
                    final int p = i;
                    TableColumn col = new TableColumn(objDBApi.dseColumnName(i));
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(p).toString());
                        }
                    });
                    tvSearchResultsTable.getColumns().addAll(col);
                }
            
                tvSearchResultsTable.setItems(olData);
            }
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }

    private void callValidation() {
        boolean bDonorIdEntered = tfDonorId.getText().trim().length() > 0;
        boolean bNameEntered = tfName.getText().trim().length() > 0;
        boolean bDOBEntered = tfDOB.getText().trim().length() > 0;
        boolean bMobileNumberEntered = tfMobileNumber.getText().trim().length() > 0;

        boolean bLandlineStdCodeEntered = tfLandlineStdCode.getText().trim().length() > 0;
        boolean bLandlineNumberEntered = tfLandlineNumber.getText().trim().length() > 0;
        
        boolean bFatherOrSpouseNameEntered = tfFatherOrSpouseName.getText().trim().length() > 0;
        boolean bPincodeEntered = tfPincode.getText().trim().length() > 0;
        
        if (bDonorIdEntered ||
            bNameEntered ||
            bDOBEntered ||
            bMobileNumberEntered ||
            bLandlineStdCodeEntered ||
            bLandlineNumberEntered ||
            bFatherOrSpouseNameEntered ||
            bPincodeEntered) {
            if (bDonorIdEntered && !bDonorIdValidate) {
                lMessage.setText("Invalid Donor ID");
                btnSearch.setDisable(true);
            } else if (bNameEntered && !bNameValidate) {
                lMessage.setText("Invalid Name");
                btnSearch.setDisable(true);
            } else if (bDOBEntered && !bDOBValidate) {
                lMessage.setText("Invalid Date of Birth");
                btnSearch.setDisable(true);
            } else if (bMobileNumberEntered && !bMobileNumberValidate) {
                lMessage.setText("Invalid Mobile Number");
                btnSearch.setDisable(true);
            } else if (bLandlineStdCodeEntered && !bLandlineStdCodeValidate) {
                lMessage.setText("Invalid Landline Std Code");
                btnSearch.setDisable(true);
            } else if (bLandlineNumberEntered && !bLandlineNumberValidate) {
                lMessage.setText("Invalid Landline Number");
                btnSearch.setDisable(true);
            } else if (bFatherOrSpouseNameEntered && !bFatherOrSpouseNameValidate) {
                lMessage.setText("Invalid Father's or Spouse's Name");
                btnSearch.setDisable(true);
            } else if (bPincodeEntered && !bPincodeValidate) {
                lMessage.setText("Invalid Pincode");
                btnSearch.setDisable(true);
            } else {
                lMessage.setText("");
                btnSearch.setDisable(false);
            }
        } else {
            lMessage.setText("Any one field must be entered");
            btnSearch.setDisable(true);
        }
            
        lMessage.setStyle("-fx-text-alignment: center; -fx-text-fill: red");
    }
    
    public void clearAllFields() {
        tfMobileNumber.clear();
        tfDOB.clear();
        tfLandlineStdCode.clear();
        tfLandlineNumber.clear();
        tfPincode.clear();
        tfName.clear();
        tfFatherOrSpouseName.clear();
        
        tvSearchResultsTable.getColumns().clear();
        
        tfMobileNumber.requestFocus();
    }
    
    public void populateDDEPage() {
        DDEController objDDEController = DDEController.getInstance();
        
        objDDEController.clearAllFields();

        if (cobxMobileType.getSelectionModel().getSelectedItem().equals("Residence")) {
            objDDEController.setResidenceMobile(tfMobileNumber.getText());
        } else {
            objDDEController.setOfficeMobile(tfMobileNumber.getText());            
        }
        objDDEController.setDOB(tfDOB.getText());
        
        if (cobxLandlineType.getSelectionModel().getSelectedItem().equals("Residence")) {
            objDDEController.setResidenceLandlineStdCode(tfLandlineStdCode.getText());
            objDDEController.setResidenceLandlineNumber(tfLandlineNumber.getText());
        } else {
            objDDEController.setOfficeLandlineStdCode(tfLandlineStdCode.getText());
            objDDEController.setOfficeLandlineNumber(tfLandlineNumber.getText());            
        }
        
        if (cobxPincodeType.getSelectionModel().getSelectedItem().equals("Residence")) {
            objDDEController.setResidencePincode(tfPincode.getText());
        } else {
            objDDEController.setOfficePincode(tfPincode.getText());            
        }
        
        objDDEController.setName(tfName.getText());
        objDDEController.setFatherOrSpouseName(tfFatherOrSpouseName.getText());
    }
}
