package iona.controller;

import iona.async.RunnerQueue;
import iona.async.asyncRunner.SaveFileRunner;
import iona.exception.FirstLoginException;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.modelView.CrewResponse;
import iona.modelView.RequestModel.CrewRequest;
import iona.pojo.Crew;
import iona.service.ICrewService;
import iona.util.IonaBase64Util;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/crew")
@ResponseBody
public class CrewController {

    @Autowired
    private ICrewService crewService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    @Value("${upload.avatar.path}")
    private String AVATAR_PATH;
    @Value("${upload.avatar.name}")
    private String AVATAR_NAME;

    /**
     * 登录，生成本次登录token并返回
     *
     * @param crew 用户数据
     * @return 返回带有token的信息包
     * @throws FirstLoginException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse crewlogin(@RequestBody Crew crew) throws FirstLoginException {
        String token = null;
        MyHttpStatus status = MyHttpStatus.OK;
        String msg = "";

        if (crewService.auth(crew)) {
            token = crewService.tokenResolve(crew, false);
        } else {
            status = MyHttpStatus.LOGIN_FAIL;
        }

        CrewResponse result = new CrewResponse(status, token);
        return result;
    }

    /**
     * 注册
     *
     * @param crew 注册信息
     * @return 返回注册反馈信息
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse register(@RequestBody Crew crew) {
        MyHttpStatus status = MyHttpStatus.OK;
        String msg = MyHttpStatus.REGISTER_SUCCESS.getReasonPhrase();

        if (crewService.isCrewAlreadyExist(crew.getCrewName(), crew.getPhoneNum(), crew.getMail())) {
            status = MyHttpStatus.REGISTER_FAIL;
            msg = MyHttpStatus.REGISTER_FAIL.getReasonPhrase();
        } else {
            int id = crewService.register(crew);
            IonaLogger.info("新注册id:" + id);
        }

        CrewResponse result = new CrewResponse(status);
        return result;
    }

    /**
     * 根据用户名获取单个用户数据
     */
    @RequestMapping(value = "/getCrew", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse getCrew(@RequestBody Crew crew) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;
        MyHttpStatus errorStatus = MyHttpStatus.REQUEST_DATA_EMPTY;
        Crew result;

        IonaLogger.info(CrewController.class.getResource("/").getPath());

        if (StringUtils.isEmpty(crew.getCrewName())) {
            return new CrewResponse(errorStatus);
        } else {
            result = crewService.getByCrewName(crew.getCrewName());
        }

        return new CrewResponse(status, result);
    }


    /**
     * 获取所有用户信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse list() {
        MyHttpStatus status = MyHttpStatus.OK;

        //TODO 后续加上分页系统
        List<Crew> crewList = crewService.selects(null);

        return new CrewResponse(status, crewList);
    }


    /**
     * 修改用户信息
     *
     * @param crew 用户数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse update(@RequestBody Crew crew) {
        MyHttpStatus status = MyHttpStatus.OK;

        crewService.update(crew);

        return new CrewResponse(status);
    }

    /**
     * 删除用户信息
     *
     * @param crew 用户数据
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse delete(@RequestBody Crew crew) {
        MyHttpStatus status = MyHttpStatus.OK;

        crewService.delete(crew.getId());

        return new CrewResponse(status);
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/passwordReset", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse passwordReset(@RequestBody CrewRequest crewRequest) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;
        MyHttpStatus passwordErrorStatus = MyHttpStatus.OLD_PASSWORD_WRONG;
        MyHttpStatus unregisterErrorStatus = MyHttpStatus.NO_REGISTER_ERROR;

        Crew crew = null;
        if (crewRequest.getUseType().equals(CrewRequest.UseType.USE_PHONE)) {
            crew = crewService.getByPhone(crewRequest.getPhoneNum());
        } else {
            crew = crewService.getByMail(crewRequest.getMail());
        }

        if (crew == null) {
            return new CrewResponse(unregisterErrorStatus);
        } else {
            //检查旧密码输入是否正确
            if (crew.getPasswordMd5().equals(crewRequest.getOldPassword())) {
                crew.setPasswordMd5(crewRequest.getNewPassword());
                crewService.update(crew);
            } else {
                return new CrewResponse(passwordErrorStatus);
            }
        }

        return new CrewResponse(status);
    }

    /**
     * 上传头像
     */
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CrewResponse uploadAvatar(@RequestBody CrewRequest crewRequest) throws IonaException, IOException {
        MyHttpStatus status = MyHttpStatus.OK;
        MyHttpStatus noCrewNameError = MyHttpStatus.NO_CREWNAME_ERROR;

        if (StringUtils.isEmpty(crewRequest.getCrewName())) {
            return new CrewResponse(noCrewNameError);
        }

        String base64 = crewRequest.getAvatarImgCode();
        IonaBase64Util imgMeta = new IonaBase64Util(base64);
        String fileName = AVATAR_NAME + "_" + crewRequest.getCrewName() + imgMeta.getSuffix();
        String file = AVATAR_PATH + File.separator + fileName;

        //异步保存图片到本地
        SaveFileRunner saveFileRunner = (SaveFileRunner) applicationContext.getBean("saveFileRunner",file,imgMeta.getData());
        runnerQueue.addNewRunner(saveFileRunner);

        //数据库保存路径
        crewService.updateAvatar(crewRequest.getCrewName(),fileName);

        CrewResponse crewResponse = new CrewResponse(status);
        crewResponse.setAvatarURL(fileName);
        return crewResponse;
    }
}
