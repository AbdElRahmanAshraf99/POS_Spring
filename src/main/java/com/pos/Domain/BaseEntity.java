package com.pos.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String code;
	private LocalDateTime creationDate;
}
