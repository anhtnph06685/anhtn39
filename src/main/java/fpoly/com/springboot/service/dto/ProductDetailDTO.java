package fpoly.com.springboot.service.dto;

import fpoly.com.springboot.domain.Product;
import fpoly.com.springboot.domain.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private Integer id;

    private String name;

    private Integer status;

    private Instant dateAdded;

    private Integer orderIndex;

    private ProductDTO product;

    private String createdBy;
    private Instant createdDate;

    private Instant toLastModifiedDate;

    private String lastModifiedBy;

    public ProductDetailDTO(ProductDetail productDetail){
        this.id= productDetail.getId();
        this.name=productDetail.getName();
        this.status=productDetail.getStatus();
        this.dateAdded=productDetail.getDateAdded();
        this.orderIndex=productDetail.getOrderIndex();
        this.createdBy = productDetail.getCreatedBy();
        this.createdDate = productDetail.getCreatedDate();
        this.lastModifiedBy= productDetail.getLastModifiedBy();
        this.toLastModifiedDate=productDetail.getLastModifiedDate();
    }

}
