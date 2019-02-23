/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author josepereira
 */
public class QuotaJaEstaPagaException extends Exception{
    
    public QuotaJaEstaPagaException(String msg){
        super(msg);
    }
    
    public QuotaJaEstaPagaException(){
        super();
    }
    
}
