package simulador.bolsa.poo.modelo.bolsa;

import simulador.bolsa.poo.excepciones.InvalidCodeException;
import simulador.bolsa.poo.excepciones.NoSuchEnterpriseException;
import simulador.bolsa.poo.interfaces.Entidad;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaActualizacion;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaCompra;
import simulador.bolsa.poo.modelo.solicitudes.MensajeRespuestaVenta;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.TreeMap;

public class BolsaValores implements Entidad,Serializable {
    private TreeMap <String, Empresa> empresas;

    public BolsaValores(){
        this.empresas= new TreeMap();
    }

    public ArrayList<String> recibirSolicitud(String mensaje)throws InvalidCodeException,NoSuchEnterpriseException {


            ArrayList<String> resultado = new ArrayList();
            String[] partes = mensaje.split("|");
            int codigoId = Integer.parseInt(partes[0]);

            if ((codigoId % 3) == 0) {
                String cliente = (partes[1]);
                float dInversion = Float.parseFloat(partes[2]);
                String nomEmpresa = (partes[3]);

                float precioAcciones = (empresas.get(nomEmpresa).getPrecioAcciones());

                if (empresas.containsKey(nomEmpresa)) {

                    boolean acceso = (dInversion > precioAcciones);
                    int numAcciones = (int) (precioAcciones / dInversion);
                    float dRestante = dInversion - (numAcciones * precioAcciones);
                    resultado.add(new MensajeRespuestaCompra(codigoId, cliente,nomEmpresa, acceso, numAcciones, precioAcciones, dRestante).codificar());
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

                    resultado.add(new MensajeRespuestaVenta(codigoId, cliente, nomEmpresa,true, numAcciones, precioAcciones, dineroVenta).codificar());
                    empresas.get(nomEmpresa).setNumAcciones(empresas.get(nomEmpresa).getNumAcciones() - numAcciones);
                    return resultado;
                } else
                    throw new NoSuchEnterpriseException("La empresa no existe");
            } else if ((codigoId % 3) == 2) {

                for (Empresa emp : empresas.values()) {

                    resultado.add(new MensajeRespuestaActualizacion(codigoId, emp.getNomEmpresa(), emp.getPrecioAcciones()).codificar());
                }
                return resultado;
            } else {
                throw new InvalidCodeException("Error interno, intentalo de nuevo");
            }




    }


    public void imprimirEstado() {
        for (Empresa emp: empresas.values()){
            emp.imprimir();
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

    public void actualizarValores (){
        for (Empresa emp: empresas.values()){
            emp.actualizarValor();
        }
    }


}
