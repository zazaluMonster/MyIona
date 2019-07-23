package iona.controller;

import iona.exception.FirstLoginException;
import iona.logger.IonaLogger;
import iona.modelView.CrewModelView;
import iona.pojo.Crew;
import iona.service.ICrewService;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/crew")
@ResponseBody
public class CrewController {

    @Autowired
    private ICrewService crewService;

    /**
     * 登录，生成本次登录token并返回
     * @param crew 用户数据
     * @return 返回带有token的信息包
     * @throws FirstLoginException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewModelView crewlogin(@RequestBody Crew crew) throws FirstLoginException {
        String token = null;
        MyHttpStatus status = MyHttpStatus.OK;
        String msg = "";

        if(crewService.auth(crew)){
            token = crewService.tokenResolve(crew,false);
        }else{
            status = MyHttpStatus.LOGIN_FAIL;
            msg = MyHttpStatus.LOGIN_FAIL.getReasonPhrase();
        }

        CrewModelView result = new CrewModelView(status,msg,"",token);
        return result;
    }

    /**
     * 注册
     * @param crew 注册信息
     * @return 返回注册反馈信息
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewModelView register(@RequestBody Crew crew) {
        MyHttpStatus status = MyHttpStatus.REGISTER_SUCCESS;
        String msg = MyHttpStatus.REGISTER_SUCCESS.getReasonPhrase();

        if(crewService.isCrewAlreadyExist(crew.getCrewName(),crew.getPhoneNum(),crew.getMail())){
            status = MyHttpStatus.REGISTER_FAIL;
            msg = MyHttpStatus.REGISTER_FAIL.getReasonPhrase();
        }else{
            int id = crewService.register(crew);
            IonaLogger.info("新注册id:" + id);
        }

        CrewModelView result = new CrewModelView(status,msg,"","");
        return result;
    }

    /**
     * 获取所有用户信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewModelView list() {
        MyHttpStatus status = MyHttpStatus.OK;
        String msg = MyHttpStatus.REGISTER_SUCCESS.getReasonPhrase();

        //TODO 后续加上分页系统
        List<Crew> crewList = crewService.selects(null);

        return new CrewModelView(status,crewList);
    }


    /**
     * 修改用户信息
     * @param crew 用户数据
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewModelView update(@RequestBody Crew crew){
        MyHttpStatus status = MyHttpStatus.OK;

        crewService.update(crew);

        return new CrewModelView(status);
    }

    /**
     * 删除用户信息
     * @param crew 用户数据
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewModelView delete(@RequestBody Crew crew){
        MyHttpStatus status = MyHttpStatus.OK;

        crewService.delete(crew.getId());

        return new CrewModelView(status);
    }

}
