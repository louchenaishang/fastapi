package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * Created by louchen on 2017/3/9.
 */
@Entity
@Table(name = "ai_sku_category_property_key")
public class SkuCategoryPropertyKeyEntity extends BaseManagementEntity {

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'属性名称'")
    private String name;

    @Column(name = "sorts", nullable = false, columnDefinition = "int(10) comment'排序值'")
    private Integer sorts;

    @Column(name = "visible", nullable = false, columnDefinition = "int(10) comment'是否可见'")
    private boolean visible;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;


}
