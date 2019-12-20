package petstore.pet.usecase;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
	
	public static Pet requireValid(Pet pet) {
		
		validate(pet.getId(), "id");		
		validate(pet.getName(), "name");
		validate(pet.getBio(), "bio");
		validate(pet.getBith(), "birth");
		validate(pet.getCategory(), "category");
		
		return pet;
	}
}
