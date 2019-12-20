package petstore.pet.usecase;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Objects;

import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
public class PetValidator {
	private PetValidator() {}
	
	private static void validate(String value, String attribute) {
		if(isBlank(value)) {
			throw new PetValidationException(attribute + ".should.not.be.blank");
		}
	}
	
	private static void validate(Object value, String attribute) {
		if(null== value) {
			throw new PetValidationException(attribute + ".should.not.be.null");
		}
	}
	

	/**
	 * @throws PetValidationException When the entity instance is valid
	 * @throws NullPointerException When pet argument is <code>null</code>
	 */
	public static Pet requireValid(Pet pet) {
		
		Pet _pet = Objects.requireNonNull(pet);
		
		validate(_pet.getId(), "id");		
		validate(_pet.getName(), "name");
		validate(_pet.getBio(), "bio");
		validate(_pet.getBith(), "birth");
		validate(_pet.getCategory(), "category");
		
		return pet;
	}
}
