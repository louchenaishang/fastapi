package personal.louchen.fastapi.entities.sku;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sku
 */
@Entity
@Table(name = "ai_sku")
public class SkuEntity {
    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    protected String id;

    @Column(name = "deleted",columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "create_time", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "update_time", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @Column(name = "create_user", columnDefinition = "varchar(255) comment'创建人id或者名称'")
    protected String createUser;//创建人id或者名称

    @Column(name = "update_user", columnDefinition = "varchar(255) comment'更新人id或者名称'")
    protected String updateUser;//更新人id或者名称

    @PrePersist
    public void prePersist() {
        createTime = updateTime = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        updateTime = new Date();
    }

    @Version
    @Column(name = "version", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "sku_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'sku编码和外部系统交互使用'")
    private String skuCode;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'名称'")
    private String name;

    @Column(name = "show_name", nullable = false, columnDefinition = "varchar(255) comment'前端显示名称'")
    private String showName;

    @Column(name = "image", nullable = true, columnDefinition = "varchar(255) comment'图片url'")
    private String image;

    @Column(name = "can_air", nullable = true, columnDefinition = "int(1) comment'是否可以被空运'")
    private boolean canAir;

    @Column(name = "can_land", nullable = true, columnDefinition = "int(1) comment'是否可以被陆运'")
    private boolean canLand;

    @Column(name = "cost", nullable = false, columnDefinition = "decimal(19,2) comment'成本'")
    private BigDecimal cost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_brand_id", referencedColumnName = "id")
    private SkuBrandEntity skuBrandEntity;


}

