package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anirudhnegi
 */
public class DBApi {
    
    static final String RESIDENCE_MOBILE_NUMBER = "ResMobile";
    static final String OFFICE_MOBILE_NUMBER = "OffMobile";
    
    static final String RESIDENCE_LANDLINE_NUMBER = "ResPhone";
    static final String OFFICE_LANDLINE_NUMBER = "OffPhone";
    
    static final String RESIDENCE_ADDRESS = "ResAddr";
    static final String OFFICE_ADDRESS = "OffAddr";
    
    String commonq = "SELECT donorid, name, age, bloodgroup, gender, " +
                     "coalesce(NULLIF(resmobile, ''), NULLIF(officemobile, ''), " +
                     "NULLIF(resphone, ''), NULLIF(officephone, ''), " +
                     "NULLIF(resemail, ''), NULLIF(officeemail, '')) as contact, " +
                     "donor_type, " +
                     "coalesce(concat(resaddress, IF(length(resaddress) > 0, ',\\n', NULL), " +
                                     "resarea, IF(length(resarea) > 0, ',\\n', ''), " +
                                     "resvillageortownorcity, IF(length(resvillageortownorcity) > 0, ',\\n', ''), " +
                                     "restaluk, IF(length(restaluk) > 0, ',\\n', ''), " +
                                     "resdistrict, IF(length(resdistrict) > 0, ',\\n', ''), " +
                                     "respincode), " +
                              "concat(officeaddress, IF(length(officeaddress) > 0, ',\\n', NULL), " +
                                     "officearea, IF(length(officearea) > 0, ',\\n', ''), " +
                                     "officevillageortownorcity, IF(length(officevillageortownorcity) > 0, ',\\n', ''), " +
                                     "officetaluk, IF(length(officetaluk) > 0, ',\\n', ''), " +
                                     "officedistrict, IF(length(officedistrict) > 0, ',\\n', ''), " +
                                     "officepincode)) as address " +
                     "FROM profile";
    String commonq1= "(TIMESTAMPDIFF(DAY,DATE_FORMAT(STR_TO_DATE(nsdod,'%d/%m/%Y'),'%Y-%m-%d'),DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) >= 0)";
    
    String strDSEQuery = "SELECT donorid as 'Donor ID', name as 'Name', " +
                         "spousename as 'Father/Spouse Name', dob as 'DOB', " +
                         "gender as 'Gender', bloodgroup as 'BloodGroup', " +
                         "resmobile as 'ResMobile', officemobile as 'OffMobile', " +
                         "resphone as 'ResPhone', officephone as 'OffPhone', " +
                         "concat(resaddress, IF(length(resaddress) > 0, ',\\n', ''), " +
                                "resarea, IF(length(resarea) > 0, ',\\n', ''), " +
                                "resvillageortownorcity, IF(length(resvillageortownorcity) > 0, ',\\n', ''), " +
                                "restaluk, IF(length(restaluk) > 0, ',\\n', ''), " +
                                "resdistrict, IF(length(resdistrict) > 0, ',\\n', ''), " +
                                "respincode) " +
                         "as ResAddr, " +
                         "concat(officeaddress, IF(length(officeaddress) > 0, ',\\n', ''), " +
                                "officearea, IF(length(officearea) > 0, ',\\n', ''), " +
                                "officevillageortownorcity, IF(length(officevillageortownorcity) > 0, ',\\n', ''), " +
                                "officetaluk, IF(length(officetaluk) > 0, ',\\n', ''), " +
                                "officedistrict, IF(length(officedistrict) > 0, ',\\n', ''), " +
                                "officepincode) " +
                         "as OffAddr " +
                         "FROM profile";
    
