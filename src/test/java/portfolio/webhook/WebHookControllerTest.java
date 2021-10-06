package portfolio.webhook;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import protfolio.webhook.WebHookApplication;
import protfolio.webhook.dao.WebhookRepository;
import protfolio.webhook.dto.WebHookDto;
import protfolio.webhook.enity.WebHook;
import protfolio.webhook.service.ConsumerService;

import java.util.Optional;

import static java.lang.Thread.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = WebHookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableKafka
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class WebHookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebhookRepository webHookRepository;

    @Test
    public void addWebHook_Successfully() throws InterruptedException {

        HttpEntity<WebHookDto> request = new HttpEntity<>(new WebHookDto("com.hook", "provider", "basic"));

        ResponseEntity<String> hello = restTemplate.postForEntity("http://localhost:" + port + "/api/webhook/add", request, String.class);

        assertThat(hello.getBody(), is("Successfully"));

        sleep(5000);

        Optional<WebHook> first = webHookRepository.findById(1L);

        assertTrue(first.isPresent());
        assertThat(first.get().getCompanyName(), is("provider"));
    }
}
