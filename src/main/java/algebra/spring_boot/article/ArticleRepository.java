package algebra.spring_boot.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<Article> fetchAll();

    Optional<Article> findById(Integer id);

    Article create(Article article);

    Article update (Article article);

    void delete(Integer id);
}
