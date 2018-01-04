package simulador.bolsa.poo.modelo.banco;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.interfaces.Entidad;
import simulador.bolsa.poo.modelo.banco.personas.*;
import simulador.bolsa.poo.modelo.banco.personas.clientes.*;
import simulador.bolsa.poo.modelo.solicitudes.MensajeActualizacion;
import simulador.bolsa.poo.modelo.solicitudes.MensajeCompra;
import simulador.bolsa.poo.modelo.solicitudes.MensajeVenta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Bank implements Entidad,Serializable {

    private String nombre;
    private TreeMap<String,Cliente>clientes;
    private AgenteBolsa agente;
    private Gestor gest = new Gestor("Antonio","01234567A");
    private int contadorSolicitudes;

    public Bank(String name, AgenteBolsa agente){
        this.setAgenteBolsa(agente);
        this.setNombre(name);
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

    public void imprimirEstado(){
        for (Cliente cliente: clientes.values()){
            cliente.imprimir();
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

    public void solicitarRecomendacion(String dni) throws NotPremiumClientException,InexistentClientException,IllegalArgumentException{
        if (dni != null) {
            if (clientes.containsKey(dni)) {
                if (clientes.get(dni) instanceof  ClientePremium) {
                   gest.recomendar();
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
                                agente.addSolicitud(new MensajeCompra((this.getContadorSolicitudes() * 3) + cod, clientes.get(dni).getDni(), dinero, empresa));
                                this.incrementarContadorSolicitudes();
                        } else {
                            throw new InexistentClientException("El Cliente no existe");
                        }
                        break;
                    case 1:
                        if (clientes.containsKey(dni)) {

                                agente.addSolicitud(new MensajeVenta((this.getContadorSolicitudes() * 3) + cod, clientes.get(dni).getNombre(), nAcc, empresa));
                                this.incrementarContadorSolicitudes();
                        }else{
                            throw new InexistentClientException("El Cliente no existe");
                         }
                        break;
                    case 2:
                            agente.addSolicitud(new MensajeActualizacion((this.getContadorSolicitudes() * 3) + cod));
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

    public void ejecutarSolicitudes () throws InvalidCodeException, NoSuchEnterpriseException, NotEnoughMoneyException, NotEnoughActionsException {
        this.resetContadorSolicitudes();
        ArrayList<String> mensajes = agente.ejecutarSolicitudes();
        for (String mensaje : mensajes) {
            try {
                String[] vector = mensaje.split(Pattern.quote("|"));
                int codigo = Integer.parseInt(vector[0]);
                if (codigo % 3 == 0) {
                    String cliente = vector[1];
                    String empresa = vector[2];
                    boolean acceso = Boolean.parseBoolean(vector[3]);
                    int numAcciones = Integer.parseInt(vector[4]);
                    float precioAcciones= Float.parseFloat(vector[5]);
                    float saldoRestante = Float.parseFloat(vector[6]);
                    Cliente clienteActual = this.clientes.get(cliente);
                    if (clienteActual.getSaldo()>precioAcciones*numAcciones) {
                        clienteActual.addStockPackage(clientes.get(cliente).tieneAcciones(empresa) + numAcciones, empresa, precioAcciones);
                        clienteActual.actualizarSaldo(saldoRestante);
                        agente.actualizarBolsa(numAcciones,empresa);
                    }else{
                        throw new NotEnoughMoneyException("El cliente no tiene dinero para hacer la compra de inversiones de la empresa"+empresa);
                    }
                } else if (codigo % 3 == 1) {
                    String cliente = vector[1];
                    String empresa = vector[2];
                    boolean acceso = Boolean.parseBoolean(vector[3]);
                    int numAcciones = Integer.parseInt(vector[4]);
                    float precioAcciones = Float.parseFloat(vector[5]);
                    float dineroRestante = Float.parseFloat(vector[6]);
                    Cliente clienteActual = this.clientes.get(cliente);
                    if(clienteActual.tieneAcciones(empresa)>= numAcciones) {
                        clienteActual.addStockPackage(clienteActual.tieneAcciones(empresa) - numAcciones, empresa, precioAcciones);
                        clienteActual.actualizarSaldo(dineroRestante + clienteActual.getSaldo());
                        agente.actualizarBolsa(-numAcciones,empresa);
                    }else{
                        throw new NotEnoughActionsException("El cliente no tiene acciones suficientes de la empresa: "+empresa+", tiene: "+clienteActual.tieneAcciones(empresa)+" acciones");
                    }
                } else if (codigo % 3 == 2) {
                    String nombEmpresa = vector[1];
                    float precioAcciones = Float.parseFloat(vector[2]);//preguntar como recorrer esto//

                    for (Cliente c : clientes.values()) {
                        if (c.estaEmpresa(nombEmpresa)) {
                            c.updateStockPackage(nombEmpresa, precioAcciones);
                        }
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}