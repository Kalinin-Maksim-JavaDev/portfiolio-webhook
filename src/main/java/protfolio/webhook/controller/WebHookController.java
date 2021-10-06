package protfolio.webhook.controller;

import protfolio.webhook.dao.WebhookRepository;
import protfolio.webhook.dto.WebHookDto;
import protfolio.webhook.enity.WebHook;
import protfolio.webhook.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class WebHookController {

    private final static Logger logger = LoggerFactory.getLogger(WebHookController.class);

    @Autowired
    private ProducerService producerService;

    @PostMapping("/generate")
    public String generate(@RequestBody String text) {
        return "OK";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWebHook(@RequestBody WebHookDto dto) {
        producerService.addWebHook(dto);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ids/{id}",
            produces = MediaType.TEXT_MARKDOWN_VALUE)
    public ResponseEntity<String> removeWebHookById(@PathVariable Long id) {
       /* WebHook webhook = webHookRepository.findOne(id);
        if (webhook != null) {
            webHookRepository.delete(webhook);
            return new ResponseEntity<>("WebHook was successfully deleted.", HttpStatus.OK);
        }*/
        return new ResponseEntity<>("Webhook doesn't exist.", HttpStatus.OK);
    }
}