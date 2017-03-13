package personal.louchen.fastapi.enums;

import personal.louchen.fastapi.enums.base.EnumType;
import personal.louchen.fastapi.excerptions.WrongStateException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by louchen on 16/8/26.
 */
public enum LoginType implements EnumType {

    EMAIL(1, "邮箱"),
    MOBILE(2, "手机"),
    WEIXIN(3, "微信"),
    ;

    public static final List<LoginType> ALL = Collections.unmodifiableList(Arrays.asList(values()));

    private final int code;
    private final String text;

    LoginType(final int code, final String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String text() {
        return text;
    }

    public static LoginType valueOf(final int code) {
        return LoginType.ALL.stream()
                .filter(e -> e.code() == code)
                .findFirst()
                .orElseThrow(() -> new WrongStateException("没有这种登陆类型", "code", code));
    }


}
