package com.carlesramos.practicaloteria;

import com.carlesramos.practicaloteria.utils.Lib;

public class Sorteig {

    private int [] bombo1;
    private int reintegroBombo;
    private int complementari;
    private int [] numeroSorteig;

    public Sorteig(){
        bombo1 = new int[50];
        reintegroBombo = Lib.random(0,9);
    }

    public int [] getBombo1(){
        return bombo1;
    }

    public int getReintegroBombo() {
        return reintegroBombo;
    }

    public int getComplementari(){
        return complementari;
    }

    public void setComplementari(int complementari){
        this.complementari = complementari;
    }

    public void plenarBombo1(){
        int numsBombo1=0;
        for (int i=0; i<bombo1.length; i++){
            bombo1[i]=numsBombo1;
            numsBombo1++;
        }
    }

    public int []generarNumeroSorteig(){
        int [] numeros = new int[6];
        int random;
        int posicioFinal=49;
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

}

