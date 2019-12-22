package petstore.pet.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.domain.entity.Category;
import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.exception.PetAlreadyExistsException;
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
	
	@InjectMocks
	CreatePet usecase;
	
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
	
	@Test
	public void should_throw_when_invalid_category() {
				
		// setup
		String categoryId = "catx1";
		
		when(categories.get(categoryId))
			.thenReturn(Optional.empty());

		Pet pet = Pet.builder()
			.id("id")
			.name("a pet")
			.birth(LocalDate.now())
			.bio("a bio")
			.category(new Category(categoryId, null, null))
			.build();
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				usecase.create(pet);
				
			});
		
		// assert
		assertEquals("category.not.found", e.getMessage());
	}
	
	@Test
	public void should_throw_when_id_already_exists() {
		
		// setup
		String petId = "petx0";
		String categoryId = "catx1";
		
		Category category = new Category(categoryId, "a cat", "cat desc");
		
		Pet pet = Pet.builder()
			.id(petId)
			.name("a pet")
			.birth(LocalDate.now())
			.bio("a bio")
			.category(category)
			.build();
		
		when(pets.get(petId))
			.thenReturn(Optional.of(pet));
		
		// assert
		assertThrows(PetAlreadyExistsException.class, () -> {
			
			// act
			usecase.create(pet);
			
		});
		
	}
}
