package test;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.modelo.banco.Bank;
import simulador.bolsa.poo.modelo.banco.personas.AgenteBolsa;
import simulador.bolsa.poo.modelo.bolsa.BolsaValores;

public class TestBanco {
    private static final Bank BANCO = new Bank("test",new AgenteBolsa("testAg","45687568S",new BolsaValores()));

    public static void main(String[]args){
        testBanco();
        testNombreNull();
        testDniNull();

    }


    public static void testBanco(){

        BANCO.addCliente("Pedro","45687984S",30);
        BANCO.imprimir();
        try {
            BANCO.deleteCliente("45687984S");
        } catch (InexistentClientException e) {
            e.printStackTrace();
        }
        BANCO.imprimir();

        BANCO.addCliente("Pedro","45687984S",30);
        BANCO.imprimir();

        try {
            BANCO.mejorarCliente("45687984S");
            BANCO.solicitarRecomendacion("45687984S");
        } catch (InexistentClientException e) {
            e.printStackTrace();
        } catch (NotPremiumClientException e) {
            e.printStackTrace();
        }

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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
        BANCO.imprimir();
    }

    public static void testNombreNull(){
        try {
            BANCO.addCliente(null, "45687984S", 30);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        BANCO.imprimir();
        try {
            BANCO.deleteCliente("45687984S");
        } catch (InexistentClientException e) {
            e.printStackTrace();
        }
        BANCO.imprimir();

        BANCO.addCliente("Pedro","45687984S",30);
        BANCO.imprimir();

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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
        BANCO.imprimir();
    }

    public static void testDniNull(){
        try {
            BANCO.addCliente("Pedro", null, 30);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        BANCO.imprimir();
        try {
            BANCO.deleteCliente("45687984S");
        } catch (InexistentClientException e) {
            e.printStackTrace();
        }
        BANCO.imprimir();

        BANCO.addCliente("Pedro","45687984S",30);
        BANCO.imprimir();

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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

        BANCO.imprimir();

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
        BANCO.imprimir();
    }



}
