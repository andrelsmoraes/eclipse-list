package andrelsmoraes.eclipselist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "eclipses")
public class EclipseElasticsearch {

    @Id
    private String id;
    private String date;
    private String type;
    private List<String> regionIds;
}
