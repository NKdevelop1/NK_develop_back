package com.nkedu.back.service_impl;
// package com.nkedu.back.service.impl;

// import java.security.Key;
// import java.util.Date;

// import javax.crypto.spec.SecretKeySpec;

// import org.springframework.stereotype.Service;

// import com.nkedu.back.api.SecurityService;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.JwtBuilder;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import jakarta.xml.bind.DatatypeConverter;

// @Service
// public class SecurityServiceImpl implements SecurityService {
    
//     private static final String secretKey = "secret";

//     @Override
//     public String createToken(String subject, long ttlMillis) {
//         if (ttlMillis == 0) {
//             throw new RuntimeException("토큰 만료기간은 0 이상이어야 합니다.");
//         }

//         SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//         byte[] apiKeySeBytes = DatatypeConverter.parseBase64Binary(secretKey);
//         Key signingKey = new SecretKeySpec(apiKeySeBytes, signatureAlgorithm.getJcaName());

//         JwtBuilder builder = Jwts.builder()
//                 .setSubject(subject)
//                 .signWith(signatureAlgorithm, signingKey);
        
//         long nowMillis = System.currentTimeMillis();
//         builder.setExpiration(new Date(nowMillis + ttlMillis));
//         return builder.compact();
//     }

//     @Override
//     public String getSubject(String token) {
//         Claims claims = Jwts.parser()
//                 .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//                 .parseClaimsJws(token).getBody();
//         return claims.getSubject();
//     }
// }
