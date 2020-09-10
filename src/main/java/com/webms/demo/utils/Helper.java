package com.webms.demo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {
    
    public static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy MM dd");
    
    public static LocalDate newDate(String date){
        return LocalDate.parse(date, YYYYMMDD);
    }
}
