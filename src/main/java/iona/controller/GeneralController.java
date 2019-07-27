package iona.controller;

import iona.async.RunnerQueue;
import iona.async.asyncRunner.VerifyCodeLogRunner;
import iona.modelView.GeneralResponse;
import iona.modelView.RequestModel.GeneralRequest;
import iona.util.DateUtil;
import iona.util.MyHttpStatus;
import iona.util.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping("/general")
@ResponseBody
public class GeneralController {

    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/verificationCode",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse getVerificationCode(@RequestBody GeneralRequest requestModel) {
        if(StringUtils.isEmpty(requestModel.getPhoneNum())){
            return new GeneralResponse(MyHttpStatus.PHONE_NUM_ERROR);
        }

        String verifyCode = MyStringUtil.getVerifyCode();

        //异步记录验证码至数据库
        VerifyCodeLogRunner verifyCodeLogRunner = (VerifyCodeLogRunner) applicationContext.getBean("verifyCodeLogRunner",verifyCode,DateUtil.getTimeString(new Date()),requestModel.getPhoneNum());
        runnerQueue.addNewRunner(verifyCodeLogRunner);
        return new GeneralResponse(MyHttpStatus.OK,verifyCode);
    }

    @RequestMapping(value = "/verificationCodeToMail",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse verificationCodeToMail(@RequestBody GeneralRequest requestModel) {
        if(StringUtils.isEmpty(requestModel.getMail())){
            return new GeneralResponse(MyHttpStatus.MAIL_ERROR);
        }

        String verifyCode = MyStringUtil.getVerifyCode();

        //异步记录验证码至数据库
        VerifyCodeLogRunner verifyCodeLogRunner = (VerifyCodeLogRunner) applicationContext
                .getBean("verifyCodeLogRunner",verifyCode,
                        DateUtil.getTimeString(new Date()),requestModel.getMail());
        runnerQueue.addNewRunner(verifyCodeLogRunner);
        return new GeneralResponse(MyHttpStatus.OK,verifyCode);
    }

}
