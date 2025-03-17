package algebra.spring_boot.article;

import algebra.spring_boot.article.dto.CreateArticleDto;
import algebra.spring_boot.category.Category;
import algebra.spring_boot.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @InjectMocks
   private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepositoryMock;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate(){
        CreateArticleDto dto = new CreateArticleDto("Article name", null, BigDecimal.TEN, 1);
        Category mockCategory = new Category(1, "Testna kategorija", "Nekakav description");
        Article article = new Article(dto.getName(), dto.getDescription(), dto.getPrice(), mockCategory);

        when(categoryRepositoryMock.findById(dto.getCategoryId())).thenReturn(Optional.of(mockCategory));
        articleService.create(dto);

        verify(articleRepositoryMock, times(1)).save(article);
    }

}