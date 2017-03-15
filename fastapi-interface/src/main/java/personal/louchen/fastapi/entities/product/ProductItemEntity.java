package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.sku.SkuEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品行信息
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_item")
public class ProductItemEntity {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED", columnDefinition = "int(1) default 0 comment'软删除标记'")
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
    public void preUpdate() {
        updateTime = new Date();
    }

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "PRODUCT_ITEM_CODE", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品行编码'")
    private String productItemCode;

    @Column(name = "PRICE", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) default 0.00 comment'销售单价'")
    private BigDecimal price;

    @Column(name = "STOCK", nullable = false, columnDefinition = "int(10) default 0 comment'商品库存'")
    private Integer stock;

    @Column(name = "STOCK_OCCUPY", nullable = false, columnDefinition = "int(10) default 0 comment'占用库存'")
    private Integer stockOccupy;

    @Column(name = "STOCK_REMAIN", nullable = false, columnDefinition = "int(10) default 0 comment'剩余库存'")
    private Integer stockRemain;

    @Column(name = "SALED_COUNT", nullable = false, columnDefinition = "int(10) default 0 comment'已销售数量'")
    private Integer saledCount;

    @Column(name = "DESCRIPTION", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

    @Column(name = "PERIOD", nullable = false, columnDefinition = "int(10) default 1 comment'周期属性,计算订单拆分次数'")
    private Integer period;

    @Column(name = "STRATEGY_PRICE_CHECK", nullable = false, columnDefinition = "int(10) default 1 comment'价格策略,是否进行价格检查,商品保存时候检查商品价格是否大于sku成本,公式:商品价格/周期期数>sku成本'")
    private boolean strategyPriceCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU_ID", referencedColumnName = "id")
    private SkuEntity skuEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")
    private ProductEntity productEntity;

}
