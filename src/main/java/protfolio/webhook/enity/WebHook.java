package protfolio.webhook.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WebHook {
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "type")
    private String type;

    public WebHook(String url, String companyName, String type) {
        this.url = url;
        this.companyName = companyName;
        this.type = type;
    }
}
