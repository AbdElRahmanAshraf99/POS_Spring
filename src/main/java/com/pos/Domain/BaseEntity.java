package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@FieldInfo(system = true)
	private Long id;
	@Column(unique = true)
	@FieldInfo(order = 0)
	private String code;
	@CreationTimestamp
	@FieldInfo(system = true)
	private LocalDateTime creationDate;
}
