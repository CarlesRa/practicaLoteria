package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;

public class Sorteig {

    private int [] bombo1;
    //private int reintegroJugador;
    private int reintegroBombo;
    private int complementari;
    private int [] numeroSorteig;

    public Sorteig(){

        bombo1 = new int[49];
        //reintegroJugador = Lib.random(0,9);
        reintegroBombo = Lib.random(0,9);
        //numeroSorteig = generarNumeroSorteig();

    }

    public int [] getBombo1(){
        return bombo1;
    }

   /* public int getReintegroJugador() {
        return reintegroJugador;
    }*/

    public int getReintegroBombo() {
        return reintegroBombo;
    }

    public int getComplementari(){
        return complementari;
    }

    public int [] getNumeroSorteig(){
        return numeroSorteig;
    }

    public void plenarBombo1(){
        int numsBombo1=1;
        for (int i=0; i<bombo1.length; i++){
            bombo1[i]=numsBombo1;
            numsBombo1++;
        }
    }

    public int []generarNumeroSorteig(){
        int [] numeros = new int[6];
        int random;
        int posicioFinal=48;
        plenarBombo1();
        for (int i=0; i<=numeros.length; i++){
            random = Lib.random(0,posicioFinal);
            if (i<numeros.length-1) {
                numeros[i] = getBombo1()[random];
                getBombo1()[random] = getBombo1()[posicioFinal];
            }
            if (i==numeros.length){
                complementari = getBombo1()[posicioFinal];
            }
            posicioFinal--;
        }
        numeroSorteig = numeros;
        return numeros;
    }

}

