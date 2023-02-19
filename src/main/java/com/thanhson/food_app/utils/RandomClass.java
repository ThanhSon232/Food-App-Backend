package com.thanhson.food_app.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomClass {
    static Random rand = new Random();

    public static int randomNumber (){
        String id = String.format("%04d", rand.nextInt(10000));
        return Integer.parseInt(id);
    };

    static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";


    static final Set<String> identifiers = new HashSet<String>();

    public static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }


}
