package test;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.modelo.banco.Bank;
import simulador.bolsa.poo.modelo.banco.personas.AgenteBolsa;
import simulador.bolsa.poo.modelo.bolsa.BolsaValores;

public class TestBanco {
    private static final Bank BANCO = new Bank("test",new AgenteBolsa("testAg","45687568S",new BolsaValores()));

    public static void main(String[]args){
        testAddCliente();
        testDelteCliente();
        testAddCliente();
        testAddSolicitudCompra();
        testAddSolicitudVenta();
        testAddSolicitudActualizacion();
        testEjecutarSolicitudes();
    }


    public static void testAddCliente(){

        BANCO.addCliente("clientetest","45687984S",30);
        BANCO.imprimirEstado();
    }
    public static void testDelteCliente(){
        try {
            BANCO.deleteCliente("45687984S");
        } catch (InexistentClientException e) {
            e.printStackTrace();
        }
        BANCO.imprimirEstado();
    }
    public static void testAddSolicitudCompra(){

        try {
            BANCO.realizarSolicitud(0,"45687984S",20,0,"Tesla");
        } catch (NoSuchEnterpriseException e) {
            e.printStackTrace();
        } catch (NotEnoughActionsException e) {
            e.printStackTrace();
        } catch (InvalidCodeException e) {
            e.printStackTrace();
        } catch (InexistentClientException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }

        BANCO.imprimirEstado();
    }

   public static void testAddSolicitudVenta(){

        try {
           BANCO.realizarSolicitud(1,"45687984S",0,2,"Tesla");
       } catch (NoSuchEnterpriseException e) {
           e.printStackTrace();
       } catch (NotEnoughActionsException e) {
           e.printStackTrace();
       } catch (InvalidCodeException e) {
           e.printStackTrace();
       } catch (InexistentClientException e) {
           e.printStackTrace();
       } catch (NotEnoughMoneyException e) {
           e.printStackTrace();
       }

       BANCO.imprimirEstado();
   }
    public static void testAddSolicitudActualizacion(){
        try {
            BANCO.realizarSolicitud(2,"00",0,0,"");
        } catch (NoSuchEnterpriseException e) {
            e.printStackTrace();
        } catch (NotEnoughActionsException e) {
            e.printStackTrace();
        } catch (InvalidCodeException e) {
            e.printStackTrace();
        } catch (InexistentClientException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }

        BANCO.imprimirEstado();
    }
    public static void testEjecutarSolicitudes(){
        try {
            BANCO.ejecutarSolicitudes();
        } catch (InvalidCodeException e) {
            e.printStackTrace();
        } catch (NoSuchEnterpriseException e) {
            e.printStackTrace();
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (NotEnoughActionsException e) {
            e.printStackTrace();
        }
        BANCO.imprimirEstado();
    }
}
