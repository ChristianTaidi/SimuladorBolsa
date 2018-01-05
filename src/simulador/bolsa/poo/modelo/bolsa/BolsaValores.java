package simulador.bolsa.poo.modelo.bolsa;

import simulador.bolsa.poo.excepciones.InvalidCodeException;
import simulador.bolsa.poo.excepciones.NoSuchEnterpriseException;
import simulador.bolsa.poo.funcionalidades.comparadores.ComparadorEmpresas;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.solicitudes.Mensaje;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaActualizacion;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaCompra;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaVenta;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class BolsaValores implements Imprimible,Serializable {
    private TreeMap <String, Empresa> empresas;

    public BolsaValores(){
        this.empresas= new TreeMap();
    }

    public ArrayList<Mensaje> recibirSolicitud(String mensaje)throws InvalidCodeException,NoSuchEnterpriseException {


            ArrayList<Mensaje> resultado = new ArrayList();
            String[] partes = mensaje.split(Pattern.quote("|"));
            int codigoId = Integer.parseInt(partes[0]);

            if ((codigoId % 3) == 0) {
                String cliente = (partes[1]);
                float dInversion = Float.parseFloat(partes[2]);
                String nomEmpresa = (partes[3]);



                if (empresas.containsKey(nomEmpresa)) {

                    float precioAcciones = (empresas.get(nomEmpresa).getPrecioAcciones());
                    boolean acceso = (dInversion > precioAcciones);
                    int numAcciones = (int) (precioAcciones / dInversion);
                    float dRestante = dInversion - (numAcciones * precioAcciones);
                    resultado.add(new MensajeRespuestaCompra(codigoId, cliente,nomEmpresa, acceso, numAcciones, precioAcciones, dRestante));
                    empresas.get(nomEmpresa).setNumAcciones(empresas.get(nomEmpresa).getNumAcciones() + numAcciones);
                    return resultado;

                } else

                    throw new NoSuchEnterpriseException("La empresa no existe");

            } else if ((codigoId % 3) == 1) {

                String cliente = (partes[1]);
                int numAcciones = Integer.parseInt(partes[2]);
                String nomEmpresa = (partes[3]);
                float precioAcciones = (empresas.get(nomEmpresa).getPrecioAcciones());
                if (empresas.containsKey(nomEmpresa)) {

                    float dineroVenta = (precioAcciones * numAcciones);

                    resultado.add(new MensajeRespuestaVenta(codigoId, cliente, nomEmpresa,true, numAcciones, precioAcciones, dineroVenta));
                    empresas.get(nomEmpresa).setNumAcciones(empresas.get(nomEmpresa).getNumAcciones() - numAcciones);

                    return resultado;

                } else
                    throw new NoSuchEnterpriseException("La empresa no existe");
            } else if ((codigoId % 3) == 2) {
                int code = 0;
                for (Empresa emp : empresas.values()) {

                    resultado.add(new MensajeRespuestaActualizacion((code*3)+2, emp.getNomEmpresa(), emp.getPrecioAcciones()));
                    code+=1;
                }
                return resultado;
            } else {
                throw new InvalidCodeException("Error interno, intentalo de nuevo");
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
        TreeSet<Empresa> empresasRentabilidad = new TreeSet<>(new ComparadorEmpresas());
        Iterator iter = empresasRentabilidad.iterator();
        if (numEmpresas < this.empresas.size()){
            for(int i=0;i<numEmpresas;i++){
                Empresa emp = (Empresa)iter.next();
                emp.imprimir();
            }

        }else{
            while (iter.hasNext()){
                Empresa emp=(Empresa) iter.next();
                emp.imprimir();
            }
            
        }

    }
}
