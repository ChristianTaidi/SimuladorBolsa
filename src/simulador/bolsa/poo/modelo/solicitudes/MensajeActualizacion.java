package simulador.bolsa.poo.modelo.solicitudes;

public class MensajeActualizacion extends Mensaje  {

    public MensajeActualizacion (int codigoId){
        this.setCodigo(codigoId);
    }

    public String codificar(){
        return String.valueOf(this.getCodigoId());
    }
}
