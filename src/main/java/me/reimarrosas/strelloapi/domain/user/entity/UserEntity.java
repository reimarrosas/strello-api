package me.reimarrosas.strelloapi.domain.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.reimarrosas.strelloapi.domain.base.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", indexes = @Index(columnList = "email", unique = true))
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
