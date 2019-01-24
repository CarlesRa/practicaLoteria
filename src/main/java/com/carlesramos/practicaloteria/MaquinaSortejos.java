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
        int comprovacio=0;
        plenarBombo1();
        plenarBombo2();
        for (int i=0; i<numeros.length-1; i++){
            while (random == comprovacio) {
                random = Lib.random(0, 48);
            }
            numeros[i] = bombo1[random];
            comprovacio = random;
        }
        numeros [7] = bombo2 [Lib.random(0,9)];
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
