package com.pgf.thymeleaf_demo;

import com.pgf.thymeleaf_demo.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@TestPropertySource(properties = {
        "countries=Brazil,France,Germany,India",
        "languages=Go,Java,C++",
        "operatingSystems=Windows,OsX,Linux"

})
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // -------------------------
    // GET /showStudentForm
    // -------------------------

    @Test
    void showStudentForm_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(status().isOk());
    }

    @Test
    void showStudentForm_returnsStudentFormView() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(view().name("student-form"));
    }

    @Test
    void showStudentForm_addsStudentToModel() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void showStudentForm_addsCountriesToModel() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(model().attribute(
                        "countries",
                        List.of("Brazil", "France", "Germany", "India")
                ));
    }

    @Test
    void showStudentForm_addsLanguagesToModel() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(model().attribute(
                        "languages",
                        List.of("Go", "Java", "C++")
                ));
    }

    @Test
    void showStudentForm_addSystemsToModel() throws Exception {
        mockMvc.perform(get("/showStudentForm"))
                .andExpect(model().attribute(
                        "favoriteOperatingSystems",
                        List.of("Windows", "OsX", "Linux")
                ));
    }

    // -------------------------
    // POST /processStudentForm
    // -------------------------

    @Test
    void processStudentForm_returnsOkStatus() throws Exception {
        mockMvc.perform(post("/processStudentForm")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("country", "Germany")
                        .param("favoriteLanguage", "Java"))
                .andExpect(status().isOk());
    }

    @Test
    void processStudentForm_returnsConfirmationView() throws Exception {
        mockMvc.perform(post("/processStudentForm")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("country", "Germany")
                        .param("favoriteLanguage", "Java")
                        .param("favoriteOperatingSystems", "Linux")
                )
                .andExpect(view().name("student-confirmation"));
    }
}

