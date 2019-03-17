package com.mcy.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
//禁止实体类中属性值为空报错
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements UserDetails {

    private static final long serialVersionUID = -9213875221900956604L;

    private Integer id;
    @NotBlank(message = "{user.username}")
    @Size(min = 4, max = 32, message = "{user.username.format}")
    private String username;
    @NotBlank(message = "{user.password}")
    @JsonIgnore
    private String password;
    private Boolean accountExpired;//账户是否可用
    private List<Role> roles;//账户对应角色信息
    private Date createdAt;//管理员创建时间
    private Date updateAt;//管理员资料更新时间
    private List<LoginIp> loginIps;//每次登录的ip

    public User(){}

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return true 没过期 false过期 下同
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return accountExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<LoginIp> getLoginIps() {
        return loginIps;
    }

    public void setLoginIps(List<LoginIp> loginIps) {
        this.loginIps = loginIps;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
