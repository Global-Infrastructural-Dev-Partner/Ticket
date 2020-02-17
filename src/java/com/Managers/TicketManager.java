/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Managers;

import com.Tables.Tables;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ndfmac
 */
public class TicketManager {

    public static String GetPaidAndFreeTicketNumbers(int TicketTypeID, int NumberOfTickets) {
        String result = "";
        int freeTicket = 0;
        int paidForTicket = 0;
        try {
            switch (TicketTypeID) {
                case 1://Single
                    freeTicket = 0;
                    paidForTicket = NumberOfTickets;
                    break;
                case 2://Five-In-One
                    freeTicket = 1;
                    paidForTicket = 5;
                    break;
                case 3://Ten-In-One
                    freeTicket = 0;
                    paidForTicket = NumberOfTickets;
                    break;
                case 4://Free
                    freeTicket = NumberOfTickets;
                    paidForTicket = 0;
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
        result = freeTicket + "-" + paidForTicket;
        return result;
    }

    public static String CreateTicket(int UserID, int Amount, int TicketTypeID, int NumberOfTickets, String TxnRef, String TxnCode) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        String tickets = GetPaidAndFreeTicketNumbers(TicketTypeID, NumberOfTickets);
        int freeTicket = Integer.parseInt(tickets.split("-")[0]);
        int paidForTicket = Integer.parseInt(tickets.split("-")[1]);
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.TicketsTable.UserID, UserID);
        tableData.put(Tables.TicketsTable.TicketTypeID, TicketTypeID);
        tableData.put(Tables.TicketsTable.AmountPaid, Amount);
        tableData.put(Tables.TicketsTable.NumberOfTicketBought, NumberOfTickets);
        tableData.put(Tables.TicketsTable.TicketPaidFor, paidForTicket);
        tableData.put(Tables.TicketsTable.FreeTickets, freeTicket);
        int TicketID = DBManager.insertTableDataReturnID(Tables.TicketsTable.Table, tableData, "");
        DBManager.UpdateCurrentDate(Tables.TicketsTable.Table, Tables.TicketsTable.Date, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        DBManager.UpdateCurrentTime(Tables.TicketsTable.Table, Tables.TicketsTable.Time, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        String result = PaymentsManager.CreatePayment(UserID, Amount, TxnRef, TxnCode, TicketID);
        for (int i = 0; i < NumberOfTickets; i++) {
            CreateTicketHistory(TicketID, UserID, TicketTypeID, Amount);
        }
        return result;
    }

    public static String CreateTicketHistory(int TicketID, int UserID, int TicketTypeID, int TicketAmount) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        StringBuilder htmlBuilder = new StringBuilder();
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.TicketHistoryTable.TicketID, TicketID);
        int TkID = DBManager.insertTableDataReturnID(Tables.TicketHistoryTable.Table, tableData, "");
        String TicketNumber = GenerateTicketNumber(TkID);
        String result = DBManager.UpdateStringData(Tables.TicketHistoryTable.Table, Tables.TicketHistoryTable.TicketNumber, TicketNumber, "where " + Tables.TicketHistoryTable.ID + " = " + TkID);
        String TicketType = GetTicketTypeNameByID(TicketTypeID);
        String Email = UserManager.getUserEmail(UserID);
        String UserName = UserManager.getUserName(UserID);
        String currentDate = "" + UtilityManager.CurrentDate();
        String currentTime = "" + UtilityManager.CurrentTime();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<body><h4> Dear ").append(UserName).append(",</h4><div style='margin-bottom:1em'> <h4>Congratulations!!! </h4><p>Your have successfully paid for a ticket for the PeinMoney Event.<strong><u>Ticket Details</u> </strong><br/>Ticket Number:").append(TicketNumber).append("<br/><strong>Ticket Type:</strong>").append(TicketType).append("<br/><strong>Ticket Amount:</strong>").append(TicketAmount).append("<br/><strong>Payment Date:</strong>").append(UtilityManager.readDate(currentDate)).append("<br/><strong>Payment Time:</strong>").append(UtilityManager.readTime(currentTime)).append("<br/><br/><strong>Event Date:</strong> 25th March, 2020<br/><strong>Event Time:</strong> 10am Prompt<br/><strong>Event Venue:</strong>Hotel De Oriental Lekki Phase 1</p></div><div style='text-align:center'><hr style='width:35em'><p>Thank you for buying a ticket</p><p>If you need any further assistance, please contact us by email at support@eventticket.com or call 0809 460 5555, or visit <a href='http://www.eventticket.com/'>http://www.eventticket.com/</a> </p></div></body>");
        htmlBuilder.append("</html>");
        String Body = htmlBuilder.toString();
        try {
            SendEmail(Email, Body, "Event Ticket");
        } catch (Exception ex) {

        }
        UserManager.sendMemberMessage(1, Body, "Event Ticket", UserID);
        return result;
    }

