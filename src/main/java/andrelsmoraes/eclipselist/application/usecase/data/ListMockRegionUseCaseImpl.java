package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.domain.model.Region;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
public class ListMockRegionUseCaseImpl implements ListMockRegionUseCase {

    private final ObjectMapper objectMapper;

    @Value("classpath:data/regions.json")
    private Resource regionsFile;

    @Override
    public List<Region> execute() {
        try (InputStream is = regionsFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error loading eclipses from file", e);
        }
    }
}
