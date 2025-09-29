package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEclipseUseCaseTest {

    @Mock
    private EclipseRepository eclipseRepository;

    @Mock
    private EclipseEventProducer eventProducer;

    private CreateEclipseUseCaseImpl createEclipseUseCase;

    @BeforeEach
    void setUp() {
        createEclipseUseCase = new CreateEclipseUseCaseImpl(eclipseRepository, eventProducer);
    }

    @Test
    void executeShouldCreateEclipseWithGeneratedIdWhenIdIsNull() {
        Eclipse inputEclipse = new Eclipse(
                null,
                LocalDate.of(2025, 4, 8),
                Type.SOLAR_TOTAL,
                List.of(UUID.randomUUID())
        );
        Eclipse expectedEclipse = inputEclipse
                .copy(UUID.randomUUID(), inputEclipse.date(), inputEclipse.type(), inputEclipse.regionIds());

        doNothing().when(eclipseRepository).save(any(Eclipse.class));

        Eclipse result = createEclipseUseCase.execute(inputEclipse);

        assertNotNull(result.id());
        assertEquals(expectedEclipse.date(), result.date());
        assertEquals(expectedEclipse.type(), result.type());
        assertEquals(expectedEclipse.regionIds(), result.regionIds());

        verify(eclipseRepository).save(any(Eclipse.class));
        verify(eventProducer).sendEclipseCreationEvent(result);
    }

    @Test
    void executeShouldCreateEclipseWithProvidedIdWhenIdIsNotNull() {
        UUID id = UUID.randomUUID();
        Eclipse inputEclipse = new Eclipse(
                id,
                LocalDate.of(2025, 4, 8),
                Type.LUNAR_PARTIAL,
                List.of(UUID.randomUUID())
        );

        doNothing().when(eclipseRepository).save(any(Eclipse.class));

        Eclipse result = createEclipseUseCase.execute(inputEclipse);

        assertEquals(id, result.id());
        assertEquals(inputEclipse.date(), result.date());
        assertEquals(inputEclipse.type(), result.type());
        assertEquals(inputEclipse.regionIds(), result.regionIds());

        verify(eclipseRepository).save(inputEclipse);
        verify(eventProducer).sendEclipseCreationEvent(result);
    }

    @Test
    void executeShouldThrowExceptionWhenEclipseIsNull() {
        assertThrows(NullPointerException.class, () -> createEclipseUseCase.execute(null));

        verifyNoInteractions(eclipseRepository, eventProducer);
    }

    @Test
    void executeShouldThrowExceptionWhenRepositoryFails() {
        Eclipse inputEclipse = new Eclipse(
                UUID.randomUUID(),
                LocalDate.of(2025, 4, 8),
                Type.SOLAR_TOTAL,
                List.of(UUID.randomUUID())
        );

        doThrow(new RuntimeException("Repository error")).when(eclipseRepository).save(any(Eclipse.class));

        assertThrows(RuntimeException.class, () -> createEclipseUseCase.execute(inputEclipse));

        verify(eclipseRepository).save(inputEclipse);
        verifyNoInteractions(eventProducer);
    }
}

