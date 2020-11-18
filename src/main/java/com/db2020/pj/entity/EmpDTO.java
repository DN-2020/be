package com.db2020.pj.entity;

import com.amazonaws.services.identitymanagement.model.UserDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {

    private int emp_seq;
    private String emp_email;
    private String emp_pw;
    private String emp_nm;
    private String emp_joinDate;
    private String emp_isUsed;
    private String emp_role;
    private int company_seq;
    private String company_nm;
    private int dept_seq;
    private String accessToken;


}
