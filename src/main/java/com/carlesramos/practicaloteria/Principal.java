package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    private static int eleccio;
    private static int eleccio2;
    private static Scanner lec;
    private static Administracio maquinaAdmin;
    private static Sorteig sorteig;
    private static int [] boletoJuagador;
    private static int [] numeroSorteig;
    private static int reintegroBombo;
    private static int complementari;
    private static int contadorJugades;
    public static void main(String[] args) {
        maquinaAdmin = new Administracio();
        lec = new Scanner(System.in);
        do {
            eleccio = menuInicial();
            switch (eleccio) {
                case 1:
                    maquinaAdmin.primitivaManual();
                    boletoJuagador = maquinaAdmin.getBoletoJugador();
                    System.out.println("Primitiva Generada: " + Arrays.toString(boletoJuagador)
                            + " R: "+maquinaAdmin.getReintegroJugador());
                    Lib.continuar();
                    break;
                case 2:
                    maquinaAdmin.primitivaAleatoria();
                    boletoJuagador = maquinaAdmin.getBoletoJugador();
                    System.out.println("Primitiva Generada: " + Arrays.toString(boletoJuagador)
                            + " R: "+maquinaAdmin.getReintegroJugador());
                    Lib.continuar();
                    break;
                case 3:
                    do {
                        eleccio2 = mostrarMenu();
                        switch (eleccio2) {
                            case 0:
                                break;
                            case 1:
                                sorteig = new Sorteig();
                                numeroSorteig = sorteig.generarNumeroSorteig();
                                reintegroBombo = sorteig.getReintegroBombo();
                                complementari = sorteig.getComplementari();
                                System.out.println(Arrays.toString(numeroSorteig)+" "+reintegroBombo);

                                maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                        , maquinaAdmin.getReintegroJugador(), reintegroBombo);
                                maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo);

                                Lib.continuar();
                                break;
                            case 2:
                                maquinaAdmin.setEstaPremiat(false);
                                while (!maquinaAdmin.getEstaPremiat()){
                                    sorteig = new Sorteig();
                                    numeroSorteig = sorteig.generarNumeroSorteig();
                                    reintegroBombo = sorteig.getReintegroBombo();
                                    maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                            , maquinaAdmin.getReintegroJugador(), reintegroBombo);
                                    if (maquinaAdmin.getEstaPremiat()){
                                        contadorJugades++;
                                        System.out.println("Ha fet un total de: " + contadorJugades + " jugades.");
                                        maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo);
                                    }
                                    contadorJugades++;
                                }
                                Lib.continuar();
                                contadorJugades = 0;
                                break;
                            case 3:
                                maquinaAdmin.setEstaPremiat(false);
                                while (!maquinaAdmin.getEstaPremiat()){
                                    sorteig = new Sorteig();
                                    numeroSorteig = sorteig.generarNumeroSorteig();
                                    reintegroBombo = sorteig.getReintegroBombo();
                                    maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                            , maquinaAdmin.getReintegroJugador(), reintegroBombo, "sense Reintegro");
                                    if (maquinaAdmin.getEstaPremiat()){
                                        contadorJugades++;
                                        System.out.println("Ha fet un total de: " + contadorJugades + " jugades.");
                                        maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo);

                                    }
                                    contadorJugades++;
                                }
                                Lib.continuar();
                                contadorJugades = 0;
                                break;
                            case 4:
                                Lib.continuar();
                                break;
                            case 5:
                                Lib.continuar();
                                break;
                        }
                        if (eleccio2<0 || eleccio2>5){
                            Lib.mensajeError();
                        }
                    }while (eleccio2 != 0);
            }
            if (eleccio<1 || eleccio>3){
                Lib.mensajeError();
            }
        }while (eleccio != 0);

    }

    public  static int menuInicial(){
        do {
            int eleccio;
            System.out.println("*************************");
            System.out.println("*******BENVINGUT*********");
            System.out.println("1-Primitiva manual...");
            System.out.println("2-Primitiva Aleatoria...");
            System.out.println("3-JUGAR!!...");
            System.out.println("*************************");
            System.out.print("Tria una opció: ");
            eleccio = lec.nextInt();
            lec.nextLine();
            return eleccio;
        }while (eleccio >=0 || eleccio <=2);
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