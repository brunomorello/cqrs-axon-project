package pt.bmo.store.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "PRODUCT_LOOKUP")
@NoArgsConstructor
@AllArgsConstructor
public class ProductLookupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;
}
