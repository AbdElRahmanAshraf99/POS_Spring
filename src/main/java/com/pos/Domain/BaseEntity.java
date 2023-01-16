package com.pos.Domain;

import com.pos.Generator.FieldInfo;
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
	@FieldInfo(system = true)
	private UUID id;
	@Column(unique = true)
	@FieldInfo(order = 0)
	private String code;
	@Value("${my.date:#{T(java.time.LocalDateTime).now()}}")
	@FieldInfo(system = true)
	private LocalDateTime creationDate;
}
