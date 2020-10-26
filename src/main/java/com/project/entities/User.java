package com.project.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {
	@NotBlank()
	private String name;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//@Size(min = 8,max = 12,message = "You password must be between 8-12 charecters.")
	private String password;
	@Column(length = 500)
	private String about;
	private String role;
	@Column(unique = true)
	private String email;
	private String image;
	@Column(name = "status")
	//@AssertTrue(message = "You must accept terms and conditions.")
	private boolean enable;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<Contact> contacts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", password=" + password + ", about=" + about + ", role=" + role
				+ ", email=" + email + ", image=" + image + ", enable=" + enable + ", contacts=" + contacts + "]";
	}
	
	
	
	
	
}
