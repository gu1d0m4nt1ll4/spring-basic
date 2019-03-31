/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.model.exception;

/**
 *
 * @author guido.mantilla
 */
public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException() {
        super();
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(Throwable cause) {
        super(cause);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
