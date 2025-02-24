package algebra.spring_boot.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CreateProductDto {

    @NotBlank
    @Size(min = 3, max = 13)
    private String name;


    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 2)
    private List<Integer> grades;
}
