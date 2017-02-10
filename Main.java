package com.robot;

import com.robot.helpers.RandomHelper;
import com.robot.helpers.StringHelper;
import com.robot.models.SphereModel;
import javafx.geometry.Point3D;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static final int MAX_COORDIANATE = 100;
    private static final int MIN_COORDIANATE = -100;
    private static final int MAX_RADIUS_SPHERE = 100;
    private static final int MIN_RADIUS_SPHERE = 1;

    private static Point3D sco; //координаты в первой СК начала координат второй системы
    private static Point3D point; //точка в двумерной СК
    private static ArrayList<SphereModel> spheres;
    private static int countSphere; //Количество сфер

    public static void main(String[] args) {

        inputData();

        initData();

        calculateResult();

    }


    /**
     * Инициализация данных, которые не вводятся пользователем
     */
    private static void initData(){
        spheres = new ArrayList<>();
        for(int i=0; i<countSphere; i++){
            Point3D centerShpere = new Point3D(RandomHelper.GetRandomNumber(MIN_COORDIANATE, MAX_COORDIANATE), RandomHelper.GetRandomNumber(MIN_COORDIANATE, MAX_COORDIANATE), RandomHelper.GetRandomNumber(MIN_COORDIANATE, MAX_COORDIANATE));
            int radiusSphere = RandomHelper.GetRandomNumber(MIN_RADIUS_SPHERE, MAX_RADIUS_SPHERE);
            SphereModel sphere = new SphereModel(centerShpere, radiusSphere);
            spheres.add(sphere);
        }
    }

    /**
     * Ввод данных
     */
    private static void inputData(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество сфер:");
        try{
            countSphere = Integer.parseInt(br.readLine());
            if(countSphere<0){
                throw new Exception("Вы ввели недопустимое количество сфер.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Введите координаты начала координат двумерной СК а фомате 'x, y, z':");
        try {
            sco = StringHelper.GetPoint3DFromString(br.readLine());
            if(sco == null){
                throw new Exception("Произошла ошибка при чтении строки");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Введите неотприцательные значения координат точки в двумерной СК а фомате 'x, y':");
        try {
            String pointString = br.readLine().concat(", 0");
            point = StringHelper.GetPoint3DFromString(pointString);
            if(point == null || point.getX()<0 || point.getY()<0){
                throw new Exception("Произошла ошибка при чтении строки");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Обработка полученных данных, подсчет и вывод результата
     */
    private static void calculateResult(){
        try{
            int counter = 0;
            Point3D point3D = convertPointTo3DSCO(sco, point);

            System.out.println("Координаты точки в трехмерной системе координат:");
            assert point3D != null;
            System.out.println("X: " + point3D.getX());
            System.out.println("Y: " + point3D.getY());
            System.out.println("Z: " + point3D.getZ());
            System.out.println();

            for(SphereModel sphereModel : spheres){
                if(sphereModel.pointContains(point3D)) {
                    System.out.println("В данную сферу точка входит:");
                    System.out.println("Радиус: " + sphereModel.getRadius());
                    System.out.println("X: " + sphereModel.getCenter().getX());
                    System.out.println("Y: " + sphereModel.getCenter().getY());
                    System.out.println("Z: " + sphereModel.getCenter().getZ());
                    System.out.println();
                    counter++;
                }
            }

            System.out.println("Всего точка входит в ".concat(String.valueOf(counter)).concat(" сфер."));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Преобразование координат точки из двумерной СК в трехмерную
     * @param sco Координаты начала координат двумерной СК
     * @param point Точка в двумерной СК
     * @return Точка в трехмерной СК
     */
    private static Point3D convertPointTo3DSCO(Point3D sco, Point3D point){
        try{
            return new Point3D(sco.getX() + point.getX(), sco.getY() + point.getY(), sco.getZ() + point.getZ());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
