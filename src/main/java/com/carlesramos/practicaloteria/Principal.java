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
    public static void main(String[] args) {
        maquinaAdmin = new Administracio();
        lec = new Scanner(System.in);
        do {
            eleccio = menuInicial();
            if (eleccio == 0){
                System.out.println("Adeu!!! fins la proxima.");
            }
            else if (eleccio<1 || eleccio>2 && maquinaAdmin.getBoletoJugador()[0] == 0 && maquinaAdmin.getBoletoJugador()[1] == 0){
                System.out.println("No ha plenat el bolet....");
                eleccio = -1;
            }
            switch (eleccio) {
                case 1://Primitiva manual.
                    maquinaAdmin.primitivaManual();
                    boletoJuagador = maquinaAdmin.getBoletoJugador();
                    System.out.println("Primitiva Generada: " + Arrays.toString(boletoJuagador)
                            + " R: "+maquinaAdmin.getReintegroJugador());
                    Lib.continuar();
                    break;
                case 2://primitiva aleatoria
                    maquinaAdmin.primitivaAleatoria();
                    boletoJuagador = maquinaAdmin.getBoletoJugador();
                    System.out.println("Primitiva Generada: " + Arrays.toString(boletoJuagador)
                            + " R: "+maquinaAdmin.getReintegroJugador());
                    Lib.continuar();
                    break;
                case 3://Juagar
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
    public  static int menuInicial(){
        int eleccio;
        do {
            Lib.limpiarPantalla();
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
    public static int menuJugar() {
        int eleccio;
        do {
            Lib.limpiarPantalla();
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
     * conte les opcions de joc del menuJugar
     */
    public static void jugar(){
        do {
            eleccio2 = menuJugar();
            switch (eleccio2) {
                case 0://eixir
                    break;
                case 1://jugar una sola vegada.
                    sorteig = new Sorteig();
                    numeroSorteig = sorteig.generarNumeroSorteig();
                    reintegroBombo = sorteig.getReintegroBombo();
                    complementari = sorteig.getComplementari();
                    maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                            , maquinaAdmin.getReintegroJugador(), reintegroBombo, complementari);
                    maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo,complementari);

                    Lib.continuar();
                    break;
                case 2://jugar fins a premi
                    maquinaAdmin.setEstaPremiat(false);
                    while (!maquinaAdmin.getEstaPremiat()){
                        sorteig = new Sorteig();
                        numeroSorteig = sorteig.generarNumeroSorteig();
                        reintegroBombo = sorteig.getReintegroBombo();
                        complementari = sorteig.getComplementari();
                        maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                , maquinaAdmin.getReintegroJugador(), reintegroBombo, complementari);
                        if (maquinaAdmin.getEstaPremiat()){
                            System.out.println("Ha fet un total de: " + maquinaAdmin.getContadorJugades()
                                    + " jugades.");
                            maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo,complementari);
                        }
                    }
                    maquinaAdmin.setContadorJugades(0);
                    Lib.continuar();
                    break;
                case 3://jugar fins a premi sense reintegrament
                    maquinaAdmin.setEstaPremiat(false);
                    while (!maquinaAdmin.getEstaPremiat()){
                        sorteig = new Sorteig();
                        numeroSorteig = sorteig.generarNumeroSorteig();
                        reintegroBombo = sorteig.getReintegroBombo();
                        complementari = sorteig.getComplementari();
                        maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                , maquinaAdmin.getReintegroJugador(), reintegroBombo, complementari
                                , "sense Reintegro");
                        if (maquinaAdmin.getEstaPremiat()){
                            System.out.println("Ha fet un total de: " +maquinaAdmin.getContadorJugades()
                                    + " jugades.");
                            maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo,complementari);

                        }
                    }
                    maquinaAdmin.setContadorJugades(0);
                    Lib.continuar();
                    break;
                case 4://Jugar 10.000 vegades
                    for (int i=0; i<10000; i++){
                        sorteig = new Sorteig();
                        numeroSorteig = sorteig.generarNumeroSorteig();
                        reintegroBombo = sorteig.getReintegroBombo();
                        complementari = sorteig.getComplementari();
                        maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                , maquinaAdmin.getReintegroJugador(), reintegroBombo, complementari);
                    }
                    System.out.println("En: " + maquinaAdmin.getContadorJugades() + " jugades."
                            + " Ha tret: ");
                    System.out.print("Categoria especial: " + maquinaAdmin.getContadorEspecial() + "\n");
                    System.out.print("Primera categoria: " + maquinaAdmin.getContadorPrimer() + "\n");
                    System.out.print("Segona categoria: " + maquinaAdmin.getContadorSegon() + "\n");
                    System.out.print("Tercera categoria: " + maquinaAdmin.getContadorTercer() + "\n");
                    System.out.print("Cuarta categoria: " + maquinaAdmin.getContadorCuart() + "\n");
                    System.out.print("Quinta categoria: " + maquinaAdmin.getContadorCinque() + "\n");
                    System.out.print("Reintegraments: " + maquinaAdmin.getContadorReintegros() + "\n");
                    maquinaAdmin.setContadorJugades(0);
                    maquinaAdmin.setContadorEspecial(0);
                    maquinaAdmin.setContadorPrimer(0);
                    maquinaAdmin.setContadorSegon(0);
                    maquinaAdmin.setContadorTercer(0);
                    maquinaAdmin.setContadorCuart(0);
                    maquinaAdmin.setContadorCinque(0);
                    maquinaAdmin.setContadorReintegros(0);
                    Lib.continuar();
                    break;
                case 5://jugar fins a especial.
                    maquinaAdmin.setEstaPremiat(false);
                    while (!maquinaAdmin.getEstaPremiat()){
                        sorteig = new Sorteig();
                        numeroSorteig = sorteig.generarNumeroSorteig();
                        reintegroBombo = sorteig.getReintegroBombo();
                        complementari = sorteig.getComplementari();
                        maquinaAdmin.coomprovarPremi(boletoJuagador,numeroSorteig
                                , maquinaAdmin.getReintegroJugador(), reintegroBombo);
                        if (maquinaAdmin.getEstaPremiat()){
                            maquinaAdmin.mostrarPremi(numeroSorteig,reintegroBombo,complementari);
                        }
                    }
                    System.out.println("Ha fet un total de: " + " jugades.");


                    Lib.continuar();
                    break;
            }
            if (eleccio2<0 || eleccio2>5){
                Lib.mensajeError();
            }
        }while (eleccio2 != 0);
    }
}