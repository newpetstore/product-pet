/*
 * Java (TM) Pet Store Modernized Edition - 2020
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
package petstore.pet.http.spring.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import petstore.pet.usecase.model.Category;

/**
 * @author fabiojose
 */
@Mapper
public interface CategoryDTOMapper {

    public static final CategoryDTOMapper INSTANCE =
        Mappers.getMapper(CategoryDTOMapper.class);

    Category map(CategoryDTO dto);

}
