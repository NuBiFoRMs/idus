package com.nubiform.idus.config.security;

import com.nubiform.idus.api.auth.AuthService;
import com.nubiform.idus.api.auth.model.Auth;
import com.nubiform.idus.api.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    private String secretKey = "nubiform";

    private long tokenValidTime = 30 * 60 * 1000L;

    private final MemberService memberService;
    private final AuthService authService;

    private final StringRedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
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
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            if (valueOperations.get(token) != null) {
                log.debug("already signed out");
                return false;
            }
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception ex) {
            return false;
        }
    }
}
