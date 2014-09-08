package br.com.oficinaivan.model.exception;

/**
 * Classe de exceção para exceções na camada de negócio.
 */
public class BusinessException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3448219765419906399L;

	/**
     * Creates a new instance of <code>BusinessException</code> without detail
     * message.
     */
    public BusinessException() {
    }

    /**
     * Constructs an instance of <code>BusinessException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BusinessException(String msg) {
        super(msg);
    }
    
    public BusinessException(String msg, Throwable rootCause){
        super(msg, rootCause);
    }
}
