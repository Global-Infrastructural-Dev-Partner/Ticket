/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author ndfmac
 */
public class PayStackManager {
    
    public static PayStackManager instance = null;
    HttpClient client = new DefaultHttpClient();

    public static final String MY_PAY_STACK_SECRET_KEY = "sk_test_a77df3ccb0244eb036d487a692492ca08a9c8ebe";
//    public static final String MY_PAY_STACK_SECRET_KEY = "sk_live_83df404b50192677bad56d0f25581b64badb8641";


    public PayStackManager() {

    }

    public static PayStackManager getInstance() {
        if (instance == null) {
            instance = new PayStackManager();
        }
        return instance;
    }

    public String PayStackPay(String trxref) {
        String payres = "";
        try {
            HttpGet newRequest = new HttpGet("https://api.paystack.co/transaction/verify/" + trxref);
            newRequest.addHeader("Content-type", "application/json");
            newRequest.addHeader("Authorization", "Bearer " + PayStackManager.MY_PAY_STACK_SECRET_KEY);
            newRequest.addHeader("Cache-Control", "no-cache");
            HttpResponse newResponse = client.execute(newRequest);
            HttpEntity entity = newResponse.getEntity();
            StringBuilder Sbuilder = new StringBuilder();
            String line;
            if (entity != null) {
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
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
}
