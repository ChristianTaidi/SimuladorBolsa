package simulador.bolsa.poo.modelo.banco.personas;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.bolsa.BolsaValores;
import simulador.bolsa.poo.modelo.solicitudes.*;

import java.io.Serializable;
import java.util.ArrayList;

public class AgenteBolsa extends Persona implements Serializable,Imprimible {

    private ArrayList<Mensaje> solicitudes;

    private BolsaValores bolsa;

    public BolsaValores getBolsa() {
        return bolsa;
    }

    public void setBolsa(BolsaValores bolsa) {
        this.bolsa = bolsa;
    }

    public AgenteBolsa(String nombre, String dni, BolsaValores bols) {
        this.setDni(dni);
        this.setNombre(nombre);
        this.solicitudes=new ArrayList();
        this.setBolsa(bols);

    }

    public void addSolicitud(Mensaje msg) throws InvalidCodeException{

        this.solicitudes.add(msg);
    }

    public void imprimir(){
        for(Mensaje msg: solicitudes){
            System.out.println(msg.codificar());
        }
    }

    public ArrayList<String> ejecutarSolicitudes() throws InvalidCodeException, NoSuchEnterpriseException {
        ArrayList<String> respuestas = new ArrayList<>();
        for (Mensaje msg:solicitudes){
            for(Mensaje mensaje:this.getBolsa().recibirSolicitud(msg.codificar()))
            respuestas.add(mensaje.codificar());
        }
        return respuestas;
    }

    public void actualizarBolsa(int nAcc, String empresa) throws NoSuchEnterpriseException {
        bolsa.actualizarValores(nAcc,empresa);
    }
}
