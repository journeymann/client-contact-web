package com.hlpp.clientcontact.security;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import java.util.Set;
import java.util.Collection;

public class SecureUserDetails implements UserDetails {

	private Integer userid;
	private String username;
	private String password;
	private boolean enabled;
	private String fullname;
	private String department;
	private String email;

	private Collection<GrantedAuthority> authorities;

	public Integer getUserid(){
		return this.userid;
	}

	public void setUserid(Integer userid){
		this.userid = userid;
	}

	public SecureUserDetails(){
		super();
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public boolean isEnabled(){
		return this.enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired(){
		return true;
	}

	public boolean isAccountNonLocked(){
		return true;
	}

	public boolean isCredentialsNonExpired(){
		return true;
	}

	@Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> list) {
        this.authorities = list;
    }

	/*@Override
	public java.util.Collection<GrantedAuthority> getAuthorities() {

		//TODO properly

		ArrayList list = new ArrayList<GrantedAuthority>();

		list.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		list.add(new GrantedAuthorityImpl("ROLE_USER"));

		return list;
	}*/

	public String getFullname(){
		return this.fullname;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getDepartment(){
		return this.department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}





}
