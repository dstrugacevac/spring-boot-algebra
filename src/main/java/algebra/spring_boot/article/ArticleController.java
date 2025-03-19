package algebra.spring_boot.article;

import algebra.spring_boot.article.dto.CreateArticleDto;
import algebra.spring_boot.article.dto.UpdateArticleDto;
import algebra.spring_boot.category.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")

public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> fetchAll(){

        List<Article> articles = articleService.fetchAll();


        List<String> articleNamesFromForLoop = new ArrayList<>();
        for (Article article : articles) {
           articleNamesFromForLoop.add(article.getName());
        }

        List<String> articleNames = articles.stream().map(Article::getName).toList();
        // TODO: Proučiti svakako jer ce vam trebati u svakodnevnom programiranju kasnije

        return ResponseEntity.status(200).body(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable Integer id){
        Optional<Article> article = articleService.findById(id);

        if (article.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(article.get());
    }

    @PostMapping()
    public ResponseEntity<Article> create (@Valid @RequestBody CreateArticleDto dto){
        Article article = articleService.create(dto);
        return ResponseEntity.status(201).body(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update (@Valid @RequestBody UpdateArticleDto dto, @PathVariable Integer id){
        Article article = articleService.update(id, dto);
        return ResponseEntity.status(200).body(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        articleService.delete(id);
        return ResponseEntity.status(204).build();
    }

}
