package com.example.DKHP_UIT.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.abstract_class.User;
import com.example.DKHP_UIT.entities.Staff;
import com.example.DKHP_UIT.entities.Student;
import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionUser;
import com.example.DKHP_UIT.repository.RoleRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.List;

@Component
public class UtilsHandleJwtToken {
    @Value("${jwt.scretKey}")
    private String secretKey;

    @Autowired
    private RoleRepository roleRepository;

    public String createToken(com.example.DKHP_UIT.abstract_class.User user) {
        String userId = "";
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            userId = staff.getId();
        } else {
            Student student = (Student) user;
            userId = student.getMssv();
        }

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claim = new JWTClaimsSet.Builder()
                .subject(userId)
                .issuer("UIT - University Of infomation technology")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("permission", buildPermission(user))
                .claim("role", buildRole(user))
                .build();
        Payload payload = new Payload(claim.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionUser(ExceptionCode.TokenCreationError);
        }
    }

    public String verifyToken(String token) {
        try {
            JWSVerifier verifier = new MACVerifier(this.secretKey.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expireTime = (Date) signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expireTime.before(new Date())) {
                throw new ExceptionUser(ExceptionCode.VerificationTokenError);
            } else {
                if (signedJWT.verify(verifier)) {
                    return signedJWT.getJWTClaimsSet().getSubject();
                } else {
                    throw new ExceptionUser(ExceptionCode.VerificationTokenError);
                }
            }
        } catch (Exception e) {
            throw new ExceptionUser(ExceptionCode.VerificationTokenError);
        }
    }

    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.secretKey.getBytes(),
                "HS256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    public String buildPermission(User user) {
        String result = "";
        if (user instanceof Staff) {
            List<String> listPermission = this.roleRepository.getPermissionOf1Role(2);
            result = supportForBuildingScope(listPermission);
        } else if (user instanceof Student) {
            List<String> listPermission = this.roleRepository.getPermissionOf1Role(1);
            result = supportForBuildingScope(listPermission);
        } else {
            List<String> listPermission = this.roleRepository.getPermissionOf1Role(3);
            result = supportForBuildingScope(listPermission);
        }
        return result;
    }

    public String supportForBuildingScope(List<String> list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s = s + " " + list.get(i);
        }
        return s;
    }

    public String buildRole(User user) {
        if (user instanceof Staff) {
            return "Staff";
        } else if (user instanceof Student) {
            return "Student";
        } else {
            return "Admin";
        }
    }
}