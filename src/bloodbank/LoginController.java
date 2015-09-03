 package bloodbank;

import database.DBApi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anirudhnegi
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private GridPane gd;
    @FXML
    private Label load;
    @FXML
    private Label lbllogin;
    @FXML
    private Label lblnotif;
    @FXML
    private TextField txtuname;
    @FXML
    private PasswordField txtpass;
    @FXML
    private ProgressIndicator pi;

    Stage stage1;
    Task<Void> task;
    int x = 0;
    DBApi ob = new DBApi();
    static String uname = "";
    static String pass = "";
    int flag = 0;

    public LoginController() {
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Connected");
                //Thread.sleep(500);
                updateMessage("Please wait...");
                //Thread.sleep(500);
                x = ob.login(uname, pass, flag);
                return null;                
            }

            @Override
            protected void succeeded() {
                try {
                    if (x == 1) {
                        new Home().start(stage1);
                        Stage stage = (Stage) lbllogin.getScene().getWindow();
                        stage.hide();
                    } else {
                        updateMessage("Invalid Credentials");
                       // Thread.sleep(2000);
                        
                    }
                } catch (Exception ex) {
                    System.out.println("Error :" + ex);
                }
                pi.progressProperty().unbind();
                pi.visibleProperty().unbind();
            }

            @Override
            protected void cancelled() {
                super.cancelled();
                updateMessage("Cancelled!");
            }

            @Override
            protected void failed() {
                super.failed();
                updateMessage("Sorry!No connection");
            }
        };
    }

    //verify credentials
    @FXML
    private void vlogin(ActionEvent event) throws Exception {
        try {
            pi.setProgress(0.5);
            pi.progressProperty().bind(task.progressProperty());
            
            uname = txtuname.getText();
            pass = txtpass.getText();
            load.textProperty().bind(task.messageProperty());
            new Thread(task).start();
        } catch (Exception ex) {
            System.out.println("Error :" + ex);
        }
    }

    public void logged() throws Exception {
        new Home().start(stage1);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage1 = new Stage();
        pi.visibleProperty().bind(task.runningProperty());
        txtpass.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    pi.setProgress(0.5);
                    pi.progressProperty().bind(task.workDoneProperty());
                    uname = txtuname.getText();
                    pass = txtpass.getText();
                    load.textProperty().bind(task.messageProperty());
                    new Thread(task).start();
                  
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        });
    }

    public String getUser() {
        return uname;
    }

    public String getPass() {
        return pass;
    }
}
