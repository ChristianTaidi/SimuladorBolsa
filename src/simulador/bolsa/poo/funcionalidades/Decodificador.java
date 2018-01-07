package simulador.bolsa.poo.funcionalidades;

import java.util.regex.Pattern;

/**
 * Esta clase decodifica los mensajes y es usada por el bank y la bolsa,
 * su constructor recibe un String que será el delimitador para hacer split del mensaje codificado
 */
public class Decodificador {

    private static final int POSICION_ID=0;
    private static final int POSICION_CLIENTE=1;
    private static final int POSICION_ACCESO=2;
    private static final int POSICION_EMPRESA=3;
    private static final int POSICION_PRECIO_ACCIONES=4;
    private static final int POSICION_NUM_ACCIONES=5;
    private static final int POSICION_DINERO_RESTANTE=6;

    private String separador;
    private String cadena;

    public Decodificador(String separador){
        this.setSeparador(separador);
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public void asignarCadena(String cad) {
        this.setCadena(cad);
    }

    private String[] decodificar(String mensaje){
        return mensaje.split(Pattern.quote(this.getSeparador()));
    }

    public String getCliente(){
        return this.decodificar(this.getCadena())[POSICION_CLIENTE];
    }

    public int getCodigoId(){
        return Integer.parseInt(this.decodificar(this.getCadena())[POSICION_ID]);
    }

    public String getEmpresa(){
        return this.decodificar(this.getCadena())[POSICION_EMPRESA];
    }

    public float getPrecioAcciones(){
        return Float.parseFloat(this.decodificar(this.getCadena())[POSICION_PRECIO_ACCIONES]);
    }

    public int getNumAcciones(){
        return Integer.parseInt(this.decodificar(this.getCadena())[POSICION_NUM_ACCIONES]);
    }

    public float getDineroRestante(){
        return Float.parseFloat(this.decodificar(this.getCadena())[POSICION_DINERO_RESTANTE]);
    }

    public boolean getAcceso(){
        return Boolean.parseBoolean(this.decodificar(this.getCadena())[POSICION_ACCESO]);
    }

    public String getSeparador() {
        return separador;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public float getDineroInversion() {
        return Float.parseFloat(this.decodificar(this.getCadena())[2]);
    }
}
