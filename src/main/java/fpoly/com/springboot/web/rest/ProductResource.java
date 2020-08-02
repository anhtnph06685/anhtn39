package fpoly.com.springboot.web.rest;
import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.*;
import fpoly.com.springboot.dto.*;
import fpoly.com.springboot.dto.Filter.GetAllProductRequest;
import fpoly.com.springboot.dto.Filter.GetAllProductResponse;
import fpoly.com.springboot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductResource {
    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity
    save(@RequestBody BaseDataRequest<SaveProductRequest> request) throws ServiceException {
        try {
        SaveProductResponse response = productService.saveProduct(request.getBody());
        return ResponseUtil.wrap(response);
        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/search")
    public ResponseEntity<BaseDataResponse<ProductFindByIdResponse>>
    findById(@RequestBody BaseDataRequest<ProductFindByIdRequest> request) throws ServiceException {
           ProductFindByIdResponse response =productService.findById(request.getBody());
           return ResponseUtil.wrap(response);
    }
    @PostMapping("/update")
    public ResponseEntity<BaseDataResponse<UpdateProductResponse>> update(
            @RequestBody BaseDataRequest<UpdateProductRequest> request) throws ServiceException {
        try {
            UpdateProductResponse response = productService.updateProduct(request.getBody());
            return ResponseUtil.wrap(response);

        }
        catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/get-product-param")
    public ResponseEntity<BaseDataResponse<SearchByParamResponse>> findByParams(
            @RequestBody BaseDataRequest<SearchByParamRequest> request
    ){
        try {
            SearchByParamResponse response = productService.findByParam(request.getBody());
            return ResponseUtil.wrap(response);
        }catch (ServiceException e){
            return ResponseUtil.generateErrorResponse(e);
        }
        catch (Exception e){
            return ResponseUtil.generateErrorResponse(e);
        }
    }
//    @PostMapping("/get-all")
//    public ResponseEntity<BaseDataResponse<GetAllProductResponse>> getAll(
//            @RequestBody BaseDataRequest<GetAllProductRequest> request
//    ){
//        try {
//            GetAllProductResponse response = productService.getAllProduct(request.getBody());
//            return ResponseUtil.wrap(response);
//        }
//        catch (ServiceException e){
//            return ResponseUtil.generateErrorResponse(e);
//        }
//        catch (Exception e){
//            return ResponseUtil.generateErrorResponse(e);
//        }
//    }
}
