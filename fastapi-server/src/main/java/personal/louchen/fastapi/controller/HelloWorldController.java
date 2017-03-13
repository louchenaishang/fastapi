package personal.louchen.fastapi.controller;

import personal.louchen.fastapi.interfaces.HelloworldService;
import personal.louchen.fastapi.rest.ApiBaseController;
import personal.louchen.fastapi.rest.annotation.RestDescription;
import personal.louchen.fastapi.rest.annotation.RestParam;
import personal.louchen.fastapi.rest.annotation.RestParams;
import personal.louchen.fastapi.rest.annotation.RestResult;
import personal.louchen.fastapi.vos.HelloWorldWrapper;
import personal.louchen.fastapi.vos.base.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Created by louchen on 16/8/22.
 */
@Controller
@RequestMapping("/api")
public class HelloWorldController extends ApiBaseController {

    @Autowired
    private HelloworldService helloworldService;

    /**
     * api url 创建演示
     * 1.必须指定method
     * 2.必须指定produces
     * 3.必须指定httpstatus
     * @param who
     * @return
     */
    @RestDescription("helloworld演示")
    @RestParams(value = { @RestParam(name = "who", remark = "String") })
    @RestResult(className = "personal.louchen.fastapi.vos.HelloWorldWrapper")
    @RequestMapping(value = "/helloworld" , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult<HelloWorldWrapper> helloworld(@RequestParam(value = "who" , required = true) String who) throws Exception {
        HelloWorldWrapper wrapper = helloworldService.generateWrapper(who);
        return new JsonResult<HelloWorldWrapper>(wrapper);
    }

}
