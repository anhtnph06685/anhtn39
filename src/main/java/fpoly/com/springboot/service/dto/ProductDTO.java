package fpoly.com.springboot.service.dto;

import fpoly.com.springboot.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private Integer quantity;

    private Integer price;

    private Instant createdDate;

    private String createdBy;

    private Instant toLastModifiedDate;

    private String lastModifiedBy;

    public ProductDTO(Product product){
    this.id=product.getId();
    this.code=product.getCode();
    this.name=product.getName();
    this.quantity=product.getQuantity();
    this.price=product.getPrice();
    this.createdDate=product.getCreatedDate();
    this.createdBy=product.getCreatedBy();
    this.toLastModifiedDate=product.getLastModifiedDate();
    this.lastModifiedBy=product.getLastModifiedBy();
    }
}
