package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.value.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIT {

    private MockMvc mockMvc;

    @Test
    void createProductTest() throws Exception {

        String requestBody = "{\"productName\":\"Test Product\",\"description\":\"Test Description\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.productName").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updateProductTest() throws Exception {

        Long id = 1L;
        String requestBody = "{\"productName\":\"Updated Product\",\"description\":\"Updated Description\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productName").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void getAllByCategoryTest() throws Exception {

        Category category = Category.HYDRAULIC;

        mockMvc.perform(MockMvcRequestBuilders.get("/products/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
}