package com.wissem.users.restControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.wissem.users.entities.User;
import com.wissem.users.repos.RoleRepository;
import com.wissem.users.repos.UserRepository;
import com.wissem.users.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
	
	@Autowired
	UserRepository userRep;
	@Autowired
	UserService userService;
	
	@RequestMapping(path ="all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }
	
	 @RequestMapping(value ="/{username}",method = RequestMethod.GET)
	    public User getUserByUsernamePassword(@PathVariable("username") String username) {
	        return userRep.findByUsername(username);
	    }

	   @RequestMapping(path="all",method = RequestMethod.POST)
	    public User addNewUserToDataBase(@RequestBody User user) {
	        System.out.println(user.getUsername());
	        System.out.println(user.getRoles().get(0).getRole());
	        userRep.save(user) ;
	        return userService.addRoleToUser(user.getUsername(),user.getRoles().get(0).getRole()) ;
	    }
	

 
}
