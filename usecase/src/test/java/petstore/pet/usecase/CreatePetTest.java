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
	
	@Test
	public void should_throw_on_null_pet_arg() {
		
		final CreatePet create = new CreatePet(pets, categories);
		
		assertThrows(NullPointerException.class, () -> {
			create.create(null);
		});
		
	}
	
	@Test
	public void should_throw_on_invalid_pet_id() {
		
		//
		final Pet pet = new Pet(null, "a pet", LocalDate.now(), "a bio",
				new Category("cid", "cname", "cdesc"));
		
//		when(pets.get(id))
//			.thenReturn(Optional.of(pet));
		
		final CreatePet create = new CreatePet(pets, categories);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				create.create(pet);
				
			});
		
		// assert
		assertEquals("id.should.not.be.blank", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_name() {
		
		// setup
		final Pet pet = new Pet("id", null, LocalDate.now(), "a bio",
				new Category("cid", "cname", "cdesc"));		
		final CreatePet create = new CreatePet(pets, categories);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				create.create(pet);
				
			});
		
		// assert
		assertEquals("name.should.not.be.blank", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_bith() {
		
		// setup
		final Pet pet = new Pet("id", "a name", null, "a bio",
				new Category("cid", "cname", "cdesc"));		
		final CreatePet create = new CreatePet(pets, categories);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				create.create(pet);
				
			});
		
		// assert
		assertEquals("birth.should.not.be.null", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_bio() {
		
		// setup
		final Pet pet = new Pet("id", "a name", LocalDate.now(), "  ",
				new Category("cid", "cname", "cdesc"));		
		final CreatePet create = new CreatePet(pets, categories);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				create.create(pet);
				
			});
		
		// assert
		assertEquals("bio.should.not.be.blank", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_pet_category() {
		
		// setup
		final Pet pet = new Pet("id", "a name", LocalDate.now(), "a bio",
				null);		
		final CreatePet create = new CreatePet(pets, categories);
		
		// assert
		PetValidationException e = 
			assertThrows(PetValidationException.class, () -> {
				
				// act
				create.create(pet);
				
			});
		
		// assert
		assertEquals("category.should.not.be.null", e.getMessage());
	}

	public void create_ok() {
		
		// setup
		
		// act
		
		// assert
		
	}
}
