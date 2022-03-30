package nts.sixblack.hexa.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtValue {
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public long getUserId(HttpServletRequest request){
        String token = jwtTokenFilter.getToken(request);
        System.out.println(token);
        return 0;
//        return jwtTokenProvider.getUserId(token);
    }
}
