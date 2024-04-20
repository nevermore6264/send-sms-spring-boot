package com.daykemit.infobip.service;

import com.daykemit.infobip.model.Destination;
import com.daykemit.infobip.model.Message;
import com.daykemit.infobip.model.Sms;
import com.daykemit.infobip.request.SmsRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SendSmsService {
    @Value("${ib.endpoint.url}")
    private static String BASE_URL;

    @Value("${ib.token}")
    private static String ACCESS_TOKEN;

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void sendSms(SmsRequest smsRequest) throws IOException {
        String receiverPhoneNumber = "+84862478150";

        Destination destination = smsRequest.getDestination();
        Message message = smsRequest.getMessage();
        Sms sms = smsRequest.getSms();

        String json = convertSmsToJson(sms);
        RequestBody body = createRequestBody(json);

        Request request = new Request.Builder()
                .url(String.format("https://%s/sms/2/text/advanced", BASE_URL))
                .method("POST", body)
                .addHeader("Authorization", ACCESS_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        System.out.println(response.code() == 200 ? "Successfully sent SMS message." : "Failed to send SMS message.");
    }

//    private static Destination createDestination(String phoneNumber) {
//        Destination destination = new Destination();
//        destination.setTo(phoneNumber);
//        return destination;
//    }
//
//    private static Message createMessage(Destination destination) {
//        Message message = new Message();
//        message.setFrom("InfoSMS");
//        message.setDestinations(List.of(destination));
//        message.setText("Hello from Infobip!");
//        return message;
//    }
//
//    private static Sms createSms(Message message) {
//        Sms sms = new Sms();
//        sms.setMessages(List.of(message));
//        return sms;
//    }

    private static String convertSmsToJson(Sms sms) {
        return gson.toJson(sms);
    }

    private static RequestBody createRequestBody(String json) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(json, mediaType);
    }

}
