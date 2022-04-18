package Web.EnglishCenter.utils;

import Web.EnglishCenter.entity.user.CustomUserDetails;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.service.impl.UsersServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;
@Slf4j
@Component
@Getter
@Setter
public class JwtHelper {



    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "khanhvo18058521";

    // Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    //User for get user info from request
    @Autowired
    private UsersService usersService;


    /**
     * ex: Bearer abcdefghjkzz => abcdefghjkzz
     * @author VQKHANH
     * @param request
     * @return jwt
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

    /**
     * Create jwt from user info (username and role)
     * @author VQKHANH
     * @param user
     * @param request
     * @return jwt
     */
    public String generateToken(CustomUserDetails user,HttpServletRequest request) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());

        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryDate)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .sign(algorithm);

        return access_token;
    }

    /**
     * Get username from jwt
     * @author VQKHANH
     * @param token
     * @return username
     */
    public String getUsernameFromJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        log.info("username: {}", username);
        return username;
    }

    public String getAuthorities(String token){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Claim author = decodedJWT.getClaim("roles");
        return  author.asString();
    }

    /**
     * auto extract token from request, decode and find user info
     * @author VQKHANH
     * @param request
     * @param dtype
     * @return
     */
    public Users getUserFromRequest(HttpServletRequest request,String dtype){
        String jwt=getJwtFromRequest(request);
        log.info("jwt:" +jwt);
        String username=getUsernameFromJWT(jwt);
        log.info("username:"+username);
        return usersService.findByUsername(username, dtype);
    }

//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
//            return true;
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }
}
