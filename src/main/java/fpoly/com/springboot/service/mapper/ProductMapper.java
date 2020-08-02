package fpoly.com.springboot.service.mapper;


import fpoly.com.springboot.domain.Product;
import fpoly.com.springboot.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{

    @Mapping(source = "id", target = "id")
    ProductDTO toDto(Product product);

    @Mapping(source = "id",target = "id")
    Product toEntity(ProductDTO productDTO);
}
