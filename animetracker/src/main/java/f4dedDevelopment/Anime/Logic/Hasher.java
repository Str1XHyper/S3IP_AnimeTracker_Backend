package f4dedDevelopment.Anime.Logic;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RequestScoped
public class Hasher {
    private final byte[] IvBytes = "JaNdRgUkXp2s5v8y".getBytes();
    private final byte[] KeyBytes = "J@NcRfUjXn2r5u8x/A?D(G-KaPdSgVkY".getBytes();

    public String Encrypt(String message) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            return null;
        }

        try {

            IvParameterSpec ivspec = new IvParameterSpec(IvBytes);
            SecretKey secretKey = new SecretKeySpec(KeyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            return null;
        }

        try {
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }
    }

    public String Decrypt(String encryptedMessage) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            return null;
        }

        try {
            IvParameterSpec ivspec = new IvParameterSpec(IvBytes);
            SecretKey secretKey = new SecretKeySpec(KeyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            return null;
        }

        try {
            return new String(cipher.doFinal(encryptedBytes));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }
    }

    public String EncryptPassword(String password) {
        try {
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(32);
            return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            return null;
        }
    }

    public boolean ValidatePassword(String password, String stored) {
        String[] saltAndHash = stored.split("\\$");
        if(saltAndHash.length != 2) {
            throw new IllegalStateException("The stored password must have the form 'salt$hash'");
        }
        try {
            String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndHash[0]));
            return hashOfInput.equals(saltAndHash[1]);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            return false;
        }
    }

    private String hash(String password, byte[] salt) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(password == null || password.length() == 0) {
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }

        char[] passChars = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(passChars, salt, 20*1000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = factory.generateSecret(spec);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
