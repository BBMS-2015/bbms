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
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TextField;
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
    private ComboBox cmbbxBloodGroupFilter;
    @FXML
    private ComboBox cmbbxAgeGroupFilter;
    @FXML
    private ComboBox cmbbxGenderFilter;
    @FXML
    private ComboBox cmbbxEligibilityFilter;
    @FXML
    private ComboBox cmbbxAreaFilter;
    @FXML
    private ComboBox cmbbxVillageOrTownOrCityFilter;
    @FXML
    private TextField tfAddressKeyword;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnClear;
    @FXML
    private TableView tvDonorTable;

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
    }    

    public void initFocus() {
    }

    private void anc() {

        String sBloodGroup;
        String sAgeGroup;
        String sGender;
        String sEligibility;
        String sArea;
        String sVillageOrTownOrCity;
        String sAddressKeyword;
        
        Object[] bg1 = (Object[]) objDBApi.getbg();
        sBloodGroup = "All Blood Groups";
        cmbbxBloodGroupFilter.getItems().add(sBloodGroup);
        cmbbxBloodGroupFilter.getItems().addAll(bg1);
        cmbbxBloodGroupFilter.getSelectionModel().selectFirst();
        
        Object[] ag = {"All Age Groups", "18-30", "31-40", "41-50", "51-60"};
        cmbbxAgeGroupFilter.getItems().addAll(ag);
        cmbbxAgeGroupFilter.getSelectionModel().selectFirst();
        sAgeGroup = cmbbxAgeGroupFilter.getSelectionModel().getSelectedItem().toString();

        Object[] gend = (Object[]) objDBApi.getgend();
        sGender = "All Genders";
        cmbbxGenderFilter.getItems().add(sGender);
        cmbbxGenderFilter.getItems().addAll(gend);
        cmbbxGenderFilter.getSelectionModel().selectFirst();

        Object[] objListing = {"All Donors", "Only Eligible Donors"};
        cmbbxEligibilityFilter.getItems().addAll(objListing);
        cmbbxEligibilityFilter.getSelectionModel().selectFirst();
        sEligibility = cmbbxEligibilityFilter.getSelectionModel().getSelectedItem().toString();        

        Object[] objArea = (Object[]) objDBApi.getAreas();
        sArea = "All Areas";
        cmbbxAreaFilter.getItems().add(sArea);
        cmbbxAreaFilter.getItems().addAll(objArea);
        cmbbxAreaFilter.getSelectionModel().selectFirst();

        Object[] objVillageOrTownOrCity = (Object[]) objDBApi.getVillagesOrTownsOrCities();
        sVillageOrTownOrCity = "All Villages/Towns/Cities";
        cmbbxVillageOrTownOrCityFilter.getItems().add(sVillageOrTownOrCity);
        cmbbxVillageOrTownOrCityFilter.getItems().addAll(objVillageOrTownOrCity);
        cmbbxVillageOrTownOrCityFilter.getSelectionModel().selectFirst();

        tfAddressKeyword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {              
                if (!newPropertyValue) {
                    /* Lost Focus */
                    if (tfAddressKeyword.getText().trim().length() > 0) {
                        cmbbxAreaFilter.getSelectionModel().selectFirst();
                        cmbbxVillageOrTownOrCityFilter.getSelectionModel().selectFirst();
                    }
                }
            }
        });
        sAddressKeyword = tfAddressKeyword.getText().trim();
        
        tvDonorTable.getColumns().clear();
        populate_table_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);
    }

    @FXML
    private void onSearchClicked() {

        String sBloodGroup;
        String sAgeGroup;
        String sGender;
        String sEligibility;
        String sArea;
        String sVillageOrTownOrCity;
        String sAddressKeyword;

        sBloodGroup = cmbbxBloodGroupFilter.getSelectionModel().getSelectedItem().toString();

        sAgeGroup = cmbbxAgeGroupFilter.getSelectionModel().getSelectedItem().toString();

        sGender = cmbbxGenderFilter.getSelectionModel().getSelectedItem().toString();

        sEligibility = cmbbxEligibilityFilter.getSelectionModel().getSelectedItem().toString();
        
        sArea = cmbbxAreaFilter.getSelectionModel().getSelectedItem().toString();
        
        sVillageOrTownOrCity = cmbbxVillageOrTownOrCityFilter.getSelectionModel().getSelectedItem().toString();
        
        sAddressKeyword = tfAddressKeyword.getText().trim();
        
        data.clear();
        data.removeAll(data);
        tvDonorTable.getColumns().clear();
        populate_table_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);      
    }
    
    @FXML
    private void onRefreshClicked() {
        
        String sBloodGroup;
        String sAgeGroup;
        String sGender;
        String sEligibility;
        String sArea;
        String sVillageOrTownOrCity;
        String sAddressKeyword;
        
        ObservableList<String> olBloodGroup = cmbbxBloodGroupFilter.getItems();
        /* Preserve the current selection */
        Object objSelectedItem = cmbbxBloodGroupFilter.getSelectionModel().getSelectedItem();
        cmbbxBloodGroupFilter.getItems().removeAll(olBloodGroup);
        cmbbxBloodGroupFilter.getItems().add("All Blood Groups");
        cmbbxBloodGroupFilter.getItems().addAll((Object[])objDBApi.getbg());
        /* Restore the previous selection if still available */
        if (cmbbxBloodGroupFilter.getItems().indexOf(objSelectedItem) >= 0) {
            cmbbxBloodGroupFilter.getSelectionModel().select(cmbbxBloodGroupFilter.getItems().indexOf(objSelectedItem));            
        } else {
            cmbbxBloodGroupFilter.getSelectionModel().selectFirst();                        
        }

        /*Navin code*/
        ObservableList<String> o = cmbbxGenderFilter.getItems();
        /* Preserve the current selection */
        objSelectedItem = cmbbxGenderFilter.getSelectionModel().getSelectedItem();
        cmbbxGenderFilter.getItems().removeAll(o);
        Object[] gend = (Object[]) objDBApi.getgend();
        cmbbxGenderFilter.getItems().add("All Genders");
        cmbbxGenderFilter.getItems().addAll(gend);
        /*Navin code*/
        /* Restore the previous selection if still available */
        if (cmbbxGenderFilter.getItems().indexOf(objSelectedItem) >= 0) {
            cmbbxGenderFilter.getSelectionModel().select(cmbbxGenderFilter.getItems().indexOf(objSelectedItem));            
        } else {
            cmbbxGenderFilter.getSelectionModel().selectFirst();
        }

        ObservableList<String> olAreas = cmbbxAreaFilter.getItems();
        /* Preserve the current selection */
        objSelectedItem = cmbbxAreaFilter.getSelectionModel().getSelectedItem();
        cmbbxAreaFilter.getItems().removeAll(olAreas);
        cmbbxAreaFilter.getItems().add("All Areas");
        cmbbxAreaFilter.getItems().addAll((Object[])objDBApi.getAreas());
        /* Restore the previous selection if still available */
        if (cmbbxAreaFilter.getItems().indexOf(objSelectedItem) >= 0) {
            cmbbxAreaFilter.getSelectionModel().select(cmbbxAreaFilter.getItems().indexOf(objSelectedItem));
        } else {
            cmbbxAreaFilter.getSelectionModel().selectFirst();
        }
        
        ObservableList<String> olVillagesOrTownsOrCities = cmbbxVillageOrTownOrCityFilter.getItems();
        /* Preserve the current selection */
        objSelectedItem = cmbbxVillageOrTownOrCityFilter.getSelectionModel().getSelectedItem();
        cmbbxVillageOrTownOrCityFilter.getItems().removeAll(olVillagesOrTownsOrCities);
        cmbbxVillageOrTownOrCityFilter.getItems().add("All Villages/Towns/Cities");
        cmbbxVillageOrTownOrCityFilter.getItems().addAll((Object[])objDBApi.getVillagesOrTownsOrCities());
        /* Restore the previous selection if still available */
        if (cmbbxVillageOrTownOrCityFilter.getItems().indexOf(objSelectedItem) >= 0) {
            cmbbxVillageOrTownOrCityFilter.getSelectionModel().select(cmbbxVillageOrTownOrCityFilter.getItems().indexOf(objSelectedItem));
        } else {
            cmbbxVillageOrTownOrCityFilter.getSelectionModel().selectFirst();
        }

        sBloodGroup = cmbbxBloodGroupFilter.getSelectionModel().getSelectedItem().toString();

        sAgeGroup = cmbbxAgeGroupFilter.getSelectionModel().getSelectedItem().toString();

        sGender = cmbbxGenderFilter.getSelectionModel().getSelectedItem().toString();

        sEligibility = cmbbxEligibilityFilter.getSelectionModel().getSelectedItem().toString();

        sArea = cmbbxAreaFilter.getSelectionModel().getSelectedItem().toString();
        
        sVillageOrTownOrCity = cmbbxVillageOrTownOrCityFilter.getSelectionModel().getSelectedItem().toString();
        
        sAddressKeyword = tfAddressKeyword.getText().trim();

        data.clear();
        data.removeAll(data);
        tvDonorTable.getColumns().clear();
        populate_table_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);      
    }
    
    @FXML
    private void onClearClicked() {
        
        String sBloodGroup;
        String sAgeGroup;
        String sGender;
        String sEligibility;
        String sArea;
        String sVillageOrTownOrCity;
        String sAddressKeyword;
        
        cmbbxBloodGroupFilter.getSelectionModel().selectFirst();
        cmbbxAgeGroupFilter.getSelectionModel().selectFirst();
        cmbbxGenderFilter.getSelectionModel().selectFirst();
        cmbbxEligibilityFilter.getSelectionModel().selectFirst();
        cmbbxAreaFilter.getSelectionModel().selectFirst();
        cmbbxVillageOrTownOrCityFilter.getSelectionModel().selectFirst();
        tfAddressKeyword.clear();

        sBloodGroup = cmbbxBloodGroupFilter.getSelectionModel().getSelectedItem().toString();

        sAgeGroup = cmbbxAgeGroupFilter.getSelectionModel().getSelectedItem().toString();

        sGender = cmbbxGenderFilter.getSelectionModel().getSelectedItem().toString();

        sEligibility = cmbbxEligibilityFilter.getSelectionModel().getSelectedItem().toString();

        sArea = cmbbxAreaFilter.getSelectionModel().getSelectedItem().toString();
        
        sVillageOrTownOrCity = cmbbxVillageOrTownOrCityFilter.getSelectionModel().getSelectedItem().toString();
        
        sAddressKeyword = tfAddressKeyword.getText().trim();

        data.clear();
        data.removeAll(data);
        tvDonorTable.getColumns().clear();
        populate_table_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);      
    }
    
    public void populate_table_fil(String sBloodGroup, String sAgeGroup, String sGender, String sEligibility, String sArea, String sVillageOrTownOrCity, String sAddressKeyword) {

        try {
            int iRowCount = objDBApi.rows_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);
            int iColumnCount = objDBApi.getColumnCount();
            row = null;
            for (int i = 0; i < iColumnCount; i++) {
                final int p = i;
                String sColumnName = objDBApi.dluColumnName(i);
                TableColumn col = new TableColumn(sColumnName);
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(p).toString());
                    }
                });
                
                col.setMinWidth(sColumnName.length()*10);

                col.setStyle("-fx-text-alignment: center;");
                
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
