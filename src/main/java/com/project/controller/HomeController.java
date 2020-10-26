package com.project.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Dao.Reporsitory;
import com.project.entities.User;
import com.project.helper.Message;


@Controller
public class HomeController {
	@Autowired
	private Reporsitory userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/test")
	@ResponseBody
	public String Test() {
		return "Its working";
		
	}
	@GetMapping("/home")
	public String home(Model m) {
		m.addAttribute("Title", "Home");
		return "home";
	}
	@GetMapping("/signup")
	public String SignUp(Model m) {
		m.addAttribute("Title", "Register");
		m.addAttribute("user",new User());
		return "SignUp";
	}
	
	//handeler for registering new user
	@PostMapping("/register")
	public String Register(@Valid @ModelAttribute("user")User user,BindingResult result,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model m,
			HttpSession session) {
		
		try {
			if(result.hasErrors()) {
				m.addAttribute("user",user);
				return "SignUp";
			}
			
			if(!agreement) {
				System.out.println("Not checked");
				throw new Exception("You didnot accept terms and conditions");
				
			}
			//default values
			user.setRole("ROLE_USER");
			user.setEnable(true);
			user.setImage("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User result1 = this.userRepository.save(user);
			System.out.println(result1);
			session.setAttribute("message",new Message("Successfully registered !!","alert-success"));
			m.addAttribute("user", new User());
			return "SignUp";
			
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("user",user);
			session.setAttribute("message", new Message(e.getMessage()+",Please try again.","alert-danger"));
	
			return "SignUp";
		}
		
		
	}
	
	@RequestMapping("/login")
	public String login() {
		return "Login";
	}

}
