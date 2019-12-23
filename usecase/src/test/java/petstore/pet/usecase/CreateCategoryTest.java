package petstore.pet.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import petstore.pet.domain.entity.Category;
import petstore.pet.usecase.exception.CategoryAlreadyExistsException;
import petstore.pet.usecase.port.CategoryDatastore;

/**
 * 
 * @author fabiojose
 *
 */
@ExtendWith(MockitoExtension.class)
public class CreateCategoryTest {

	@Mock
	CategoryDatastore categories;
	
	@InjectMocks
	CreateCategory usecase;
	
	@Test
	public void should_throw_when_categories_arg_is_null() {
		
		assertThrows(NullPointerException.class, () -> 
				new CreateCategory(null));
		
	}

	@Test
	public void should_throw_when_entity_already_exists() {
		
		String id = "catx0";
		Category category = Category.builder()
				.id(id)
				.name("cat")
				.description("desc")
				.build();
		
		when(categories.get(id))
			.thenReturn(Optional.of(category));
		
		assertThrows(CategoryAlreadyExistsException.class, () ->
				usecase.create(category));
				
	}
}
