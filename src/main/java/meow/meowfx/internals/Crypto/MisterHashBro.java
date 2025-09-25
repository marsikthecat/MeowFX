package meow.meowfx.internals.Crypto;

import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.bouncycastle.util.encoders.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class MisterHashBro {

  public static String sha256(String stuffToGetHashed) throws NoSuchAlgorithmException {
    MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
    byte[] hash256 = sha256.digest(stuffToGetHashed.getBytes());
    return bytesToHex(hash256);
  }

  public static String sha512(String stuffToGetHashed) throws NoSuchAlgorithmException {
    MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
    byte[] hash512 = sha512.digest(stuffToGetHashed.getBytes());
    return bytesToHex(hash512);
  }

  public static String sha3_256(String stuffToGetHashed) throws NoSuchAlgorithmException {
    MessageDigest sha3_256 = MessageDigest.getInstance("SHA3-256");
    byte[] hash3_256 = sha3_256.digest(stuffToGetHashed.getBytes());
    return bytesToHex(hash3_256);
  }

  public static String sha3_384(String stuffToGetHashed) throws NoSuchAlgorithmException {
    MessageDigest sha3_384 = MessageDigest.getInstance("SHA3-384");
    byte[] hash3_384 = sha3_384.digest(stuffToGetHashed.getBytes());
    return bytesToHex(hash3_384);
  }

  public static String sha3_512(String stuffToGetHashed) throws NoSuchAlgorithmException {
    MessageDigest sha3_512 = MessageDigest.getInstance("SHA3-512");
    byte[] hash3_512 = sha3_512.digest(stuffToGetHashed.getBytes());
    return bytesToHex(hash3_512);
  }

  public static String shake_128(String stuffToGetHashed) {
    org.bouncycastle.crypto.digests.SHAKEDigest shake128 = new org.bouncycastle.crypto.digests.SHAKEDigest(128);
    shake128.update(stuffToGetHashed.getBytes(), 0, stuffToGetHashed.getBytes().length);
    byte[] shake128Output = new byte[64];
    shake128.doFinal(shake128Output, 0, shake128Output.length);
    return bytesToHex(shake128Output);
  }

  public static String shake_256(String stuffToGetHashed) {
    SHAKEDigest shake256 = new SHAKEDigest(256);
    shake256.update(stuffToGetHashed.getBytes(), 0, stuffToGetHashed.getBytes().length);
    byte[] shake256Output = new byte[64];
    shake256.doFinal(shake256Output, 0, shake256Output.length);
    return bytesToHex(shake256Output);
  }
  
  public static String blake2b(String stuffToGetHashed) {
    byte[] input = stuffToGetHashed.getBytes();
    Blake2bDigest digest = new Blake2bDigest(64);
    digest.update(input, 0, input.length);
    byte[] out = new byte[digest.getDigestSize()];
    digest.doFinal(out, 0);
    return Hex.toHexString(out);
  }

  public static String blake2b(String stuffToGetHashed, String keyString) {
    byte[] key = keyString.getBytes();
    Blake2bDigest keyed = new Blake2bDigest(key, 64, null, null);
    keyed.update(stuffToGetHashed.getBytes(), 0, stuffToGetHashed.length());
    byte[] outKeyed = new byte[keyed.getDigestSize()];
    keyed.doFinal(outKeyed, 0);
    return Hex.toHexString(outKeyed);
  }

  /**
   *
   * Important: return value contains 2 elements: salt and the hash encoded in String format.
   */
  public static String[] argon2(String stuffToGetHashed) {
    byte[] salt = new byte[16];
    new SecureRandom().nextBytes(salt);

    Argon2Parameters params = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withSalt(salt)
            .withIterations(3)
            .withMemoryAsKB(65536)
            .withParallelism(1)
            .build();

    Argon2BytesGenerator gen = new Argon2BytesGenerator();
    gen.init(params);

    byte[] hash = new byte[32];
    gen.generateBytes(stuffToGetHashed.toCharArray(), hash, 0, hash.length);
    String saltString = Base64.getEncoder().encodeToString(salt);
    String hashString = Base64.getEncoder().encodeToString(hash);
    return new String[]{saltString, hashString};
  }

  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}