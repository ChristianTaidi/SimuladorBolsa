package simulador.bolsa.poo.modelo.solicitudes;

import java.io.Serializable;

public abstract class RespuestaCompraVenta extends CompraVenta implements Serializable{
    private boolean acceso;
    private int numAcciones;
    private float precioAcciones;
    private float dRestante;

    public RespuestaCompraVenta(int codigoId, String cliente,String empresa, boolean acceso, int numAcciones, float precioAcciones, float dRestante){
        super(codigoId, cliente,empresa);
        this.setAcceso(acceso);
        this.setNumAcciones(numAcciones);
        this.setPrecioAcciones(precioAcciones);
        this.setdRestante(dRestante);
    }

    public String codificar (){
        return this.getCodigoId() + "|" + this.getCliente() + "|"+this.getAcceso()+ "|" +  this.getnomEmpresa() + "|" + getPrecioAcciones() + "|" +this.getNumAcciones()+ "|" + getdRestante();
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public boolean getAcceso(){
        return acceso;
    }

    public void setNumAcciones(int numAcciones) {
        this.numAcciones = numAcciones;
    }

    public int getNumAcciones(){
        return numAcciones;
    }

    public void setPrecioAcciones(float precioAcciones) {
        this.precioAcciones = precioAcciones;
    }

    public float getPrecioAcciones(){
        return precioAcciones;
    }

    public void setdRestante(float dRestante) {
        this.dRestante = dRestante;
    }

    public float getdRestante(){
        return dRestante;
    }
}
