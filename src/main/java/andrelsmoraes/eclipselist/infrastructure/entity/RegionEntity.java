package andrelsmoraes.eclipselist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * Entity class representing a Region in the DynamoDB table.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class RegionEntity {

    private String id;
    private String name;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
