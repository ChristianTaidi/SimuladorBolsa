package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeRespuestaCompra extends RespuestaCompraVenta {

    public MensajeRespuestaCompra(int codigoId, String cliente,String empresa, boolean acceso, int numAcciones, float precioAcciones, float dRestante){
        super(codigoId, cliente,empresa, acceso, numAcciones, precioAcciones, dRestante);
    }

    public String codificar (){
        return  super.codificar();
    }

}
