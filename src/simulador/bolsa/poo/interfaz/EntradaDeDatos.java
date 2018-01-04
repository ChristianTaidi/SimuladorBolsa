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
        entrada.useDelimiter("\\n");
    }


    public String leerCadena() {
        entrada.nextLine();
        return entrada.nextLine();
    }


    public int leerEntero() throws InputMismatchException{
        return entrada.nextInt();
    }

    public float leerFloat()throws InputMismatchException{
        return entrada.nextFloat();
    }

    public char leerCaracter(){
        return entrada.next().charAt(0);
    }
}