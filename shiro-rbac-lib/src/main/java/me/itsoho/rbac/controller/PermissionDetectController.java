package me.itsoho.rbac.controller;

import java.util.List;

import me.itsoho.rbac.util.PermissionDetect;
import me.itsoho.rbac.util.PermissionDetect.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
@EnableAutoConfiguration
@RequestMapping("/resources")
public class PermissionDetectController {
	@Autowired
	PermissionDetect permissionDetect;// = new PermissionDetect();
	
	
	@RequestMapping(method=RequestMethod.GET)
	List<Resource> getResources(){
		return permissionDetect.getResources();
	}
}
