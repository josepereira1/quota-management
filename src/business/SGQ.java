package business;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import business.QuotaJaEstaPagaException;

public class SGQ extends Observable implements Serializable {
    
    private Map<String,Aluno> alunos;       
    private int idAtual; // id atual, no intuito de sabermos qual id iremos atribuir a seguir.

    public SGQ() {
      this.alunos = new HashMap<>();
      this.idAtual = 0;

      this.setChanged();
      this.notifyObservers();
    }
      
    public SGQ(Map<String, Aluno> alunos, int idAtual) {
      this.setAlunos(alunos);
      this.idAtual = idAtual;

      this.setChanged();
      this.notifyObservers();
    }

    public SGQ (SGQ sistema){
        this.alunos = sistema.getAlunos();
        this.idAtual = sistema.getIdAtual();

        this.setChanged();
        this.notifyObservers();
    }

    public Map<String, Aluno> getAlunos() {
        Map <String,Aluno> res = new HashMap<>();
        for(Map.Entry<String,Aluno> entry: this.alunos.entrySet()){
            res.put(entry.getKey(), entry.getValue().clone());
        }
        return res;
    }
    
    public void setAlunos (Map<String,Aluno> m) {
        this.alunos = new HashMap<>();
        for(Map.Entry<String,Aluno> entry: m.entrySet()){
            this.alunos.put(entry.getKey(), entry.getValue().clone());
        }

        this.setChanged();
        this.notifyObservers();
    }

    public int getIdAtual() {
      return this.idAtual;
    }

    public void setIdAutal (int n) {
      this.idAtual = n;

      this.setChanged();
      this.notifyObservers();        
    }

    @Override
    public SGQ clone () {
      return new SGQ(this);
    }

    @Override
    public boolean equals (Object o) {
      if(o == this) return true;
      if(o == null || o.getClass() != this.getClass()) return false;
      SGQ outra = (SGQ) o;
      return outra.getIdAtual() == this.idAtual &&
              outra.getAlunos() == this.alunos;
    }

    @Override
    public String toString() {
        return "SGQ{" + "alunos=" + alunos + ", idAtual=" + idAtual + '}';
    }

    public void addAluno(String numero, String nome, String sexo, int idade, String morada) throws AlunoExisteException, ParametrosInvalidosException {
        
        if(alunos.containsKey(numero)) throw new AlunoExisteException(numero);
        else if(numero.equals(null) || numero.equals("") 
                || nome.equals(null) || nome.equals("")
                || morada.equals(null) || morada.equals("")
                || sexo.equals(null) || sexo.equals("")
                || idade < 0) throw new ParametrosInvalidosException("parâmetros inválidos");
        
        alunos.put(numero, new Aluno(numero, nome, sexo, idade, morada, new HashMap<>()));
        
        this.setChanged();
        this.notifyObservers();
    }

    public void addObserverToAluno(Observer o, String cod) throws AlunoNaoExisteException {
        if (!this.alunos.containsKey(cod)) throw new AlunoNaoExisteException(); 
        this.alunos.get(cod).addObserver(o);
    }

    public Map<String, Quota> getQuotas(String cod) throws AlunoNaoExisteException {
        if (!this.alunos.containsKey(cod)) throw new AlunoNaoExisteException(); 
        return this.alunos.get(cod).getQuotas();
    }
    
    public void registarQuota(String numero, LocalDate dataInicio, LocalDate dataFim, float valor) throws ParametrosInvalidosException, AlunoNaoExisteException, QuotaExisteException {
        if (!this.alunos.containsKey(numero)) throw new AlunoNaoExisteException(numero);    
        else if (numero.equals(null) || numero.equals("") || dataInicio.equals(null) || dataFim.equals(null) || valor <= 0) throw new ParametrosInvalidosException(); 
        this.alunos.get(numero).addQuota(Integer.toString(this.idAtual), dataInicio, dataFim, valor, false);
        this.idAtual++;
    }
    
    public void pagarQuota(String id, String numero) throws QuotaJaEstaPagaException, AlunoNaoExisteException, QuotaNaoExisteException{
        if (!this.alunos.containsKey(numero)) throw new AlunoNaoExisteException(numero); 
        else if(this.alunos.get(numero).getQuotas().get(id).isPaga()) throw new QuotaJaEstaPagaException(id);
        this.alunos.get(numero).setQuotaPaga(id, true);
    }
}