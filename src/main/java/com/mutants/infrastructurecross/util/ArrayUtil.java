package com.mutants.infrastructurecross.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        int largeVertical = array.length;
        int largeHorizontal = 0;
        if(largeVertical > 0){
            largeHorizontal = array[0].length();
        }
        String[][] matrix = new String[largeHorizontal][largeVertical];
        if(largeHorizontal == largeVertical){
            for (int r=0; r<matrix.length; r++) {
                for (int c=0; c<matrix[r].length; c++) {
                    matrix[r][c] = array[r].split("")[c];
                }
            }
        }
        List<String> positionRow = new ArrayList<>();
        List<String> positionColumn = new ArrayList<>();
        for(int r = 0; r < largeHorizontal; r ++){
            positionRow.add("");
            for(Integer e = 0; e <= r; e++){
                positionRow.add(e.toString());
            }
        }
        for(int i=largeHorizontal;i>= 0;i--){
            positionColumn.add("");
            for(Integer j=i;j<=largeHorizontal - 1;j++){
                positionColumn.add(j.toString());
            }
        }
        positionColumn.remove(0);
        List<String> positionFinal = new ArrayList<>();
        int i = 0;
        for(String positionr : positionRow){
            positionFinal.add(positionr.concat(positionColumn.get(i)));
            i++;
        }

        int e = 0;
        String[] listValue =  new String[largeVertical + largeHorizontal];

        for(i= 0; i < listValue.length; i++){
            if (listValue[i] == null) {
                listValue[i]="";
            }
        }
        for(String positionf: positionFinal){
            if(positionf != ""){
                listValue[e] = listValue[e] + matrix[Integer.parseInt(positionf.split("")[0])][Integer.parseInt(positionf.split("")[1])];
            }else{
                e++;
            }
        }
        List<String> listFinal = Arrays.asList(listValue);
        ArrayList<String> arraListFinal = new ArrayList<String>(listFinal);

        for(int h=0; h<arraListFinal.size();h++){
            arraListFinal.remove("");
        }
        Object [] diagonalArray  = arraListFinal.toArray();
        String[] stringArray = Arrays.copyOf(diagonalArray, diagonalArray.length, String[].class);
        
        return stringArray;
    }
}
