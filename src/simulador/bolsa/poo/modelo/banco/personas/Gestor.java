package simulador.bolsa.poo.modelo.banco.personas;

import simulador.bolsa.poo.modelo.bolsa.BolsaValores;

import java.io.Serializable;

public class Gestor extends Persona implements Serializable {

    private BolsaValores bolsa;

    public Gestor(String nombre, String dni, BolsaValores bolsa){
        this.setDni(dni);
        this.setNombre(nombre);
        this.setBolsa(bolsa);
    }

    public void recomendar(int numEmpresas){
        this.getBolsa().mejoresEmpresas(numEmpresas);
    }

    public void setBolsa(BolsaValores bolsa) {
        this.bolsa = bolsa;
    }

    public BolsaValores getBolsa() {
        return bolsa;
    }
}
