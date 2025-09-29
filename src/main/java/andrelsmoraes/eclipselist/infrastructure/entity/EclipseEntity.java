package andrelsmoraes.eclipselist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

/**
 * Entity class representing an Eclipse in the DynamoDB table.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class EclipseEntity {

    private String id;
    private String date;
    private String type;
    private List<String> regionIds;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
