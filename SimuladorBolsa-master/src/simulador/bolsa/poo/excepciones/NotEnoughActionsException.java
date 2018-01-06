package simulador.bolsa.poo.excepciones;

public class NotEnoughActionsException extends Exception {

    public NotEnoughActionsException() {
        super();
    }

    public NotEnoughActionsException(String message) {
        super(message);
    }
}
