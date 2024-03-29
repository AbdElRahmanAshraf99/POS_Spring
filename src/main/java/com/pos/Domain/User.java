package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import com.pos.Generator.HTMLInputType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "POS_USER")
public class User extends BaseEntity implements UserDetails
{
	@FieldInfo(required = true, order = 1)
	private String username;
	@FieldInfo(required = true, order = 2, inputType = HTMLInputType.PASSWORD)
	private String password;
	@FieldInfo(order = 3)
	private String email;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
