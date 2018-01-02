package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeRespuestaCompra extends RespuestaCompraVenta {

    public MensajeRespuestaCompra(int codigoId, String cliente,String empresa, boolean acceso, int numAcciones, float precioAcciones, float dRestante){
        super(codigoId, cliente,empresa, acceso, numAcciones, precioAcciones, dRestante);
    }

    public String codificar (){
        return "Resultado de Compra: " + this.getCodigoId() + "|" + this.getCliente() + "|" +this.getnomEmpresa()+ "|" + this.getAcceso() + "|" + getNumAcciones() + "|" + getPrecioAcciones() + "|" + getdRestante();
    }
    public int ejecutar(){
        int codigoMensaje = 1;

        return codigoMensaje;
    }
}
