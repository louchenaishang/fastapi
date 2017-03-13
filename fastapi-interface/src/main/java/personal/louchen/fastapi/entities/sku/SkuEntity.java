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
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED",columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @Column(name = "CREATE_USER", columnDefinition = "varchar(255) comment'创建人id或者名称'")
    protected String createUser;//创建人id或者名称

    @Column(name = "UPDATE_USER", columnDefinition = "varchar(255) comment'更新人id或者名称'")
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
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "SKU_CODE", nullable = false, unique = true, columnDefinition = "varchar(255) comment'sku编码和外部系统交互使用'")
    private String skuCode;

    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255) comment'名称'")
    private String name;

    @Column(name = "SHOW_NAME", nullable = false, columnDefinition = "varchar(255) comment'前端显示名称'")
    private String showName;

    @Column(name = "IMAGE", nullable = true, columnDefinition = "varchar(255) comment'图片url'")
    private String image;

    @Column(name = "CAN_AIR", nullable = true, columnDefinition = "int(1) comment'是否可以被空运'")
    private boolean canAir;

    @Column(name = "CAN_LAND", nullable = true, columnDefinition = "int(1) comment'是否可以被陆运'")
    private boolean canLand;

    @Column(name = "COST", nullable = false, columnDefinition = "decimal(19,2) comment'成本'")
    private BigDecimal cost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU_CATEGORY_ID", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU_BRAND_ID", referencedColumnName = "id")
    private SkuBrandEntity skuBrandEntity;


}

