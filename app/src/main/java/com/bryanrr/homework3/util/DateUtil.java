package com.bryanrr.homework3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

      SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public  Date currentDate(String date){


        try {
            return formatter1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
