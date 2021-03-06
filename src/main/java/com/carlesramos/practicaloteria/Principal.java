package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    private static Scanner lec;
    private static Administracio administracio;

    public static void main(String[] args) {
        int eleccio;
        administracio = new Administracio();
        lec = new Scanner(System.in);
        do {
            eleccio = menuInicial();
            if (eleccio == 0){
                System.out.println("Adeu!!! fins la proxima.");
            }
            else if (eleccio==3 && administracio.getBoletoJugador()[0] == 0
                && administracio.getBoletoJugador()[1] == 0){
                System.out.println("No ha plenat el bolet....");
                eleccio = -1;
            }
            switch (eleccio) {
                case 1:
                    primitivaManual();
                    break;
                case 2:
                    primitivaAleatoria();
                    break;
                case 3:
                    jugar();
            }
            if (eleccio<0 || eleccio>3){
                Lib.mensajeError();
            }
        }while (eleccio != 0);

    }

    /**
     * mostra el menu inicial
     * @return retorna la eleccio del switch
     */
    private  static int menuInicial(){
        int eleccio;
        do {
            System.out.print(Lib.limpiarPantalla());
            System.out.println("*************************");
            System.out.println("*******BENVINGUT*********");
            System.out.println("1-Primitiva manual...");
            System.out.println("2-Primitiva Aleatoria...");
            System.out.println("3-JUGAR!!...");
            System.out.println("*************************");
            System.out.println("0-Eixir.....");
            System.out.print("Tria una opció: ");
            eleccio = lec.nextInt();
            lec.nextLine();
            return eleccio;
        }while (eleccio >=0 || eleccio <=2);
    }

    /**
     * mostra les opcions de joc
     * @returnretorna retorna la elecció del switch
     */
    private static int menuJugar() {
        int eleccio;
        do {
            System.out.print(Lib.limpiarPantalla());
            System.out.println("******************************************************");
            System.out.println("**********************PRIMITIVA***********************");
            System.out.println("1-Joc unic...");
            System.out.println("2-Jugar fins a obtenir premi...");
            System.out.println("3-Jugar fins a obtenir premi (sense reintegrament)...");
            System.out.println("4-Cicle de 10.000 sortejos...");
            System.out.println("5-Jugar fins a obtenir premi de categoria especial...");
            System.out.println("*****************************************************");
            System.out.println("0-Eixir");
            System.out.print("Tria una opció: ");
            eleccio = lec.nextInt();
            lec.nextLine();
            return eleccio;
        } while (eleccio >= 0 || eleccio <= 5);
    }

    /**
     * fa les accions necesaries per fer una primitiva manual.
     */
    private static  void primitivaManual(){
        administracio.primitivaManual();
        System.out.println("Primitiva Generada: " + Arrays.toString(administracio.getBoletoJugador())
        + " R: "+ administracio.getReintegroJugador());
        Lib.continuar();
    }

    /**
     * fa les accions necesaries per fer una primitiva aleatoria.
     */
    private static void primitivaAleatoria(){
        administracio.primitivaAleatoria();
        System.out.println("Primitiva Generada: " + Arrays.toString(administracio.getBoletoJugador())
        + " R: "+ administracio.getReintegroJugador());
        Lib.continuar();
    }

    /**
     * conte les opcions de joc del menuJugar
     */
    private static void jugar(){
        int eleccio2;
        do {
            eleccio2 = menuJugar();
            switch (eleccio2) {
                case 0://eixir
                    break;
                case 1:
                    administracio.jugarUnaVegada();
                    break;
                case 2:
                    administracio.jugarFinsPremi();
                    break;
                case 3:
                    administracio.jugarFinsPremiNoReintegrament();
                    break;
                case 4:
                    administracio.jugarDeumilVegades();
                    break;
                case 5:
                    administracio.jugarFinsEspecial();
                    break;
            }
            if (eleccio2<0 || eleccio2>5){
                Lib.mensajeError();
            }
        }while (eleccio2 != 0);
    }
}