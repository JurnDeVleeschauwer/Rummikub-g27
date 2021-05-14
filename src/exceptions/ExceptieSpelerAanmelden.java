package exceptions;

public class ExceptieSpelerAanmelden extends Exception {
	

	    /** exceptie gooien
	     * 
	     */
	    public ExceptieSpelerAanmelden() {
	    }

	    /** exceptie gooien met bericht
	     * @param message bericht
	     */
	    public ExceptieSpelerAanmelden(String message) {
	        super(message);
	    }

	    /** exceptie gooien met bericht en oorzaak
	     * @param message bericht
	     * @param cause oorzaak 
	     */
	    public ExceptieSpelerAanmelden(String message, Throwable cause) {
	        super(message, cause);
	    }

	    /**exceptie gooien met oorzaak
	     * 
	     * @param cause oorzaak
	     */
	    public ExceptieSpelerAanmelden(Throwable cause) {
	        super(cause);
	    }

	    /**exceptie gooien met bericht, oorzaak...
	     * 
	     * @param message bericht
	     * @param cause oorzaak
	     * @param enableSuppression enableSuppression
	     * @param writableStackTrace writableStackTrace
	     */
	    public ExceptieSpelerAanmelden(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
	}

