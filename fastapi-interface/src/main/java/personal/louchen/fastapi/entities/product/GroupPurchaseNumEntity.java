package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 团购信息,几人团
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_group_purchase_num")
public class GroupPurchaseNumEntity {

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

    @Column(name = "PERSON_NUM", nullable = true, columnDefinition = "int(10) default 2 comment'几人团'")
    private Integer personNum;

    @Column(name = "MINIMUM_NUM", nullable = true, columnDefinition = "int(10) default 1 comment'最低成团人数'")
    private Integer minimumNum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_PURCHASE_ID", referencedColumnName = "id")
    private GroupPurchaseEntity groupPurchaseEntity;

}
