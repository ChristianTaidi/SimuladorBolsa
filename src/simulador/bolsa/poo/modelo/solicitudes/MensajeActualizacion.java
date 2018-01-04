package simulador.bolsa.poo.modelo.solicitudes;

import java.io.Serializable;

public class MensajeActualizacion extends Mensaje implements Serializable {

    public MensajeActualizacion (int codigoId){
        this.setCodigo(codigoId);
    }

    public String codificar(){
        return String.valueOf(this.getCodigoId());
    }
}
