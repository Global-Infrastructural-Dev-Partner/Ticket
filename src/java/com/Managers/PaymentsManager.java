/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Managers;

import com.Tables.Tables;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author ndfmac
 */
public class PaymentsManager {

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
        String result = WalletManager.UpdateAdminMainBalance(Amount);
        return result;
    }

}
