package simulador.bolsa.poo.modelo.solicitudes;


import simulador.bolsa.poo.interfaces.Imprimible;

import java.io.Serializable;

public class MensajeCompra extends CompraVenta implements Serializable,Imprimible{

    private float dInversion;



    public void setdInversion(float dInversion) {
        this.dInversion = dInversion;
    }

    public float getdInversion() {
        return dInversion;
    }

    public MensajeCompra (int codigoId, String cliente, float dInversion, String nomEmpresa){
        super (codigoId, cliente, nomEmpresa);
        this.setdInversion(dInversion);
    }

    public String codificar(){
        return getCodigoId() + "|" + getCliente() + "|" + getdInversion() + "|" + getnomEmpresa();
    }

    @Override
    public void imprimir() {
        System.out.println("Mensaje de Compra con código: "+this.getCodigoId());
        System.out.println("Solicitado por el cliente: "+this.getCliente()+" , que invierte "+this.getdInversion()+" euros en la empresa "+this.getnomEmpresa());
    }


}