    String commonp = "SELECT * FROM donor_dir where";      
    String commonp1=" (TIMESTAMPDIFF(DAY,DATE_FORMAT(STR_TO_DATE(dor,'%d/%m/%Y'),'%Y-%m-%d'),DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) >90) AND "
            + "(TIMESTAMPDIFF(DAY,DATE_FORMAT(STR_TO_DATE(dor,'%d/%m/%Y'),'%Y-%m-%d'),DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) >=0 )";
    ResultSet res1;
    String[] questions;
    String[] def_ans;
    String user_name;
    ResultSet res = null;
    Connection conn = null;
    Statement stmt = null;
    String sql;
    int R = 0;
    Object[][] MyArray = null;
    ArrayList<Object[]> MyArrayList = null;
    String[] AttributeNames;
    private int NumberOfRows, NumberOfColumns;
    
///*
    String sDBServer = "209.126.96.72";
    String sDBSchema = "bloodbank";
    String sUser = "Bloodbank";
    String sPasswd = "Blo0db@nk600091";
//*/    
/*
    String sDBServer = "localhost";
    String sDBSchema = "bloodbank";
    String sUser = "root";
    String sPasswd = "rO0t@locAlH0st";
*/
    //Connecting to database
    public void connect() {

        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://" + sDBServer + "/" + sDBSchema;
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database server at " + sDBServer);
            conn = DriverManager.getConnection(DB_URL, sUser, sPasswd);
            //System.out.println("Verifying Credentials...");

            System.out.println("Connected");
        } catch (SQLException | ClassNotFoundException se) {
            System.out.println(se);
        }

    }

    //Login Verification
    public int login(String uname, String pass, int flag) {

        try {
            this.connect();
            stmt = conn.createStatement();
            sql = "SELECT count(*) as total FROM staff where username='" + uname + "'and password='" + pass + "'";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                rs.next();
                int x = rs.getInt("total");
                if (x != 0) {
                    user_name = uname;
                    flag = 1;
                }
            }
            stmt.close();
           // conn.close();
        } catch (SQLException se) {
            System.out.println("Wrong query");
        }

        return flag;

    }
    public Boolean donorexist(){
        return true;
    }
    //Insert data
    public boolean insert(String[] arr) {
        boolean bReturn = false;
        this.connect();
        System.out.println("insert");
        try {
             sql = "INSERT INTO `" + sDBSchema + "`.`profile` (`donorid`, `name`, `dob`, `age`, `bloodgroup`, `gender`, `spousename`, `education`, `occupation`, `resaddress`, `resphone`, `resmobile`, `email`, `officeaddress`, `officephone`, `officemobile`, `officeemail`, `dor`, `nsdod`, `donor_type`, `willl_bday`, `will_wed_day`, `will_term`) VALUES (";
            for (int p = 0; p < 23; p++) {
                sql += "'" + arr[p] + "'";
                if (p != 22) {
                    sql += ",";
                }
            }
            sql += ")";
            stmt = conn.createStatement();
            bReturn = (stmt.executeUpdate(sql) == 1) ? true : false;
            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
            System.out.println("ERR");
        }
        return bReturn;
    }
        /*Navin code 104 to 125*/
     public boolean insert1(String[] arr) {
        boolean bReturn = false;
        this.connect();
        System.out.println("insert1");
        try {
             sql = "INSERT INTO `" + sDBSchema + "`.`profile` ( `name`, `dob`, `age`,`bloodgroup`, `gender`, `spousename`, `education`, `occupation`, `resaddress`, `resarea`, `resvillageortownorcity`, `restaluk`, `resdistrict`, `respincode`, `resphone`, `resmobile`, `resemail`, `officeaddress`, `officearea`, `officevillageortownorcity`, `officetaluk`, `officedistrict`, `officepincode`, `officephone`, `officemobile`, `officeemail`, `dor`, `nsdod`, `donor_type`, `willl_bday`, `will_wed_day`, `will_oth_day`, `will_term`) VALUES (";
            for (int p = 0; p < arr.length; p++) {
                sql += "'" + arr[p] + "'";
                if (p != arr.length - 1) {
                    sql += ",";
                }
                
            }
            sql += ")";
            System.out.println("Query : "+sql);
            stmt = conn.createStatement();
            bReturn = (stmt.executeUpdate(sql) == 1);
            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
            System.out.println("ERR");
        }
        return bReturn;
    }
         /*Navin code 104 to 125*/
