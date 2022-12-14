package com.elbourn.android.clock2;

import java.util.Calendar;

import processing.core.PApplet;
import processing.core.PFont;

class Sketch extends PApplet {

    String TAG = getClass().getSimpleName();
    PFont font = createFont("SansSerif", 1);
    float largeFont = 40;
    float smallFont = 20;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        frameRate(1);
        fill(255);
    }

    public void draw() {
        // Black background: OLED friendly
        background(0);

        // time
        String hh = addPadding(hour(), " ", 2);
        String mm = addPadding(minute(), "0", 2);
        String ss = addPadding(second(), "0", 2);
        String time = hh + ":" + mm + ":" + ss;
        textFont(font,40 * displayDensity);
        text(time, (width - textWidth(time)) / 2, height / 2f + 0.5f * largeFont * displayDensity);

        // Day of Week
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String td = textDay(dayOfWeek);
        textFont(font,20 * displayDensity);
        text(td, (width - textWidth(td)) / 2, height / 2f - 3 * smallFont * displayDensity);

        // timename
        String tn = textTimeName(hour());
        textFont(font,20 * displayDensity);
        text(tn, (width - textWidth(tn)) / 2, height / 2f - 2 * smallFont * displayDensity);

        // day Month
        String dd_mo = addPadding(day(), " ", 2) + " " + textMonth(month());
        textFont(font,20 * displayDensity);
        text(dd_mo, (width - textWidth(dd_mo)) / 2, height / 2f + 3 * smallFont * displayDensity);

        // Year
        String yyyy = addPadding(year(), " ", 4);
        textFont(font,20 * displayDensity);
        text(yyyy, (width - textWidth(yyyy)) / 2, height / 2f + 4 * smallFont * displayDensity);

//        try {
//            if (wearInteractive()) {
//                frameRate(1);
//            } else {
//                frameRate(0.1f);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    String textTimeName(int hour){
        String t = "error";
        if (hour <= 4) t = "early";
        if (hour > 4 && hour <= 11) t = "morning";
        if (hour > 11 && hour <= 18) t = "afternoon";
        if (hour > 18 && hour <= 21) t = "evening";
        if (hour > 21) t = "late";
        return t;
    }

    String addPadding(int number, String padding, int size)  {
        String padded = "" + number;
        for (int i=1; i<size; i++) {
            if (number < 10) padded = padding + padded;
            number = number / 10;
        }
        return padded;
    }

    String textDay(int dayofweek) {
        String t = "error";
        switch (dayofweek) {
            case 1:
                t = "Monday";
                break;
            case 2:
                t = "Tuesday";
                break;
            case 3:
                t = "Wednesday";
                break;
            case 4:
                t = "Thursday";
                break;
            case 5:
                t = "Friday";
                break;
            case 6:
                t = "Saturday";
                break;
            case 7:
                t = "Sunday";
                break;
            default:
        }
        return t;
    }

    String textMonth(int month) {
        String t = "";
        switch (month) {
            case 1:
                t = "January";
                break;
            case 2:
                t = "February";
                break;
            case 3:
                t = "March";
                break;
            case 4:
                t = "April";
                break;
            case 5:
                t = "May";
                break;
            case 6:
                t = "June";
                break;
            case 7:
                t = "July";
                break;
            case 8:
                t = "August";
                break;
            case 9:
                t = "September";
                break;
            case 10:
                t = "October";
                break;
            case 11:
                t = "November";
                break;
            case 12:
                t = "December";
                break;
            default:
                t = "error";
        }
        return t;
    }
}
