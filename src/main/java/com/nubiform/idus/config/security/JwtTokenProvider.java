package com.nubiform.idus.config.security;

import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.auth.service.AuthService;
import com.nubiform.idus.api.auth.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "idus";

    private long tokenValidTime = 30 * 60 * 1000L;

    private final AuthService authService;

    private final TokenService tokenService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        String newToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        tokenService.setToken(userPk, newToken);
        return newToken;
    }

    public Authentication getAuthentication(String token) {
        Auth auth = authService.getAuth(this.getUserPk(token));
        auth.setToken(token);
        return new UsernamePasswordAuthenticationToken(auth, "", auth.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        log.debug("header : {}", header);
        if (header != null && header.startsWith("Bearer ")) return header.replace("Bearer ", "");
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            String userPk = claims.getBody().getSubject();
            if (claims.getBody().getExpiration().before(new Date())) {
                log.debug("expired token (expiration date) {} {}", claims.getBody().getExpiration(), new Date());
                return false;
            }
            if (!tokenService.validateToken(userPk, token)) {
                log.debug("signed out token");
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
