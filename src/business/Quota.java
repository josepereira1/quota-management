package business;
import java.io.Serializable;
import java.time.LocalDate;

public class Quota implements Serializable {

    private String id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private float valor;
    private boolean paga;

    /**
     * Construtor vazio.
     */
    public Quota(){
        this.id = "0";
        this.dataInicio = LocalDate.now();
        this.dataFim = LocalDate.now();
        this.valor = 0;
        this.paga = false;
    }

    /**
     * Construtor parametrizado.
     * @param id id/código da quota
     * @param dataInicio data do lançamento da quota
     * @param dataFim data do pagamento da quota
     * @param valor valor da quota
     */
    public Quota(String id, LocalDate dataInicio, LocalDate dataFim, float valor, boolean paga){
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.paga = paga;
    }

    public Quota(Quota quota){
        this.id = quota.id;
        this.dataInicio = quota.dataInicio;
        this.dataFim = quota.dataFim;
        this.valor = quota.valor;
        this.paga = quota.isPaga();
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    /**
     * Devolve o Id.
     * @return retorna o Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Coloca o id.
     * @param id id a colocar.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devolve a data do lançamento da quota.
     * @return retorna a data de lançamento da quota.
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * Coloca a data do lançamento da quota.
     * @param dataInicio data do lançamento da quota.
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Devolve a data do pagamento da quota.
     * @return retorna a data do pagamento da quota.
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * Coloca a data de pagamento da quota.
     * @param dataFim data do pagamento da quota.
     */
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Devolve o valor da quota.
     * @return retorna o valor da quota.
     */
    public float getValor() {
        return valor;
    }

    /**
     * Coloca o valor da quota.
     * @param valor valor da quota.
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * Faz um cópia deste objeto.
     * @return retorna uma cópia deste objeto.
     */
    public Quota clone(){
        return new Quota(this);
    }

    /**
     * Verifica se o objeto recebido como argumento é igual ou diferente deste objeto.
     * @param o objeto a verificar.
     * @return retorna a validade da igualdade entre o objeto recebido como argumento e este objeto.
     */
    public boolean equals(Object o){
        if(this == o)return true;

        if(this == null || this.getClass() != o.getClass())return false;

        Quota quota = (Quota) o;

        return this.id == quota.id
                && this.dataInicio.equals(quota.dataInicio)
                && this.dataFim.equals(quota.dataFim)
                && this.valor == quota.getValor()
                && this.paga == quota.isPaga(); 
    }

    /**
     * Retorna toda a informação deste objeto numa String.
     * @return retorna toda a informação deste objeto numa String.
     */
    @Override
    public String toString() {
        return "Quota{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", valor=" + valor + ", paga=" + paga + '}';
    }
}
