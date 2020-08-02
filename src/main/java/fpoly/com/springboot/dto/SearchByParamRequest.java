package fpoly.com.springboot.dto;
import fpoly.com.springboot.dto.Filter.FilterProductRequest;
import fpoly.com.springboot.service.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByParamRequest {
    private FilterProductRequest product;
}
