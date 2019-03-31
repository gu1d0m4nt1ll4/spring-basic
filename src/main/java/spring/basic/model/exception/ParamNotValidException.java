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
public class ParamNotValidException extends RuntimeException {

    public ParamNotValidException() {
        super();
    }

    public ParamNotValidException(String message) {
        super(message);
    }

    public ParamNotValidException(Throwable cause) {
        super(cause);
    }

    public ParamNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