// UPDATE PASSWORD
    public void change_pass(String user, String new_pass) throws SQLException {
        
        try {
            this.connect();
            sql = "UPDATE staff set password='" + new_pass + "' where username='" +user+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Done");
               stmt.close();
        } catch (SQLException se) {
            System.out.println("ERR");
        }

    }

    // UPDATE PASSWORD
    public void update_value(String did, String col,String val) throws SQLException {
        
        try {
            this.connect();
            sql = "UPDATE profile set "+col+"='" + val + "' where donorid='" +did+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Done");
               stmt.close();
        } catch (SQLException se) {
            System.out.println("ERR");
        }

    }

//DLU - Get Data
   public void dlu_fil(String val, String val1, String val2, String sListing, String sArea, String sVillageOrTownOrCity, String sAddressKeyword) throws SQLException {

        try {
            StringBuilder sbQueryStatement = new StringBuilder();
            String sLogicAND = "";
            
            stmt = conn.createStatement();
            stmt.setQueryTimeout(10);
            
            sbQueryStatement.append(commonq);
            
            if (!val.contains("All") || !val1.contains("All") || !val2.contains("All") || !sListing.contains("All") || !sArea.contains("All") || !sVillageOrTownOrCity.contains("All") || sAddressKeyword.length() > 0) {
                sbQueryStatement.append(" where ");
            
                if (!val.contains("All")) {
                    sbQueryStatement.append("bloodgroup='").append(val).append("'");
                    sLogicAND = " AND ";
                }

                if (!val1.contains("All")) {
                    sbQueryStatement.append(sLogicAND);

                    if (val1.equals("18-30")) {
                        sbQueryStatement.append("age BETWEEN 18 AND 30");
                        sLogicAND = " AND ";
                    } else if (val1.equals("31-40")) {
                        sbQueryStatement.append("age BETWEEN 31 AND 40");
                        sLogicAND = " AND ";
                    } else if (val1.equals("41-50")) {
                        sbQueryStatement.append("age BETWEEN 41 AND 50");
                        sLogicAND = " AND ";
                    } else if (val1.equals("51-60")) {
                        sbQueryStatement.append("age BETWEEN 51 AND 60");                        
                        sLogicAND = " AND ";
                    }
                }

                if (!val2.contains("All")) {
                    sbQueryStatement.append(sLogicAND);
                    sbQueryStatement.append("gender='").append(val2).append("'");
                    sLogicAND = " AND ";
                }            

                if (sListing.equals("Only Eligible Donors")) {
                    sbQueryStatement.append(sLogicAND);
                    sbQueryStatement.append(commonq1);
                }

                /*
                 * When we perform an address based search,
                 * we search for the address details to be ALL
                 * under Residence or Office address, NOT a mix of both.
                 * Therefore, the address search query is framed as below ...
                 * (
                 *  (
                 *   (resarea='<sArea>') OR
                 *   (officearea='<sArea>')
                 *  )
                 *  AND
                 *  (
                 *   (resvillageortownorcity='<sVillageOrTownOrCity>')
                 *   (officevillageortownorcity='<sVillageOrTownOrCity>')
                 *  )
                 * )
                 */
                if (!sArea.contains("All") || !sVillageOrTownOrCity.contains("All")) {
                    sbQueryStatement.append(sLogicAND);
                    sbQueryStatement.append("(");
                    /* Start of a new paranthesised expression*/
                    sLogicAND = "";
                }
                
                
                if (!sArea.contains("All")) {
                    sbQueryStatement.append("(");
                    sbQueryStatement.append("resarea='").append(sArea).append("'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officearea='").append(sArea).append("'");
                    sbQueryStatement.append(")");
                    sLogicAND = " AND ";                    
                }
                
                if (!sVillageOrTownOrCity.contains("All")) {                                    
                    sbQueryStatement.append(sLogicAND);
                    sbQueryStatement.append("(");
                    sbQueryStatement.append("resvillageortownorcity='").append(sVillageOrTownOrCity).append("'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officevillageortownorcity='").append(sVillageOrTownOrCity).append("'");
                    sbQueryStatement.append(")");
                }

                if (!sArea.contains("All") || !sVillageOrTownOrCity.contains("All")) {
                    sbQueryStatement.append(")");
                    sLogicAND = " AND ";
                }                
                
                /*
                 * When we perform an address keyword based search,
                 * we search for the keyword to appear under any of
                 * the Residence or Office address fields, including
                 * Area, VillageOrTownOrCity, Taluk & District.
                 * Therefore, the address keyword search query is framed as below ...
                 * (
                 *  //Residence address fields
                 *  resaddress like '%<sAddressKeyword>%' OR
                 *  resarea like '%<sAddressKeyword>%' OR
                 *  resvillageortownorcity like '%<sAddressKeyword>%' OR
                 *  restaluk like '%<sAddressKeyword>%' OR
                 *  resdistrict like '%<sAddressKeyword>%' OR
                 *  //Office address fields
                 *  officeaddress like '%<sAddressKeyword>%' OR
                 *  officearea like '%<sAddressKeyword>%' OR
                 *  officevillageortownorcity like '%<sAddressKeyword>%' OR
                 *  officetaluk like '%<sAddressKeyword>%' OR
                 *  officedistrict like '%<sAddressKeyword>%' OR
                 * )
                 */
                if (sAddressKeyword.length() > 0) {
                    
                    sbQueryStatement.append(sLogicAND);

                    sbQueryStatement.append("(");
                    
                    sbQueryStatement.append("resaddress like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("resarea like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("resvillageortownorcity like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("restaluk like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("resdistrict like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officeaddress like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officearea like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officevillageortownorcity like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officetaluk like '%").append(sAddressKeyword).append("%'");
                    sbQueryStatement.append(" OR ");
                    sbQueryStatement.append("officedistrict like '%").append(sAddressKeyword).append("%'");

                    sbQueryStatement.append(")");
                }// ~if (sArea.length() > 0 || sVillageOrTownOrCity.length() > 0 || sTaluk.length() > 0 || sDistrict.length() > 0)
            }// ~if (!val.equals("null") || !val1.equals("null") || !val2.equals("null") || !sListing.equals("null") || sArea.length() > 0 || sVillageOrTownOrCity.length() > 0 || sTaluk.length() > 0 || sDistrict.length() > 0)
            
            System.out.println("Executing query : " + sbQueryStatement.toString());
            
            res = stmt.executeQuery(sbQueryStatement.toString());
            
            ResultSetMetaData RSMD = res.getMetaData();
            NumberOfColumns = RSMD.getColumnCount();
            AttributeNames = new String[NumberOfColumns];
            for (int i = 0; i < NumberOfColumns; i++) {
                String ab=RSMD.getColumnName(i + 1);

                if(ab.equals("donorid"))
                    AttributeNames[i]="Donor ID";
                else if(ab.equals("name"))
                    AttributeNames[i]="Name";
                else if(ab.equals("age"))
                    AttributeNames[i]="Age";
                else if(ab.equals("bloodgroup"))
                    AttributeNames[i]="BloodGroup";
                else if(ab.equals("gender"))
                    AttributeNames[i]="Gender";
                else if(ab.equals("contact"))
                    AttributeNames[i]="Contact Info";
                else if(ab.equals("donor_type"))
                    AttributeNames[i]="Donor Type";
                else if(ab.equals("address"))
                    AttributeNames[i]="Address";
/*
                else if(ab.equals("dor"))
                    AttributeNames[i]="Registration Date";
                else if(ab.equals("nsdod"))
                    AttributeNames[i]="Next Suggested Donation Date";
                else
                    AttributeNames[i] = RSMD.getColumnName(i + 1);
*/
            }
            MyArray = new Object[10000][NumberOfColumns];
            R = 0;
            while (res.next()) {
                for (int C = 1; C <= NumberOfColumns; C++) {
                    MyArray[R][C - 1] = res.getObject(C);
                }
                R++;
            }
            res.close();
            NumberOfRows = R;
            Object[][] TempArray = MyArray;
            MyArray = new Object[NumberOfRows][NumberOfColumns];
            for (R = 0; R < NumberOfRows; R++) {
                for (int C = 0; C < NumberOfColumns; C++) {
                    MyArray[R][C] = TempArray[R][C];
                }
            }
            stmt.close();
        } catch (SQLException e) {

        }
    }

    //Column Name
    public String dluColumnName(int i) {

        return AttributeNames[i];
    }

    //Row
    public String dluColumnValue(int k, int i) {

        return MyArray[k][i].toString();
    }

    //NUMBER OF COLUMNS
    public int columns_fil(String sBloodGroup, String sAgeGroup, String sGender, String sEligibility, String sArea, String sVillageOrTownOrCity, String sAddressKeyword) {
        try {
            this.dlu_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);
        } catch (SQLException ex) {
            System.out.println("Error :"+ex);
        }
        
        return NumberOfColumns;
    }

    // NUMBER OF ROWS  
    public int rows_fil(String sBloodGroup, String sAgeGroup, String sGender, String sEligibility, String sArea, String sVillageOrTownOrCity, String sAddressKeyword) {
        try {
            this.dlu_fil(sBloodGroup, sAgeGroup, sGender, sEligibility, sArea, sVillageOrTownOrCity, sAddressKeyword);
        } catch (SQLException ex) {
            System.out.println("Error :"+ex);
        }
        
        return NumberOfRows;
    }

    public int getColumnCount() {
        return NumberOfColumns;
    }
    
    public String[] question(String i) {
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT * FROM questions where sectionname='" + i + "' ORDER BY questionno");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("question"));
            }
            questions = (String[]) rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();
        } catch (SQLException e) {
        }
        return questions;
    }

    public String[] def_answers(String i) {
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT * FROM questions where sectionname='" + i + "' ORDER BY questionno");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("def_ans"));
            }
            def_ans = (String[]) rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();
        } catch (SQLException e) {
        }
        return def_ans;
    }

    public void sub_answers(String[] answers) {
        try {
           // this.connect();
        } catch (Exception ex) {

        }

    }
    public String[] getbg(){
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT DISTINCT bloodgroup FROM profile");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("bloodgroup"));
            }
            def_ans = (String[]) rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();
        } catch (SQLException e) {
        }
        return def_ans;
    }
    public String[] getgend(){
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT DISTINCT gender FROM profile");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("gender"));
            }
            def_ans = (String[]) rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();
        } catch (SQLException e) {
        }
        return def_ans;
    }

    public String[] getAreas() {
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT DISTINCT resarea, officearea FROM profile where resarea != \"\" AND officearea != \"\"");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("resarea"));
                rowValues.add(res.getString("officearea"));
            }
            def_ans = (String[])rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();
        } catch (SQLException e) {
        }
        
        return def_ans;
    }
    
    public String[] getVillagesOrTownsOrCities() {
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT DISTINCT resvillageortownorcity, officevillageortownorcity FROM profile where resvillageortownorcity != \"\" AND officevillageortownorcity != \"\"");
            List rowValues = new ArrayList();
            while (res.next()) {
                rowValues.add(res.getString("resvillageortownorcity"));
                rowValues.add(res.getString("officevillageortownorcity"));
            }
            def_ans = (String[])rowValues.toArray(new String[rowValues.size()]);
            res.close();
            stmt.close();            
        } catch (SQLException e) {
            
        }
        
        return def_ans;
    }
    
    public String dlu_fil1(String val1, String val2) {

       String query="";
            if (val1.equals("null") && val2.equals("null")) {
                query = commonp+commonp1;
            } else if ( val1.equals("null") && !val2.equals("null")) {

                query =commonp+commonp1
                        + "AND gender='" + val2 + "'" ;
            } else if ( !val1.equals("null") && val2.equals("null")) {
                if (val1.equals("case1")) {
                    query = commonp+commonp1
                            + "AND age BETWEEN 18 AND 30";
                } else if (val1.equals("case2")) {
                    query = commonp+commonp1
                            + "AND age BETWEEN 30 AND 40"
                    ;
                } else if (val1.equals("case3")) {
                    query =commonp+commonp1
                            + "AND age BETWEEN 40 AND 50"
                    ;
                }
            } else if (!val1.equals("null") && !val2.equals("null")) {
                if (val1.equals("case1")) {
                    query = commonp+commonp1
                            + "AND gender='" + val2 + "' AND age BETWEEN 18 AND 30"
                    ;
                }
                if (val1.equals("case2")) {
                    query= commonp+commonp1
                            + "AND gender='" + val2 + "' AND age BETWEEN 30 AND 40"
                    ;
                }
                if (val1.equals("case3")) {
                    query = commonp+commonp1
                            + "AND gender='" + val2 + "' AND age BETWEEN 40 AND 50"
                    ;
                }
            }
         return  query;
    }
            
