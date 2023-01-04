package com.pos.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "POS_USER")
public class User extends BaseEntity
{
	private String username;
	private String password;
	private String email;
}
