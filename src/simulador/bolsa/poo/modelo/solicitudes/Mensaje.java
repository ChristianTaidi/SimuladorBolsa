package simulador.bolsa.poo.modelo.solicitudes;




import java.io.Serializable;

public abstract class  Mensaje implements Serializable {
    private int codigoId;



    public void setCodigo (int codigoId){
        this.codigoId = codigoId;
    }

    public int getCodigoId() {
        return codigoId;
    }

    public abstract String codificar ();


    public void imprimir() {
        System.out.println("Codigo del mensaje: "+this.getCodigoId());
    }
}
