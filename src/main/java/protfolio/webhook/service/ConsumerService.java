package protfolio.webhook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import protfolio.webhook.dao.WebhookRepository;
import protfolio.webhook.dto.WebHookDto;
import protfolio.webhook.enity.WebHook;

@Service
public class ConsumerService {

    @Autowired
    private WebhookRepository webHookRepository;

    @KafkaListener(topics = "webHook-add", groupId = "group_id")
    public void addWebHook(WebHookDto dto) {
        WebHook webHook = new WebHook(dto.getUrl(), dto.getCompanyName(), dto.getType());
        webHookRepository.save(webHook);
    }
}