package fpoly.com.springboot.dto.Filter;

import fpoly.com.springboot.service.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductResponse {
    private List<ProductDTO> products;
}
