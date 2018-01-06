package simulador.bolsa.poo.interfaz;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase se utiliza para...
 */
public class EntradaDeDatos {
    private Scanner entrada;

    public EntradaDeDatos(){
        entrada = new Scanner(System.in);
    }


    public String leerCadena() {
        return entrada.nextLine();
    }


    public int leerEntero() throws NumberFormatException{
        return Integer.parseInt(entrada.nextLine().trim());
    }

    public float leerFloat()throws NumberFormatException{
        return Float.parseFloat(entrada.nextLine().trim());
    }

    public char leerCaracter(){
        return entrada.next().charAt(0);
    }
}