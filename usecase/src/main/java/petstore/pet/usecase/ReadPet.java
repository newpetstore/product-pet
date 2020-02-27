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

import static java.util.Objects.requireNonNull;
import java.util.Optional;

import petstore.pet.domain.entity.Pet;
import petstore.pet.usecase.port.Categories;
import petstore.pet.usecase.port.PetDatastore;

/**
 * 
 * @author fabiojose
 *
 */
public class ReadPet {

	private final PetDatastore pets;
	private final Categories categories;
	
	/**
	 * 
	 * @param pets The instance to access pets data store
	 * @throws NullPointerException When any argument is <code>null</code>
	 */
	public ReadPet(PetDatastore pets, Categories categories) {
		this.pets = requireNonNull(pets);
		this.categories = requireNonNull(categories);
	}
	
	public Optional<Pet> findById(String id) {
		
		//TODO get from pets
		
		//TODO get category
		
		return Optional.empty();
	}
}
