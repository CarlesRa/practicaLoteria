package com.carlesramos.practicaloteria;
import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;
public class Administracio {
    public enum premis{ESPECIAL,PRIMERA,SEGONA,TERCERA,CUARTA,QUINTA,DEVOLUCIÓ_DINES, NO_PREMIAT}
    private Scanner lec;
    private Sorteig reintegroBombo;
    private int reintegroJugador;
    private boolean estaPremiat;
    private int opcioPremi;
    private int [] boletoJugador;
    private int contadorJugades;
    private int contadorEspecial;
    private int contadorPrimer;
    private int contadorSegon;
    private int contadorTercer;
    private int contadorCuart;
    private int contadorCinque;
    private int contadorReintegros;
    public Administracio(){
        reintegroBombo = new Sorteig ();
        lec = new Scanner(System.in);
        opcioPremi = 0;
        boletoJugador = new int[6];
        contadorJugades = 0;
        contadorEspecial = 0;
        contadorPrimer = 0;
        contadorSegon = 0;
        contadorTercer = 0;
        contadorCuart = 0;
        contadorCinque = 0;
        contadorReintegros = 0;
    }

    public int getContadorJugades() {
        return contadorJugades;
    }

    public void setContadorJugades(int contadorJugades) {
        this.contadorJugades = contadorJugades;
    }

    public int getContadorEspecial() {
        return contadorEspecial;
    }

    public void setContadorEspecial(int contadorEspecial) {
        this.contadorEspecial = contadorEspecial;
    }

    public int getContadorPrimer() {
        return contadorPrimer;
    }

    public void setContadorPrimer(int contadorPrimer) {
        this.contadorPrimer = contadorPrimer;
    }

    public int getContadorSegon() {
        return contadorSegon;
    }

    public void setContadorSegon(int contadorSegon) {
        this.contadorSegon = contadorSegon;
    }

    public int getContadorTercer() {
        return contadorTercer;
    }

    public void setContadorTercer(int contadorTercer) {
        this.contadorTercer = contadorTercer;
    }

    public int getContadorCuart() {
        return contadorCuart;
    }

    public void setContadorCuart(int contadorCuart) {
        this.contadorCuart = contadorCuart;
    }

    public int getContadorCinque() {
        return contadorCinque;
    }

    public int getContadorReintegros() {
        return contadorReintegros;
    }

    public void setContadorReintegros(int contadorReintegros) {
        this.contadorReintegros = contadorReintegros;
    }

    public void setContadorCinque(int contadorCinque) {
        this.contadorCinque = contadorCinque;
    }

    public int [] getBoletoJugador(){
        return boletoJugador;
    }

    public int getReintegroJugador(){
        return reintegroJugador;
    }

    public boolean getEstaPremiat(){
        return estaPremiat;
    }

    public void setEstaPremiat(boolean estaPremiat){
        this.estaPremiat = estaPremiat;
    }

    public void primitivaManual(){
        int random;
        int [] boletoJugador = new int[6];
        boolean estaRepetido;
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
        this.boletoJugador = boletoJugador;
        reintegroJugador = Lib.random(0,9);
    }
    public void primitivaAleatoria(){
        int [] numeros = new int[6];
        int random=0;
        int posicioFinal=48;
        reintegroBombo.plenarBombo1();
        for (int i=0; i<numeros.length; i++){
            random = Lib.random(0,posicioFinal);
            numeros [i] = reintegroBombo.getBombo1()[random];
            reintegroBombo.getBombo1()[random] = reintegroBombo.getBombo1()[posicioFinal];
            posicioFinal--;
        }
        this.boletoJugador = numeros;
        reintegroJugador = Lib.random(0,9);
    }

