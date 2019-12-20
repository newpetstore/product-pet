package petstore.pet.usecase.exception;

/**
 * 
 * @author fabiojose
 *
 */
public class CategoryValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CategoryValidationException(String message) {
		super(message);
	}

}
