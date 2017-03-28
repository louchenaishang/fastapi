package personal.louchen.fastapi.entities.order.enums;

/**
 * 发货单状态
 * Created by louchen on 2017/3/28.
 */
public enum SubOrderDeliveryStatus {

    CANCELED,//已取消,付款前的"关闭"

    CLOSE,//已关闭,付款后的"关闭"

    NEEDPAY,//待付款

    NEEDDELIVERY,//待发货

    DELIVERY//已发货


}
