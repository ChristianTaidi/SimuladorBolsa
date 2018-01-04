package simulador.bolsa.poo.modelo.banco.personas.clientes;

import simulador.bolsa.poo.interfaces.Imprimible;
import simulador.bolsa.poo.modelo.banco.personas.Persona;

import java.io.Serializable;

public class ClientePremium extends Cliente implements Imprimible,Serializable{

    private Persona gestor;

    public ClientePremium(Cliente c, Persona gestor){
        super(c);
        this.setGestor(gestor);
    }

    public void imprimir(){
        super.imprimir();
        System.out.print("Es cliente premium con gestor: ");
        this.gestor.imprimir();
    }

    public void setGestor(Persona g){
        this.gestor=g;
    }
}
