package bloodbank;

import database.DBApi;

/**
 *
 * @author anirudhnegi
 */
public class DDA {
 String username;
 String password;
 String lbl1="";
public String disp(String old_p,String new_p,String confirm_p){
    username=new LoginController().getUser();
    password=new LoginController().getPass();
    if(!old_p.equals(password))
    {
    lbl1= "Entered Correct Old password.";    
    }
    else{
    if((new_p == null)||(new_p.equals(""))){
    lbl1= "Password Field must not be Empty.";
    }
    else 
    {
        if(!new_p.equals(confirm_p)){
    lbl1="Password do not Match.";
    }
    else{
        try{
    new DBApi().change_pass(username,new_p);
    lbl1="Password Updated Successfully.";
        }
        catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            lbl1="Error in Connecting Server.";
        }
    }
    }
    }
    return lbl1;
}
}
