package nl.Str1XHyper.jwt;

import org.jose4j.base64url.Base64Url;
import org.jose4j.json.internal.json_simple.JSONObject;

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

public class JWTEncoded {
    private String headerString;
    private String payloadString;
    private String signatureString;

    protected JWTEncoded() {
        
    }
    
    protected void encodeHeader(HashMap<String, Object> header) {
        JSONObject headerJson = new JSONObject(header);
        this.headerString = Base64Url.encode(headerJson.toJSONString(), StandardCharsets.UTF_8.name());
    }

    protected void encodePayload(HashMap<String, Object> payload) {
        JSONObject payloadJson = new JSONObject(payload);
        this.payloadString = Base64Url.encode(payloadJson.toJSONString(), StandardCharsets.UTF_8.name());
    }

    protected void createSignature(String algorithm, SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hasher = Mac.getInstance(algorithm);
        hasher.init(key);
        byte[] jwtBytes = (headerString + "." + payloadString).getBytes(StandardCharsets.UTF_8);
        this.signatureString = Base64Url.encode(hasher.doFinal(jwtBytes));
    }

    public String getToken() {
        return this.headerString + "." + this.payloadString + "." + this.signatureString;
    }
}
