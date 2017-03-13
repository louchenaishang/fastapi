package personal.louchen.fastapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import personal.louchen.fastapi.entities.user.UserEntity;
import personal.louchen.fastapi.interfaces.UserService;
import personal.louchen.fastapi.rest.ApiBaseController;
import personal.louchen.fastapi.rest.annotation.RestDescription;
import personal.louchen.fastapi.rest.annotation.RestParam;
import personal.louchen.fastapi.rest.annotation.RestParams;
import personal.louchen.fastapi.rest.annotation.RestResult;
import personal.louchen.fastapi.vos.base.JsonResult;

/**
 * Created by louchen on 16/8/25.
 */
@Controller
@RequestMapping("/api/user")
public class UserController extends ApiBaseController{

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     * @param nickName
     * @return
     */
    @RestDescription("创建用户")
    @RestParams(value = { @RestParam(name = "nickName", remark = "String") })
    @RestResult(className = "personal.louchen.fastapi.entities.user.UserEntity")
    @RequestMapping(value = "/create/{nickName}" , method = RequestMethod.GET
            , consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public JsonResult<UserEntity> create(@PathVariable("nickName") String nickName) throws Exception {
        UserEntity userEntity = userService.create(nickName);
        return new JsonResult<UserEntity>(userEntity);
    }

    /**
     * 查找用户
     * @param nickName
     * @return
     */
    @RestDescription("查找用户")
    @RestParams(value = { @RestParam(name = "nickName", remark = "String") })
    @RestResult(className = "personal.louchen.fastapi.entities.user.UserEntity")
    @RequestMapping(value = "/query/{nickName}" , method = RequestMethod.GET
            , consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public JsonResult<UserEntity> query(@PathVariable("nickName") String nickName) throws Exception {
        UserEntity userEntity = userService.query(nickName);
        return new JsonResult<UserEntity>(userEntity);
    }

}
