package com.project.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.Dao.Reporsitory;
import com.project.entities.Contact;
import com.project.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Reporsitory userReprository;
	//this method contains common data for all the handeler under UserController class
	@ModelAttribute
	public void commonData(Model m,Principal principal) {
		
		String name = principal.getName();
		System.out.println(name);
		User user = userReprository.getUserByUserName(name);
		m.addAttribute("user",user);
	}
	
	
	@RequestMapping("/dashboard")
	public String dashBoard(Model m,Principal principal) {
		m.addAttribute("Title","Dashboard");
	
		return "normal/dashBoard";
	}
	@RequestMapping("/add_contact")
	public String addContact(Model m){
		
		m.addAttribute("Title","Add Contact");
		m.addAttribute("contact",new Contact());
		return "normal/addContact";
	}
	
	//Processing the form of AddContact
	@PostMapping("/add-process")
	public String add_process(@Valid@ModelAttribute("contact")Contact contact,BindingResult result,Model m
			,Principal principal){
		
		String name = principal.getName();
		m.addAttribute(contact);
		User user = this.userReprository.getUserByUserName(name);
		
		contact.setUser(user);
		//to set contact in user 
		user.getContacts().add(contact);
		this.userReprository.save(user);
		if(result.hasErrors()) {
			return "addConatact";
		}
		
		System.out.println(contact.getName());
		
		return "normal/addContact";
	}
			
	
}
