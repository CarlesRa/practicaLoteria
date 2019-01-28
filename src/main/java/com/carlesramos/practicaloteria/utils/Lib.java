package com.carlesramos.practicaloteria.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.Scanner;
import java.util.Random;
public class Lib {
    static  Scanner lec = new Scanner (System.in);
    public static  String limpiarPantalla(){
        return "\u001B[H\u001B[2J";
    }

    public static String letraRoja(){
        return "\u001B[31m";
    }

    public static  String reiniciarColorLetra(){
        return "\u001B[0m";
    }

    public  static void mensajeError(){

        System.out.print(letraRoja()+
                "Dada erronea..... "+ reiniciarColorLetra() + " Intro per continuar: ");
        lec.nextLine();
    }

    public static void mensajeSalida(){
        System.out.print("Eixint, intro per continuar: ");

        lec.nextLine();
    }

    public static  void continuar(){
        System.out.print("intro per continuar: ");
        lec.nextLine();
    }

    /**
     * Metode per validar dates.
     * @param fecha entra un string amb la data.
     * @return
     */
    public static boolean validarFecha(String fecha) {

        try {

            SimpleDateFormat formatoFecha;
            formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

            formatoFecha.setLenient(false);

            formatoFecha.parse(fecha);

        } catch (ParseException e) {

            return false;

        }

        return true;

    }

    //Generar un numero random compres entre dos enters.
    public static int random(int min, int max){
        int aleatori;
        Random rnd=new Random();
        aleatori=rnd.nextInt(max-min+1)+min;
        return aleatori;
    }

    public static void intercambio(int[] vector, int i, int j) {
        int aux = vector[i];
        vector[i] = vector[j];
        vector[j] = aux;

    }

    public static int [] ordernaVector(int[] vector) {
        boolean hayCambios = true;
        while(hayCambios) {
            hayCambios = false;
            for(int i = 0; i < vector.length - 1; i++) {
                if(vector[i] > vector[i+1]) {
                    intercambio(vector, i, i+1);
                    hayCambios = true;
                }
            }
        }
        return vector;
    }

}
