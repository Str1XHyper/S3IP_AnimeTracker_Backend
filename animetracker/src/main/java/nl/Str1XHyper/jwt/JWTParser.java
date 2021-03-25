package nl.Str1XHyper.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * JWT generator and decryption.
 *
 * @author  Xander Vos
 * @version 0.1
 * @since   2020-10-11
 */

public class JWTParser {
    private final JWT jwt;
    private SecretKey key;

    protected JWTParser(JWT jwt) {
        this.jwt = jwt;
    }

    public JWTParser setKey(SecretKey key) {
        this.key = key;
        return this;
    }

    public JWT verify(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, JWTException, ParseException {
        if(this.key == null) {
            throw new InvalidKeyException("No key found. Have you called setKey(SecretKey key) before calling this function?");
        }

        String[] parts = jwt.split("\\.");
        Mac hasher = Mac.getInstance(this.key.getAlgorithm());
        hasher.init(this.key);
        byte[] jwtBytes = (parts[0] + "." + parts[1]).getBytes(StandardCharsets.UTF_8);
        if(!parts[2].equals(Base64Url.encode(hasher.doFinal(jwtBytes)))) {
            throw new JWTException("JWT is not valid.");
        }
        return getJwt(jwt);
    }

    private JWT getJwt(String jwt) throws ParseException, JWTException {
        String[] parts = jwt.split("\\.");
        if(parts.length != 3) {
            throw new JWTException("The given token consists of " + parts.length + " when it needs to be 3.");
        }
        String headerJsonString = new String(Base64Url.decode(parts[0]));
        String payloadJsonString = new String(Base64Url.decode(parts[1]));
        JSONObject headerJson = (JSONObject) new JSONParser().parse(headerJsonString);
        JSONObject payloadJson = (JSONObject) new JSONParser().parse(payloadJsonString);
        HashMap<String, Object> header = new ObjectMapper().convertValue(headerJson, HashMap.class);
        HashMap<String, Object> payload = new ObjectMapper().convertValue(payloadJson, HashMap.class);
        this.jwt.setPayloadBatch(payload);
        this.jwt.setHeaderBatch(header);
        return this.jwt;
    }

}
