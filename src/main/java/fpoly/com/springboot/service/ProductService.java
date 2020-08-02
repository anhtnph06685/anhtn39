package fpoly.com.springboot.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import fpoly.com.springboot.dto.*;
import fpoly.com.springboot.dto.Filter.FilterProductRequest;
import fpoly.com.springboot.dto.Filter.GetAllProductRequest;
import fpoly.com.springboot.dto.Filter.GetAllProductResponse;
import fpoly.com.springboot.domain.Product;
import fpoly.com.springboot.repository.ProductRepository;
import fpoly.com.springboot.service.dto.ProductDTO;
import fpoly.com.springboot.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.*;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private EntityManager entityManager;
    private ProductMapper productMapper;
    public ProductService(
            ProductRepository productRepository,
            EntityManager entityManager,
            ProductMapper productMapper
    ){
        this.productRepository=productRepository;
        this.entityManager=entityManager;
        this.productMapper=productMapper;
    }
    public ProductFindByIdResponse findById(ProductFindByIdRequest request) throws ServiceException {
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getId() <1){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id", ValidationError.NegativeOrZero))
                        .build();
            }
            if(StringUtils.isEmpty(request.getId())){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.NotNull))
                        .build();
            }
            Product product = productRepository.findById(request.getId()).orElse(null);
            ProductFindByIdResponse response = new ProductFindByIdResponse();
            ProductDTO productDTO = productMapper.toDto(product);
            response.setProduct(productDTO);
            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public SaveProductResponse saveProduct(SaveProductRequest request) throws ServiceException {
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            Product product = new Product();
            product.setCode(request.getProduct().getCode());
            product.setName(request.getProduct().getName());
            product.setQuantity(request.getProduct().getQuantity());
            product.setPrice(request.getProduct().getPrice());
            product.setCreatedBy("anhtn39");
            product.setCreatedDate(Instant.now());
            product.setLastModifiedBy("anhtn39");
            product.setLastModifiedDate(Instant.now());
            SaveProductResponse response = new SaveProductResponse();
            response.setProduct(product);
            productRepository.save(product);
            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public UpdateProductResponse updateProduct(UpdateProductRequest request) throws ServiceException {
        try {
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getProduct() == null){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("product",ValidationError.NotNull))
                        .build();
            }
           Optional<Product> product = productRepository.findById(request.getProduct().getId());
            if(!product.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("id",ValidationError.Duplicate))
                        .build();
            }
            Product pr = new Product();
            pr.setId(request.getProduct().getId());
            pr.setCode(request.getProduct().getCode());
            pr.setName(request.getProduct().getName());
            pr.setQuantity(request.getProduct().getQuantity());
            pr.setPrice(request.getProduct().getPrice());
            pr.setCreatedDate(Instant.now());
            pr.setCreatedBy("anhtn39");
            pr.setLastModifiedDate(Instant.now());
            pr.setLastModifiedBy("anhtn39");
            UpdateProductResponse response = new UpdateProductResponse();
            response.setProduct(pr);
            productRepository.save(pr);
            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    public SearchByParamResponse findByParam(SearchByParamRequest request) throws ServiceException {
        try {
            List<Product> products;
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if(request.getProduct() == null){
            products=productRepository.findAll();
            }
            else{
                FilterProductRequest filterProductRequest =request.getProduct();
                products= productRepository.findByParam(
                        filterProductRequest.getName(),
                        filterProductRequest.getCode(),
                        filterProductRequest.getQuantity(),
                        filterProductRequest.getPrice(),filterProductRequest.getCreatedBy(),
                        filterProductRequest.getCreatedDate(),filterProductRequest.getToLastModifiedDate(),
                        filterProductRequest.getLastModifiedBy()
                );
            }
            SearchByParamResponse response = new SearchByParamResponse();
            response.setProducts(productMapper.toDto(products));
            return response;
        }
        catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    @Transactional(readOnly = true)
    public GetAllProductResponse getAllProduct(GetAllProductRequest request) throws ServiceException {
        try {
            GetAllProductResponse response = new GetAllProductResponse();
            List<Product> list = null;
            if(request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getProduct() == null){
                list= productRepository.findAll();
            }
            else{
                list= query(request.getProduct(),null).getResultList();
            }
            response.setProducts(productMapper.toDto(list));
            return response;
        }
        catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
    private TypedQuery<Product> query(FilterProductRequest filter, String orderBy){
        StringBuilder stringBuilder = new StringBuilder("select a from Product a");
        List<String> param = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        String statement="";
        String wheres = "";

        if(filter != null){
        wheres="where";

        if(filter.getId() !=null){
            param.add("a.id=:id");
            params.put("id",filter.getId());
        }
        if(filter.getCode() !=null){
            param.add("a.code like concat('%',:code,'%')");
            params.put("code",filter.getCode());
        }
        if(filter.getName() !=null){
            param.add("a.name like concat('%',:name,'%')");
            params.put("name",filter.getName());
        }
        if(filter.getQuantity() !=null){
            param.add("a.quantity =:quantity");
            params.put("quantity",filter.getQuantity());
        }
        if(filter.getPrice()!=null){
            param.add("a.price =:price");
            params.put("price",filter.getPrice());
        }
        }
        String orderSQL=orderBy == null ? "" : "Order By" + "a." + orderBy;
        statement =String.join("and",param);
        String sql = stringBuilder.append(wheres).append(statement).append(orderSQL).toString();
        TypedQuery<Product> query = entityManager.createQuery(sql,Product.class);
        params.forEach((k, v) -> {
                query.setParameter(k,v);
        });
        return query;
    }
}