    public static String GenerateTicketNumber(int Ticketid) {
        String TicketID = "" + Ticketid;
        String result = "";
        if (TicketID.length() < 10) {
            result = "A00000" + TicketID;//A000001 =>000,001
        } else if (TicketID.length() < 100) {
            result = "A0000" + TicketID;//A000099 =>000,010
        } else if (TicketID.length() < 1000) {
            result = "A000" + TicketID;//A000999 =>000,100
        } else if (TicketID.length() < 10000) {
            result = "A00" + TicketID;//A009999 =>001,000
        } else if (TicketID.length() < 100000) {
            result = "A0" + TicketID;//A099999 =>010,000
        } else if (TicketID.length() < 1000000) {
            result = "A" + TicketID;//A999999 =>100,000
        }
        return result;
    }

    public static String SendEmail(String To, String Body, String Subject) {
        String from = "info@thewealthmarket.com";
        String result = "false";
        final String username = "info@thewealthmarket.com";//change accordingly
        final String password = "@TheWM1234";//change accordingly
//        String host = "localhost";
        String host = "thewealthmarket.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.from", from);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setContent(Body, "text/html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            result = "success";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String GetTicketTypeNameByID(int TicketTypeID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = "";
        result = DBManager.GetString(Tables.TicketTypeTable.Name, Tables.TicketTypeTable.Table, "where " + Tables.TicketTypeTable.ID + " = " + TicketTypeID);
        return result;
    }

    public static int GetTicketTypeByName(String TicketTypeName) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int result = 0;
        result = DBManager.GetInt(Tables.TicketTypeTable.ID, Tables.TicketTypeTable.Table, "where " + Tables.TicketTypeTable.Name + " = '" + TicketTypeName + "'");
        return result;
    }

    public static ArrayList<Integer> GetUserTickets(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.ID, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.UserID + " = " + UserID);
        return IDs;
    }

    public static ArrayList<Integer> GetUserTotalFreeTickets(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.FreeTickets, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.UserID + " = " + UserID);
        return IDs;
    }

    public static ArrayList<Integer> GetUserTotalPaidForTickets(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.TicketPaidFor, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.UserID + " = " + UserID);
        return IDs;
    }

    public static int GetTicketFreeTicketByID(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        Tickets = DBManager.GetInt(Tables.TicketsTable.FreeTickets, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        return Tickets;
    }

    public static int GetTicketPaidForTicketByID(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        Tickets = DBManager.GetInt(Tables.TicketsTable.TicketPaidFor, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        return Tickets;
    }

    public static int GetTicketBoughtByID(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        Tickets = DBManager.GetInt(Tables.TicketsTable.NumberOfTicketBought, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        return Tickets;
    }

    public static HashMap<String, Object> GetTicketDetails(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, Object> Details = new HashMap<>();
        Details = DBManager.GetTableObjectData(Tables.TicketsTable.Table, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        String dt = (String) Details.get(Tables.TicketsTable.Date);
        String date = UtilityManager.readDate(dt);
        Details.put(Tables.TicketsTable.Date, date);
        String tm = (String) Details.get(Tables.TicketsTable.Time);
        String time = UtilityManager.readTime(tm);
        Details.put(Tables.TicketsTable.Time, time);
        Object ticketypeid = Details.get(Tables.TicketsTable.TicketTypeID);
        int TicketTypeID = Integer.parseInt("" + ticketypeid);
        String tickettypename = GetTicketTypeNameByID(TicketTypeID);
        Details.put("TicketName", tickettypename);
        HashMap<Integer, HashMap<String, Object>> historyDetails = GetTicketHistoryDetails(TicketID);
        Details.put("historyDetails", historyDetails);
        return Details;
    }

    public static HashMap<Integer, HashMap<String, Object>> GetTicketHistoryDetails(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, Object> details = new HashMap<>();
        HashMap<Integer, HashMap<String, Object>> det = new HashMap<>();
        ArrayList<Integer> IDS = GetTicketHistory(TicketID);
        if (!IDS.isEmpty()) {
            for (int id : IDS) {
                details = DBManager.GetTableObjectData(Tables.TicketHistoryTable.Table, "where " + Tables.TicketHistoryTable.ID + " = " + id);
                det.put(id, details);
            }
        }
        return det;
    }

    public static ArrayList<Integer> GetTicketHistory(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketHistoryTable.ID, Tables.TicketHistoryTable.Table, "where " + Tables.TicketHistoryTable.TicketID + " = " + TicketID);
        return IDs;
    }

    public static ArrayList<Integer> GetAllTickets() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.ID, Tables.TicketsTable.Table, "");
        return IDs;
    }

    public static int GetSingleTickets() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.ID, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.TicketTypeID + " = " + 1);
        Tickets = IDs.size();
        return Tickets;
    }

    public static int GetFiveInOneTickets() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.ID, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.TicketTypeID + " = " + 2);
        Tickets = IDs.size();
        return Tickets;
    }

    public static int GetTenInOneTickets() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int Tickets = 0;
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.TicketsTable.ID, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.TicketTypeID + " = " + 3);
        Tickets = IDs.size();
        return Tickets;
    }

    public static int GetTicketTypeIDByTicketID(int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int id = 0;
        id = DBManager.GetInt(Tables.TicketsTable.TicketTypeID, Tables.TicketsTable.Table, "where " + Tables.TicketsTable.ID + " = " + TicketID);
        return id;
    }

}
