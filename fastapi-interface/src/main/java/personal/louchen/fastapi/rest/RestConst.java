package personal.louchen.fastapi.rest;

/**
 * Created by louchen on 16/8/25.
 */
public class RestConst {


    /**
     * 响应枚举
     */
    public static enum Response{
        SUCCESS(000000+"","SUCCESS","成功")
        ,UNKNOWN(999999+"","UNKNOWN","代码处理出错")
        ,FORBIDDEN(900000+"","FORBIDDEN","禁止访问")
        ,UNSIGN(900001+"","UNSIGN","签名失败")
        ,UNAUTHORIZED(900002+"","UNAUTHORIZED","未授权")
        ,ARGS_VALIDATE_FAILED(900003+"","ARGS_VALIDATE_FAILED","参数校验失败")
        ,DATA_VERSION_FAILED(900004+"","DATA_VERSION_FAILED","数据版本过期")
        ;
        public String code;
        public String textEN;
        public String textCN;
        Response(String code, String textEN, String textCN) {
            this.code = code;
            this.textEN = textEN;
            this.textCN = textCN;
        }
    }

    public static int PAGE_SIZE = 20;

}
