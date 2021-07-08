package com.example.mylogi.workers;

public class BirthDateConverter {
    public String getBirthDate(String pid) {
        int year = Integer.parseInt(pid.substring(0, 1));
        String month = pid.substring(2, 3);
        String day = pid.substring(4, 5);
        String birthDate = null;

        if (year < 40) {
            return birthDate = day + "/" + month + "/20" + year;
        } else {
            return birthDate = day + "/" + month + "/19" + year;
        }
    }
}
