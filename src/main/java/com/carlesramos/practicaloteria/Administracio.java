package com.carlesramos.practicaloteria;
import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;
public class MaquinaSortejos {
    private int [] bombo1;
    private int [] bombo2;
    private Scanner lec;
    public MaquinaSortejos(){
        bombo1 = new int[49];
        bombo2 = new int[10];
        lec = new Scanner(System.in);
    }

    public int [] primitivaManual(){
        int random;
        int [] numsJugador = new int[8];
        boolean condicion = true;
        int numero;
        int posicion = 0;
        for(int i=0; i<numsJugador.length-2; i++) {
            do {
                condicion = true;
                do {
                    System.out.print("Introduix el número " + (i + 1) + ":");
                    numero = lec.nextInt();
                    lec.nextLine();
                    if (numero<0 || numero>49){
                        Lib.mensajeError();
                    }
                }while (numero<0 || numero>49);

                for (int z=0; z<posicion; z++){
                    if (numero == numsJugador[z]) {
                        condicion = false;
                        Lib.mensajeError();
                    }
                }
            }while (!condicion);
            numsJugador[i] = numero;
            posicion++;
        }
        do {
            random = Lib.random(0,48);
            condicion = true;
            for (int i=0; i<numsJugador.length-1; i++){

                    if (random == numsJugador[i]) {
                        random = Lib.random(0, 48);
                        condicion = false;
                    }

            }
        }while(!condicion);
        numsJugador[6]=random;
        plenarBombo2();
        numsJugador[7]=bombo2[Lib.random(0,9)];
        System.out.println(Arrays.toString(numsJugador));
        return numsJugador;
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
