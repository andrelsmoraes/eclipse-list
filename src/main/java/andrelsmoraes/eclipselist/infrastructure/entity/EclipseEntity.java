package andrelsmoraes.eclipselist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class EclipseEntity {

    private String id;
    private String date;
    private String type;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
