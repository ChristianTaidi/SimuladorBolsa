package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeActualizacion extends Mensaje  {

    public MensajeActualizacion (int codigoId){
        this.setCodigo(codigoId);
    }

    public String codificar(){
        int codigoMensaje = 3;

        return String.valueOf(codigoMensaje);
    }
}
