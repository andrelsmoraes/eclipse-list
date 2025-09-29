package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
public class ListMockEclipseUseCaseImpl implements ListMockEclipseUseCase {

    private final ObjectMapper objectMapper;

    @Value("classpath:data/eclipses.json")
    private Resource eclipsesFile;

    @Override
    public List<Eclipse> execute() {
        try (InputStream is = eclipsesFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error loading eclipses from file", e);
        }
    }
}
