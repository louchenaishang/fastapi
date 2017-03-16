package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * 商品行信息
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_item_image")
public class ProductItemImageEntity extends BaseManagementEntity {

    @Column(name = "url", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品行信息图片url'")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id", referencedColumnName = "id")
    private ProductItemEntity productItemEntity;


}
