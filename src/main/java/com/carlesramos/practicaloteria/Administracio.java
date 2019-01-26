package com.carlesramos.practicaloteria;
import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;
public class Administracio {
    public enum premis{ESPECIAL,PRIMERA,SEGONA,TERCERA,CUARTA,QUINTA, NO_PREMIAT}
    private Scanner lec;
    private Sorteig sorteig;
    public Administracio(){
        sorteig = new Sorteig ();
        lec = new Scanner(System.in);
    }

    public int [] primitivaManual(){
        int random;
        int [] boletoJugador = new int[6];
        boolean estaRepetido = true;
        int numero;
        for(int i=0; i<boletoJugador.length; i++) {
            do {
                estaRepetido = true;
                do {
                    System.out.print("Introduix el número " + (i + 1) + ":");
                    numero = lec.nextInt();
                    lec.nextLine();
                    if (numero<0 || numero>49){
                        Lib.mensajeError();
                    }
                }while (numero<0 || numero>49);

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
        return boletoJugador;
    }
    public int [] primitivaAleatoria(){
        int [] numeros = new int[6];
        int random=0;
        int posicioFinal=48;
        sorteig.plenarBombo1();
        for (int i=0; i<numeros.length; i++){
            random = Lib.random(0,posicioFinal);
            numeros [i] = sorteig.getBombo1()[random];
            sorteig.getBombo1()[random] = sorteig.getBombo1()[posicioFinal];
            posicioFinal--;
        }
        return numeros;
    }

    //comprovar si el el numero esta premiat e indicar el premi.
    public void coomprovarPremi(int [] numeroClient,int [] numeroSorteig, int reintegroJugador, int reintegroBombo){
        int contador = 0;
        boolean aciertoComplementario;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length-1;z++){
                if(numeroClient[i]==numeroSorteig[z]) {
                    contador++;
                }
                if(numeroClient[i] == numeroSorteig [6]){
                    aciertoComplementario = true;
                }
            }
        }
        if (contador == 6 && reintegroJugador == reintegroBombo){
            System.out.println("Ha eixit: " + numeroSorteig + " Voste te: " + numeroClient);
            System.out.println("El seu premi es: " + premis.ESPECIAL);
            return;
        }
        //goto meter los reintegros
        if (contador == 0){
            System.out.println(" Ha eixit: "+Arrays.toString(numeroSorteig)
                    +" voste te: "+Arrays.toString(numeroClient));
            System.out.println("El seu premi es: "+premis.NO_PREMIAT);

        }
    }


}
