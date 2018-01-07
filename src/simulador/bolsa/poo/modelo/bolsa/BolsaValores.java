package simulador.bolsa.poo.modelo.bolsa;

import simulador.bolsa.poo.excepciones.InvalidCodeException;
import simulador.bolsa.poo.excepciones.NoSuchEnterpriseException;
import simulador.bolsa.poo.funcionalidades.Decodificador;
import simulador.bolsa.poo.funcionalidades.comparadores.ComparadorEmpresas;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.solicitudes.Mensaje;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaActualizacion;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaCompra;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaVenta;

import java.io.Serializable;
import java.util.*;

import java.util.regex.Pattern;

public class BolsaValores implements Imprimible,Serializable {

    private TreeMap <String, Empresa> empresas;
    private static final int MODULO=3;

    public BolsaValores(){
        this.empresas= new TreeMap();
    }

    public ArrayList<Mensaje> recibirSolicitud(String mensaje)throws InvalidCodeException,NoSuchEnterpriseException {


        ArrayList<Mensaje> resultado = new ArrayList();
        Decodificador decod = new Decodificador("|");
        decod.setCadena(mensaje);
        if (empresas.containsKey(decod.getEmpresa())) {
            if ((decod.getCodigoId() % MODULO) == 0) {
                float precioAcciones = (empresas.get(decod.getEmpresa()).getPrecioAcciones());
                boolean acceso = (decod.getDineroInversion() >= precioAcciones);
                int numAcciones = (int) (decod.getDineroInversion() / precioAcciones);
                float dRestante = -(numAcciones * precioAcciones);
                resultado.add(new MensajeRespuestaCompra(decod.getCodigoId(), decod.getCliente(), decod.getEmpresa(), acceso, numAcciones, precioAcciones, dRestante));

                return resultado;

        } else if ((decod.getCodigoId() % MODULO) == 1) {


                float precioAcciones = (empresas.get(decod.getEmpresa()).getPrecioAcciones());
                float dineroVenta = (precioAcciones * decod.getNumAcciones());
                resultado.add(new MensajeRespuestaVenta(decod.getCodigoId(), decod.getCliente(), decod.getEmpresa(), true, decod.getNumAcciones(), precioAcciones, dineroVenta));

                return resultado;

        } else if ((decod.getCodigoId() % MODULO) == 2) {
            int code = 0;

            for (Empresa emp : empresas.values()) {
                resultado.add(new MensajeRespuestaActualizacion((code * MODULO) + 2, emp.getNomEmpresa(), emp.getPrecioAcciones()));
                code ++;
            }

            return resultado;
        } else {
            throw new InvalidCodeException("Error interno, intentalo de nuevo");
        }
    } else {
            throw new NoSuchEnterpriseException("La empresa no existe");

        }


    }


    public void imprimir() {
        if (this.empresas.size() >0) {
            for (Empresa emp : empresas.values()) {
                emp.imprimir();
            }
        }else{
            System.out.println("La Bolsa no tiene empresas");
        }
    }

    public void  añadirEmpresa (String nomEmpresa, float precioAcciones)throws IllegalArgumentException{

            Empresa emp = new Empresa(nomEmpresa, precioAcciones);
            empresas.put(nomEmpresa, emp);
            System.out.println();
            emp.imprimir();

    }

    public void  añadirEmpresa (String nomEmpresa,int numAcciones ,float precioAcciones)throws IllegalArgumentException{

        Empresa emp = new Empresa(nomEmpresa,numAcciones, precioAcciones);
        empresas.put(nomEmpresa, emp);
        System.out.println();
        emp.imprimir();

    }

    public void borrarEmpresa (String nomEmpresa) throws NoSuchEnterpriseException,IllegalArgumentException{

            if (empresas.containsKey(nomEmpresa)) {
                empresas.remove(nomEmpresa);
            } else {
                throw new NoSuchEnterpriseException("La empresa no existe");
            }

    }

    public void actualizarValores (int numAcc,String nomEmpresa) throws NoSuchEnterpriseException {
            if (empresas.containsKey(nomEmpresa)) {
                Empresa emp = this.empresas.get(nomEmpresa);
                emp.actualizarValor(numAcc);
            }else{
                throw new NoSuchEnterpriseException("Fallo a la hora de actualizar el valor de la empresa,"+nomEmpresa +"no existe");
            }

    }


    public void mejoresEmpresas(int numEmpresas) {
        ArrayList<Empresa> empresasRentabilidad = new ArrayList<>(this.empresas.values());
        Collections.sort(empresasRentabilidad,new ComparadorEmpresas());
        if (numEmpresas < this.empresas.size()){
            System.out.println("Imprimiendo las "+ numEmpresas+" empresas más rentables");
            for(int i=0;i<numEmpresas;i++){
               empresasRentabilidad.get(i).imprimir();
            }

        }else{
            System.out.println("Solo hay "+ empresasRentabilidad.size()+" empresas en la bolsa");
            for(int i=0;i<empresasRentabilidad.size();i++){
                empresasRentabilidad.get(i).imprimir();
            }
            
        }

    }
}
