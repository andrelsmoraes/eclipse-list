package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.domain.model.Region;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Implementation of the use case for listing mock Region data from a JSON file.
 * It is useful for populating the application with sample data during development or testing.
 */
@RequiredArgsConstructor
public class ListMockRegionUseCaseImpl implements ListMockRegionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListMockRegionUseCaseImpl.class);

    private final ObjectMapper objectMapper;

    @Value("classpath:data/regions.json")
    private Resource regionsFile;

    /**
     * Loads and returns a list of Region objects from a JSON file.
     *
     * @return List of Region objects
     * @throws RuntimeException if there is an error reading the file or parsing the JSON
     */
    @Override
    public List<Region> execute() {
        logger.info("Loading mock regions from file: {}", regionsFile.getFilename());
        try (InputStream is = regionsFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error loading eclipses from file", e);
        }
    }
}
