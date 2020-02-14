package com.Connector;

import com.Managers.DBManager;
import com.Tables.Tables;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;


public class PayStackManager {

    public static PayStackManager instance = null;

    HttpClient client = (HttpClient) new DefaultHttpClient();

    public static PayStackManager getInstance() {
        if (instance == null) {
            instance = new PayStackManager();
        }
        return instance;
    }

    public String PayStackPay(String trxref) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String payres = "";
        String secretKey = GetPaystackSecretKey();
        payres = Pay(trxref, secretKey);
        return payres;
    }

    public String Pay(String trxref, String SecretKey) {
        String payres = "";
        try {
            HttpGet newRequest = new HttpGet("https://api.paystack.co/transaction/verify/" + trxref);
            newRequest.addHeader("Content-type", "application/json");
            newRequest.addHeader("Authorization", "Bearer " + SecretKey);
            newRequest.addHeader("Cache-Control", "no-cache");
            HttpResponse newResponse = this.client.execute((HttpUriRequest) newRequest);
            HttpEntity entity = newResponse.getEntity();
            StringBuilder Sbuilder = new StringBuilder();
            if (entity != null) {
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Sbuilder.append(line);
                    }
                } catch (IOException | IllegalStateException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new Exception("Error Occured while connecting to paystack url");
            }
            payres = Sbuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return payres;
    }

    public static String GetPaystackSecretKey() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String result = "";
        result = DBManager.GetString(Tables.ParameterTable.SecretKet, Tables.ParameterTable.Table, "where " + Tables.ParameterTable.ID + " = " + 1);
        return result;
    }
}
