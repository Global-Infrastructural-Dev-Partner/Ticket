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

/**
 *
 * @author ndfmac
 */
public class UserManager {

    public static boolean checkEmailAddressOrPhoneNumberExist(String string_to_check) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        boolean result = false;
        int usid = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.Email + " = '" + string_to_check + "' or " + Tables.UsersTable.PhoneNumber + " = '" + string_to_check + "'");
        if (usid != 0) {
            result = true;
        }
        return result;
    }

    public static int getUserID(String EmailAddress, String PhoneNumber) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int usid = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.Email + " = '" + EmailAddress + "' and " + Tables.UsersTable.PhoneNumber + " = '" + PhoneNumber + "'");
        return usid;
    }

    public static String getUserStatus(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = DBManager.GetString(Tables.UsersTable.UserType, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        return result;
    }

    public static int checkPasswordEmailMatch(String Password, String Email_PhoneNum) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int result = 0;
        String memPassword = "";
        String email = Email_PhoneNum;
        memPassword = DBManager.GetString(Tables.UsersTable.Password, Tables.UsersTable.Table, "where " + Tables.UsersTable.Email + " = '" + Email_PhoneNum + "'");
        if (memPassword.equals("none")) {
            memPassword = DBManager.GetString(Tables.UsersTable.Password, Tables.UsersTable.Table, "where " + Tables.UsersTable.PhoneNumber + " = '" + Email_PhoneNum + "'");
            email = DBManager.GetString(Tables.UsersTable.Email, Tables.UsersTable.Table, "where " + Tables.UsersTable.PhoneNumber + " = '" + Email_PhoneNum + "'");
        }
        if (memPassword.equals(Password)) {
            result = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.Email + " = '" + email + "'");
        }
        return result;
    }

//    public static String UpdateUserStatus(int userid, String status) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
//        String result = DBManager.UpdateStringData(Tables.UsersTable.Table, Tables.UsersTable.Status, status, "where " + Tables.UsersTable.ID + " = " + userid);
//        return result;
//    }
    public static ArrayList<Integer> GetAllUsers() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.UsersTable.ID, Tables.UsersTable.Table, "");
        return IDs;
    }

    public static HashMap<String, String> GetUserDetails(int objID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, String> FavDetails = new HashMap<>();
        HashMap<String, String> Details = new HashMap<>();
        FavDetails = DBManager.GetTableData(Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + objID);
        FavDetails.putAll(Details);
        return FavDetails;
    }

