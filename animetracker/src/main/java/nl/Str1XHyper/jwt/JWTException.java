package nl.Str1XHyper.jwt;

/**
 * JWT generator and decryption.
 *
 * @author  Xander Vos
 * @version 0.1
 * @since   2020-10-11
 */

public class JWTException extends Exception {
    public JWTException(String message) {
        super(message);
    }
}
