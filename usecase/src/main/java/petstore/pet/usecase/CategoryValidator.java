/*
 * Java (TM) Pet Store Modernized Edition - 2019
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package petstore.pet.usecase;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Objects;

import petstore.pet.domain.entity.Category;
import petstore.pet.usecase.exception.CategoryValidationException;

/**
 * 
 * @author fabiojose
 *
 */
public class CategoryValidator {
	private CategoryValidator() {}
	
	private static void validate(String value, String attribute) {
		if(isBlank(value)) {
			throw new CategoryValidationException(attribute + ".should.not.be.blank");
		}
	}

	/**
	 * 
	 * @param category Instance to validate
	 * @return A valid instance of {@link Category}
	 * @throws CategoryValidationException When the entity instance is invalid
	 * @throws NullPointerException When category argument is <code>null</code>
	 */
	public static Category requireValid(Category category) {
		
		Category _category = Objects.requireNonNull(category);
		
		validate(_category.getId(), "id");
		validate(_category.getName(), "name");
		validate(_category.getDescription(), "description");
		
		return category;
	}
}
