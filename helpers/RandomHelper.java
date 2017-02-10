package com.robot.helpers;

import java.util.Random;

public class RandomHelper {


    /**
     * @param minValue левая граница интервала
     * @param maxValue правая граница интервала
     * @return Возвращает случайное число в пределах указанного интервала
     */
    public static int GetRandomNumber(int minValue, int maxValue){
        try{
            Random random = new Random();
            return random.nextInt(((maxValue - minValue) + 1)) + minValue;
        }
        catch (Exception e){
            e.printStackTrace();
            return maxValue;
        }
    }

}
