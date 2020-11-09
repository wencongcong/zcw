package com.service.service.impl;



import com.service.util.HmacSha256;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class CrmSignature {

    public static String generateSignature(String data, String signature_key) {
        String signature = HmacSha256.sha256_HMAC(data, signature_key);
        String asB64 = "";
        try {
            asB64 = Base64.getEncoder().encodeToString(signature.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return asB64;

    }


}
