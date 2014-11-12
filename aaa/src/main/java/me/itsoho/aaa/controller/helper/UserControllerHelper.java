package me.itsoho.aaa.controller.helper;

import me.itsoho.aaa.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserControllerHelper {
	public void userEnable(User user){
		user.setIsActive(true);
	}
	
	public void userDisable(User user){
		user.setIsActive(false);
	}
}
