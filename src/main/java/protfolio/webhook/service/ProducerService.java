package protfolio.webhook.service;

import protfolio.webhook.dto.WebHookDto;
import protfolio.webhook.enity.WebHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, WebHookDto> kafkaTemplate;

    public void addWebHook(WebHookDto dto) {
        kafkaTemplate.send("webHook-add", dto);
    }
}