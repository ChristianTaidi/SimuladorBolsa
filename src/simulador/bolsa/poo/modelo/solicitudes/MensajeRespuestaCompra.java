package simulador.bolsa.poo.modelo.solicitudes;

import java.io.Serializable;

public class MensajeRespuestaCompra extends RespuestaCompraVenta implements Serializable{

    public MensajeRespuestaCompra(int codigoId, String cliente,String empresa, boolean acceso, int numAcciones, float precioAcciones, float dRestante){
        super(codigoId, cliente,empresa, acceso, numAcciones, precioAcciones, dRestante);
    }

    public String codificar (){
        return  super.codificar();
    }

}
