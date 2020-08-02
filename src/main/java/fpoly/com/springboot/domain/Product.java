package fpoly.com.springboot.domain;

import com.fis.egp.common.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "price",nullable = false)
    private Integer price;
}
