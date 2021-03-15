package exceptions;

public class ExceptieSpelerAanmelden extends Exception {
	

	    /**
	     * 
	     */
	    public ExceptieSpelerAanmelden() {
	    }

	    /**
	     * @param message
	     */
	    public ExceptieSpelerAanmelden(String message) {
	        super(message);
	    }

	    /**
	     * @param message
	     * @param cause
	     */
	    public ExceptieSpelerAanmelden(String message, Throwable cause) {
	        super(message, cause);
	    }

	    /**
	     * 
	     * @param cause
	     */
	    public ExceptieSpelerAanmelden(Throwable cause) {
	        super(cause);
	    }

	    /**
	     * 
	     * @param message
	     * @param cause
	     * @param enableSuppression
	     * @param writableStackTrace
	     */
	    public ExceptieSpelerAanmelden(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
	}

