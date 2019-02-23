package business;
public class AlunoExisteException extends Exception {
    public AlunoExisteException () {
        super();
    }
    public AlunoExisteException (String message) {
        super(message);
    }
}
