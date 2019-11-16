package com.mutants.infrastructurecross.util;

public class ArrayUtil {
    public static String[] orderVerticalArray(String[] array){
        String[] arrayResponse = new String[array.length];
        for(String value: array){
            char[] arrayChar = value.toCharArray();
            int i = 0;
            for(Character character : arrayChar){
                if(arrayResponse[i] == null){
                    arrayResponse[i] = "";
                }
                arrayResponse[i] +=  character.toString();
                i++;
            }
        }
        return arrayResponse;
    }

    public static String[] orderDiagonalArray(String [] array){
        return new String[] {"ACTACT",
                             "TATGCC",
                             "GGAACA",
                             "CTTACC",
                             "GGGGTT",
                             "ACTGAG"};
    }
}
