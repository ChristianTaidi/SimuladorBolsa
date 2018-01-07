package simulador.bolsa.poo.modelo.banco;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.funcionalidades.Decodificador;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.banco.personas.*;
import simulador.bolsa.poo.modelo.banco.personas.clientes.*;
import simulador.bolsa.poo.modelo.solicitudes.MensajeActualizacion;
import simulador.bolsa.poo.modelo.solicitudes.MensajeCompra;
import simulador.bolsa.poo.modelo.solicitudes.MensajeVenta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Bank implements Imprimible,Serializable {

    private String nombre;
    private TreeMap<String,Cliente>clientes;
    private AgenteBolsa agente;
    private  Gestor gest;
    private int contadorSolicitudes;
    private static final int MODULO=3;


    public Bank(String name, AgenteBolsa agente,Gestor gest){
        this.setAgenteBolsa(agente);
        this.setNombre(name);
        this.setGest(gest);
        clientes = new TreeMap<>();
    }

    public int getContadorSolicitudes(){
        return this.contadorSolicitudes;
    }

    public void setAgenteBolsa(AgenteBolsa agent) {
        this.agente = agent;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addCliente(String nombre, String dni,float saldo)throws  IllegalArgumentException{

            clientes.put(dni, new Cliente(nombre, dni, saldo));
    }

    public void deleteCliente(String dni) throws  InexistentClientException,IllegalArgumentException{
        if (dni!= null) {
            if (clientes.containsKey(dni)) {
                clientes.remove(dni);
            } else {
                throw new InexistentClientException("El cliente que desea borrar no existe, si desea añadirlo sleccione la opción <3> del menú");
            }
        }else{
            throw new IllegalArgumentException("El dni no puede ser nulo");
        }
    }

    public void imprimir(){
        if (this.clientes.size()>0) {
            for (Cliente cliente : clientes.values()) {
                cliente.imprimir();
            }
        }else{
            System.out.println("El banco no tiene clientes");
        }
    }

    public void mejorarCliente(String dni)throws InexistentClientException,IllegalArgumentException{
        if (dni != null) {
            if (clientes.containsKey(dni)) {
                clientes.put(dni, new ClientePremium(clientes.get(dni), gest));
            } else {
                throw new InexistentClientException("El Cliente no existe");
            }
        }else{
            throw new IllegalArgumentException("El dni no puede ser nulo");
        }

    }

    public void solicitarRecomendacion(String dni,int numEmpresas) throws NotPremiumClientException,InexistentClientException,IllegalArgumentException{
        if (dni != null) {
            if (clientes.containsKey(dni)) {
                if (clientes.get(dni) instanceof  ClientePremium) {
                    gest.recomendar(numEmpresas);
                } else {
                    throw new NotPremiumClientException("El cliente no es premium, para ser premium seleccione la opción <7>");
                }
            }else {
                throw new InexistentClientException("El Cliente no existe, para añadir el cliente seleccione la opción <3>");
            }
        }else{
            throw new IllegalArgumentException("El dni no puede ser nulo");
        }
    }
    public void realizarSolicitud(int cod,String dni, float dinero,int nAcc, String empresa) throws IllegalArgumentException,NoSuchEnterpriseException,NotEnoughActionsException,InvalidCodeException,InexistentClientException,NotEnoughMoneyException{
        if ((dni!= null)&&(empresa != null)) {

                switch (cod) {
                    case 0:
                        if (clientes.containsKey(dni)) {
                                agente.addSolicitud(new MensajeCompra((this.getContadorSolicitudes() * MODULO) + cod, clientes.get(dni).getDni(), dinero, empresa));
                                this.incrementarContadorSolicitudes();
                        } else {
                            throw new InexistentClientException("El Cliente no existe");
                        }
                        break;
                    case 1:
                        if (clientes.containsKey(dni)) {

                                agente.addSolicitud(new MensajeVenta((this.getContadorSolicitudes() * MODULO) + cod, clientes.get(dni).getNombre(), nAcc, empresa));
                                this.incrementarContadorSolicitudes();
                        }else{
                            throw new InexistentClientException("El Cliente no existe");
                         }
                        break;
                    case 2:
                            agente.addSolicitud(new MensajeActualizacion((this.getContadorSolicitudes() * MODULO) + cod));
                            this.incrementarContadorSolicitudes();
                        break;

                    default:
                        throw new InvalidCodeException("Fallo a la hora de ejecutar la solicitud, inténtelo de nuevo");
                }



        }else{
            throw new IllegalArgumentException("El dni no puede ser nulo, y la empresa tampoco") ;
        }
    }

    private void incrementarContadorSolicitudes() {
        this.contadorSolicitudes=this.getContadorSolicitudes()+1;
    }

    private void resetContadorSolicitudes(){
        this.contadorSolicitudes=0;
    }

    public void ejecutarSolicitudes () throws InvalidCodeException, NoSuchEnterpriseException, NotEnoughMoneyException, NotEnoughActionsException, InexistentClientException {
        this.resetContadorSolicitudes();
        ArrayList<String> mensajes = agente.ejecutarSolicitudes();
        Decodificador decod = new Decodificador("|");
        for (String mensaje : mensajes) {
            try {
                decod.asignarCadena(mensaje);
                if (decod.getAcceso()) {
                    if (decod.getCodigoId() % MODULO == 0) {
                        if (clientes.containsKey(decod.getCliente())) {
                            Cliente clienteActual = this.clientes.get(decod.getCliente());
                            if (clienteActual.getSaldo() >= decod.getPrecioAcciones() * decod.getNumAcciones()) {
                                clienteActual.addStockPackage(clientes.get(decod.getCliente()).tieneAcciones(decod.getEmpresa()) + decod.getNumAcciones(), decod.getEmpresa(), decod.getPrecioAcciones());
                                clienteActual.actualizarSaldo(decod.getDineroRestante() + clienteActual.getSaldo());
                                agente.actualizarBolsa(decod.getNumAcciones(), decod.getEmpresa());
                            } else {
                                throw new NotEnoughMoneyException("El cliente no tiene dinero para hacer la compra de inversiones de la empresa" + decod.getEmpresa());
                            }
                        } else {
                            throw new InexistentClientException("El cliente con DNI: " + decod.getCliente() + "ya no existe");
                        }
                    } else if (decod.getCodigoId() % MODULO == 1) {

                        Cliente clienteActual = this.clientes.get(decod.getCliente());
                        if (clienteActual.tieneAcciones(decod.getEmpresa()) >= decod.getNumAcciones()) {
                            clienteActual.addStockPackage(clienteActual.tieneAcciones(decod.getEmpresa()) - decod.getNumAcciones(), decod.getEmpresa(), decod.getPrecioAcciones());
                            clienteActual.actualizarSaldo(decod.getDineroRestante() + clienteActual.getSaldo());
                            agente.actualizarBolsa(-decod.getNumAcciones(), decod.getEmpresa());
                        } else {
                            throw new NotEnoughActionsException("El cliente no tiene acciones suficientes de la empresa: " + decod.getEmpresa() + ", tiene: " + clienteActual.tieneAcciones(decod.getEmpresa()) + " acciones");
                        }
                    } else if (decod.getCodigoId() % MODULO == 2) {

                        for (Cliente c : clientes.values()) {
                            if (c.estaEmpresa(decod.getEmpresa())) {
                                c.updateStockPackage(decod.getEmpresa(), decod.getPrecioAcciones());
                            }
                        }
                    }
                }else{
                    System.out.println("No se ha podido realizar la operación por saldo o numero de acciones insuficientes");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void setGest(Gestor gest) {
        this.gest = gest;
    }

    public AgenteBolsa getAgente() {
        return this.agente;
    }
}