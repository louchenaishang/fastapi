package personal.louchen.fastapi.entities.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by louchen on 16/8/27.
 */
@Entity
@Table(name = "ai_user_wealth", indexes = {
        @Index(columnList = "USER_ID", name="UK_UI", unique = true)
})
public class UserWealthEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Version
    @Column(name = "VERSION")
    private long version;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance = new BigDecimal("0.00");

    @Column(name = "BONUS_POINT", nullable = false)
    private int bonusPoint = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

}
