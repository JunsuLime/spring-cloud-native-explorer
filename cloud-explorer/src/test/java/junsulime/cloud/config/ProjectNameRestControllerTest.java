package junsulime.cloud.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(ProjectNameRestController.class)
class ProjectNameRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void mvcTest() throws Exception {
        mvc.perform(request(HttpMethod.GET, "/project-name"))
                .andExpect(status().isOk());
    }
}