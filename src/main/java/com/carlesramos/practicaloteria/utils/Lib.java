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

        System.out.print(letraRoja()
        + "Dada erronea..... "+ reiniciarColorLetra() + " Intro per continuar: ");
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
     * @return retorna un boolean amb el resultat de la validacio
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

    /**
     * Genera un numero random compres entre dos enters
     * @param min li pasem el menor valor
     * @param max li pasem el maajor valor
     * @return un numero random
     */
    public static int random(int min, int max){
        int aleatori;
        Random rnd=new Random();
        aleatori=rnd.nextInt(max-min+1)+min;
        return aleatori;
    }

    /**
     * metode per orddenar vector de venor a major
     * @param vector vector a ordenar.
     * @return vector ordenat
     */
    public static int [] ordernaVector(int [] vector) {
        boolean haCamviat = true;
        while(haCamviat) {
            haCamviat = false;
            for(int i = 0; i < vector.length-1; i++) {
                if(vector[i] > vector[i+1]) {
                    int aux = vector[i];
                    vector[i] = vector[i+1];
                    vector[i+1] = aux;
                    haCamviat = true;
                }
            }
        }
        return vector;
    }
}