package com.carlesramos.practicaloteria;
import com.carlesramos.practicaloteria.utils.Lib;
import java.util.Arrays;
import java.util.Scanner;
public class Administracio {
    public enum premis{ESPECIAL,PRIMERA,SEGONA,TERCERA,CUARTA,QUINTA,DEVOLUCIÓ_DINES, NO_PREMIAT}
    private Scanner lec;
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
    private int [] bomboAdmin;
    /**
     * constructor de administració
     */
    public Administracio(){
        bomboAdmin = new int[49];
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

    //getters i setters

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

    /**
     * comprova si el premi esta premiat
     * @param numeroClient pasem el número del client
     * @param numeroSorteig pasem el numero que ha eixit al sorteig
     * @param reintegroJugador pasem el reintegro del jugador
     * @param reintegroBombo pasem el reintegro del sorteig
     * @param complementari pasem el complementari del sorteig
     */
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

    /**
     * comprova el premi sense tindre en compte els del reintegrament
     * @param numeroClient pasem el numero de client.
     * @param numeroSorteig pasem el numero del sorteig.
     * @param reintegroJugador pasem el reintegro del client
     * @param reintegroBombo pasem el reintegro del sorteig
     * @param complementari pasem el complementari del sorteig
     * @param noReintegrament pasem un string per diferenciar els metodes
     */
    public void coomprovarPremi(int [] numeroClient, int [] numeroSorteig, int reintegroJugador
            , int reintegroBombo, int complementari, String noReintegrament){
        opcioPremi = 0;
        int contador = 0;
        boolean aciertoComplementario = false;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length;z++){
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

    /**
     * comprova si ha hagut premi especial.
     * @param numeroClient pasem el numero del client.
     * @param numeroSorteig pasem el numero del sorteig.
     * @param reintegroJugador pasem el reintegro del client.
     * @param reintegroBombo pasem el reintegro del sorteig.
     */
    public void coomprovarPremi(int [] numeroClient, int [] numeroSorteig, int reintegroJugador
            , int reintegroBombo){
        opcioPremi = 0;
        int contador = 0;
        for (int i=0; i<numeroClient.length; i++){
            for (int z=0; z<numeroSorteig.length;z++){
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
        else{
            setContadorJugades(getContadorJugades()+1);
        }
    }

    /**
     * mosytra els premis segons els acerts.
     * @param numeroSorteig pasem el numero del sorteig.
     * @param reintegroBombo pasem el reintegro del sorteig.
     * @param complementari pasem el complementari del sorteig.
     */
    public void mostrarPremi(int [] numeroSorteig, int reintegroBombo, int complementari){
        switch (opcioPremi){
            case 1:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig))
                        + " C:"+ complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.ESPECIAL);
                break;
            case 2:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig))+ " C:"+ complementari
                        +" R:" + reintegroBombo+ " voste te: "+Arrays.toString(Lib.ordernaVector(getBoletoJugador()))
                        + " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.PRIMERA);
                break;
            case 3:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig)) + " C:"
                        + complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.SEGONA);
                break;
            case 4:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig)) + " C:"
                        + complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.TERCERA);
                break;
            case 5:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig))
                        + " C:"+ complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.CUARTA);
                break;
            case 6:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig)) + " C:"
                        + complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.QUINTA);
                break;
            case 7:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig))
                        + " C:"+ complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.DEVOLUCIÓ_DINES);
                break;
            case 8:
                System.out.println("Ha eixit: "+Arrays.toString(Lib.ordernaVector(numeroSorteig)) + " C:"
                        + complementari+" R:" + reintegroBombo+ " voste te: "
                        +Arrays.toString(Lib.ordernaVector(getBoletoJugador()))+ " R:" + reintegroJugador);
                System.out.println("El seu premi es: " + premis.NO_PREMIAT);
                break;
        }
    }
    public void plenarBombo(){
        int numsBombo1=1;
        for (int i=0; i<bomboAdmin.length; i++){
            bomboAdmin[i]=numsBombo1;
            numsBombo1++;
        }
    }
}
