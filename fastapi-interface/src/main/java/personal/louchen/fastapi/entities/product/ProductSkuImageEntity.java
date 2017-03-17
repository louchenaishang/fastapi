package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * 商品sku图片
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_sku_image")
public class ProductSkuImageEntity extends BaseManagementEntity {

    @Column(name = "url", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品行信息图片url'")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sku_id", referencedColumnName = "id")
    private ProductSkuEntity productSkuEntity;


}
