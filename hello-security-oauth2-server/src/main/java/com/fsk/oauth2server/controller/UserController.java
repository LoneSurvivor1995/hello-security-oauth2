package com.fsk.oauth2server.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.fsk.oauth2server.config.LocalCache;
import com.fsk.oauth2server.domain.ImageCode;
import com.fsk.oauth2server.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UserController {

    @GetMapping("/user/getInfo")
    public Object index(Authentication authentication){
        return authentication;
    }

    @GetMapping("/code/image")
    public ImageCode createCode(String uuid) throws IOException {
        if (StrUtil.isNotEmpty(uuid)) {
            LocalCache.getCache().remove(uuid);
        }
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 10);
        String newUuid = UUID.randomUUID().toString();
        log.info("图形验证码:{}", captcha.getCode());
        LocalCache.getCache().put(newUuid, captcha.getCode());
        ImageCode imageCode = new ImageCode();
        imageCode.setImg("data:image/gif;base64," + captcha.getImageBase64());
        imageCode.setUuid(newUuid);
        return imageCode;
    }

    @GetMapping("/code/sms")
    public void createSmsCode(String phone) throws IOException {
        if (StrUtil.isEmpty(phone)){
            throw new ValidateCodeException("手机号不能为空");
        }
        String smsCode = RandomUtil.randomNumbers(4);
        LocalCache.getCache().put(phone, smsCode);
        System.out.println(StrUtil.format("向手机号{}发送验证码{}", phone, smsCode));
    }
}
