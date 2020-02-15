/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Services;

import com.Managers.PayStackManager;
import com.Managers.TicketManager;
import com.Managers.UserManager;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TicketServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String json = "";
            String type = request.getParameter("type").trim();
            String empty = "none";
            String result = "";
            String json1 = "";
            String json2 = "";
            String json3 = "";
            String json4 = "";
            HttpSession session = request.getSession(true);
            switch (type) {
                case "GetUserTickets": {
                    String sessionid = request.getParameter("data");
                    int userid = UserManager.GetUserIDBySessionID(sessionid);
                    ArrayList<Integer> ids = TicketManager.GetUserTickets(userid);
                    HashMap<Integer, HashMap<String, String>> tickets = new HashMap<>();
                    if (!ids.isEmpty()) {
                        for (int id : ids) {
                            HashMap<String, String> det = TicketManager.GetTicketDetails(id);
                            tickets.put(id, det);
                        }
                        json1 = new Gson().toJson(tickets);
                        json2 = new Gson().toJson(ids.size());
                        json = "[" + json1 + "," + json2 + "]";
                    } else {
                        json = new Gson().toJson(empty);
                    }
                    break;
                }
                case "BuyTicket": {
                    String[] data = request.getParameterValues("data[]");
                    String tickettype = data[0].trim();
                    String amount = data[1].trim();
                    String numberofticket = data[2].trim();
                    String reference = data[3].trim();
                    String transref = data[4].trim();
                    String sessionid = data[5].trim();
                    int UserID = UserManager.GetUserIDBySessionID(sessionid);
                    String payresult = PayStackManager.getInstance().PayStackPay(reference);
                    JSONParser parser = new JSONParser();
                    JSONObject jsonParameter = null;
                    try {
                        jsonParameter = (JSONObject) parser.parse(payresult);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String message = "Your payment validation was not successful, Please contact the admin if your account was debited and send prove of payment!";
                        json1 = new Gson().toJson("success");
                        json2 = new Gson().toJson(message);
                        json = "[" + json1 + "," + json2 + "]";
                        break;
                    }
                    String Status = jsonParameter.get("status").toString();
                    if (Status.equals("false")) {
                        String message = "Your payment validation was not successful, Please contact the admin if your account was debited and send prove of payment!";
                        json1 = new Gson().toJson("success");
                        json2 = new Gson().toJson(message);
                        json = "[" + json1 + "," + json2 + "]";
                        break;
                    } else if (Status.equals("true")) {
                        int Amount = Integer.parseInt(amount);
                        int NumberOfTickets = Integer.parseInt(numberofticket);
                        int TicketTypeID = TicketManager.GetTicketTypeByName(tickettype);
                        result = TicketManager.CreateTicket(UserID, Amount, TicketTypeID, NumberOfTickets, reference, transref);
                        json = new Gson().toJson(result);
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
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
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
