package business;

public class QuotaExisteException extends Exception{
    /**
     * Construtor da exception vazio.
     */
    public QuotaExisteException(){
        super();
    }

    /**
     * Construtor da exception parametrizado
     * @param msg mensagem da exception
     */
    public QuotaExisteException(String msg){
        super(msg);
    }
}
