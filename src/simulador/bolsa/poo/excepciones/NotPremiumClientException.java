package simulador.bolsa.poo.excepciones;

public class NotPremiumClientException extends Exception{
    public NotPremiumClientException() {
        super();
    }

    public NotPremiumClientException(String message) {
        super(message);
    }
}
