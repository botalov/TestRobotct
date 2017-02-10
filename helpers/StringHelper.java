package com.robot.helpers;

import javafx.geometry.Point3D;

public class StringHelper {


    /**
     * Парсит строку и возвращает точку в трехмерной СК
     * @param str Исходная строка в формате 'x, y, z'
     * @return Точка
     */
    public static Point3D GetPoint3DFromString(String str){
        String[] arrayCoordinate = str.split(",");
        if(arrayCoordinate.length == 3){
           try{
               double x = Double.parseDouble(arrayCoordinate[0].trim());
               double y = Double.parseDouble(arrayCoordinate[1].trim());
               double z = Double.parseDouble(arrayCoordinate[2].trim());

               return new Point3D(x, y, z);
           }
           catch (Exception e){
               e.printStackTrace();
           }
        }
        return null;
    }

}
