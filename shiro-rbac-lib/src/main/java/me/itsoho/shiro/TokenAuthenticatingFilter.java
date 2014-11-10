package me.itsoho.shiro;

import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import me.itsoho.rbac.domain.Token;
import me.itsoho.rbac.repo.TokenRepository;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenAuthenticatingFilter extends AuthenticatingFilter {
	@Autowired
	TokenRepository tokenRepo;
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		String tkstring = null;
		
		Cookie[] cookies =  req.getCookies();
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("token")){
				tkstring = cookie.getValue();
				break;
			}
		}
		
		if(null == tkstring){
			return null;
		}
		
		Token tk = tokenRepo.findOne(UUID.fromString(tkstring)); 
		
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return createToken(tk.getUsername(), tk.getPassword(), rememberMe, host);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
