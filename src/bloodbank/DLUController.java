/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import database.DBApi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rajagopal
 */
public class DLUController implements Initializable {

    @FXML
    private AnchorPane anc_filter;
    @FXML
    private TableView tvDonorTable;

    String bgv;
    String agev;
    String gendv;
    String listv;

    DBApi objDBApi;

    private ObservableList<ObservableList> data;
    ObservableList<String> row;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        objDBApi = HomeController.getDBApi();
        data = FXCollections.observableArrayList();
        anc();
        tvDonorTable.getColumns().clear();
        populate_table_fil(bgv, agev, gendv, listv);
    }    

    public void initFocus() {
    }

    private void anc() {

        HBox hbf = new HBox();

        Object[] bg1 = (Object[]) objDBApi.getbg();
        ComboBox bg = new ComboBox();
        bgv = "All Blood Groups";
        bg.getItems().add(bgv);
        bg.getItems().addAll((Object[])bg1);
        bg.getSelectionModel().selectFirst();
        
        Object[] ag = {"All Age Groups", "18-30", "31-40", "41-50", "51-60"};
        ComboBox age1 = new ComboBox();
        age1.getItems().addAll(ag);
        age1.getSelectionModel().selectFirst();
        agev = age1.getSelectionModel().getSelectedItem().toString();

        Object[] gend = (Object[]) objDBApi.getgend();
        final ComboBox gend1 = new ComboBox();
        gendv = "All Genders";
        gend1.getItems().add(gendv);
        gend1.getItems().addAll((Object[]) gend);
        gend1.getSelectionModel().selectFirst();

        Object[] objListing = {"All Donors", "Only Eligible Donors"};
        final ComboBox cmbbxListing = new ComboBox();
        cmbbxListing.getItems().addAll(objListing);
        cmbbxListing.getSelectionModel().selectFirst();
        listv = cmbbxListing.getSelectionModel().getSelectedItem().toString();
        
        Button btgo = new Button("Search");
        btgo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                bgv = bg.getSelectionModel().getSelectedItem().toString();

                agev = age1.getSelectionModel().getSelectedItem().toString();

                gendv = gend1.getSelectionModel().getSelectedItem().toString();

                listv = cmbbxListing.getSelectionModel().getSelectedItem().toString();
                
                data.clear();
                data.removeAll(data);
                tvDonorTable.getColumns().clear();
                populate_table_fil(bgv, agev, gendv, listv);
            }
        });
        Button btr = new Button("Refresh");
        btr.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /*Navin code*/
                ObservableList<String> o = gend1.getItems();
                /* Preserve the current selection */
                Object objSelectedItem = gend1.getSelectionModel().getSelectedItem();
                gend1.getItems().removeAll(o);
                Object[] gend = (Object[]) objDBApi.getgend();
                gend1.getItems().add("All Genders");
                gend1.getItems().addAll((Object[]) gend);
                /*Navin code*/
                /* Restore the previous selection*/
                gend1.getSelectionModel().select(gend1.getItems().indexOf(objSelectedItem));
                
                ObservableList<String> olBloodGroup = bg.getItems();
                /* Preserve the current selection */
                objSelectedItem = bg.getSelectionModel().getSelectedItem();
                bg.getItems().removeAll(olBloodGroup);
                bg.getItems().add("All Blood Groups");
                bg.getItems().addAll(objDBApi.getbg());
                /* Restore the previous selection */
                bg.getSelectionModel().select(bg.getItems().indexOf(objSelectedItem));
                
                bgv = bg.getSelectionModel().getSelectedItem().toString();

                agev = age1.getSelectionModel().getSelectedItem().toString();

                gendv = gend1.getSelectionModel().getSelectedItem().toString();

                listv = cmbbxListing.getSelectionModel().getSelectedItem().toString();

                data.clear();
                data.removeAll(data);
                tvDonorTable.getColumns().clear();
                populate_table_fil(bgv, agev, gendv, listv);
            }
        });
        Button clr = new Button("Clear");
        clr.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                bg.getSelectionModel().selectFirst();
                age1.getSelectionModel().selectFirst();
                gend1.getSelectionModel().selectFirst();
                cmbbxListing.getSelectionModel().selectFirst();
                
                bgv = bg.getSelectionModel().getSelectedItem().toString();

                agev = age1.getSelectionModel().getSelectedItem().toString();

                gendv = gend1.getSelectionModel().getSelectedItem().toString();

                listv = cmbbxListing.getSelectionModel().getSelectedItem().toString();

                data.clear();
                data.removeAll(data);
                tvDonorTable.getColumns().clear();
                populate_table_fil(bgv, agev, gendv, listv);
            }
        });
        hbf.getChildren().add(bg);
        hbf.getChildren().add(age1);
        hbf.getChildren().add(gend1);
        hbf.getChildren().add(cmbbxListing);
        hbf.getChildren().add(btgo);
        hbf.getChildren().add(btr);
        hbf.getChildren().add(clr);

        anc_filter.getChildren().add(hbf);
    }

    public void populate_table_fil(String val, String val1, String val2, String sListing) {

        try {
            int iRowCount = objDBApi.rows_fil(val, val1, val2, sListing);
            int iColumnCount = objDBApi.getColumnCount();
            row = null;
            for (int i = 0; i < iColumnCount; i++) {
                final int p = i;
                TableColumn col = new TableColumn(objDBApi.dluColumnName(i));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(p).toString());
                    }
                });
                
                if (i == iColumnCount-1)
                    col.setMinWidth(800);
                
                tvDonorTable.getColumns().addAll(col);
            }

            for (int k = 0; k < iRowCount; k++) {
                row = FXCollections.observableArrayList();
                for (int i = 0; i < iColumnCount; i++) {
                    row.add(objDBApi.dluColumnValue(k, i));
                }
                data.add(row);
            }
            
            if (data.isEmpty()) {
                System.out.println("Empty");
                tvDonorTable.setPlaceholder(new Label("Sorry! No Eligible Donor Found."));
            } else {
                tvDonorTable.setItems(data);
            }
        } catch (Exception e) {
            System.out.println("Error on Building Data : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
