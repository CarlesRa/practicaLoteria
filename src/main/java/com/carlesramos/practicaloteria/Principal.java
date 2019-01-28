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

    public static void main(String[] args) {
        maquinaAdmin = new Administracio();
        lec = new Scanner(System.in);
        sorteig = new Sorteig();
        do {
            eleccio = menuInicial();
            if (eleccio == 0){
                System.out.println("Adeu!!! fins la proxima.");
            }
            else if (eleccio<1 || eleccio>2 && maquinaAdmin.getBoletoJugador()[0] == 0
                    && maquinaAdmin.getBoletoJugador()[1] == 0){
                System.out.println("No ha plenat el bolet....");
                eleccio = -1;
            }
            switch (eleccio) {
                case 1://Primitiva manual.
                    maquinaAdmin.primitivaManual();

                    System.out.println("Primitiva Generada: " + Arrays.toString(maquinaAdmin.getBoletoJugador())
                            + " R: "+maquinaAdmin.getReintegroJugador());
                    Lib.continuar();
                    break;
                case 2://primitiva aleatoria
                    maquinaAdmin.primitivaAleatoria();

                    System.out.println("Primitiva Generada: " + Arrays.toString(maquinaAdmin.getBoletoJugador())
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
    public static int menuJugar() {
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
     * conte les opcions de joc del menuJugar
     */
    public static void jugar(){
        do {
            eleccio2 = menuJugar();
            switch (eleccio2) {
                case 0://eixir
                    break;
                case 1:
                    jugarUnaVegada();
                    break;
                case 2:
                    jugarFinsPremi();
                    break;
                case 3:
                    jugarFinsPremiNoReintegrament();
                    break;
                case 4:
                    jugarDeumilVegades();
                    break;
                case 5:
                    jugarFinsEspecial();
                    break;
            }
            if (eleccio2<0 || eleccio2>5){
                Lib.mensajeError();
            }
        }while (eleccio2 != 0);
    }

    /**
     * realitza un sol sorteig
     */
    public static void jugarUnaVegada(){
        maquinaAdmin.coomprovarPremi(maquinaAdmin.getBoletoJugador(),sorteig.generarNumeroSorteig()
        , maquinaAdmin.getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari());
        maquinaAdmin.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                ,sorteig.getComplementari());
        Lib.continuar();
    }

    /**
     * juga fins obtindre un premi
     */
    public static void jugarFinsPremi(){
        maquinaAdmin.setEstaPremiat(false);
        while (!maquinaAdmin.getEstaPremiat()){

            maquinaAdmin.coomprovarPremi(maquinaAdmin.getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , maquinaAdmin.getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari());
            if (maquinaAdmin.getEstaPremiat()){
                System.out.println("Ha fet un total de: " + maquinaAdmin.getContadorJugades()
                        + " jugades.");
                maquinaAdmin.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                        ,sorteig.getComplementari());
            }
        }
        maquinaAdmin.setContadorJugades(0);
        Lib.continuar();
    }

    /**
     * jugta fins obtindre premi (sense reintegrament)
     */
    public static void jugarFinsPremiNoReintegrament(){
        maquinaAdmin.setEstaPremiat(false);
        while (!maquinaAdmin.getEstaPremiat()){

            maquinaAdmin.coomprovarPremi(maquinaAdmin.getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , maquinaAdmin.getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari()
                    , "sense Reintegro");
            if (maquinaAdmin.getEstaPremiat()){
                System.out.println("Ha fet un total de: " +maquinaAdmin.getContadorJugades()
                        + " jugades.");
                maquinaAdmin.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                        ,sorteig.getComplementari());

            }
        }
        maquinaAdmin.setContadorJugades(0);
        Lib.continuar();
    }

    /**
     * juga 10.000 vegades i mostra el numero de premis de cada categoria
     */
    public static void jugarDeumilVegades(){
        for (int i=0; i<10000; i++){
            maquinaAdmin.coomprovarPremi(maquinaAdmin.getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , maquinaAdmin.getReintegroJugador(), sorteig.getReintegroBombo(), sorteig.getComplementari());
        }
        System.out.println("En: " + maquinaAdmin.getContadorJugades() + " jugades." + " Ha tret: ");
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
    }

    /**
     * juga fins que trau un premi especial, i diu les tirades neseçaries per aconseguiro.
     */
    public static void jugarFinsEspecial(){
        maquinaAdmin.setEstaPremiat(false);
        while (!maquinaAdmin.getEstaPremiat()){
            maquinaAdmin.coomprovarPremi(maquinaAdmin.getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , maquinaAdmin.getReintegroJugador(), sorteig.generarReintegroBombo());
            if (maquinaAdmin.getEstaPremiat()){
                maquinaAdmin.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                        ,sorteig.getComplementari());
            }
        }
        System.out.println("Ha fet un total de: " + maquinaAdmin.getContadorJugades() + " jugades.");
        maquinaAdmin.setContadorJugades(0);
        Lib.continuar();
    }
}