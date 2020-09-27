package com.db2020.pj.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Table(name = "member" ,schema = "test")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, unique = true, length = 30)
    private String user_id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100)
    private String user_password;
    @Column(nullable = false, length = 100)
    private String user_name;
    @Column(nullable = false, length = 30)
    private String user_tel;
    @Column(nullable = false, length = 30)
    private int user_postNum;
    @Column(nullable = false, length = 100)
    private String user_address;
    @Column(nullable = false, length = 100)
    private String user_detail_address;
   
//    @NotBlank
//    private String email;
//    @NotNull
//    private String address;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="social_id")
//    private SocialData social;

    // 계정이 가지고 있는 권한 목록
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
        return this.user_id;
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

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}