package nl.Str1XHyper.jwt;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.UUID;

/**
 * JWT generator and decryption.
 *
 * @author  Xander Vos
 * @version 0.1
 * @since   2020-10-11
 */

public class JWT {
    private final HashMap<String, Object> header = new HashMap<>();
    private final HashMap<String, Object> payload = new HashMap<>();

    protected JWT() {
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        payload.put("jti", UUID.randomUUID().toString());
    }

    public static JWTBuilder build(SecretKey key){
        return new JWTBuilder(new JWT(), key);
    }

    public static JWTParser parser() {
        return new JWTParser(new JWT());
    }

    protected void setPayloadBatch(HashMap<String, Object> payload) {
        this.payload.putAll(payload);
    }

    protected void setPayload(String key, Object value) {
        payload.put(key, value);
    }

    protected void setHeaderBatch(HashMap<String, Object> headers) {
        this.header.putAll(headers);
    }

    protected void setHeader(String key, Object value) {
        header.put(key, value);
    }

    protected HashMap<String, Object> getHeader() {
        return this.header;
    }

    protected HashMap<String, Object> getPayload() {
        return this.payload;
    }

    public String getType() {
        return this.getHeader("typ").toString();
    }

    public String getSigningAlgorithm() {
        return this.getHeader("alg").toString();
    }

    public Object getHeader(String key) {
        return this.header.get(key);
    }

    public String getIssuer() {
        return this.getClaim("iss").toString();
    }

    public long getExpirationTime() {
        return (long) this.getClaim("exp");
    }

    public long getIssuedAt() {
        return (long) this.getClaim("iat");
    }

    public String getJwtId() {
        return this.getClaim("jti").toString();
    }

    public String getSessionId() {
        return this.getClaim("sid").toString();
    }

    public String getSubject() {
        return this.getClaim("sub").toString();
    }

    public String getAudience() {
        return this.getClaim("aud").toString();
    }

    public Object getClaim(String key) {
        return this.payload.get(key);
    }
}
