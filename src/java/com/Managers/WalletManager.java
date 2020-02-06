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
public class WalletManager {

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
}
