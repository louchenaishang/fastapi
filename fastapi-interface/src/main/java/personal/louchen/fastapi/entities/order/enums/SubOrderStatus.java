package personal.louchen.fastapi.entities.order.enums;

/**
 * 子订单状态
 * Created by louchen on 2017/3/28.
 */
public enum SubOrderStatus {

    CANCELED,//已取消,付款前的"关闭"

    CLOSE,//已关闭,付款后的"关闭"

    NEEDPAY,//待付款

    SERVICE,//服务中,周期购的子订单的状态

    NEEDDELIVERY,//待发货,非周期购的子订单的状态

    DELIVERY,//已发货,非周期购的子订单的状态

    COMPLETED//已完成

}
