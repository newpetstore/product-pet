package petstore.pet.http.spring.category;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import petstore.pet.usecase.exception.CategoryFetchingException;
import petstore.pet.usecase.model.Category;

@Tag("integration")
@SpringBootTest
public class CategoriesHTTPAdapterTest {

    private static WireMockServer WIREMOCK =
        new WireMockServer(options().dynamicPort());

    @Autowired
    CategoriesHTTPAdapter categories;

    @BeforeAll
    public static void beforeAll(){
        WIREMOCK.start();

        // Configure the categories http endpoint
        System.setProperty("CATEGORIES_HTTP_ENDPOINT_URL", WIREMOCK.baseUrl());
    }

    @AfterAll
    public static void afterAll() {
        WIREMOCK.stop();
    }

    @Test
    public void should_result_empty_when_404_from_endpoint() {

        // setup
        final String categoryId = "x0999";

        WIREMOCK
            .stubFor(get(urlEqualTo("/categories/" + categoryId))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // act
        Optional<Category> actual = categories.getById(categoryId);

        // assert
        assertTrue(actual.isEmpty());
    }

    @Test
    public void should_throw_exception_when_400_from_endpoint() {

        // setup
        WIREMOCK
            .stubFor(get(urlEqualTo("/categories/x088"))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())));

        // assert
        assertThrows(CategoryFetchingException.class, () -> {
            // act
            categories.getById("x088");
        });
    }

    @Test
    public void should_throw_exception_when_500_from_endpoint() {

        // setup
        WIREMOCK
            .stubFor(get(urlEqualTo("/categories/x088"))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        // assert
        assertThrows(CategoryFetchingException.class, () -> {
            // act
            categories.getById("x088");
        });
    }

    @Test
    public void should_result_a_category_when_200_from_endpoint()
        throws Exception {

        // setup
        String categoryId = "x780";
        CategoryDTO dto =
            new CategoryDTO(categoryId, "x780 Name", "x780 Desc");

        ObjectMapper mapper = new ObjectMapper();

        WIREMOCK
            .stubFor(get(urlEqualTo("/categories/" + categoryId))
                .willReturn(okJson(mapper.writeValueAsString(dto))));

        // act
        Optional<Category> actual = categories.getById(categoryId);

        // assert
        assertTrue(actual.isPresent());
        actual.ifPresent(c -> {
            assertEquals(categoryId, c.getId());
            assertEquals("x780 Name", c.getName());
            assertEquals("x780 Desc", c.getDescription());
        });
    }
}
