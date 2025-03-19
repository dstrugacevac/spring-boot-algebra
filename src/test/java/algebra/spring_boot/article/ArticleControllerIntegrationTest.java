package algebra.spring_boot.article;

import algebra.spring_boot.article.dto.CreateArticleDto;
import algebra.spring_boot.auth.AuthController;
import algebra.spring_boot.auth.dto.LoginDto;
import algebra.spring_boot.auth.dto.LoginResponseDto;
import algebra.spring_boot.category.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ArticleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleController articleController;

    @Autowired
    private AuthController authController;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private String accessToken;
    private CreateArticleDto createArticleDto = new CreateArticleDto("Test Hardware",  "Central processing unit", new BigDecimal("999.99"), 1);

    @BeforeEach
    void setUp() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("password");

        LoginResponseDto jwtResponse = authController.login(loginDto).getBody();
        accessToken = jwtResponse.getAccessToken();
    }

    @Test
    void createNewArticle() throws Exception {
        mockMvc.perform(post("/articles")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createArticleDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/articles")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4))));
    }
}
