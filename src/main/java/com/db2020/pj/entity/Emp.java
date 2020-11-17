package com.db2020.pj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class Emp implements UserDetails {

    private int emp_seq;
    private String emp_email;
    private String emp_pw;
    private String emp_nm;
    private String emp_account;
    private String emp_joinDate;
    private String emp_address;
    private String emp_tel;
    private String emp_phone;
    private String emp_createAt;
    private String emp_updateAt;
    private String emp_isUsed;
    private String emp_vacationDate;
    private String emp_role;
    private String company_nm;
    private int dept_seq;
    private int company_seq;


    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();

        for (String role : emp_role.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
//      return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    /*
     * Spring Security 부분
     * @author : Wishoon
     * UserDetails 인터페이스를 오버라이드 하였음. (true는 검사하지 않는다.)
     */

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {

        return this.emp_email;
    }

    // 해당 필드는 오직 쓰려는 경우(역직렬화)에만 접근이 허용된다.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getEmp_email() {
        return this.emp_email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getCompnay_seq() {
        return this.company_seq;
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

}
