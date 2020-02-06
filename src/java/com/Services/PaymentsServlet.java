/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Services;

import com.Managers.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ndfmac
 */
public class PaymentsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            String type = request.getParameter("type");
            String json = "";
            String json1 = "";
            String json2 = "";
            String result = "";
            String empty = "none";
            switch (type) {
                case "ValidatePaystackTransaction": {
                    String[] data = request.getParameterValues("data[]");
                    String userid = data[0].trim();
                    String actualamount = data[1].trim();
                    String trxref = data[2].trim();
                    String transcode = data[3].trim();
                    String paytype = data[4].trim();
                    String tickettypeid = data[5].trim();
                    String numberoftickets = data[6].trim();
                    int TicketTypeID = Integer.parseInt(tickettypeid);
                    int NumberOfTickets = Integer.parseInt(numberoftickets);
                    int MemberUserID = Integer.parseInt(userid);
                    int Amount = Integer.parseInt(actualamount);
                    String message = "";
                    String payresult = PayStackManager.getInstance().PayStackPay(trxref);
                    JSONParser parser = new JSONParser();
                    JSONObject jsonParameter = null;
                    try {
                        jsonParameter = (JSONObject) parser.parse(payresult);
                    } catch (Exception e) {
                        message = "Your payment validation was not successful, Please contact the admin if your account was debited and send prove of payment!";
                        json1 = new Gson().toJson(paytype);
                        json2 = new Gson().toJson(result);
                        String json3 = new Gson().toJson(message);
                        json = "[" + json1 + "," + json2 + "," + json3 + "]";
                        e.printStackTrace();
                    }
                    String Status = jsonParameter.get("status").toString();
                    if (Status.equals("false")) {
                        message = "Your payment validation was not successful, Please contact the admin if your account was debited and send prove of payment!";
                        json1 = new Gson().toJson(paytype);
                        json2 = new Gson().toJson(result);
                        String json3 = new Gson().toJson(message);
                        json = "[" + json1 + "," + json2 + "," + json3 + "]";

                    } else if (Status.equals("true")) {
                        if (paytype.equals("Ticket Fees")) {
                            result = TicketManager.CreateTicket(MemberUserID, Amount, TicketTypeID, NumberOfTickets, trxref, transcode);
                            if (result.equals("success")) {
                                result = "success";
                                message = "Your Payment was Successful and your Ticket(s) has been sent to the registered Email.";
                            } else {
                                result = "error";
                                message = "Something went wrong! We would fix it in no time!";
                            }
                            json1 = new Gson().toJson(paytype);
                            json2 = new Gson().toJson(result);
                            String json3 = new Gson().toJson(message);
                            json = "[" + json1 + "," + json2 + "," + json3 + "]";
                        }
                    }
                    break;
                }

            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PaymentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
