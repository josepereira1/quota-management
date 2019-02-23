/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author filipap
 */
public class ParametrosInvalidosException extends Exception {
    public ParametrosInvalidosException () {
        super();
    }
    public ParametrosInvalidosException (String message) {
        super(message);
    }
}
