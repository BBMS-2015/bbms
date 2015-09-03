package bloodbank;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import database.DBApi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.io.IOException;
import java.net.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author anirudhnegi
 */
public class HomeController implements Initializable {

    static HomeController hcInstance;
    private Label lbl_msg;
    @FXML
    private TabPane tabq;
    @FXML
    private ListView lvmodule;
    //@FXML
    // private TableView dlu1;

    private Label lbl_update;
    /*  @FXML
     /*Navin code  */
//Navin code Donor Entry UI
    String x;
    String oldpass;
    String newpass;
    String confirmpass;
    String donorid;
    String col;
    String val;
    String[] prof = {"Donor ID", "Name", "Date Of Birth", "Age", "Sex", "Spouse Name", "Education", "Occupation", "Residential Address", "Residential Phone", "Residential Mobile", "Residential Email", "Official Address", "Official Phone", "Official Mobile", "Official Email", "Willingness", "Willingness Answer1", "Wedding Day", "Special Day", "Willing Quaterly", "Willing Half Yearly", "Willing Annually"};
    // String[] sec = {"","", "hrg", "aidsrc", "donorq", "occh", "rinf", "preg", "surg", "heartd", "cardiov", "seizures", "endod", "viralh", "jaundice", "hiv", "malaria", "syphilis", "tb", "fever", "kidney", "digs", "vacc", "medic", "inelig", "permdef", "minorcell", "physical", "will", "adverse"};
    // String[] title = {"Short Profile","Profile", "High Risk Group", "Aids", "Donor Ques.", "Occupational Hazards", "Respiratory Infections", "Pregnancy", "Surgical", "Heart diseases", "Cardio-vascular", "Seizures", "Endocranial disorders", "Viral hepatitis", "Jaundice", "Hiv/aids", "Malaria", "Syphilis", "Tuberculosis", "Fever", "Kidney", "Digestive system", "Vaccination", "Medication", "Ineligible", "Permanent Deferment", "Minor cell ", "Physical exam.", "willingnessChoice", "Adverse Donor Reaction"};

    String[] sec = {"", "hrg", "aidsrc", "donorq", "occh", "rinf", "preg", "surg", "heartd", "cardiov", "seizures", "endod", "viralh", "jaundice", "hiv", "malaria", "syphilis", "tb", "fever", "kidney", "digs", "vacc", "medic", "inelig", "permdef", "minorcell", "physical", "will", "adverse"};
    String[] title = {"Profile", "High Risk Group", "Aids", "Donor Ques.", "Occupational Hazards", "Respiratory Infections", "Pregnancy", "Surgical", "Heart diseases", "Cardio-vascular", "Seizures", "Endocranial disorders", "Viral hepatitis", "Jaundice", "Hiv/aids", "Malaria", "Syphilis", "Tuberculosis", "Fever", "Kidney", "Digestive system", "Vaccination", "Medication", "Ineligible", "Permanent Deferment", "Minor cell ", "Physical exam.", "willingness", "Adverse Donor Reaction"};
    String[] profile = {"name", "dob", "bloodgroup", "spousename", "resphone", "email", "dod", "nsdod", "willingness"};
    String[] ques = {};
    String[] def_ans = {};
    RadioButton g1, g2;
    String[][] ans = new String[100][100];
    String[][] ans1 = new String[100][100];
    static String gender_sel = "";
    String[] last = new String[10];
    int tbn;
    int j = 0, jj;
    int i;
    static DBApi objDBApi;
    ToggleGroup btnGroup1;
    ToggleGroup btnGroup2;
    ToggleGroup btnGroup3;

    TextField tf1;
    TextField tf2;
    TextField tf3;
    TextField tf4;
    ComboBox tf5;
    TextField tf7;
    TextField tf8;
    TextField tf9;
    ComboBox tf10;
    TextField tf11;
    TextField tf12;
    TextField tf13;
    TextField tf14;
    TextField tf15;
    TextField tf16;
    TextField tf161;
    TextField tf17;
    TextField tf18;
    TextField tf19;
    TextField tf191;
    RadioButton tf201;
    RadioButton tf202;
    ToggleGroup tg1;
    TextField tf21;
    TextField tf22;
    ComboBox tf23;
    TextField tf24;
    TextField tf25;
    RadioButton chk;
    String message = "";
    String message_up = "";
    /*Navin code */
    protected boolean willingnessChoice = false;


