package com.springbatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonRootName("Customer")
@Entity
@Table(name = "customer")
public class CustomerDTO {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	public String name;
	public String cpf;
	public String phone;
	public String address;
	public CustomerDTO(String name, String cpf, String phone, String address) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.address = address;
	}
	
	
}
