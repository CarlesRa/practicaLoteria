package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    private static int eleccio;
    private static int eleccio2;
    private static Scanner lec;
    private static MaquinaSortejos maquina;

    public static void main(String[] args) {
        maquina = new MaquinaSortejos();
        lec = new Scanner(System.in);
        do{
            eleccio = menuInicial();
            switch (eleccio){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    do {
                        eleccio2 = mostrarMenu();
                        switch (eleccio2) {
                            case 0:
                                break;
                            case 1:
                                System.out.println(Arrays.toString(maquina.jocUnic()));
                                Lib.continuar();
                                break;
                            case 2:
                                Lib.continuar();
                                break;
                            case 3:
                                Lib.continuar();
                                break;
                            case 4:
                                Lib.continuar();
                                break;
                            case 5:
                                Lib.continuar();
                                break;
                        }
                    }while (eleccio2 != 0);
                    break;
            }
        }while(eleccio != 3);

    }

    public  static int menuInicial(){
        int eleccio;
        do {
            System.out.println("*************************");
            System.out.println("*******BENVINGUT*********");
            System.out.println("1-Primitiva manual...");
            System.out.println("2-Primitiva Aleatoria... ");
            System.out.println("3-Jugar!!!...");
            System.out.println("*************************");
            System.out.print("Tria una opció: ");
            eleccio = lec.nextInt();
            lec.nextLine();
            return eleccio;
        } while (eleccio >= 0 || eleccio <= 5);
    }

    public static int mostrarMenu() {
        int eleccio;
        do {
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
}