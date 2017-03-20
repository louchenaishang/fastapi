package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * 商品头HTML
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_html")
public class ProductHtmlEntity extends BaseManagementEntity {

    @Column(name = "html", nullable = true, columnDefinition = "text comment'商品详情普通屏幕'")
    private String html;

    @Column(name = "html_small", nullable = true, columnDefinition = "text comment'商品详情小屏幕'")
    private String htmlSmall;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

}
