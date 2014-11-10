package me.itsoho.shiro;

import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm{
	//must has the same name with shir-redis-sso,otherwise authc and authz cache deserialization will failed

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (
			PrincipalCollection principals) {//this should not be called
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(new HashSet<String>());
		authorizationInfo.setStringPermissions(new HashSet<String>());
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		throw new AuthenticationException("Should not do authentication in shiro-redis-sso-client");
	}
}
