package com.carlesramos.practicaloteria;
import com.carlesramos.practicaloteria.utils.Lib;

public class MaquinaSortejos {
    private int [] bombo1;
    private int [] bombo2;
    public MaquinaSortejos(){
        bombo1 = new int[49];
        bombo2 = new int[10];
    }

    public int [] getBombo1(){
        return bombo1;
    }

    public int [] getBombo2(){
        return bombo2;
    }

    public int [] jocUnic(){
        int [] numeros = new int[8];
        int random=0;
        int posicioFinal=49;
        plenarBombo1();
        plenarBombo2();
        for (int i=0; i<numeros.length; i++){
            random = Lib.random(0,posicioFinal);
            numeros [i] = bombo1 [random];
            for (int x=random; x<bombo1.length-1; x++){
                bombo1[x]=bombo1[x+1];
            }
            posicioFinal--;
        }

        return numeros;
    }

    public void plenarBombo1(){
        int numsBombo1=1;
        for (int i=0; i<bombo1.length; i++){
            bombo1[i]=numsBombo1;
            numsBombo1++;
        }
        for (int z=0; z<bombo2.length; z++){
            bombo2[z]=z;
        }
    }

    public void plenarBombo2(){
        for (int z=0; z<bombo2.length; z++){
            bombo2[z]=z;
        }
    }
}
