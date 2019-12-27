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
package petstore.pet.usecase.model;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import petstore.pet.domain.entity.Pet;

/**
 * 
 * @author fabiojose
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PetCreated {
	
	private String id;
	
	private String name;
	private LocalDate birthdate;
	private String biography;
	private CategoryCreated category;
	
	@Mapper
	public static interface PetCreatedMapper {
		
		public static final PetCreatedMapper INSTANCE = 
				Mappers.getMapper(PetCreatedMapper.class);
		
		@Mapping(source = "birth", target = "birthdate")
		@Mapping(source = "bio", target = "biography")
		PetCreated map(Pet pet);
		
	}
}
