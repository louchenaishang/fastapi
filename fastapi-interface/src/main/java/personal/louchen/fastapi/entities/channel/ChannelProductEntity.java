package personal.louchen.fastapi.entities.channel;

import personal.louchen.fastapi.entities.BaseManagementEntity;
import personal.louchen.fastapi.entities.product.ProductEntity;
import personal.louchen.fastapi.entities.channel.enums.ShelvesState;

import javax.persistence.*;

/**
 * 渠道商品
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_channel_product")
public class ChannelProductEntity extends BaseManagementEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private ChannelEntity channelEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    @Column(name = "shelves_state", nullable = false, columnDefinition = "varchar(255) comment'上下架状态,详细见枚举类ShelvesState'")
    @Enumerated(EnumType.STRING)
    private ShelvesState shelvesState;

}
