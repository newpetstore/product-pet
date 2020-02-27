package petstore.pet.usecase.port;

import java.util.Optional;

import petstore.pet.usecase.exception.CategoryFetchingException;
import petstore.pet.usecase.model.Category;

/**
 * Port to fetch categories from whatever they are.
 *
 * @author fabiojose
 */
public interface Categories {

    /**
     * @param id The category id
     * @return The found category instance, or {@link Optional#empty()} when
     * no category found
     * @throws CategoryFetchingException When some kind of exception happens
     * during the data fetching
     */
    Optional<Category> getById(String id);

}
