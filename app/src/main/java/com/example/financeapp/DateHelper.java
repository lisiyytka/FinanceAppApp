package com.example.financeapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateHelper {

    public static String getDate() {
        Date currentDate = new Date();
// Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
// Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String resultTime = dateText + " " + timeText;
        return resultTime;
    }
}
