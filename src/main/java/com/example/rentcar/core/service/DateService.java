package com.example.rentcar.core.service;

import java.util.Calendar;
import java.util.Date;

public class DateService {

    public static Date dateLocalToUTC(Date date) {
        return new Date(date.getTime() + Calendar.getInstance().getTimeZone().getOffset(date.getTime()));

    }

}
