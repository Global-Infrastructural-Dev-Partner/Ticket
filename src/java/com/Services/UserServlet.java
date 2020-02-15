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
public class UserServlet extends HttpServlet {

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
                case "Login": {
                    String[] data = request.getParameterValues("data[]");
                    String Email_PhoneNumber = data[0].trim();
                    String Password = data[1].trim();
                    int UserID = 0;
                    if (UserManager.checkEmailAddressOrPhoneNumberExist(Email_PhoneNumber)) {
                        UserID = UserManager.checkPasswordEmailMatch(Password, Email_PhoneNumber);
                        if (UserID != 0) {
                            String sessionid = session.getId();
                            UserManager.UpdateSessionID(sessionid, UserID);
                            session.setMaxInactiveInterval(5 * 60);
                            session.setAttribute("sessionid", sessionid);
                            String status = UserManager.getUserStatus(UserID);
                            json = new Gson().toJson(status);
                        } else {
                            result = "Incorrect Login Details";
                            json = new Gson().toJson(result);
                        }
                    } else {
                        result = "Email or Phone Number Entered Doesn't Exist";
                        json = new Gson().toJson(result);
                    }
                    break;
                }
                case "MemberRegistration": {
                    String[] data = request.getParameterValues("data[]");
                    String regfirstname = data[0].trim();
                    String reglastname = data[1].trim();
                    String regemail = data[2].trim();
                    String regphone = data[3].trim();
                    String regpassword = data[4].trim();
                    String regrefcode = data[5].trim();
                    String Subclass = "Subscriber";
                    int MemberUserID = 0;
                    if (!UserManager.checkEmailAddressOrPhoneNumberExist(regemail)) {
                        if (!UserManager.checkEmailAddressOrPhoneNumberExist(regphone)) {
                            MemberUserID = UserManager.CreateUser(Subclass, regfirstname, reglastname, regemail, regphone, regpassword, regrefcode);
                            if (MemberUserID != 0) {
                                String sessionid = session.getId();
                                UserManager.UpdateSessionID(sessionid, MemberUserID);
                                session.setMaxInactiveInterval(5 * 60);
                                session.setAttribute("sessionid", sessionid);
                                String status = UserManager.getUserStatus(MemberUserID);
                                result = "success";
                                json1 = new Gson().toJson(result);
                                json2 = new Gson().toJson(status);
                                json = "[" + json1 + "," + json2 + "]";
                            } else {
                                result = "Something went wrong while creating User Account";
                                json1 = new Gson().toJson("failed");
                                json2 = new Gson().toJson(result);
                                json = "[" + json1 + "," + json2 + "]";
                            }
                        } else {
                            result = "Account with Phone Number already Exists";
                            json1 = new Gson().toJson("failed");
                            json2 = new Gson().toJson(result);
                            json = "[" + json1 + "," + json2 + "]";
                        }
                    } else {
                        result = "Account with Email Address already Exists";
                        json1 = new Gson().toJson("failed");
                        json2 = new Gson().toJson(result);
                        json = "[" + json1 + "," + json2 + "]";
                    }
                    break;
                }
                case "RegistrationAndPayment": {
                    String[] data = request.getParameterValues("data[]");
                    String regfirstname = data[0].trim();
                    String reglastname = data[1].trim();
                    String regemail = data[2].trim();
                    String regphone = data[3].trim();
                    String regpassword = data[4].trim();
                    String tickettype = data[5].trim();
                    String amount = data[6].trim();
                    String reference = data[7].trim();
                    String numberofticket = data[8].trim();
                    String transref = data[9].trim();
                    String regrefcode = data[10].trim();
                    String Subclass = "Subscriber";
                    int MemberUserID = 0;
                    if (!UserManager.checkEmailAddressOrPhoneNumberExist(regemail)) {
                        if (!UserManager.checkEmailAddressOrPhoneNumberExist(regphone)) {
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
                                MemberUserID = UserManager.CreateUser(Subclass, regfirstname, reglastname, regemail, regphone, regpassword, regrefcode);
                                if (MemberUserID != 0) {
                                    String sessionid = session.getId();
                                    int Amount = Integer.parseInt(amount);
                                    int NumberOfTickets = Integer.parseInt(numberofticket);
                                    int TicketTypeID = TicketManager.GetTicketTypeByName(tickettype);
                                    TicketManager.CreateTicket(MemberUserID, Amount, TicketTypeID, NumberOfTickets, reference, transref);
                                    UserManager.UpdateSessionID(sessionid, MemberUserID);
                                    session.setMaxInactiveInterval(5 * 60);
                                    session.setAttribute("sessionid", sessionid);
                                    String status = UserManager.getUserStatus(MemberUserID);
                                    result = "success";
                                    json1 = new Gson().toJson(result);
                                    json2 = new Gson().toJson(status);
                                    json = "[" + json1 + "," + json2 + "]";
                                } else {
                                    result = "Something went wrong while creating User Account";
                                    json1 = new Gson().toJson("failed");
                                    json2 = new Gson().toJson(result);
                                    json = "[" + json1 + "," + json2 + "]";
                                }
                            }
                        } else {
                            result = "Account with Phone Number already Exists";
                            json1 = new Gson().toJson("failed");
                            json2 = new Gson().toJson(result);
                            json = "[" + json1 + "," + json2 + "]";
                        }
                    } else {
                        result = "Account with Email Address already Exists";
                        json1 = new Gson().toJson("failed");
                        json2 = new Gson().toJson(result);
                        json = "[" + json1 + "," + json2 + "]";
                    }
                    break;
                }

                case "GetMemberDetails": {
                    String sessionid = request.getParameter("data");
                    int UserID = UserManager.GetUserIDBySessionID(sessionid);
                    HashMap<String, String> memberdetails = UserManager.GetUserDetails(UserID);
                    json = new Gson().toJson(memberdetails);
                    break;
                }
                case "GetAllUsers": {
                    ArrayList<Integer> ids = UserManager.GetAllUsers();
                    HashMap<Integer, HashMap<String, String>> users = new HashMap<>();
                    if (!ids.isEmpty()) {
                        for (int id : ids) {
                            HashMap<String, String> det = UserManager.GetUserDetails(id);
                            users.put(id, det);
                        }
                        json1 = new Gson().toJson(users);
                        json2 = new Gson().toJson(ids.size());
                        json = "[" + json1 + "," + json2 + "]";
                    } else {
                        json = new Gson().toJson(empty);
                    }
                    break;
                }
                case "GetUserNotifications": {
                    String sessionid = request.getParameter("data");
                    int userid = UserManager.GetUserIDBySessionID(sessionid);
                    ArrayList<Integer> ids = UserManager.getUserNotifications(userid);
                    HashMap<Integer, HashMap<String, String>> notifications = new HashMap<>();
                    if (!ids.isEmpty()) {
                        for (int id : ids) {
                            HashMap<String, String> det = UserManager.GetNotificationDetails(id);
                            notifications.put(id, det);
                        }
                        json1 = new Gson().toJson(notifications);
                        json2 = new Gson().toJson(ids.size());
                        json = "[" + json1 + "," + json2 + "]";
                    } else {
                        json = new Gson().toJson(empty);
                    }
                    break;
                }
                case "GetAllNotifications": {
                    ArrayList<Integer> ids = UserManager.getAllNotifications();
                    HashMap<Integer, HashMap<String, String>> notifications = new HashMap<>();
                    if (!ids.isEmpty()) {
                        for (int id : ids) {
                            HashMap<String, String> det = UserManager.GetNotificationDetails(id);
                            notifications.put(id, det);
                        }
                        json1 = new Gson().toJson(notifications);
                        json2 = new Gson().toJson(ids.size());
                        json = "[" + json1 + "," + json2 + "]";
                    } else {
                        json = new Gson().toJson(empty);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
