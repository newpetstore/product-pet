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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import petstore.pet.usecase.exception.CategoryFetchingException;
import petstore.pet.usecase.model.Category;
import petstore.pet.usecase.port.Categories;

/**
 * @author fabiojose
 */
@Component
@Slf4j
public class CategoriesHTTPAdapter implements Categories {

    @Autowired
    GetByIdRestClient http;

    @Override
    public Optional<Category> getById(String id) {

        try {
            return
                Optional.ofNullable(http.getById(id))
                    .map(CategoryDTOMapper.INSTANCE::map);

        }catch(Exception e) {
            log.error(e.getMessage(), e);
            throw new CategoryFetchingException(e.getMessage(), e);
        }

    }
}
