package simulador.bolsa.poo.modelo.banco.personas;

import java.io.Serializable;

public class Gestor extends Persona implements Serializable {

    public Gestor(String nombre, String dni){
        this.setDni(dni);
        this.setNombre(nombre);
    }

    public void recomendar(){

    }
}
