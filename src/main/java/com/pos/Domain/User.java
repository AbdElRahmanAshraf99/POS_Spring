package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "POS_USER")
public class User extends BaseEntity
{
	@FieldInfo(required = true, order = 1)
	private String username;
	@FieldInfo(required = true, order = 2)
	private String password;
	@FieldInfo(order = 3)
	private String email;
}
