/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Managers;

import com.Tables.Tables;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ndfmac
 */
public class PaymentsManager {

    public static final int MainAccountID = 1;
    public static final int DepositAccountID = 2;

    public static String CreateWallet(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = "failed";
        String Balance = MainAccountID + ":" + 0 + ";" + DepositAccountID + ":" + 0;//1:0;2:0
        HashMap<String, Object> data = new HashMap<>();
        data.put(Tables.WalletsTable.UserID, UserID);
        data.put(Tables.WalletsTable.Balance, Balance);
        result = DBManager.insertTableData(Tables.WalletsTable.Table, data, "");
        return result;
    }

    public static String UpdateAdminMainBalance(int Amount) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = "";
        String userbalance = DBManager.GetString(Tables.WalletsTable.Balance, Tables.WalletsTable.Table, "where " + Tables.WalletsTable.UserID + " = " + 1);
        String mainBalRes = userbalance.split(";")[0];
        int mainBalValue = Integer.parseInt(mainBalRes.split(":")[1]);
        int mainBalID = Integer.parseInt(mainBalRes.split(":")[0]);
        int newMainBal = mainBalValue + Amount;
        String newMainBalRes = mainBalID + ":" + newMainBal + ";" + userbalance.split(";")[1];
        userbalance = userbalance.replace(userbalance, newMainBalRes);
        result = DBManager.UpdateStringData(Tables.WalletsTable.Table, Tables.WalletsTable.Balance, userbalance, "where " + Tables.WalletsTable.UserID + " = " + 1);
        return result;
    }

    public static String CreatePayment(int userID, int Amount, String txrref, String transcode, int TicketID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, Object> tableData = new HashMap<>();
        tableData.put(Tables.PaymentsTable.UserID, userID);
        tableData.put(Tables.PaymentsTable.TicketID, TicketID);
        tableData.put(Tables.PaymentsTable.Amount_Paid, Amount);
        tableData.put(Tables.PaymentsTable.ReferenceCode, txrref);
        tableData.put(Tables.PaymentsTable.TransactionCode, transcode);
        int id = DBManager.insertTableDataReturnID(Tables.PaymentsTable.Table, tableData, "");
        DBManager.UpdateCurrentDate(Tables.PaymentsTable.Table, Tables.PaymentsTable.Date, "where " + Tables.PaymentsTable.ID + " = " + id);
        DBManager.UpdateCurrentTime(Tables.PaymentsTable.Table, Tables.PaymentsTable.Time, "where " + Tables.PaymentsTable.ID + " = " + id);
        String result = UpdateAdminMainBalance(Amount);
        return result;
    }

    public static ArrayList<Integer> GetUserPayments(int UserID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.PaymentsTable.ID, Tables.PaymentsTable.Table, "where " + Tables.PaymentsTable.UserID + " = " + UserID);
        return IDs;
    }

    public static HashMap<String, Object> GetPaymentDetails(int PaymentID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        HashMap<String, Object> Details = new HashMap<>();
        Details = DBManager.GetTableObjectData(Tables.PaymentsTable.Table, "where " + Tables.PaymentsTable.ID + " = " + PaymentID);
        String dt = (String) Details.get(Tables.PaymentsTable.Date);
        String date = UtilityManager.readDate(dt);
        Details.put(Tables.PaymentsTable.Date, date);
        String tm = (String) Details.get(Tables.PaymentsTable.Time);
        String time = UtilityManager.readTime(tm);
        Details.put(Tables.PaymentsTable.Time, time);
        Object ticketid = Details.get(Tables.PaymentsTable.TicketID);
        int TicketID = Integer.parseInt("" + ticketid);
        int TicketTypeID = TicketManager.GetTicketTypeIDByTicketID(TicketID);
        String tickettypename = TicketManager.GetTicketTypeNameByID(TicketTypeID);
        Details.put("TicketName", tickettypename);
        int ticketbought = TicketManager.GetTicketBoughtByID(TicketID);
        Details.put("TicketBought", ticketbought);
        Object userid = Details.get(Tables.PaymentsTable.UserID);
        int UserID = Integer.parseInt("" + userid);
        String username = UserManager.getUserName(UserID);
        Details.put("UserName", username);
        return Details;
    }

    public static ArrayList<Integer> GetAllPayments() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        ArrayList<Integer> IDs = new ArrayList<>();
        IDs = DBManager.GetIntArrayList(Tables.PaymentsTable.ID, Tables.PaymentsTable.Table, "");
        return IDs;
    }

    public static int GetTotalPaymentAmount(int PaymentID) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        int freeTickets = 0;
        freeTickets = DBManager.GetInt(Tables.PaymentsTable.Amount_Paid, Tables.PaymentsTable.Table, "where " + Tables.PaymentsTable.ID + " = " + PaymentID);
        return freeTickets;
    }
}
