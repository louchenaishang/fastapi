package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 团购信息
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_group_purchase")
public class GroupPurchaseEntity {

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
    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255) comment'团购名称'")
    private String name;

    @Column(name = "BEGIN_TIME", columnDefinition = "datetime comment'有效期开始时间'")
    protected Date beginTime;

    @Column(name = "END_TIME", columnDefinition = "datetime comment'有效期结束时间'")
    protected Date endTime;

    @Column(name = "ENABLE_HINT", nullable = true, columnDefinition = "int(1) default 0 comment'开启凑团提示'")
    private boolean enableHint;

    @Column(name = "ENABLE_BUY_LIMIT", nullable = true, columnDefinition = "int(1) default 0 comment'开启限购'")
    private boolean enableAddition;

    @Column(name = "BUY_LIMIT", nullable = true, columnDefinition = "int(10) default 0 comment'限购数量'")
    private Integer buyLimit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_PRODUCT_ID", referencedColumnName = "id")
    private ProductEntity saleProductEntity;

}
