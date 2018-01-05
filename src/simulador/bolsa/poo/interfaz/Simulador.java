package simulador.bolsa.poo.interfaz;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.funcionalidades.io.ReadFile;
import simulador.bolsa.poo.funcionalidades.io.WriteFile;
import simulador.bolsa.poo.modelo.banco.*;
import simulador.bolsa.poo.modelo.banco.personas.AgenteBolsa;
import simulador.bolsa.poo.modelo.banco.personas.Gestor;
import simulador.bolsa.poo.modelo.bolsa.BolsaValores;

import java.io.IOException;

public class Simulador {

    private Bank banco;
    private BolsaValores bolsa;
    private AgenteBolsa broker;
    private Gestor gest;
    private static final String FICHERO_BANCO = "banco.dat";
    private static final String FICHERO_BOLSA = "bolsa.dat";

    public Simulador(){
        this.bolsa = new BolsaValores();
        this.broker = new AgenteBolsa("Pedro","76514859G",this.bolsa);
        this.gest = new Gestor("Antonio","12345678A",this.bolsa);
        this.banco = new Bank("Simulador",this.broker,this.gest);


    }

    public void printMenu(){
        System.out.println("***** MENU *****");
        System.out.println("0.- Salir");
        System.out.println("<<<<< ESTADO >>>>>");
        System.out.println("1.- Imprimir estado de los clientes");
        System.out.println("2.- Imprimir estado de la bolsa");
        System.out.println("<<<<< BANCO >>>>>");
        System.out.println("3.- Añadir cliente");
        System.out.println("4.- Eliminar cliente");
        System.out.println("5.- Realizar copia de seguridad");
        System.out.println("6.- Restaurar copia de seguridad");
        System.out.println("7.- Mejorar cliente a premium");
        System.out.println("8.- Solicitar recomendación de inversión");
        System.out.println("<<<<< BOLSA >>>>>");
        System.out.println("9.- Añadir empresa a la bolsa");
        System.out.println("10.- Eliminar empresa de la bolsa");
        System.out.println("11.- Actualización de valores");
        System.out.println("12.- Realizar copia de seguridad");
        System.out.println("13.- Restaurar copia de seguridad");
        System.out.println("<<<<< OPERACIONES >>>>>");
        System.out.println("14.- Solicitar compra de acciónes");
        System.out.println("15.- Solicitar venta de acciónes");
        System.out.println("16.- Solicitar actualizacion de valores");
        System.out.println("<<<<< BRÓKER >>>>>");
        System.out.println("17.- Imprimir operaciones pendientes");
        System.out.println("18.- Ejecutar operaciones");
    }

    public void backupLoad(String codElem) throws InvalidBackupElementException{
        ReadFile load =null;

        try {
        switch (codElem) {
            case "banc":

                    load= new ReadFile(FICHERO_BANCO);
                    banco = (Bank) load.read();
                    this.setBroker(banco.getAgente());
                break;
            case "bolsa":

                    load = new ReadFile(FICHERO_BOLSA);
                    bolsa = (BolsaValores) load.read();
                    this.broker.setBolsa(bolsa);

                break;
            default:
                throw new InvalidBackupElementException("No se puede hacer copia de seguridad del elemento: " + codElem);
        }
        } catch (IOException e) {
            System.out.println("Error al cargar copia de seguridad");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            load.cerrar();
        }
    }

    private void setBroker(AgenteBolsa agente) {
        this.broker=agente;
    }

    public void backupSave(String codElem) throws InvalidBackupElementException{
        WriteFile save = null;
        try {
            switch (codElem) {
                case "banc":

                    save = new WriteFile(FICHERO_BANCO);
                    save.write(banco);



                    break;
                case "bolsa":

                    save = new WriteFile(FICHERO_BOLSA);;
                    save.write(bolsa);


                    break;
                default:
                    throw new InvalidBackupElementException("No se puede hacer copia de seguridad del elemento: " + codElem);

            }
            }catch (IOException e){
                    System.out.println("Error al guardar copia de seguridad");
                    e.printStackTrace();

        }finally{
                save.cerrar();
        }
    }

    public void printClients() {
        banco.imprimir();
    }

    public void printBolsa() {
        bolsa.imprimir();
    }

    public void addCliente(String nombre, String dni,float saldo) {

        banco.addCliente(nombre,dni,saldo);
    }

    public void deleteCliente(String dni){
        try {
            banco.deleteCliente(dni);
        }catch(InexistentClientException e){
            System.out.println(e.getMessage());
        }
    }

    public void mejorarCliente(String dni){
        try {
            banco.mejorarCliente(dni);
        }catch(InexistentClientException e){
            System.out.println(e.getMessage());
        }
    }

    public void addEmpresa(String nombre, float precio){
            bolsa.añadirEmpresa(nombre, precio);
    }

    public void deleteEmpresa (String nombre) throws NoSuchEnterpriseException{
            bolsa.borrarEmpresa(nombre);

    }

    public void solicitarCompra(String dni,float dinero, String empresa) throws NoSuchEnterpriseException,NotEnoughActionsException,NotEnoughMoneyException,InexistentClientException{
       try{
        banco.realizarSolicitud(0,dni,dinero,0,empresa);
       }catch (InvalidCodeException e){
           e.printStackTrace();
       }
    }

    public void solicitarVenta( String dni,int nAcciones, String empresa) throws NoSuchEnterpriseException,NotEnoughActionsException,InexistentClientException,NotEnoughMoneyException{
        try{
            banco.realizarSolicitud(1,dni,0,nAcciones,empresa);
        }catch(InvalidCodeException e){
            e.printStackTrace();
        }
    }

    public void updateValores (){
        System.out.println("Los valores en bolsa estan actualizados( se actualizan automaticamente)");
    }

    public void solicitarActualizacion(){
        try {
            banco.realizarSolicitud(2,"",0,0,"");
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
    }

    public void recomendacion(String dni,int numEmpresas) throws NotPremiumClientException,InexistentClientException{
         banco.solicitarRecomendacion(dni,numEmpresas);

    }

    public void printOperaciones(){
        broker.imprimir();
    }
    public void ejecutarOperaciones(){

        try {
            banco.ejecutarSolicitudes();
        } catch (InvalidCodeException e) {
            e.printStackTrace();
        } catch (NoSuchEnterpriseException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughActionsException e) {
            System.out.println(e.getMessage());
        }
    }
}
