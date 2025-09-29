package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
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
 * Implementation of the use case for listing mock Eclipse data from a JSON file.
 * It is useful for populating the application with sample data during development or testing.
 */
@RequiredArgsConstructor
public class ListMockEclipseUseCaseImpl implements ListMockEclipseUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListMockEclipseUseCaseImpl.class);

    private final ObjectMapper objectMapper;

    @Value("classpath:data/eclipses.json")
    private Resource eclipsesFile;

    /**
     * Loads and returns a list of Eclipse objects from a JSON file.
     *
     * @return List of Eclipse objects
     * @throws RuntimeException if there is an error reading the file or parsing the JSON
     */
    @Override
    public List<Eclipse> execute() {
        logger.info("Loading mock eclipses from file: {}", eclipsesFile.getFilename());
        try (InputStream is = eclipsesFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error loading eclipses from file", e);
        }
    }
}
