package com.pos.Domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseLine
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private UUID parentId;
}
