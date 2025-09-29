package andrelsmoraes.eclipselist.api.response;

import java.time.LocalDateTime;

/**
 * Error response structure for API responses.
 *
 * @param timestamp the time the error occurred
 * @param status the HTTP status code
 * @param error the error description
 * @param message the detailed error message
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {}
