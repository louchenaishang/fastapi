package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.enums.base.EnumType;
import personal.louchen.fastapi.excerptions.WrongStateException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by louchen on 17/03/13.
 */
public enum ProductType{

    SALE,//销售商品
    GIFT,//赠送商品
    ADDITION,//加价购商品
    ;

}