    @FXML
    private ScrollPane spMasterPane;
    
    private Pane objDDEPane;
    private Pane objDLUPane;
    private Pane objDSEPane;
    
    private FXMLLoader dseFXMLLoader;
    private FXMLLoader dluFXMLLoader;
    private FXMLLoader ddeFXMLLoader;
    
    public HomeController() {
        hcInstance = this;
    }
    
    public static HomeController getInstance() {
        return hcInstance;
    }
    /*Navin code */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        objDBApi = new DBApi();
        objDBApi.connect();
        
        /* Is populate_tab needed here ?*/
        //this.populate_tab();
        populate_home();
/*
        profile_combo.getItems().addAll((Object[]) profile);
        update_bt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                donorid = donorid_tb.getText();
                if (profile_combo.getSelectionModel().getSelectedItem() != null) {
                    col = profile_combo.getSelectionModel().getSelectedItem().toString();
                } else {
                    col = "";
                }
                val = profile_val.getText();
                message_up = new DRM().disp(donorid, col, val);
                set_lblmsg_up(message_up);
            }
        });
        change_b.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                oldpass = old_pass.getText();
                newpass = new_pass.getText();
                confirmpass = confirm_pass.getText();
                message = new DDA().disp(oldpass, newpass, confirmpass);
                set_lblmsg(message);
            }
        });
        
        if (tg1 != null) {
            tg1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                    chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    //  System.out.println("Selected Radio Button - "+chk.getText());

                }
            });
        }
        
        sub_but.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int co = 0;//bug fix 6.6.2015
                check = 0;
                try {
                    // getdata_toarray();
                    for (int mm = 0; mm < 23; mm++) {
                        if ((mm >= 9 && mm <= 16) || mm == 20 || mm == 21 || mm == 22 || mm == 6) {

                        } else {
                            if (abca[mm].equals("") || abca[mm] == null) {
                                co = 1;
                                break;
                            }
                        }
                    }
                    if (abca[9].equals("") && abca[13].equals("")) {
                        co = 2;
                    }
                    if (abca[10].equals("") && abca[14].equals("") && abca[11].equals("") && abca[15].equals("")) {
                        co = 3;
                    }
                    if (abca[12].equals("") && abca[16].equals("")) {
                        co = 4;
                    }
                    if (co > 0) {
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
                        BorderPane bp = new BorderPane();
                        if (co == 1) {
                            bp.setCenter(new Label("Please Fill All Details."));
                        } else if (co == 2) {
                            bp.setCenter(new Label("Either of the Address must be filled"));
                        } else if (co == 3) {
                            bp.setCenter(new Label("Either of the phone/mobile no. must be filled"));
                        } else if (co == 4) {
                            bp.setCenter(new Label("Either of the Email must be filled"));
                        }

                        Button b = new Button("Ok");
                        b.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                stage.hide();
                            }
                        });
                        bp.setBottom(b);
                        stage.setScene(new Scene(bp, 200, 80));
                        stage.show();
                    } else {
                        Stage stage = new Stage();
                        Parent parent = FXMLLoader.load(getClass().getResource("/bloodbank/Confirm.fxml"));
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) (event.getSource())).getScene().getWindow());
                        stage.setScene(new Scene(parent));
                        stage.show();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
*/
    }
    /* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/

    public void populate_home() {
        
        /*Navin code*/
        ObservableList<String> items = FXCollections.observableArrayList("Donor Search", "Donor Look Up");

        lvmodule.setItems(items);

        try {
            dseFXMLLoader = new FXMLLoader(getClass().getResource("DSE.fxml"));
            dluFXMLLoader = new FXMLLoader(getClass().getResource("DLU.fxml"));
            ddeFXMLLoader = new FXMLLoader(getClass().getResource("DDE.fxml"));
            
            objDSEPane = (Pane)dseFXMLLoader.load();
            objDLUPane = (Pane)dluFXMLLoader.load();
            objDDEPane = (Pane)ddeFXMLLoader.load();
            
            lvmodule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                
                @Override
                public void changed(ObservableValue<? extends String> observable, String strOldValue, String strNewValue) {                    
                    int x = lvmodule.getSelectionModel().getSelectedIndex();
                    switch (x) {
                        case 0:
                            spMasterPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                            spMasterPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                            spMasterPane.setContent(objDSEPane);
                            dseFXMLLoader.<DSEController>getController().initFocus();
                            break;

                        case 1:
                            spMasterPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                            spMasterPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                            spMasterPane.setContent(objDLUPane);
                            dluFXMLLoader.<DLUController>getController().initFocus();
                            break;                            
                    }
                }
            });
            
            lvmodule.getSelectionModel().select("Donor Search");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDSEPage() {

        lvmodule.getSelectionModel().select("Donor Search");
    }

    public void showDDEPage() {

        spMasterPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        spMasterPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        spMasterPane.setContent(objDDEPane);
        
        ddeFXMLLoader.<DDEController>getController().initFocus();

        lvmodule.getSelectionModel().clearSelection();
    }

    /*Navin code 362 to 769*/
    public void populate_tab() {

        for (i = 0; i < 29; i++) {
            ScrollPane sc = new ScrollPane();
            Tab tab = new Tab();
            tab.setText(title[i]);
            VBox vbox = new VBox();
            ques = objDBApi.question(sec[i]);
            //default answers..........
            def_ans = objDBApi.def_answers(sec[i]);

            tbn = ques.length;

            if (i == 0) {
                for (int len = 0; len < prof.length; len++) {
                    // Label l = new Label(prof[len]);
                    // TextField t1 = new TextField();
                    // Label l1 = new Label("");
                    GridPane gridPane = new GridPane();
                    gridPane.setPadding(new Insets(40, 40, 40, 40));
                    gridPane.setHgap(25);
                    gridPane.setVgap(20);

                    // Scene scene = new Scene(gridPane, 300, 150);
                    Label lba = new Label("SECTION A");
                    GridPane.setHalignment(lba, HPos.CENTER);

                    Label lb1 = new Label("Donor ID:");
                    GridPane.setHalignment(lb1, HPos.RIGHT);
                    tf1 = new TextField();

                    Label lb2 = new Label("Donor serial no.:");
                    GridPane.setHalignment(lb2, HPos.RIGHT);
                    tf2 = new TextField();

                    Label lb3 = new Label("Donor no.:");
                    GridPane.setHalignment(lb3, HPos.RIGHT);
                    tf3 = new TextField();

                    Label lb4 = new Label("Donor blood bag no.:");
                    GridPane.setHalignment(lb4, HPos.RIGHT);
                    tf4 = new TextField();

                    Object[] bg = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
                    Label lb5 = new Label("Blood Group:");
                    GridPane.setHalignment(lb5, HPos.RIGHT);
                    tf5 = new ComboBox();
                    tf5.getItems().addAll(bg);

                    Label lbb = new Label("SECTION B");
                    GridPane.setHalignment(lbb, HPos.CENTER);

                    Label lb7 = new Label("Name:");
                    GridPane.setHalignment(lb7, HPos.RIGHT);
                    tf7 = new TextField();

                    Label lb8 = new Label("DOB:");
                    GridPane.setHalignment(lb8, HPos.RIGHT);
                    tf8 = new TextField();
                    tf8.promptTextProperty().setValue("DD/MM/YYYY");

                    tf8.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            if (newPropertyValue) {
                                System.out.println("Textfield on focus");
                            } else {
                                if (tf8.getText().length() == 10) {
                                    int y = Integer.parseInt(tf8.getText().substring(6));
                                    int curr = Calendar.getInstance().get(Calendar.YEAR);
                                    int diff = curr - y;
                                    tf9.setText(String.valueOf(diff));
                                }
                            }
                        }
                    });
                    Label lb9 = new Label("Age:");
                    GridPane.setHalignment(lb9, HPos.RIGHT);
                    tf9 = new TextField();
                    tf9.setDisable(true);

                    Object[] gen = {"Male", "Female", "TransGender"};
                    Label lb10 = new Label("Gender:");
                    GridPane.setHalignment(lb10, HPos.RIGHT);
                    tf10 = new ComboBox();
                    tf10.getItems().addAll(gen);
                    tf10.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            gender_sel = tf10.getSelectionModel().getSelectedItem().toString();
                            if (gender_sel.equals("Female")) {
                                g1.setDisable(false);
                                g2.setDisable(false);
                            } else {
                                g1.setDisable(true);
                                g2.setDisable(true);
                            }
                        }
                    });
                    Label lb11 = new Label("Spouse Name:");
                    GridPane.setHalignment(lb11, HPos.RIGHT);
                    tf11 = new TextField();

                    Label lb12 = new Label("Occupation:");
                    GridPane.setHalignment(lb12, HPos.RIGHT);
                    tf12 = new TextField();

                    Label lb13 = new Label("Education:");
                    GridPane.setHalignment(lb13, HPos.RIGHT);
                    tf13 = new TextField();

                    Label lbc = new Label("SECTION C");
                    GridPane.setHalignment(lbc, HPos.CENTER);

                    Label lb14 = new Label("Residential Address:");
                    GridPane.setHalignment(lb14, HPos.RIGHT);
                    tf14 = new TextField();

                    Label lb15 = new Label("Residential phone:");
                    GridPane.setHalignment(lb15, HPos.RIGHT);
                    tf15 = new TextField();

                    Label lb16 = new Label("Residential mobile: +91-");
                    GridPane.setHalignment(lb16, HPos.RIGHT);
                    tf16 = new TextField();

                    Label lb161 = new Label("Residential Email: ");
                    GridPane.setHalignment(lb161, HPos.RIGHT);
                    tf161 = new TextField();

                    Label lb17 = new Label("Office Address:");
                    GridPane.setHalignment(lb17, HPos.RIGHT);
                    tf17 = new TextField();

                    Label lb18 = new Label("Office phone:");
                    GridPane.setHalignment(lb18, HPos.RIGHT);
                    tf18 = new TextField();

                    Label lb19 = new Label("Office mobile: +91-");
                    GridPane.setHalignment(lb19, HPos.RIGHT);
                    tf19 = new TextField();

                    Label lb191 = new Label("Office Email: ");
                    GridPane.setHalignment(lb191, HPos.RIGHT);
                    tf191 = new TextField();

                    Label lbd = new Label("SECTION D");
                    GridPane.setHalignment(lbd, HPos.CENTER);

                    Label lb20 = new Label("Willingness:");
                    GridPane.setHalignment(lb20, HPos.RIGHT);
                    HBox h1 = new HBox();
                    tf201 = new RadioButton("Yes");
                    tf202 = new RadioButton("No");
                    tg1 = new ToggleGroup();
                    tf201.setToggleGroup(tg1);
                    tf202.setToggleGroup(tg1);
                    tf201.setPadding(new Insets(0, 0, 0, 15));
                    tf202.setPadding(new Insets(0, 0, 0, 15));
                    h1.getChildren().add(tf201);
                    h1.getChildren().add(tf202);

                    Label lb21 = new Label("willingness Wedding day:");
                    GridPane.setHalignment(lb21, HPos.RIGHT);
                    tf21 = new TextField();
                    tf21.promptTextProperty().setValue("DD/MM/YYYY");

                    Label lb22 = new Label("willingness Birthday:");
                    GridPane.setHalignment(lb22, HPos.RIGHT);
                    tf22 = new TextField();
                    tf22.promptTextProperty().setValue("DD/MM/YYYY");

                    Object[] will = {"Annually", "Half Yearly", "Quaterly"};
                    Label lb23 = new Label("willingness Day:");
                    GridPane.setHalignment(lb23, HPos.RIGHT);
                    tf23 = new ComboBox();
                    tf23.getItems().addAll(will);
                    Label lb25 = new Label("Donation Date:");
                    GridPane.setHalignment(lb25, HPos.RIGHT);
                    tf25 = new TextField();
                    tf25.promptTextProperty().setValue("DD/MM/YYYY");

                    Label lb24 = new Label("Next Suggested Donation Date:");
                    GridPane.setHalignment(lb24, HPos.RIGHT);
                    tf24 = new TextField();

                    tg1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                        @Override
                        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                            RadioButton chk = (RadioButton) tg1.getSelectedToggle(); // Cast object to radio button
                            System.out.println(chk.getText());
                            if (chk.getText().equals("No")) {
                                tf21.setDisable(true);
                                tf22.setDisable(true);
                                tf23.setDisable(true);
                                tf24.setDisable(true);
                            } else {
                                tf21.setDisable(false);
                                tf22.setDisable(false);
                                tf23.setDisable(false);
                                tf24.setDisable(false);
                            }

                        }
                    });

                    tf25.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            if (newPropertyValue) {
                                //  System.out.println("Textfield on focus");
                            } else {
                                if (tf25.getText().length() == 10) {
                                    String dt = tf25.getText();  // Start date
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Calendar c = Calendar.getInstance();
                                    try {
                                        if (!dt.isEmpty()) {
                                            c.setTime(sdf.parse(dt));
                                        }
                                    } catch (ParseException ex) {
                                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (tf10.getSelectionModel().getSelectedItem() != null) {
                                        String gen = tf10.getSelectionModel().getSelectedItem().toString();
                                        if (gen.equals("Female")) {
                                            c.add(Calendar.MONTH, 4);  // number of days to add

                                        } else {
                                            c.add(Calendar.MONTH, 3);
                                        }
                                    }
                                    dt = sdf.format(c.getTime());  // dt is now the new date
                                    tf24.setText(dt);
                                    //System.out.println(dt);

                                }
                            }
                        }
                    });
                    //GridPane.setMargin(btLogin, new Insets(10, 5, 5, 5));
                    // gridPane.add(lbUser, 0, 0);
                    //gridPane.add(tfUser, 1, 0);
                    lba.setFont(Font.font("Arial Black", 20));
                    lbb.setFont(Font.font("Arial Black", 20));
                    lbc.setFont(Font.font("Arial Black", 20));
                    lbd.setFont(Font.font("Arial Black", 20));

                    gridPane.add(lba, 2, 0);

                    gridPane.add(lb1, 0, 1);
                    gridPane.add(tf1, 1, 1);
                    gridPane.add(lb2, 2, 1);
                    gridPane.add(tf2, 3, 1);
                    gridPane.add(lb3, 4, 1);
                    gridPane.add(tf3, 5, 1);
                    gridPane.add(lb4, 0, 2);
                    gridPane.add(tf4, 1, 2);
                    gridPane.add(lb5, 2, 2);
                    gridPane.add(tf5, 3, 2);

                    gridPane.add(lbb, 2, 3);

                    gridPane.add(lb7, 0, 4);
                    gridPane.add(tf7, 1, 4);
                    gridPane.add(lb8, 2, 4);
                    gridPane.add(tf8, 3, 4);
                    gridPane.add(lb9, 4, 4);
                    gridPane.add(tf9, 5, 4);
                    gridPane.add(lb10, 0, 5);
                    gridPane.add(tf10, 1, 5);
                    gridPane.add(lb11, 2, 5);
                    gridPane.add(tf11, 3, 5);
                    gridPane.add(lb12, 4, 5);
                    gridPane.add(tf12, 5, 5);
                    gridPane.add(lb13, 0, 6);
                    gridPane.add(tf13, 1, 6);

                    gridPane.add(lbc, 2, 7);

                    gridPane.add(lb14, 0, 8);
                    gridPane.add(tf14, 1, 8);
                    gridPane.add(lb15, 2, 8);
                    gridPane.add(tf15, 3, 8);
                    gridPane.add(lb16, 4, 8);
                    gridPane.add(tf16, 5, 8);
                    gridPane.add(lb161, 0, 9);
                    gridPane.add(tf161, 1, 9);
                    gridPane.add(lb17, 2, 9);
                    gridPane.add(tf17, 3, 9);
                    gridPane.add(lb18, 4, 9);
                    gridPane.add(tf18, 5, 9);
                    gridPane.add(lb19, 0, 10);
                    gridPane.add(tf19, 1, 10);
                    gridPane.add(lb191, 2, 10);
                    gridPane.add(tf191, 3, 10);

                    gridPane.add(lbd, 2, 11);

                    gridPane.add(lb20, 0, 12);
                    gridPane.add(h1, 1, 12);
                    gridPane.add(lb21, 2, 12);
                    gridPane.add(tf21, 3, 12);
                    gridPane.add(lb22, 4, 12);
                    gridPane.add(tf22, 5, 12);
                    gridPane.add(lb23, 0, 13);
                    gridPane.add(tf23, 1, 13);
                    gridPane.add(lb25, 2, 13);
                    gridPane.add(tf25, 3, 13);
                    gridPane.add(lb24, 4, 13);
                    gridPane.add(tf24, 5, 13);
                    //gridPane.add(btLogin, 1, 2);

                    sc.setContent(gridPane);

                }
            }
            for (jj = 0; jj < ques.length; jj++) {
                Label l = new Label("Q" + (jj + 1) + "." + ques[jj]);

                if (i > 0 && i < 26) {
                    ans[i][jj] = def_ans[jj].toLowerCase();
                    ans1[i][jj] = def_ans[jj].toLowerCase();
                    //System.out.println("ANS=" + ans[i][jj]);
                    ToggleGroup bg = new ToggleGroup();
                    RadioButton rb1 = new RadioButton("YES");
                    RadioButton rb2 = new RadioButton("NO");
                    Label l1 = new Label("");
                    rb1.setToggleGroup(bg);
                    rb2.setToggleGroup(bg);
                    bg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                        @Override
                        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                            x = newValue.toString().toLowerCase();
                            // System.out.println(x.substring(46).equals("no'"));
                            CharSequence cs1 = "no";
                            // System.out.println("Before=" + ans1[i][jj]);
                            if (x.contains(cs1) == true) {
                                ans1[i][jj] = "no";
                            } else {
                                ans1[i][jj] = "yes";
                            }

                            // System.out.println("After=" + ans1[i][jj]);
                        }

                    });
                    if (i == 1) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }

                    }

                    if (i == 2) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;
                            case 7:
                                bg.selectToggle(rb2);
                                break;
                            case 8:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    if (i == 3) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb1);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;
                            case 7:
                                bg.selectToggle(rb2);
                                break;
                            case 8:
                                bg.selectToggle(rb2);
                                break;
                            case 9:
                                bg.selectToggle(rb1);
                                break;
                            case 10:
                                bg.selectToggle(rb2);
                                break;
                            case 11:
                                bg.selectToggle(rb2);
                                break;
                            case 12:
                                bg.selectToggle(rb2);
                                break;
                            case 13:
                                bg.selectToggle(rb2);
                                break;
                            case 14:
                                bg.selectToggle(rb2);
                                break;
                            case 15:
                                bg.selectToggle(rb2);
                                break;
                            case 16:
                                bg.selectToggle(rb2);
                                break;
                            case 17:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    if (i == 5) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    if (i == 5) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;
                            case 7:
                                bg.selectToggle(rb2);
                                break;
                            case 8:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    if (i == 6) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 7) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;
                            case 7:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 8) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 9) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 10) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 11) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 12) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 13) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 14) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    if (i == 15) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 16) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 17) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 18) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 19) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 20) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 21) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            case 4:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 22) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb1);
                                break;
                            case 2:
                                bg.selectToggle(rb1);
                                break;
                            case 3:
                                bg.selectToggle(rb1);
                                break;
                            case 4:
                                bg.selectToggle(rb1);
                                break;
                            case 5:
                                bg.selectToggle(rb2);
                                break;
                            case 6:
                                bg.selectToggle(rb2);
                                break;
                            case 7:
                                bg.selectToggle(rb2);
                                break;
                            case 8:
                                bg.selectToggle(rb2);
                                break;
                            case 9:
                                bg.selectToggle(rb2);
                                break;
                            case 10:
                                bg.selectToggle(rb2);
                                break;
                            case 11:
                                bg.selectToggle(rb2);
                                break;
                            case 12:
                                bg.selectToggle(rb2);
                                break;
                            case 13:
                                bg.selectToggle(rb1);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 23) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 24) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;

                            default:
                                break;

                        }
                    }
                    if (i == 25) {
                        switch (jj + 1) {
                            //default selected values
                            // bg.selectToggle(rb1);
                            case 1:
                                bg.selectToggle(rb2);
                                break;
                            case 2:
                                bg.selectToggle(rb2);
                                break;
                            case 3:
                                bg.selectToggle(rb2);
                                break;
                            default:
                                break;

                        }
                    }
                    //change listner for radio button group
                    // answer changed -> answer array changed

                    vbox.getChildren().add(l);
                    vbox.getChildren().add(rb1);
                    vbox.getChildren().add(rb2);
                    vbox.getChildren().add(l1);
                    vbox.setAlignment(Pos.TOP_LEFT);
                    sc.setContent(vbox);
                }
                if (i == 26) {
                    last = ques;
                }
            }
            if (i == 26) {
                //Label space=new Label("");
                Label l1 = new Label("Q1." + last[0]);
                TextField t1 = new TextField();

                Label l2 = new Label("Q2." + last[1]);
                g1 = new RadioButton("YES");
                g2 = new RadioButton("No");
                ToggleGroup tgmf = new ToggleGroup();
                g1.setToggleGroup(tgmf);
                g2.setToggleGroup(tgmf);
                //tgmf = new TextField();
                if (!gender_sel.equals("Female")) {
                    g1.setDisable(true);
                    g2.setDisable(true);
                } else {
                    g1.setDisable(false);
                    g2.setDisable(false);
                }
                Label l3 = new Label("Q3." + last[2]);
                TextField t3 = new TextField();
                Label l4 = new Label("Q4." + last[3]);
                TextField t4 = new TextField();
                Label l5 = new Label("Q5." + last[4]);
                TextField t5 = new TextField();
                Label l6 = new Label("Q6." + last[5]);
                TextField t6 = new TextField();
                Label l7 = new Label("Q7." + last[6]);
                TextField t7 = new TextField();
                Label l8 = new Label("Q8." + last[7]);
                TextField t8 = new TextField();
                Label l9 = new Label("Q9." + last[8]);
                RadioButton rb1 = new RadioButton("YES");
                RadioButton rb2 = new RadioButton("NO");
                ToggleGroup tog = new ToggleGroup();

                rb1.setToggleGroup(tog);
                rb2.setToggleGroup(tog);
                tog.selectToggle(rb2);

                Label l10 = new Label("Q10." + last[9]);
                TextField t10 = new TextField();
                // vbox.getChildren().add(space);
                vbox.getChildren().add(l1);
                vbox.getChildren().add(t1);
                vbox.getChildren().add(l2);
                vbox.getChildren().add(g1);

                vbox.getChildren().add(g2);
                vbox.getChildren().add(l3);
                vbox.getChildren().add(t3);
                vbox.getChildren().add(l4);
                vbox.getChildren().add(t4);
                vbox.getChildren().add(l5);
                vbox.getChildren().add(t5);
                vbox.getChildren().add(l6);
                vbox.getChildren().add(t6);
                vbox.getChildren().add(l7);
                vbox.getChildren().add(t7);
                vbox.getChildren().add(l8);
                vbox.getChildren().add(t8);
                vbox.getChildren().add(l9);
                vbox.getChildren().add(rb1);
                vbox.getChildren().add(rb2);
                vbox.getChildren().add(l10);
                vbox.getChildren().add(t10);
                vbox.setAlignment(Pos.TOP_LEFT);
                sc.setContent(vbox);
            }
            tab.setContent(sc);
            tabq.getTabs().add(tab);

        }
    }

    public void set_lblmsg(String val) {
        lbl_msg.setText(val);
    }
    /*Navin code*/

    public void set_lblmsg_up(String val) {
        lbl_update.setText(val);
    }

    public static DBApi getDBApi() {
        return objDBApi;
    }
}

class ValidationBean {

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private int property = 0;

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        pcs.firePropertyChange("property", this.property, property);
        this.property = property;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);

    }
}
