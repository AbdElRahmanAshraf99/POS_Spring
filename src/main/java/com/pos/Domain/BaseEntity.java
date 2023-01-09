package com.pos.Domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(unique = true)
	private String code;
	@Value("${my.date:#{T(java.time.LocalDateTime).now()}}")
	private LocalDateTime creationDate;
}
