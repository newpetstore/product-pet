package petstore.pet.domain.exception;

/**
 * 
 * @author fabiojose
 *
 */
public class PetValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PetValidationException(String message) {
		super(message);
	}

}
