package simulador.bolsa.poo.modelo.banco.personas;

import simulador.bolsa.poo.interfaces.Imprimible;

import java.io.Serializable;

public abstract class Persona implements Imprimible,Serializable {
    private String nombre;
    private String dni;


    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void imprimir() {
        System.out.println("DNI: " + this.getDni());
        System.out.println("Nombre: " + this.getNombre());
    }
}
