package algebra.spring_boot.product;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
      return productService.findById(id);
    }

    @GetMapping()
    public List<Product> fetchAll(){
        return productService.fetchAll();
    }

    @PostMapping()
    public Product create(@RequestBody CreateProductDto dto){
        return productService.create(dto);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody UpdateProductDto dto, @PathVariable Long id){
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
