package simulador.bolsa.poo.modelo.solicitudes;

import simulador.bolsa.poo.interfaces.Imprimible;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MensajeActualizacion extends Mensaje implements Serializable,Imprimible {

    private Date fecha;
    
    public MensajeActualizacion (int codigoId,Date fecha){
        this.setCodigo(codigoId);
        this.setFecha(fecha);
    }
    public MensajeActualizacion (int codigoId){
        this.setCodigo(codigoId);
    }

    public String codificar(){
        return String.valueOf(this.getCodigoId());
    }

    @Override
    public void imprimir() {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Mensaje de actualización con código: "+this.getCodigoId());
        System.out.println("Fecha de la solicitud: "+formatoFecha.format(this.getFecha()));
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Date getFecha() {
        return fecha;
    }

}
