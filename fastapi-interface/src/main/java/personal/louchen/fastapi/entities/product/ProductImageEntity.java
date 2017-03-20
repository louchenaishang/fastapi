package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * 商品头信息图片
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_image")
public class ProductImageEntity extends BaseManagementEntity {

    @Column(name = "url", nullable = false, columnDefinition = "varchar(255) comment'商品头信息图片url'")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

}
