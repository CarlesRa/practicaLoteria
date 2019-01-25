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

        System.out.print(limpiarPantalla()+letraRoja()+
                "Dada erronea, els numeros van del 0 al 49 y no se poden repetir: "+ reiniciarColorLetra());
        lec.nextLine();
    }

    public static void mensajeSalida(){
        System.out.print("Eixint intro per continuar: ");

        lec.nextLine();
    }

    public static  void continuar(){
        System.out.println("intro per continuar: ");
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
}
