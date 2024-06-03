package com.controller;

import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class CaptchaController {
    public static final String VERIFY_CODE_IN_SESSION_KEY = "captcha";
    public static final String REQUEST_VERIFY_CODE = "code";

    @GetMapping("/verify")
    public void captcha(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha(130, 48, 4);
        String code = chineseCaptcha.text();
        System.out.println(REQUEST_VERIFY_CODE + code);
        request.getSession().setAttribute(VERIFY_CODE_IN_SESSION_KEY, code);
        CaptchaUtil.out(chineseCaptcha, request, response);
    }


}
