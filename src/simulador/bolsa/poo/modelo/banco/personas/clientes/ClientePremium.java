package simulador.bolsa.poo.modelo.banco.personas.clientes;

import simulador.bolsa.poo.modelo.banco.personas.Persona;

public class ClientePremium extends Cliente {

    private Persona gestor;

    public ClientePremium(Cliente c, Persona gestor){
        super(c);
        this.setGestor(gestor);
    }

    public void setGestor(Persona g){
        this.gestor=g;
    }
}
