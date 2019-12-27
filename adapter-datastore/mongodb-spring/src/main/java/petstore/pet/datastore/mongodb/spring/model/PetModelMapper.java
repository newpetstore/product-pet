/*
 * New Pet Store - 2019
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
package petstore.pet.datastore.mongodb.spring.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import petstore.pet.datastore.mongodb.spring.MongoCategoryDatastore;
import petstore.pet.domain.entity.Pet;
import petstore.pet.domain.exception.PetValidationException;

/**
 * 
 * @author fabiojose
 *
 */
@Mapper(
	componentModel = "spring"
)
public abstract class PetModelMapper {

	public static final PetModelMapper INSTANCE = 
			Mappers.getMapper(PetModelMapper.class);
	
	@Autowired
	protected MongoCategoryDatastore categories;
	
	/**
	 * 
	 * @param model
	 * @throws PetValidationException When categoryId is no present in the 
	 * data store
	 */
	@Mapping(
		source = "birthdate", target = "birth",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
	@Mapping(source = "biography", target = "bio")
	@Mapping(
		expression =  "java(categories.get(model.getCategoryId()).orElse(null))",
		target = "category"
	)
	public abstract Pet map(PetModel model);
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "name", target = "name"),
		@Mapping(
			source = "birth", target = "birthdate",
			nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
		),
		@Mapping(source = "bio", target = "biography"),
		@Mapping(source = "category.id", target = "categoryId")
	})
	public abstract PetModel map(Pet pet);
}
