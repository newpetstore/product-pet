package petstore.pet.domain.exception;

/**
 * 
 * @author fabiojose
 *
 */
public class NullOrEmptyArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NullOrEmptyArgumentException(String message) {
		super(message);
	}

}
