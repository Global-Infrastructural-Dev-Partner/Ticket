/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Managers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author ndfmac
 */
public class UtilityManager {

    public static String GenerateAlphaNumericCode(int LengthOfCode) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LengthOfCode; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    public static java.sql.Date CurrentDate() throws ParseException {
        Calendar currentdate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        String Placeholder = formatter.format(currentdate.getTime());
        java.util.Date datenow = formatter.parse(Placeholder);
        java.sql.Date CurrentDate = new Date(datenow.getTime());
        return CurrentDate;
    }

    public static java.sql.Date getSqlDateFromString(String StringDate) {
        Date date;
        try {
            date = Date.valueOf(StringDate);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static java.sql.Time CurrentTime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Time(today.getTime());
    }

    public static String readTime(String time) throws ClassNotFoundException, SQLException {
        String realTime = "";
        String suffix = "A.M";
        String nums[] = time.split(":");
        if (Integer.parseInt(nums[0]) > 12) {
            nums[0] = "" + (Integer.parseInt(nums[0]) - 12);
            suffix = "P.M";
        } else if (Integer.parseInt(nums[0]) == 12) {
            suffix = "P.M";
        }
        realTime = "" + nums[0] + ":" + nums[1] + " " + suffix;
        return realTime;
    }

    public static String readDate(String date) {
        // this function reads out any date with format yyyy-MM-dd
        String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String dateText = "";
        String superscript = "ᵗʰ";
        try {
            String[] dates = date.split("-");
            String year = dates[0].trim();
            String month = dates[1].trim();
            String day = dates[2].trim();
            int mth = Integer.parseInt(month);
            int dy = Integer.parseInt(day);

            if (dy != 13 && day.charAt(day.length() - 1) == '3') {
                superscript = "rd";
            } else if (dy != 11 && day.charAt(day.length() - 1) == '1') {
                superscript = "st";
            } else if (dy != 12 && day.charAt(day.length() - 1) == '2') {
                superscript = "nd";
            }

            dateText = dy + superscript + " " + months[mth - 1] + " " + year;
        } catch (Exception e) {
            dateText = "N/A";
        }
        return dateText;
    }

}
