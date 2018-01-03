package test;

import simulador.bolsa.poo.io.*;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;


public class TestIO {

    public static void main(String[]args){
        testFicheroEntero();

    }

    public static void testFicheroEntero(){
        try {
            WriteFile testEntero = new WriteFile("entero.dat");
            testEntero.write(3);
            ReadFile testlectura = new ReadFile("entero.dat");
            Object resultado= testlectura.read();
            if (resultado==null){
                System.out.println("Fallo");
            }
            if (!(resultado instanceof Integer)){
                System.out.println("Fallo");
            }
            if((Integer)resultado!=3){
                System.out.println("Fallo");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testFicheroCadena(){

    }

}
