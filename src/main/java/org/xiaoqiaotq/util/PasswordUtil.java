package org.xiaoqiaotq.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public final class PasswordUtil
{
  public static String encrypt(String plaintext)
  {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA");
      md.update(plaintext.getBytes("UTF-8"));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
    byte raw[] = md.digest();
    String hash = (new BASE64Encoder()).encode(raw);
    return hash;
  }

  public  static String base64(String plaintext){
    byte[] raw= new byte[0];
    try {
      raw = plaintext.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return (new BASE64Encoder()).encode(raw);
  }

  static final String HEXES = "0123456789ABCDEF";
  private static String getHex( byte [] raw ) {
    if ( raw == null ) {
      return null;
    }
    final StringBuilder hex = new StringBuilder( 2 * raw.length );
    for ( final byte b : raw ) {
      hex.append(HEXES.charAt((b & 0xF0) >> 4))
              .append(HEXES.charAt((b & 0x0F)));
    }
    return hex.toString();
  }
  public static void main(String[] args) {
    String dsfsdaf = PasswordUtil.encrypt("admin");
    System.err.println(dsfsdaf);
  }
}