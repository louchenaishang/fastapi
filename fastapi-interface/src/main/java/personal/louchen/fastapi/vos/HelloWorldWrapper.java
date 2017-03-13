package personal.louchen.fastapi.vos;

import personal.louchen.fastapi.rest.annotation.RestAttribute;

/**
 * Created by louchen on 16/8/22.
 */
public class HelloWorldWrapper {

    @RestAttribute(len = 0 , name = "who" , notnull = false , remark = "who字段")
    private String who;

    @RestAttribute(len = 0 , name = "name" , notnull = false , remark = "name字段")
    private String name;

    @RestAttribute(len = 0 , name = "platform" , notnull = false , remark = "platform字段")
    private String platform;

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
