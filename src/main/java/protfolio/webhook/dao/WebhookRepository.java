package protfolio.webhook.dao;

import org.springframework.data.repository.CrudRepository;
import protfolio.webhook.enity.WebHook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebhookRepository  extends CrudRepository<WebHook, Long> {

    public List<WebHook> findByCompanyNameAndType(String companyName, String type);

    public List<WebHook> findByCompanyName(String companyName);
}
