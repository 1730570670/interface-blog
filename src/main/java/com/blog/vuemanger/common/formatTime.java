package com.blog.vuemanger.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class formatTime {

    public static String getTime(){
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }
}
