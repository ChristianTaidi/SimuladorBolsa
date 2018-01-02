package simulador.bolsa.poo.interfaz;

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


    public int leerEntero() {
        return entrada.nextInt();
    }

    public float leerFloat(){
        return entrada.nextFloat();
    }

    public char leerCaracter(){
        return entrada.next().charAt(0);
    }
}