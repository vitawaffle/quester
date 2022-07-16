package by.vitalylobatsevich.quester.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.*;
import io.vavr.control.Option;
import lombok.val;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Value("${application.auth.jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        val authorizationHeader = request.getHeader("Authorization");
        if (isValidAuthorizationHeader(authorizationHeader))
            authorizeByAuthorizationHeader(authorizationHeader);
        filterChain.doFilter(request, response);
    }

    private boolean isValidAuthorizationHeader(final String authorizationHeader) {
        return authorizationHeader != null
                && StringUtils.hasText(authorizationHeader)
                && authorizationHeader.startsWith("Bearer ");
    }

    private void authorizeByAuthorizationHeader(final String authorizationHeader) {
        val authorizationToken = authorizationHeader.substring(7);
        toJwsClaims(authorizationToken).peek(this::authorizeByJwsClaims);
    }

    private Option<Jws<Claims>> toJwsClaims(final String authorizationToken) {
        try {
            return Option.of(
                    Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(authorizationToken)
            );
        } catch (final SignatureException
                | MalformedJwtException
                | ExpiredJwtException
                | UnsupportedJwtException
                | IllegalArgumentException ignore) {
            return Option.none();
        }
    }

    private void authorizeByJwsClaims(final Jws<Claims> jwsClaims) {
        val userDetails = userDetailsService.loadUserByUsername(getUsernameFromJwsClaims(jwsClaims));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        ));
    }

    private String getUsernameFromJwsClaims(final Jws<Claims> jwsClaims) {
        return jwsClaims.getBody().getSubject();
    }

}
