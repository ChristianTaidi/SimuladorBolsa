package simulador.bolsa.poo.excepciones;

public class InexistentClientException extends Exception {

    public InexistentClientException(){
        super();
    }

    public InexistentClientException(String msg){
        super(msg);
    }
}
