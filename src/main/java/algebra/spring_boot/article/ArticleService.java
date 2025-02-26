package algebra.spring_boot.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService (ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public List<Article> fetchAll(){
        return articleRepository.fetchAll();
    }
}
