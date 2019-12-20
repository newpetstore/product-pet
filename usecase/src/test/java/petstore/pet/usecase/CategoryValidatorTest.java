package petstore.pet.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import petstore.pet.domain.entity.Category;
import petstore.pet.usecase.exception.CategoryValidationException;

/**
 * 
 * @author fabiojose
 *
 */
public class CategoryValidatorTest {

	@Test
	public void should_throw_on_null_category_arg() {

		assertThrows(NullPointerException.class, () -> {
			CategoryValidator.requireValid(null);
		});
		
	}
	
	@Test
	public void should_throw_on_invalid_id() {
		
		final Category category = new Category(null, "a name", "Desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					CategoryValidator.requireValid(category));
		
		assertEquals("id.should.not.be.blank", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_name() {
		
		final Category category = new Category("id", "  ", "Desc");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					CategoryValidator.requireValid(category));
		
		assertEquals("name.should.not.be.blank", e.getMessage());
	}
	
	@Test
	public void should_throw_on_invalid_description() {
		
		final Category category = new Category("id", "a name", "  ");
		
		CategoryValidationException e =
			assertThrows(CategoryValidationException.class, () -> 
					CategoryValidator.requireValid(category));
		
		assertEquals("description.should.not.be.blank", e.getMessage());
	}
}
