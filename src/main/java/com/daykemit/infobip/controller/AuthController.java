package com.daykemit.infobip.controller;

import com.daykemit.infobip.request.SmsRequest;
import com.daykemit.infobip.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private SendSmsService sendSmsService;

    @PostMapping("/forgot-pasword")
    public void sendOtp(@RequestBody SmsRequest smsRequest) {
        // Xử lý xác minh mật khẩu ...

        //    {
        //        "messages": [
        //        {
        //            "from": "InfoSMS",
        //                "destinations": [
        //            {
        //                "to": "41793026727"
        //            }
        //      ],
        //            "text": "This is a sample message"
        //        }
        //  ]
        //    }

        // Thực hiện các bước tạo và gửi mã OTP đến người dùng
        try {
            sendSmsService.sendSms(smsRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Check OTP và thay đổi mật khẩu
    }

}