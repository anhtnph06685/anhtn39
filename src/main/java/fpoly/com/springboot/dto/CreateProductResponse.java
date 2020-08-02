package fpoly.com.springboot.dto;

import fpoly.com.springboot.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private Product productList;
}
