package simulador.bolsa.poo.modelo.solicitudes;

import simulador.bolsa.poo.interfaces.Imprimible;

import java.io.Serializable;

public class MensajeVenta extends CompraVenta implements Serializable,Imprimible {

    private int numAcciones;

    public void setnumAcciones(int nAcciones) {
        this.numAcciones = nAcciones;
    }

    public int getnumAcciones() {
        return numAcciones;
    }

    public MensajeVenta (int codigoId, String cliente, int numAcciones, String nomEmpresa){
        super(codigoId, cliente, nomEmpresa);
        this.setnumAcciones(numAcciones);
    }

    public String codificar (){
        return this.getCodigoId() + "|" + this.getCliente() + "|" + this.getnumAcciones() + "|" + this.getnomEmpresa();
    }

    @Override
    public void imprimir() {
        System.out.println("Mensaje de Venta con código: " + this.getCodigoId());
        System.out.println("Solicitado por el cliente: " + this.getCliente()+" , que vende "+this.getnumAcciones()+" acciones de la empresa"+this.getnomEmpresa());
    }

}
