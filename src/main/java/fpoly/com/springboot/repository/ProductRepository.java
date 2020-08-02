package fpoly.com.springboot.repository;

import fpoly.com.springboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("from Product p where 1=1 " +
            "and (p.name like concat('%',:name,'%') or :name is null) " +
            "and (p.code like concat('%',:code,'%') or :code is null)" +
            "and (p.quantity =:quantity or :quantity is null)" +
            "and (p.price =:price or :price is null)" +
            "and (p.createdBy like concat('%',:createdBy,'%') or :createdBy is null )"+
            "and (p.createdDate =:createdDate or :createdDate is null)" +
            "and (p.lastModifiedDate =:updatedDate or :updatedDate is null)" +
            "and (p.lastModifiedBy like concat('%',:updatedBy,'%') or :updatedBy is null)"
    )
    List<Product> findByParam(@Param("name")String name,
                              @Param("code") String code,
                              @Param("quantity") Integer quantity,
                              @Param("price") Integer price,
                              @Param("createdBy") String createdBy,
                              @Param("createdDate")Instant createdDate,
                              @Param("updatedDate") Instant updatedDate,
                              @Param("updatedBy") String updatedBy
                              );
}
