/**
 * 
 */
package me.itsoho.aaa.monitor;

import java.text.SimpleDateFormat;

import me.itsoho.aaa.controller.UserController;
import me.itsoho.aaa.model.Radcheck;
import me.itsoho.aaa.model.Radreply;
import me.itsoho.aaa.model.User;
import me.itsoho.aaa.repo.RadcheckRepository;
import me.itsoho.aaa.repo.RadreplyRepository;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shaojunwu  --sjw
 * @date 2014-6-1
 */
@Aspect
@Component
public class UserControllerMonitor {

	@Autowired
	RadcheckRepository radcheckRepo;
	@Autowired
	RadreplyRepository radreplyRepo;
	
	//for aop test
	@AfterReturning(value="execution(* me.itsoho.aaa.controller.UserController.getUser(Long))",returning = "returnValue")
	public void logControllerAccessa(JoinPoint joinPoint,Object returnValue) {
		Object[] paramValues = joinPoint.getArgs();
		Long id = (Long)paramValues[0];
		System.out.println("get User for id: " + id);
		User user = (User)returnValue;
		System.out.println("User for id  username: " + user.getUsername());
		//UserController controller = (UserController)joinPoint.getTarget();
	}
	
	// this is example  add two function to one joinPoint
	@AfterReturning("execution(* me.itsoho.aaa.controller.UserController.getUser(Long))")
	public void logControllerAccessb(JoinPoint joinPoint) {
		System.out.println("Completed:bbbb " + joinPoint);
	}

	//question!!!!
	@AfterReturning(value="execution(* me.itsoho.aaa.controller.helper.UserControllerHelper.userEnable(me.itsoho.aaa.model.User))")
	public void enableUserMonitor(JoinPoint joinPoint){
		Object[] paramValues = joinPoint.getArgs();
		User user = (User)paramValues[0];
		
		//add radcheck
		Radcheck radcheck;
		radcheck = new Radcheck(user.getUsername(),"Cleartext-Password",":=",user.getPassword());
		radcheckRepo.save(radcheck);
		
		//add radreply
		Radreply radreply;
		SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy-hh:mm");
		if(null!=user.getStarttime()){
			radreply = new Radreply(user.getUsername(),"Symbol-Start-Date-Time",":=",format.format(user.getStarttime()));
			radreplyRepo.save(radreply);
		}
		
		if(null!=user.getExpire()){
			radreply = new Radreply(user.getUsername(),"Symbol-Start-Date-Time",":=",format.format(user.getExpire()));
			radreplyRepo.save(radreply);
		}
	}
	
	@AfterReturning(value="execution(* me.itsoho.aaa.controller.helper.UserControllerHelper.userDisable(me.itsoho.aaa.model.User))")
	public void disableUserMonitor(JoinPoint joinPoint){
		Object[] paramValues = joinPoint.getArgs();
		User user = (User)paramValues[0];
		
		radcheckRepo.deleteRadcheckByUsername(user.getUsername());
		radreplyRepo.deleteRadreplyByUsername(user.getUsername());
	}
	
	@Before(value="execution(* me.itsoho.aaa.controller.UserController.deleteUser(..))")
	public void deleteUserMonitor(JoinPoint joinPoint) throws Exception {
		UserController controller = (UserController)joinPoint.getTarget();
		Object[] paramValues = joinPoint.getArgs();
		Long id = (Long)paramValues[0];
		controller.userDisable(id);
	}
}



