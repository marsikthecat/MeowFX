package meow.meowfx.internals.Crypto;

import meow.meowfx.internals.BasicAPIs.Console;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.macs.KMAC;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.*;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Crypto {
  private static final int GCM_NONCE_LENGTH = 12;
  private static final int GCM_TAG_LENGTH = 128;
  private static int SALT_LENGTH;
  private static final int KEY_SIZE = 64;
  private static SecureRandom secureRandom;
  private static CryptoMode cryptoMode;

  public static void setMode(CryptoMode cryptoMode) {
    if (cryptoMode == CryptoMode.NORMAL) {
      SALT_LENGTH = 32;
      secureRandom = new SecureRandom();
    }
    if (cryptoMode == CryptoMode.HARDCORE) {
      Console.warn("Hardcore mode has just been activated! \n" +
              "This method should be treated with caution!");
      SALT_LENGTH = 64;
      try {
        secureRandom = SecureRandom.getInstanceStrong();
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }
    if (cryptoMode == CryptoMode.EXTREME) {
      SALT_LENGTH = 128;
      Console.error("////   EXTREME WARNING   ////");
      Console.error("EXTREME_MODE has just been activated!");
      Console.error("You have just activated the apocalyptic Mode!\n" +
              "This Mode uses extreme amount of RAM and CPU and can destroy your PC!!!");
      try {
        secureRandom = SecureRandom.getInstanceStrong();
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }
    Crypto.cryptoMode = cryptoMode;
  }

  public static CryptoData generateKey() throws Exception {
    if (cryptoMode == null) {
      throw new IllegalStateException("You need to set the Mode before using it");
    }
    byte[] salt = new byte[SALT_LENGTH];
    secureRandom.nextBytes(salt);
    if (cryptoMode == CryptoMode.NORMAL) {
      SecretKey key = deriveKey(salt);
      return new CryptoData().setSalt(salt).setSecretKey(key);
    } else {
      return generateKeyWithArgon(new char[]{'a', 'd', 'i', 'e', 'd'}, salt);
    }
  }

  private static SecretKey deriveKey(byte[] salt) throws Exception {
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
    char[] password = "secretPassword1234567890".toCharArray(); //TODO: Store this securely
    KeySpec spec = new PBEKeySpec(password, salt, 1000000, 256);
    SecretKey tmp = factory.generateSecret(spec);
    return new SecretKeySpec(tmp.getEncoded(), "AES");
  }

  private static CryptoData generateKeyWithArgon(char[] password, byte[] salt) {
    int parallelism = cryptoMode == CryptoMode.HARDCORE ? 4 : 8;
    int memory = cryptoMode == CryptoMode.HARDCORE ? 262144 : 1048576;
    int iterations = cryptoMode == CryptoMode.HARDCORE ? 12 : 64;
    Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withSalt(salt)
            .withParallelism(parallelism)
            .withMemoryAsKB(memory)
            .withIterations(iterations);

    Argon2BytesGenerator generator = new Argon2BytesGenerator();
    generator.init(builder.build());
    byte[] result = new byte[KEY_SIZE];
    generator.generateBytes(password, result);
    Arrays.fill(password, '\0');
    return new CryptoData().setSalt(salt).setArgonKey(result);
  }

  public static CryptoData encrypt(String plainText, CryptoData cryptoData) throws Exception {
    if (cryptoMode == CryptoMode.NORMAL) {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      byte[] iv = generateIV();
      GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
      cipher.init(Cipher.ENCRYPT_MODE, cryptoData.getSecretKey(), spec);
      cryptoData.destroyKey();
      byte[] cipherText = cipher.doFinal(plainText.getBytes());
      return cryptoData.setEncrypted(cipherText).setIv(iv);
    }
    else if (cryptoMode == CryptoMode.HARDCORE) {
      byte[] aesKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 0, 32);
      byte[] hmacKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 32, 64);
      byte[] encrypted = aes(plainText, cryptoData, aesKey);
      byte[] hmac = computeHMAC(encrypted, hmacKey);
      return cryptoData.setHmac(hmac).setEncrypted(encrypted);
    } else {
      byte[] aesKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 0, 32);
      byte[] chaChaKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 32, 64);
      byte[] kMacKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 64, 128);
      byte[] encrypted = aes(plainText, cryptoData, aesKey);
      byte[] bytes = chaCha(chaChaKey, encrypted);
      byte[] tag = computeKMAC(kMacKey, bytes);
      byte[] tagAndPayload = new byte[tag.length + bytes.length];
      System.arraycopy(tag, 0, tagAndPayload, 0, tag.length);
      System.arraycopy(bytes, 0, tagAndPayload, tag.length, bytes.length);
      return cryptoData.setKMac(tagAndPayload);
    }
  }

  private static byte[] aes(String plainText, CryptoData cryptoData, byte[] aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
    byte[] iv = generateIV();
    cryptoData.setIv(iv);
    GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
    byte[] cipherText = cipher.doFinal(plainText.getBytes());
    byte[] salt = cryptoData.getSalt();
    byte[] encrypted = new byte[salt.length + iv.length + cipherText.length];
    System.arraycopy(salt, 0, encrypted, 0, salt.length);
    System.arraycopy(iv, 0, encrypted, salt.length, iv.length);
    System.arraycopy(cipherText, 0, encrypted, salt.length + iv.length, cipherText.length);
    return encrypted;
  }

  public static String decrypt(CryptoData cryptoData) throws Exception {
    if (cryptoMode == CryptoMode.NORMAL) {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      byte[] iv = cryptoData.getIv();
      byte[] cipherText = cryptoData.getEncrypted();
      byte[] salt = cryptoData.getSalt();
      GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
      cipher.init(Cipher.DECRYPT_MODE, deriveKey(salt), spec);
      return new String(cipher.doFinal(cipherText));
    }
    else if (cryptoMode == CryptoMode.HARDCORE) {
      byte[] aesKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 0, 32);
      byte[] hmacKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 32, 64);
      byte[] encrypted = cryptoData.getEncrypted();
      byte[] expectedHmac = cryptoData.getHmac();
      byte[] actualHmac = computeHMAC(encrypted, hmacKey);
      if (!MessageDigest.isEqual(expectedHmac, actualHmac)) {
        throw new SecurityException("HMAC verification failed. Data may have been tampered with.");
      }
      byte[] salt = cryptoData.getSalt();
      int saltLength = salt.length;
      byte[] cipherText = Arrays.copyOfRange(encrypted, saltLength + GCM_NONCE_LENGTH, encrypted.length);

      cryptoData.destroyArgonKey();
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
      SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
      GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, cryptoData.getIv());
      cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
      byte[] plainBytes = cipher.doFinal(cipherText);
      return new String(plainBytes);
    } else {
      byte[] aesKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 0, 32);
      byte[] chaChaKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 32, 64);
      byte[] kMacKey = Arrays.copyOfRange(cryptoData.getArgonKey(), 64, 128);

      int kMacLength = new KMAC(128, new byte[0]).getMacSize();
      if (cryptoData.getKMac().length < kMacLength)
        throw new IllegalArgumentException("Input too short");

      byte[] expectedKMAC = Arrays.copyOfRange(cryptoData.getKMac(), 0, kMacLength);
      byte[] actualEncrypted = Arrays.copyOfRange(cryptoData.getKMac(), kMacLength, cryptoData.getKMac().length);
      byte[] actualKMAC = computeKMAC(kMacKey, actualEncrypted);
      if (!MessageDigest.isEqual(expectedKMAC, actualKMAC)) {
        throw new SecurityException("KMac verification failed");
      }

      byte[] nonce = Arrays.copyOfRange(actualEncrypted, 0, 12);
      byte[] chaChaCiphertext = Arrays.copyOfRange(actualEncrypted, 12, actualEncrypted.length);

      Cipher chachaCipher = Cipher.getInstance("ChaCha20");
      SecretKey chachaSecretKey = new SecretKeySpec(chaChaKey, "ChaCha20");
      ChaCha20ParameterSpec chaChaParamSpec = new ChaCha20ParameterSpec(nonce, 1);
      chachaCipher.init(Cipher.DECRYPT_MODE, chachaSecretKey, chaChaParamSpec);
      byte[] decryptedChaCha = chachaCipher.doFinal(chaChaCiphertext);

      byte[] salt = Arrays.copyOfRange(decryptedChaCha, 0, cryptoData.getSalt().length);
      byte[] iv = Arrays.copyOfRange(decryptedChaCha, salt.length, salt.length + GCM_NONCE_LENGTH);
      byte[] aesCipherText = Arrays.copyOfRange(decryptedChaCha, salt.length + GCM_NONCE_LENGTH, decryptedChaCha.length);

      Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
      SecretKeySpec aesKeySpec = new SecretKeySpec(aesKey, "AES");
      GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
      aesCipher.init(Cipher.DECRYPT_MODE, aesKeySpec, gcmSpec);
      byte[] plainBytes = aesCipher.doFinal(aesCipherText);

      return new String(plainBytes, StandardCharsets.UTF_8);
    }
  }

  private static byte[] generateIV() {
    byte[] iv = new byte[Crypto.GCM_NONCE_LENGTH];
    secureRandom.nextBytes(iv);
    return iv;
  }

  private static byte[] computeHMAC(byte[] data, byte[] hmacKey) throws Exception {
    Mac hmac = Mac.getInstance("HmacSHA512");
    SecretKeySpec keySpec = new SecretKeySpec(hmacKey, "HmacSHA512");
    hmac.init(keySpec);
    return hmac.doFinal(data);
  }

  private static byte[] chaCha(byte[] key, byte[] encrypted) throws Exception {
    byte[] nonce = new byte[12];
    secureRandom.nextBytes(nonce);
    Cipher cipher = Cipher.getInstance("ChaCha20");
    SecretKey secretKey = new SecretKeySpec(key, "ChaCha20");
    ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonce, 1);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
    byte[] ciphertext = cipher.doFinal(encrypted);
    byte[] output = new byte[nonce.length + ciphertext.length];
    System.arraycopy(nonce, 0, output, 0, nonce.length);
    System.arraycopy(ciphertext, 0, output, nonce.length, ciphertext.length);
    return output;
  }

  private static byte[] computeKMAC(byte[] key, byte[] data) {
    KMAC kmac = new KMAC(128, new byte[0]);
    kmac.init(new KeyParameter(key));
    kmac.update(data, 0, data.length);
    byte[] output = new byte[kmac.getMacSize()];
    kmac.doFinal(output, 0);
    return output;
  }
}