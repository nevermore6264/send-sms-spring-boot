package com.daykemit.infobip.request;

import com.daykemit.infobip.model.Destination;
import com.daykemit.infobip.model.Message;
import com.daykemit.infobip.model.Sms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequest {

    private Destination destination;

    private Message message;

    private Sms sms;
}
