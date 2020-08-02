package fpoly.com.springboot.dto.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterProductRequest {
    private Integer id;
    private String code;
    private String name;
    private Integer quantity;
    private Integer price;
    private Instant createdDate;
    private String createdBy;
    private Instant toLastModifiedDate;
    private String lastModifiedBy;
}
