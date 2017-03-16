package personal.louchen.fastapi.entities.product.enums;

/**
 * Created by louchen on 2017/3/16.
 */
public enum ShelvesType {

    ON,//立刻上架,对应ShelvesState.UP
    OFF,//放入仓库,对应ShelvesState.DOWN
    SCHEDULE,//定时上下架,对应ShelvesState.DOWN
    ;

}
