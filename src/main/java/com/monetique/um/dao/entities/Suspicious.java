package com.monetique.um.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "suspicious")
	public class Suspicious implements Serializable{
		
		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
		  private Long id;
		@Column(nullable = true)
		private String phone;
		@Column(nullable = true)
		private String nni;
		@Column(nullable = true)
		private String cif;
		
		private boolean active;
		
		
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getNni() {
			return nni;
		}
		public void setNni(String nni) {
			this.nni = nni;
		}
		public String getCif() {
			return cif;
		}
		public void setCif(String cif) {
			this.cif = cif;
		}
		
		
		
		
		
}
