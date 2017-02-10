package com.robot.models;

import javafx.geometry.Point3D;


/**
 * Модель, описывающая сферу
 */
public class SphereModel {
    private Point3D center;
    private int radius;

    public SphereModel(Point3D center, int radius){
        this.center = center;
        this.radius = radius;
    }

    /**
     * @return Возвращает координаты центра сферы
     */
    public Point3D getCenter(){
        return this.center;
    }

    /**
     *  @return Возвращает радиус окружности
     */
    public int getRadius(){
        return this.radius;
    }

    /**
     * Определяет, содержится ли точка в данной сфере
     * @param point Точка
     * @return true, если содержится
     */
    public boolean pointContains(Point3D point){
        try{
            return Math.sqrt(Math.pow(point.getX() - this.getCenter().getX(), 2) + Math.pow(point.getY() - this.getCenter().getY(), 2) + Math.pow(point.getZ() - this.getCenter().getZ(), 2)) <= this.radius;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