//    public static ArrayList<Integer> getInboxMessageIDs(int meid) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids = DBManager.GetIntArrayList(Tables.MessagesTable.ID, Tables.MessagesTable.Table, "where " + Tables.MessagesTable.ToMemberID + " = " + meid);
//        return ids;
//    }
//
//    public static ArrayList<Integer> getSentMessageIDs(int meid) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids = DBManager.GetIntArrayList(Tables.MessagesTable.ID, Tables.MessagesTable.Table, "where " + Tables.MessagesTable.FromMemberID + " = " + meid);
//        return ids;
//    }
//    public static HashMap<String, String> GetMessageDetails(int MsgID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
//        HashMap<String, String> msgdetails = DBManager.GetTableData(Tables.MessagesTable.Table, "where " + Tables.MessagesTable.ID + " = " + MsgID);
//        int FromUserID = Integer.parseInt(msgdetails.get("from_member_id"));
//        String sendername = getUserName(FromUserID);
//        msgdetails.put("SenderName", sendername);
//        int ToUserID = Integer.parseInt(msgdetails.get("to_member_id"));
//        String recievername = getUserName(ToUserID);
//        msgdetails.put("RecieverName", recievername);
//        String Msgdate = DBManager.GetString("date", "messages", "where id =" + MsgID);
//        String msgdate = Msgdate.substring(0, Msgdate.length() - 10);
//        String msgtime = Msgdate.substring(10, Msgdate.length() - 2);
//        msgdetails.put("msgdate", msgdate);
//        msgdetails.put("msgtime", msgtime);
//        msgdetails.put("id", "" + MsgID);
//        return msgdetails;
//    }
    public static String getUserName(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String Name = "", FirstName, LastName;
        FirstName = DBManager.GetString(Tables.UsersTable.FirstName, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        LastName = DBManager.GetString(Tables.UsersTable.LastName, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        Name = FirstName + " " + LastName;
        return Name;
    }

    public static String sendMemberMessage(int sender, String bdy, String subject, int recipientid) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.NotificationsTable.Subject, subject);
        tableData.put(Tables.NotificationsTable.FromUserID, sender);
        tableData.put(Tables.NotificationsTable.ToUserID, recipientid);
        tableData.put(Tables.NotificationsTable.Body, bdy);
        int msgid = DBManager.insertTableDataReturnID(Tables.NotificationsTable.Table, tableData, "");
        String result = DBManager.UpdateCurrentTime(Tables.NotificationsTable.Table, Tables.NotificationsTable.Time, "where " + Tables.NotificationsTable.ID + " = " + msgid);
        DBManager.UpdateCurrentDate(Tables.NotificationsTable.Table, Tables.NotificationsTable.Date, "where " + Tables.NotificationsTable.ID + " = " + msgid);
        return result;
    }

    public static HashMap<String, String> getSearchResult(String UserInput, int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, String> details = new HashMap<>();
        String userName = "none";
        String userPhone = "none";
        String userEmail = "none";
        if (!UserInput.equals("")) {
            int memberID = UserManager.checkVerifyingEmail(UserInput);
            if (memberID == 0) {
                int membID = UserManager.checkVerifyingPhone(UserInput);
                if (membID != 0) {
                    userName = getUserName(membID);
                    userPhone = UserManager.getUserPhone(membID);
                    userEmail = UserManager.getUserEmail(membID);
                    details.put("Beneficiaryid", "" + membID);
                }
            } else {
                userName = getUserName(memberID);
                userPhone = UserManager.getUserPhone(memberID);
                userEmail = UserManager.getUserEmail(memberID);
                details.put("Beneficiaryid", "" + memberID);
            }
        } else {
            userName = getUserName(UserID);
            userPhone = UserManager.getUserPhone(UserID);
            userEmail = UserManager.getUserEmail(UserID);
            details.put("Beneficiaryid", "" + UserID);
        }
        details.put("BeneficiaryName", userName);
        details.put("BeneficiaryPhone", "" + userPhone);
        details.put("BeneficiaryEmail", "" + userEmail);
        return details;
    }

    public static int checkVerifyingEmail(String email) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int benid = 0;
        benid = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.Email + " = '" + email + "'");
        return benid;
    }

    public static int checkVerifyingPhone(String phone) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int benid = 0;
        benid = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.PhoneNumber + " = '" + phone + "'");
        return benid;
    }

    public static String getUserPhone(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String benid = "";
        benid = DBManager.GetString(Tables.UsersTable.PhoneNumber, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        return benid;
    }

    public static String getUserPassword(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String benid = "";
        benid = DBManager.GetString(Tables.UsersTable.Password, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        return benid;
    }

    public static String getUserEmail(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String Email = DBManager.GetString(Tables.UsersTable.Email, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + UserID);
        return Email;
    }

    public static int CreateUser(String UserType, String FirstName, String LastName, String EmailAddress, String PhoneNumber, String Password, String ReferralUserLink) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        String myReferralLink = CreateUserReferralLink();
        int ReferralUserID = GetReferralUserID(ReferralUserLink);
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.UsersTable.UserType, UserType);
        tableData.put(Tables.UsersTable.FirstName, FirstName);
        tableData.put(Tables.UsersTable.LastName, LastName);
        tableData.put(Tables.UsersTable.Email, EmailAddress);
        tableData.put(Tables.UsersTable.PhoneNumber, PhoneNumber);
        tableData.put(Tables.UsersTable.Password, Password);
        tableData.put(Tables.UsersTable.ReferralLink, myReferralLink);
        tableData.put(Tables.UsersTable.ReferralUserID, ReferralUserID);
        int UserID = DBManager.insertTableDataReturnID(Tables.UsersTable.Table, tableData, "");
        UpdateCreateUser(UserID, ReferralUserLink, ReferralUserID);
        return UserID;
    }

    public static String UpdateCreateUser(int UserID, String ReferralUserLink, int ReferralUserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        String result = "";
        StringBuilder htmlBuilder = new StringBuilder();
        if (!ReferralUserLink.equals("")) {
            int refCount = DBManager.GetInt(Tables.UsersTable.ReferralCount, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + ReferralUserID);
            int newrefCount = 0;
            if (refCount == 10) {
                result = TicketManager.CreateTicket(UserID, 0, 3, 1, "Free-Ticket-Reference", "Free-Ticket-Code");
                DBManager.UpdateIntData(Tables.UsersTable.ReferralCount, newrefCount, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + ReferralUserID);
            } else {
                refCount++;
                DBManager.UpdateIntData(Tables.UsersTable.ReferralCount, refCount, Tables.UsersTable.Table, "where " + Tables.UsersTable.ID + " = " + ReferralUserID);
            }
        }
        DBManager.UpdateCurrentDate(Tables.UsersTable.Table, Tables.UsersTable.DateRegistered, "where " + Tables.UsersTable.ID + " = " + UserID);
        DBManager.UpdateCurrentTime(Tables.UsersTable.Table, Tables.UsersTable.TimeRegistered, "where " + Tables.UsersTable.ID + " = " + UserID);
        String msgbdy = "Congratulations!!! Your account has been created successfully for the PeinMoney Event.";
        sendMemberMessage(1, msgbdy, "PeinMoney Subscriber Account Created", UserID);
        String UserName = UserManager.getUserName(UserID);
        String UserEmail = UserManager.getUserEmail(UserID);
        String UserPassword = UserManager.getUserPassword(UserID);
        String ReferalEmailLink = "http://localhost:8084/Ticket/Register?action=Register&userOnlineReferralLink=" + ReferralUserLink;
        htmlBuilder.append("<!DOCTYPE html><html>");
        htmlBuilder.append("<body>"
                + "<h2 style='color:#d85a33'> Dear " + UserName + "</h2>"
                + "<div style='margin-bottom:2em'> "
                + "<h3>Congratulations!!! </h3>"
                + "<p>Your account has been created successfully for the PeinMoney Event. <br/>"
                + "<strong><u>Login Details</u> </strong><br/>"
                + "<strong>Email:<strong> " + UserEmail + "<br/>"
                + "<strong>Password:</strong> " + UserPassword + "</br>"
                + "<br/><strong>Referral Link:</strong>  <a href=" + ReferalEmailLink + " target='blank'>Referal Link</a> </p>"
                + "</div>"
                + "<div style='text-align:center'>"
                + "<hr style='width:35em'>"
                + "<p>Thank you for registering.</p>"
                + "<p>If you need any further assistance, please contact us by email at support@eventticket.com or call 0809 460 5555, or visit <a href='http://www.eventticket.com/'>http://www.eventticket.com/</a> </p>"
                + "</div></body>");
        htmlBuilder.append("</html>");
        String Body = htmlBuilder.toString();
        TicketManager.SendEmail(UserEmail, "Subscriber Account Created - PeinMoney Event", Body);
        result = WalletManager.CreateWallet(UserID);
        return result;
    }

    public static String CreateUserReferralLink() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String reflink = "";
        String myReferralLink = UtilityManager.GenerateAlphaNumericCode(9);
        ArrayList<String> RLinks = DBManager.GetStringArrayList(Tables.UsersTable.ReferralLink, Tables.UsersTable.Table, "");
        if (!RLinks.isEmpty()) {
            for (String link : RLinks) {
                if (link.equals(myReferralLink)) {
                    CreateUserReferralLink();
                } else {
                    reflink = myReferralLink;
                }
            }
        } else {
            reflink = myReferralLink;
        }
        return reflink;
    }

    public static int GetReferralUserID(String ReferralUserLink) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int userid = 0;
        userid = DBManager.GetInt(Tables.UsersTable.ID, Tables.UsersTable.Table, "where " + Tables.UsersTable.ReferralLink + " = '" + ReferralUserLink + "'");
        return userid;
    }

    public static String UpdateSessionID(String SessionID, int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = "";
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.SessionTable.SessionID, SessionID);
        tableData.put(Tables.SessionTable.UserID, UserID);
        int id = DBManager.insertTableDataReturnID(Tables.SessionTable.Table, tableData, "");
        result = DBManager.UpdateCurrentTime(Tables.SessionTable.Table, Tables.SessionTable.Time, "where " + Tables.SessionTable.ID + " = " + id);
        DBManager.UpdateCurrentDate(Tables.SessionTable.Table, Tables.SessionTable.Date, "where " + Tables.SessionTable.ID + " = " + id);
        return result;
    }

    public static int GetUserIDBySessionID(String SessionID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int userid = 0;
        userid = DBManager.GetInt(Tables.SessionTable.UserID, Tables.SessionTable.Table, "where " + Tables.SessionTable.SessionID + " = '" + SessionID + "'");
        return userid;
    }

}
