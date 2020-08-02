package fpoly.com.springboot.service.mapper;

import fpoly.com.springboot.domain.ProductDetail;
import fpoly.com.springboot.service.dto.ProductDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface ProductDetailMapper extends EntityMapper<ProductDetailDTO, ProductDetail>{

}