//DSE - Search for donor records
    public void dseDonorSearch(String strDonorId,
                               String strMobileNumber,
                               String strDOB,
                               String strLandlineNumber,
                               String strPincode,
                               String strName,
                               String strFatherOrSpouseName) throws SQLException {
        
        try {
            StringBuilder sbQueryStatement = new StringBuilder();
            Object[] objaryRecord;
            int iConditionCount;
            
            stmt = conn.createStatement();
            stmt.setQueryTimeout(10);
            
            sbQueryStatement.append(strDSEQuery);
            sbQueryStatement.append(" where");
            
            iConditionCount = 0;
            
            if (strDonorId != null) {
                sbQueryStatement.append(" donorid = '").append(strDonorId).append("'");
            } else {
                if (strMobileNumber != null) {
                    sbQueryStatement.append(" (resmobile = '").append(strMobileNumber).append("'");
                    sbQueryStatement.append(" OR officemobile = '").append(strMobileNumber).append("')");
                    iConditionCount++;
                }
                
                if (strDOB != null) {
                    if (iConditionCount > 0) {
                        sbQueryStatement.append(" AND");
                    }
                    
                    sbQueryStatement.append(" dob = '").append(strDOB).append("'");
                }
                
                if (strLandlineNumber != null) {
                    if (iConditionCount > 0) {
                        sbQueryStatement.append(" AND");
                    }
                    
                    sbQueryStatement.append(" (resphone = '").append(strLandlineNumber).append("'");
                    sbQueryStatement.append(" OR officephone = '").append(strLandlineNumber).append("')");
                }
                
                if (strPincode != null) {
                    if (iConditionCount > 0) {
                        sbQueryStatement.append(" AND");
                    }
                    
                    sbQueryStatement.append(" (respincode = '").append(strPincode).append("'");
                    sbQueryStatement.append(" OR officepincode = '").append(strPincode).append("')");
                }
                
                if (strName != null) {
                    if (iConditionCount > 0) {
                        sbQueryStatement.append(" AND");
                    }
                    
                    sbQueryStatement.append(" name LIKE '%").append(strName).append("%'");
                }
                
                if (strFatherOrSpouseName != null) {
                    if (iConditionCount > 0) {
                        sbQueryStatement.append(" AND");
                    }
                    
                    sbQueryStatement.append(" spousename LIKE '%").append(strFatherOrSpouseName).append("%'");
                }
            }
            
            System.out.println("Executing query : " + sbQueryStatement.toString());
            
            res = stmt.executeQuery(sbQueryStatement.toString());
            
            ResultSetMetaData RSMD = res.getMetaData();
            NumberOfColumns = RSMD.getColumnCount();
            AttributeNames = new String[NumberOfColumns];
            for (int i = 0; i < NumberOfColumns; i++) {
                AttributeNames[i] = RSMD.getColumnLabel(i + 1);
            }
            MyArrayList = new ArrayList<>();
            R = 0;
            while (res.next()) {
                objaryRecord = new Object[NumberOfColumns];
                for (int C = 1; C <= NumberOfColumns; C++) {
                    objaryRecord[C-1] = res.getObject(C);
                }
                MyArrayList.add(objaryRecord);
                R++;
            }
            res.close();
            NumberOfRows = R;
            stmt.close();
        } catch (SQLException e) {

        }
    }
    
    public int dseColumnCount() {
        return NumberOfColumns;
    }
    
    public int dseRowCount() {
        return NumberOfRows;
    }
    
    public String dseColumnName(int iIndex) {
        return AttributeNames[iIndex];
    }
    
    public String dseColumnValue(int iRowIndex, int iColumnIndex) {
        return MyArrayList.get(iRowIndex)[iColumnIndex].toString();
    }
    
}
