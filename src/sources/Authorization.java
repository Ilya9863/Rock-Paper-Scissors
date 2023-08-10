package sources;

/**
 * Interface for authorization functionality.
 */

public interface Authorization {

    /**
     * Performs the authentication process.
     *
     * @return {@code true} if the authentication is successful, {@code false} otherwise.
     */
    public boolean auth();
}
