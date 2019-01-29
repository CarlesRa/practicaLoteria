package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;

public class Sorteig {

    private int [] bombo1;
    private int [] numeroSorteig;
    private int reintegroBombo;
    private int complementari;

    /**
     * constructor de sortejos
     */
    public Sorteig(){
        bombo1 = new int[49];
    }

    //getters i setters

    public int getReintegroBombo(){
        return reintegroBombo;
    }

    public int [] getNumeroSorteig(){
        return numeroSorteig;
    }
    public int [] getBombo1(){
        return bombo1;
    }

    public int getComplementari(){
        return complementari;
    }

    public void setComplementari(int complementari){
        this.complementari = complementari;
    }

    //metodes

    /**
     * metode per omplir el bombo amb numeros del 1 al 49.
     */
    public void plenarBombo1(){
        int numsBombo1=1;
        for (int i=0; i<bombo1.length; i++){
            bombo1[i]=numsBombo1;
            numsBombo1++;
        }
    }

    /**
     * genera el numero del sorteig
     * @return retorna el numero del sorteig
     */
    public int []generarNumeroSorteig(){
        int [] numeros = new int[6];
        int random;
        int posicioFinal=48;
        plenarBombo1();
        for (int i=0; i<=numeros.length; i++){
            random = Lib.random(0,posicioFinal);
            if (i<numeros.length) {
                numeros[i] = getBombo1()[random];
                getBombo1()[random] = getBombo1()[posicioFinal];
            }
            if (i==numeros.length){
                setComplementari(getBombo1()[random]);
            }
            posicioFinal--;
        }
        numeroSorteig = numeros;
        return numeros;
    }

    public int generarReintegroBombo(){
        reintegroBombo = Lib.random(0,9);
        return reintegroBombo;
    }

}