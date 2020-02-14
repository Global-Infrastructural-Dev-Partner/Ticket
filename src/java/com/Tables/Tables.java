/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tables;

/**
 *
 * @author ndfmac
 */
public class Tables {

    public static class NotificationsTable {

        public static String Table = "notifications";
        public static String ID = "id";
        public static String Date = "date";
        public static String Time = "time";
        public static String Subject = "subject";
        public static String FromUserID = "from_user_id";
        public static String ToUserID = "to_user_id";
        public static String Body = "body";
        public static String Status = "status";
    }

    public static class PaymentsTable {

        public static String Table = "payments";
        public static String ID = "id";
        public static String UserID = "userid";
        public static String Amount_Paid = "amount_paid";
        public static String Date = "date";
        public static String Time = "time";
        public static String TicketID = "ticket_id";
        public static String ReferenceCode = "reference_code";
        public static String TransactionCode = "transaction_code";
    }

    public static class TicketTypeTable {

        public static String Table = "ticket_type";
        public static String ID = "id";
        public static String Name = "name";
    }

    public static class TicketsTable {

        public static String Table = "tickets";
        public static String ID = "id";
        public static String UserID = "userid";
        public static String TicketTypeID = "ticket_type_id";
        public static String NumberOfTicketBought = "number_of_ticket_bought";
        public static String AmountPaid = "amount_paid";
        public static String Date = "date";
        public static String Time = "time";
        public static String TicketPaidFor = "ticket_paid_for";
        public static String FreeTickets = "free_ticket";
    }
    public static class TicketHistoryTable {

        public static String Table = "ticket_history";
        public static String ID = "id";
        public static String TicketID = "ticket_id";
        public static String TicketNumber = "ticket_number";
    }

    public static class UsersTable {

        public static String Table = "users";
        public static String ID = "id";
        public static String UserType = "usertype";
        public static String FirstName = "firstname";
        public static String LastName = "lastname";
        public static String Email = "email";
        public static String PhoneNumber = "phone";
        public static String Password = "password";
        public static String ReferralCode = "referral_code";
        public static String ReferralCount = "referral_count";
        public static String ReferralUserID = "referral_userid";
        public static String DateRegistered = "date_registered";
        public static String TimeRegistered = "time_registered";
    }

    public static class WalletTypeTable {

        public static String Table = "wallet_type";
        public static String ID = "id";
        public static String Name = "name";
    }

    public static class WalletsTable {

        public static String Table = "wallets";
        public static String ID = "id";
        public static String UserID = "userid";
        public static String Balance = "balance";
    }
    public static class SessionTable {

        public static String Table = "session";
        public static String ID = "id";
        public static String SessionID = "sessionid";
        public static String UserID = "userid";
        public static String Time = "time";
        public static String Date = "date";
    }
    public static class ParameterTable {

        public static String Table = "parameters";
        public static String ID = "id";
        public static String SecretKet = "secretkey";
    }
}
