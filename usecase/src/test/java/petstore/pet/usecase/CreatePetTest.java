package petstore.pet.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.exception.PetValidationException;
import petstore.pet.usecase.port.CategoryDatastore;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
@ExtendWith(MockitoExtension.class)
public class CreatePetTest {
	
	@Mock
	PetDatastore pets;
	
	@Mock
	CategoryDatastore categories;
	
	@Test
	public void should_throw_on_null_pets_args() {
		
		assertThrows(NullPointerException.class, () -> {
			new CreatePet(pets, null);
		});
		
	}
	
	@Test
	public void should_throw_on_null_categories_args() {
		
		assertThrows(NullPointerException.class, () -> {
			new CreatePet(null, categories);
		});
		
	}
	
	public void create_ok() {
		
		// setup
		
		// act
		
		// assert
		
	}
}
