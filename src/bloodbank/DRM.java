/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import database.DBApi;

/**
 *
 * @author anirudhnegi
 */
public class DRM {
 String lbl_up;
public String disp(String did,String col,String val){
    if(did.equals(""))
    {
    lbl_up= "Please Enter DonorID";    
    }
    else{
    if((col.equals(""))||(val.equals(""))){
    lbl_up= "Profile Field or New Value missing.";
    }
    else 
    {
        try{
    new DBApi().update_value(did,col,val);
    lbl_up="Value Updated Successfully.";
        }
        catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            lbl_up="Oops!Something is wrong with the server.";
        }
    }
    }
    return lbl_up;
}
}
