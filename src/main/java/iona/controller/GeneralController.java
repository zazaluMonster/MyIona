package iona.controller;

import iona.modelView.GeneralModelView;
import iona.util.MyHttpStatus;
import iona.util.MyStringUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/general")
@ResponseBody
public class GeneralController {

    @RequestMapping(value = "/verificationCode",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralModelView getVerificationCode() {
        String verifyCode = MyStringUtil.getVerifyCode();
        return new GeneralModelView(MyHttpStatus.OK,verifyCode);
    }

}
