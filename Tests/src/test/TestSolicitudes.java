package test;

import simulador.bolsa.poo.modelo.solicitudes.*;

public class TestSolicitudes {


    public static void main(String[]args){
        testCompra();
        testRespuestaCompra();
        testVenta();
        testRespuestaVenta();
        testActualizacion();
        testRespuestaActualizacion();
    }

    public static void testCompra(){
        Mensaje testCompra = new MensajeCompra(0,"45687568S",(float)10.5,"Tesla");
        System.out.println(testCompra.codificar());
    }

    public static void testRespuestaCompra(){
        Mensaje testRespCompra = new MensajeRespuestaCompra(0,"45687568S","Tesla",true,2,(float)30.5,20);
        System.out.println(testRespCompra.codificar());
    }

    public static void testVenta(){
        Mensaje testVenta = new MensajeVenta(1,"45687568S",2,"Tesla");
        System.out.println(testVenta.codificar());
    }

    public static void testRespuestaVenta(){
        Mensaje testRespVenta = new MensajeRespuestaVenta(1,"45687568S","Tesla",true,2,(float)30.5,20);
        System.out.println(testRespVenta.codificar());
    }

    public static void testActualizacion(){
        Mensaje testActualizacion = new MensajeActualizacion(2);
        System.out.println(testActualizacion.codificar());
    }

    public static void testRespuestaActualizacion(){
        Mensaje testRespActualizacion = new MensajeRespuestaActualizacion(2,"Tesla",20);
        System.out.println(testRespActualizacion.codificar());
    }
}
