package nl.Str1XHyper.jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * JWT generator and decryption.
 *
 * @author  Xander Vos
 * @version 0.1
 * @since   2020-10-11
 */

public class JWTBuilder {
    private final JWT jwt;
    private final String algorithm;
    private final SecretKey key;

    protected JWTBuilder(JWT jwt, SecretKey key) {
        this.jwt = jwt;
        this.algorithm = key.getAlgorithm();
        this.key = key;
    }

    protected JWTBuilder(JWT jwt, String key, String algorithm) {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        this.jwt = jwt;
        this.algorithm = keySpec.getAlgorithm();
        this.key = keySpec;
    }

    public JWT getJwt() {
        return jwt;
    }

    public JWTEncoded encode() throws InvalidKeyException, NoSuchAlgorithmException {
        JWTEncoded encoded = new JWTEncoded();
        encoded.encodeHeader(jwt.getHeader());
        encoded.encodePayload(jwt.getPayload());
        encoded.createSignature(this.algorithm, this.key);
        return encoded;
    }

    public JWTBuilder setType(String type) {
        return this.setHeader("typ", type);
    }

    public JWTBuilder setSigningAlgorithm(String algorithm) {
        return this.setHeader("alg", algorithm);
    }

    public JWTBuilder setHeader(String key, Object value) {
        jwt.setHeader(key, value);
        return this;
    }

    public JWTBuilder setIssuer(String issuer) {
        return this.setClaim("iss", issuer);
    }

    public JWTBuilder setExpirationTime(long expirationTime) {
        return this.setClaim("exp", expirationTime);
    }

    public JWTBuilder setIssuedAt(long issuedAt) {
        return this.setClaim("iat", issuedAt);
    }

    public JWTBuilder setJwtId(String jwtId) {
        return this.setClaim("jti", jwtId);
    }

    public JWTBuilder setSessionId(String sessionId) {
        return this.setClaim("sid", sessionId);
    }

    public JWTBuilder setSubject(String subject) {
        return this.setClaim("sub", subject);
    }

    public JWTBuilder setAudience(String subject) {
        return this.setClaim("aud", subject);
    }

    public JWTBuilder setClaim(String key, Object value) {
        jwt.setPayload(key, value);
        return this;
    }
}