    //comprovar si el el numero esta premiat e indicar el premi.
    public void coomprovarPremi(int [] numeroClient,int [] numeroSorteig, int reintegroJugador
            , int reintegroBombo, int complementari){
        int contador = 0;
        opcioPremi = 0;
        boolean aciertoComplementario = false;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length;z++){
                if(numeroClient[z] == complementari){
                    aciertoComplementario = true;
                }
                if(numeroClient[i]==numeroSorteig[z]) {
                    contador++;
                }
            }
        }
        if (contador == 6 && reintegroJugador == reintegroBombo){
            setContadorJugades(getContadorJugades()+1);
            setContadorEspecial(getContadorEspecial()+1);
            opcioPremi = 1;
            estaPremiat = true;
        }

        else if (contador == 6 && reintegroJugador != reintegroBombo){
            setContadorJugades(getContadorJugades()+1);
            setContadorPrimer(getContadorPrimer()+1);
            opcioPremi = 2;
            estaPremiat = true;
        }

        else if (contador == 5 && aciertoComplementario){
            setContadorJugades(getContadorJugades()+1);
            setContadorSegon(getContadorSegon()+1);
            opcioPremi = 3;
            estaPremiat = true;
        }

        else if (contador == 5){
            setContadorJugades(getContadorJugades()+1);
            setContadorTercer(getContadorTercer()+1);
            opcioPremi = 4;
            estaPremiat = true;
        }

        else if (contador == 4){
            setContadorJugades(getContadorJugades()+1);
            setContadorCuart(getContadorCuart()+1);
            opcioPremi = 5;
            estaPremiat = true;
        }

        else if (contador == 3){
            setContadorJugades(getContadorJugades()+1);
            setContadorCinque(getContadorCinque()+1);
            opcioPremi = 6;
            estaPremiat = true;
        }

        else if (reintegroJugador == reintegroBombo){
            setContadorJugades(getContadorJugades()+1);
            setContadorReintegros(getContadorReintegros()+1);
            opcioPremi = 7;
            estaPremiat = true;
        }

        else if (contador <3){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 8;
            estaPremiat = false;
        }

    }

    //comprova si hi ha premi pero sense tindre en compte els reintegraments.
    public void coomprovarPremi(int [] numeroClient, int [] numeroSorteig, int reintegroJugador
            , int reintegroBombo, int complementari, String noReintegrament){
        opcioPremi = 0;
        int contador = 0;
        boolean aciertoComplementario = false;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length-1;z++){
                if(numeroClient[i] == complementari){
                    aciertoComplementario = true;
                }
                if(numeroClient[i]==numeroSorteig[z]) {
                    contador++;
                }
            }
        }
        if (contador == 6 && reintegroJugador == reintegroBombo){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 1;
            estaPremiat = true;
        }

        else if (contador == 6 && reintegroJugador != reintegroBombo){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 2;
            estaPremiat = true;
        }

        else if (contador == 5 && aciertoComplementario){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 3;
            estaPremiat = true;
        }

        else if (contador == 5){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 4;
            estaPremiat = true;
        }

        else if (contador == 4){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 5;
            estaPremiat = true;
        }

        else if (contador == 3){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 6;
            estaPremiat = true;
        }

        else if (contador <3){
            setContadorJugades(getContadorJugades()+1);
            opcioPremi = 8;
            estaPremiat = false;
        }


    }

    public void coomprovarPremi(int [] numeroClient, int [] numeroSorteig, int reintegroJugador
            , int reintegroBombo){
        opcioPremi = 0;
        int contador = 0;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length-1;z++){
                if(numeroClient[i]==numeroSorteig[z]) {
                    contador++;
                }
            }
        }
        if (contador == 6 && reintegroJugador == reintegroBombo){

            opcioPremi = 1;
            estaPremiat = true;
        }
    }

    public void mostrarPremi(int [] numeroSorteig, int reintegroBombo, int complementari){
        switch (opcioPremi){
            case 1:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.ESPECIAL);
                break;
            case 2:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.PRIMERA);
                break;
            case 3:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.SEGONA);
                break;
            case 4:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.TERCERA);
                break;
            case 5:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.CUARTA);
                break;
            case 6:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.QUINTA);
                break;
            case 7:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.DEVOLUCIÓ_DINES);
                break;
            case 8:
                System.out.println("Ha eixit: "+Arrays.toString(numeroSorteig) + " C:"+ complementari+" R:"
                        + reintegroBombo+ " voste te: "+Arrays.toString(getBoletoJugador())+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.NO_PREMIAT);
                break;
        }
    }
}
