package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeRespuestaVenta extends RespuestaCompraVenta {

    public MensajeRespuestaVenta(int codigoId, String cliente,String empresa, boolean acceso, int numAcciones, float precioAcciones, float dRestante){
        super(codigoId, cliente,empresa, acceso, numAcciones, precioAcciones, dRestante);
    }

    public String codificar (){
        return this.getCodigoId() + "|" + this.getCliente() + "|" + this.getnomEmpresa() + "|" +this.getAcceso() + "|" + getNumAcciones() + "|" + getPrecioAcciones() + "|" + getdRestante();
    }
    public int ejecutar(){
        int codigoMensaje = 2;

        return codigoMensaje;
    }
}
