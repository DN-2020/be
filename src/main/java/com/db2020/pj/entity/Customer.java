package com.db2020.pj.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class Customer implements UserDetails{

   
    private long customer_seq;
    private String customer_email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customer_pw;
    private String customer_nm;
    private String customer_tel;
    private int customer_post;
    private String customer_address;
    private String customer_detail_address;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customer_role;
    
//    private List<String> roles = new ArrayList<>();
    
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	Set<GrantedAuthority> roles = new HashSet<>();
    	
    	for (String role : customer_role.split(",")) {
    	      roles.add(new SimpleGrantedAuthority(role));
    	}
    	return roles;
//      return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

	/*
     * Spring Security 부분
     * @author : Wishoon
     * UserDetails 인터페이스를 오버라이드 하였음. (true는 검사하지 않는다.)
     */
    

	// 해당 필드는 오직 쓰려는 경우(역직렬화)에만 접근이 허용된다.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.customer_email;
    }
   

    // 계정이 만료되지 않았는지를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않을지를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 패스워드가 만료되지 않았는지를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    // 계정이 사용가능한 계정인지를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Customer [customer_seq=" + customer_seq + ", customer_email=" + customer_email + ", customer_pw="
				+ customer_pw + ", customer_nm=" + customer_nm + ", customer_tel=" + customer_tel + ", customer_post="
				+ customer_post + ", customer_address=" + customer_address + ", customer_detail_address="
				+ customer_detail_address + ", customer_role=" + customer_role + "]";
	}

    
    
}