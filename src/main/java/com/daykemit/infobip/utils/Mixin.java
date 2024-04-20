package com.daykemit.infobip.utils;

import java.util.Random;

public class Mixin {

    /**
     * generate OTP
     * @return otp
     */
    public static String generateOTP() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
