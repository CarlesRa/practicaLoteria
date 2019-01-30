package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Scanner;



public class Administracio {
    private Scanner lec;
    private int reintegroJugador;
    private Sorteig sorteig;
    private Terminal terminal;
    private int [] boletoJugador;
    private int [] bomboAdmin;

    /**
     * constructor de administració
     */
    public Administracio(){
        bomboAdmin = new int[49];
        lec = new Scanner(System.in);
        boletoJugador = new int[6];
        sorteig = new Sorteig();
        terminal = new Terminal();
    }

    //getters i setters

    public int [] getBoletoJugador(){
        return boletoJugador;
    }

    public int getReintegroJugador(){
        return reintegroJugador;
    }

    //metodes

    /**
     * plena una primitiva manualment
     */
    public void primitivaManual(){
        int random;
        boolean estaRepetido;
        int numero;
        for(int i=0; i<boletoJugador.length; i++) {
            do {
                estaRepetido = true;
                do {
                    System.out.print("Introduix el número " + (i + 1) + ":");
                    numero = lec.nextInt();
                    lec.nextLine();
                    if (numero<1 || numero>49){
                        Lib.mensajeError();
                    }
                }while (numero<1 || numero>49);

                for (int z=0; z<i; z++){
                    if (numero == boletoJugador[z]) {
                        estaRepetido = false;
                        Lib.mensajeError();
                    }
                }
            }while (!estaRepetido);
            boletoJugador[i] = numero;
        }
        do {
            random = Lib.random(0,48);
            estaRepetido = true;
            for (int i=0; i<boletoJugador.length; i++){

                    if (random == boletoJugador[i]) {
                        random = Lib.random(0, 48);
                        estaRepetido = false;
                    }
            }
        }while(!estaRepetido);
        reintegroJugador = Lib.random(0,9);
    }

    /**
     * plena una primitiva aleatoria.
     */
    public void primitivaAleatoria(){
        int random=0;
        int posicioFinal=48;
        plenarBombo();
        for (int i=0; i<boletoJugador.length; i++){
            random = Lib.random(0,posicioFinal);
            boletoJugador [i] = bomboAdmin[random];
            bomboAdmin[random] = bomboAdmin[posicioFinal];
            posicioFinal--;
        }
        reintegroJugador = Lib.random(0,9);
    }


    public void plenarBombo(){
        int numsBombo1=1;
        for (int i=0; i<bomboAdmin.length; i++){
            bomboAdmin[i]=numsBombo1;
            numsBombo1++;
        }
    }

    /**
     * realitza un sol sorteig
     */
    public void jugarUnaVegada(){
        terminal.coomprovarPremi(getBoletoJugador(),sorteig.generarNumeroSorteig()
        , getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari());
        terminal.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
        ,sorteig.getComplementari(),getBoletoJugador(),getReintegroJugador());
        Lib.continuar();
    }

    /**
     * juga fins obtindre un premi
     */
    public void jugarFinsPremi(){
        terminal.setEstaPremiat(false);
        while (!terminal.getEstaPremiat()){

            terminal.coomprovarPremi(getBoletoJugador(),sorteig.generarNumeroSorteig()
            , getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari());
            if (terminal.getEstaPremiat()){
                System.out.println("Ha fet un total de: " + terminal.getContadorJugades()
                        + " jugades.");
                terminal.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo(),
                sorteig.getComplementari(), getBoletoJugador(),getReintegroJugador());
            }
        }
        terminal.setContadorJugades(0);
        Lib.continuar();
    }

    /**
     * juga fins obtindre premi (sense reintegrament)
     */
    public void jugarFinsPremiNoReintegrament(){
        terminal.setEstaPremiat(false);
        while (!terminal.getEstaPremiat()){

            terminal.coomprovarPremi(getBoletoJugador(),sorteig.generarNumeroSorteig()
            , getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari()
            , "sense Reintegro");
            if (terminal.getEstaPremiat()){
                System.out.println("Ha fet un total de: " + terminal.getContadorJugades()
                + " jugades.");
                terminal.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                ,sorteig.getComplementari(),getBoletoJugador(), getReintegroJugador());

            }
        }
        terminal.setContadorJugades(0);
        Lib.continuar();
    }

    /**
     * juga 10.000 vegades i mostra el numero de premis de cada categoria
     */
    public void jugarDeumilVegades(){
        for (int i=0; i<10000; i++){
            terminal.coomprovarPremi(getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , getReintegroJugador(), sorteig.generarReintegroBombo(), sorteig.getComplementari());
        }
        System.out.println("En: " + terminal.getContadorJugades() + " jugades." + " Ha tret: ");
        System.out.print("Categoria especial: " + terminal.getContadorEspecial() + "\n");
        System.out.print("Primera categoria: " + terminal.getContadorPrimer() + "\n");
        System.out.print("Segona categoria: " + terminal.getContadorSegon() + "\n");
        System.out.print("Tercera categoria: " + terminal.getContadorTercer() + "\n");
        System.out.print("Cuarta categoria: " + terminal.getContadorCuart() + "\n");
        System.out.print("Quinta categoria: " + terminal.getContadorCinque() + "\n");
        System.out.print("Reintegraments: " + terminal.getContadorReintegros() + "\n");
        terminal.setContadorJugades(0);
        terminal.setContadorEspecial(0);
        terminal.setContadorPrimer(0);
        terminal.setContadorSegon(0);
        terminal.setContadorTercer(0);
        terminal.setContadorCuart(0);
        terminal.setContadorCinque(0);
        terminal.setContadorReintegros(0);
        Lib.continuar();
    }

    /**
     * juga fins que trau un premi especial, i diu les tirades neseçaries per conseguiro.
     */
    public void jugarFinsEspecial(){
        terminal.setEstaPremiat(false);
        while (!terminal.getEstaPremiat()){
            terminal.coomprovarPremi(getBoletoJugador(),sorteig.generarNumeroSorteig()
                    , getReintegroJugador(), sorteig.generarReintegroBombo());
            if (terminal.getEstaPremiat()){
                terminal.mostrarPremi(sorteig.getNumeroSorteig(),sorteig.getReintegroBombo()
                        ,sorteig.getComplementari(),getBoletoJugador(), getReintegroJugador());
            }
        }
        System.out.println("Ha fet un total de: " + terminal.getContadorJugades() + " jugades.");
        terminal.setContadorJugades(0);
        Lib.continuar();
    }
}