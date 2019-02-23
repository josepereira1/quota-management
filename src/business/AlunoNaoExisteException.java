package business;
public class AlunoNaoExisteException extends Exception {
    public AlunoNaoExisteException () {
        super();
    }
    public AlunoNaoExisteException (String message) {
        super(message);
    }
}
