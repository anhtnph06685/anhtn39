package fpoly.com.springboot.dto;

import fpoly.com.springboot.service.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByParamResponse {
    List<ProductDTO> products;
}
