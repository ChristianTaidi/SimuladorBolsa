package simulador.bolsa.poo.modelo.banco.personas;

import simulador.bolsa.poo.excepciones.*;
import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.bolsa.BolsaValores;
import simulador.bolsa.poo.modelo.solicitudes.*;

import java.io.Serializable;
import java.util.ArrayList;

public class AgenteBolsa extends Gestor implements Serializable,Imprimible {

    private ArrayList<Mensaje> solicitudes;

    public AgenteBolsa(String nombre, String dni, BolsaValores bols) {
        super(nombre,dni,bols);
        this.solicitudes=new ArrayList();

    }

    public void addSolicitud(Mensaje msg) throws InvalidCodeException{

        this.solicitudes.add(msg);
    }

    public void imprimir(){
        for(Mensaje msg: solicitudes){
            msg.imprimir();
        }
    }

    public ArrayList<String> ejecutarSolicitudes() throws InvalidCodeException, NoSuchEnterpriseException {
        ArrayList<String> respuestas = new ArrayList<>();
        for (Mensaje msg:solicitudes){
            for(Mensaje mensaje:this.getBolsa().recibirSolicitud(msg.codificar()))
            respuestas.add(mensaje.codificar());
        }
        this.solicitudes.clear();
        return respuestas;
    }

    public void actualizarBolsa(int nAcc, String empresa) throws NoSuchEnterpriseException {
        this.getBolsa().actualizarValores(nAcc,empresa);
    }
}
