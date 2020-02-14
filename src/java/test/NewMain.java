/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.Managers.*;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author ndfmac
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        // TODO code application logic here
        int userid = UserManager.CreateUser("Admin", "Admin", "Admin", "st.deemene@gmail.com", "07082828244", "admin", "");
//        int userid = UserManager.CreateUser("Subscriber", "Saint", "Deemene", "st.deemene@gmail.com", "07082828249", "saint", "");

//        int userid = UserManager.CreateUser("Subscriber", "Pinky", "Saint", "st.pinky@gmail.com", "07082828240", "pinky", "8DQE3C0PH");
        String result = TicketManager.CreateTicket(2, 4000, 1, 2, "Test343", "test232");
//         HashMap
        System.out.println(userid);
    }

}
