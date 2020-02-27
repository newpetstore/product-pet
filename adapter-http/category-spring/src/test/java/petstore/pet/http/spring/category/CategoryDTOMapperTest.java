
package petstore.pet.http.spring.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import petstore.pet.usecase.model.Category;

@Tag("unit")
public class CategoryDTOMapperTest {

    @Test
    public void should_map_the_id_to_usecase_model() {

        // setup
        CategoryDTO dto = new CategoryDTO("cat88", "cat88Name", "cat88Desc");

        // act
        Category actual = CategoryDTOMapper.INSTANCE.map(dto);

        // assert
        assertEquals(dto.getId(), actual.getId());
    }

    @Test
    public void should_map_the_name_to_usecase_model() {

        // setup
        CategoryDTO dto = new CategoryDTO("cat88", "cat88Name", "cat88Desc");

        // act
        Category actual = CategoryDTOMapper.INSTANCE.map(dto);

        // assert
        assertEquals(dto.getName(), actual.getName());
    }

    @Test
    public void should_map_the_description_to_usecase_model() {

        // setup
        CategoryDTO dto = new CategoryDTO("cat88", "cat88Name", "cat88Desc");

        // act
        Category actual = CategoryDTOMapper.INSTANCE.map(dto);

        // assert
        assertEquals(dto.getDescription(), actual.getDescription());
    }

    @Test
    public void should_not_throw_when_dto_is_null() {

        Category actual = CategoryDTOMapper.INSTANCE.map(null);

        // assert
        assertNull(actual);
    }
}