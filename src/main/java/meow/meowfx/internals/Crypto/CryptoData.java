package meow.meowfx.internals.Crypto;

import javax.crypto.SecretKey;
import java.util.Arrays;

public class CryptoData {

  // Used by Every Mode
  private byte[] iv;
  private byte[] encrypted;

  // Normal Mode
  private SecretKey secretKey;
  private byte[] salt;

  // HardCore Mode
  private byte[] argonKey;
  private byte[] hmacKey;

  // Apocalyptic
  private byte[] kMac;

  public CryptoData() {}

  public CryptoData setSecretKey(SecretKey secretKey) {
    if (secretKey == null) {
      throw new IllegalArgumentException("SecretKey cannot be null");
    }
    this.secretKey = secretKey;
    return this;
  }

  public CryptoData setIv(byte[] iv) {
    if (iv == null) {
      throw new IllegalArgumentException("IV cannot be null");
    }
    this.iv = iv.clone();
    return this;
  }

  public CryptoData setSalt(byte[] salt) {
    if (salt == null) {
      throw new IllegalArgumentException("Salt cannot be null, go to the kitchen to find it!");
    }
    this.salt = salt.clone();
    return this;
  }

  public CryptoData setEncrypted(byte[] encrypted) {
    if (encrypted == null) {
      throw new IllegalArgumentException("Encrypted cannot be null");
    }
    this.encrypted = encrypted.clone();
    return this;
  }

  public CryptoData setArgonKey(byte[] argonKey) {
    this.argonKey = argonKey.clone();
    return this;
  }

  public CryptoData setHmac(byte[] hmacKey) {
    this.hmacKey = hmacKey.clone();
    return this;
  }

  public CryptoData setKMac(byte[] kMac) {
    this.kMac = kMac.clone();
    return this;
  }

  public byte[] getKMac() {
    return kMac.clone();
  }

  public byte[] getHmac() {
    return hmacKey.clone();
  }

  public byte[] getArgonKey() {
    return argonKey.clone();
  }

  public SecretKey getSecretKey() {
    return secretKey;
  }

  public byte[] getSalt() {
    return salt.clone();
  }

  public byte[] getIv() {
    return iv.clone();
  }

  public byte[] getEncrypted() {
    return encrypted.clone();
  }

  public void destroyKey() {
    try {
      byte[] encoded = secretKey.getEncoded();
      if (encoded != null) {
       Arrays.fill(encoded, (byte) 0);
      }
    } catch (Exception ignored) {}
      secretKey = null;
    }

  public void destroyArgonKey() {
    try {
      if (argonKey != null) {
        Arrays.fill(argonKey, (byte) 0);
      }
    } catch (Exception ignored) {}
    argonKey = null;
  }
}