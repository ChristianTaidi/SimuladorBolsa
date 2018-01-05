package test;

import simulador.bolsa.poo.funcionalidades.io.ReadFile;
import simulador.bolsa.poo.funcionalidades.io.WriteFile;


import java.io.IOException;


public class TestIO {

    public static void main(String[]args){
        testFicheroEntero();
        testFicheroCadena();

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testFicheroCadena(){
        try {
            WriteFile testString = new WriteFile("string.dat");
            testString.write("Test");
            ReadFile testlectura = new ReadFile("string.dat");
            Object resultado= testlectura.read();
            if (resultado==null){
                System.out.println("Fallo");
            }
            if (!(resultado instanceof String)){
                System.out.println("Fallo");
            }
            if((String)resultado!="Test"){
                System.out.println("Fallo");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
