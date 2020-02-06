/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Services;

import com.Managers.UserManager;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
                            session.setAttribute("Id", UserID);
                            result = "success";
                            json = new Gson().toJson(result);
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
                case "GetMemberDetails": {
                    String userid = request.getParameter("data");
                    int id = Integer.parseInt(userid);
                    HashMap<String, String> memberdetails = UserManager.GetUserDetails(id);
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
                case "GetMessages": {
//                    String[] params = request.getParameterValues("data");
//                    String[] data = null;
//                    for (String param : params) {
//                        data = param.split(",");
//                    }
//                    String userid = data[0].trim();
//                    String option = data[1].trim();
//                    int userId = Integer.parseInt(userid);
//                    ArrayList<Integer> IDS = new ArrayList<>();
//                    HashMap<Integer, HashMap<String, String>> MessageList = new HashMap<>();
//                    String mtype = "";
//                    if (option.equals("inbox")) {
//                        mtype = "inbox";
//                        IDS = UserManager.getInboxMessageIDs(userId);
//                    } else if (option.equals("sent")) {
//                        mtype = "sent";
//                        IDS = UserManager.getSentMessageIDs(userId);
//                    }
//                    if (!IDS.isEmpty()) {
//                        for (int id : IDS) {
//                            HashMap<String, String> msgdetails = UserManager.GetMessageDetails(id);
//                            MessageList.put(id, msgdetails);
//                        }
//                        json1 = new Gson().toJson(MessageList);
//                        json2 = new Gson().toJson(mtype);
//                        json = "[" + json1 + "," + json2 + "]";
//                    } else {
//                        json = new Gson().toJson(empty);
//                    }
                    break;
                }
                case "GetMessageCounts": {
//                    String[] params = request.getParameterValues("data");
//                    String[] data = null;
//                    for (String param : params) {
//                        data = param.split(",");
//                    }
//                    String userid = data[0].trim();
//                    int userId = Integer.parseInt(userid);
//                    ArrayList<Integer> IIDS = new ArrayList<>();
//                    ArrayList<Integer> SIDS = new ArrayList<>();
//                    IIDS = UserManager.getInboxMessageIDs(userId);
//                    SIDS = UserManager.getSentMessageIDs(userId);
//                    json1 = new Gson().toJson(IIDS.size());
//                    json2 = new Gson().toJson(SIDS.size());
//                    json = "[" + json1 + "," + json2 + "]";
                    break;
                }
                case "GetMessageDetails": {
//                    int messageid = Integer.parseInt(request.getParameter("data"));
//                    HashMap<String, String> msgdetails = UserManager.GetMessageDetails(messageid);
//                    json = new Gson().toJson(msgdetails);
                    break;
                }
                case "NewMessage": {
//                    String[] data = request.getParameterValues("data[]");
//                    String userid = data[0].trim();
//                    String contactid = data[1].trim();
//                    String subject = data[2].trim();
//                    String body = data[3].trim();
//                    int userId = Integer.parseInt(userid);
//                    int contactId = Integer.parseInt(contactid);
//                    result = UserManager.sendMemberMessage(userId, body, subject, contactId);
//                    json = new Gson().toJson(result);
                    break;
                }
                case "GetSearchUserDetails": {
                    String UserInput = request.getParameter("data");
                    HashMap<String, String> details = UserManager.getSearchResult(UserInput, 0);
                    json = new Gson().toJson(details);
                    break;
                }
                case "Register": {
                    String[] data = request.getParameterValues("data[]");
                    String regfirstname = data[0].trim();
                    String reglastname = data[1].trim();
                    String regemail = data[2].trim();
                    String regphone = data[3].trim();
                    String regpassword = data[4].trim();
                    String regreflink = data[5].trim();
                    String Subclass = "Subscriber";
                    int MemberUserID = 0;
                    if (!UserManager.checkEmailAddressOrPhoneNumberExist(regemail)) {
                        if (!UserManager.checkEmailAddressOrPhoneNumberExist(regphone)) {
                            MemberUserID = UserManager.CreateUser(Subclass, regfirstname, reglastname, regemail, regphone, regpassword, regreflink);
                            if (MemberUserID != 0) {
                                session.setAttribute("Id", MemberUserID);
                                result = "success";
                                json1 = new Gson().toJson(result);
                                json2 = new Gson().toJson(regemail);
                                json3 = new Gson().toJson(regpassword);
                                json = "[" + json1 + "," + json2 + "," + json3 + "]";
                            } else {
                                result = "Something went wrong while creating User Account";
                                json = new Gson().toJson(result);
                            }
                        } else {
                            result = "Account with Phone Number already Exists";
                            json = new Gson().toJson(result);
                        }
                    } else {
                        result = "Account with Email Address already Exists";
                        json = new Gson().toJson(result);
                    }
                    break;
                }
                case "GetMemberCounts": {
//                    ArrayList<Integer> OnlineIDs = new ArrayList<>();
//                    ArrayList<Integer> MemberIDs = new ArrayList<>();
//                    ArrayList<Integer> OfflineIDs = new ArrayList<>();
//                    ArrayList<String> NewMemberIDs = new ArrayList<>();
//                    OnlineIDs = UserManager.getOnlineIDs();
//                    MemberIDs = UserManager.getMemberIDs();
//                    OfflineIDs = UserManager.getOfflineIDs();
//                    NewMemberIDs = UserManager.getNewMemberIDs();
//                    json1 = new Gson().toJson(OnlineIDs.size());
//                    json2 = new Gson().toJson(MemberIDs.size());
//                    json3 = new Gson().toJson(OfflineIDs.size());
//                    json4 = new Gson().toJson(NewMemberIDs.size());
//                    json = "[" + json1 + "," + json2 + "," + json3 + "," + json4 + "]";
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
