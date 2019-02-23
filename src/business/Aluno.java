package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import java.util.Observable;


public class Aluno extends Observable implements Serializable {

    private String numero;
    private String nome;
    private String sexo;
    private int idade;
    private String morada;
    private Map<String, Quota> quotas;

    /**
     * Construtor vazio.
     */
    public Aluno(){
        this.numero = "";
        this.nome = "";
        this.sexo = "";
        this.idade = 0;
        this.morada = "";
        this.quotas = new HashMap<>();
        
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Construtor parametrizado.
     */
    public Aluno(String numero, String nome, String sexo, int idade, String morada, Map<String, Quota> quotas){
        this.numero = numero;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.morada = morada;
        this.setQuotas(quotas);
        
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Construtor cópia.
     * @param a aluno a ser copiado
     */
    public Aluno(Aluno a){
        this.numero = a.getNumero();
        this.nome = a.getNome();
        this.sexo = a.getSexo();
        this.idade = a.getIdade();
        this.morada = a.getMorada();
        this.quotas = a.getQuotas();
        
        this.setChanged();
        this.notifyObservers();
    }
    
    /** métodos de instancia */
    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
        
        this.setChanged();
        this.notifyObservers();
    }

    public String getSexo(){
        return this.sexo;
    }

    public void setSexo(String sexo){
        this.sexo = sexo;
        
        this.setChanged();
        this.notifyObservers();
    }

    public int getIdade(){
        return this.idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
        
        this.setChanged();
        this.notifyObservers();
    } 

    public String getMorada(){
        return this.morada;
    }

    public void setMorada(String morada){
        this.morada = morada;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void setQuotas(Map<String, Quota> quotas) {
        this.quotas = new HashMap<>(quotas.size());
        for(Map.Entry<String, Quota> entry: quotas.entrySet())
            this.quotas.put(entry.getKey(), entry.getValue().clone());
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public Map<String, Quota> getQuotas() {
        Map<String, Quota> res = new HashMap<>(this.quotas.size());
        for(Map.Entry<String, Quota> entry: this.quotas.entrySet())
            res.put(entry.getKey(), entry.getValue().clone());
        return res;
    }

    @Override
    public String toString() {
        return "Aluno{" + "numero=" + numero + ", nome=" + nome + ", sexo=" + sexo + ", idade=" + idade + ", morada=" + morada + ", quotas=" + quotas + '}';
    }
    
    @Override
    public Aluno clone(){
        return new Aluno(this);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) 
            return true;
    
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
    
        Aluno umInd = (Aluno) o;
            return this.numero.equals(umInd.getNumero()) && 
                    this.nome.equals(umInd.getNome()) && 
                    this.sexo.equals(umInd.getSexo()) && 
                    this.idade == umInd.getIdade() && 
                    this.morada.equals(umInd.getMorada());
    }
    
    public void addQuota(String id, LocalDate dataInicio, LocalDate dataFim, float valor, boolean paga) throws QuotaExisteException {
        if (this.quotas.containsKey(id)) throw new QuotaExisteException(id);
        this.quotas.put(id, new Quota(id, dataInicio, dataFim, valor, paga));
        
        this.setChanged();
        this.notifyObservers();
    }

    public void setQuotaPaga(String id, boolean paga) throws QuotaNaoExisteException {
        if (!this.quotas.containsKey(id)) throw new QuotaNaoExisteException(id);
        this.quotas.get(id).setPaga(paga);
        
        this.setChanged();
        this.notifyObservers();
    }
}
 