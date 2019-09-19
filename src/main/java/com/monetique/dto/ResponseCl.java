package com.monetique.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ResponseCl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<ItemInfo> infrmationClearing;
	
	List<ItemInfo> informationAnomalie;
	
	Set<String> etats;
	Set<String> etatFinals;
	
	List<ClearingDto> list;
	
	List<ClearingDto> listFInal;

	
	
	
	public Set<String> getEtatFinals() {
		return etatFinals;
	}

	public void setEtatFinals(Set<String> etatFinals) {
		this.etatFinals = etatFinals;
	}

	public List<ClearingDto> getListFInal() {
		return listFInal;
	}

	public void setListFInal(List<ClearingDto> listFInal) {
		this.listFInal = listFInal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ItemInfo> getInfrmationClearing() {
		return infrmationClearing;
	}

	public void setInfrmationClearing(List<ItemInfo> infrmationClearing) {
		this.infrmationClearing = infrmationClearing;
	}

	public List<ItemInfo> getInformationAnomalie() {
		return informationAnomalie;
	}

	public void setInformationAnomalie(List<ItemInfo> informationAnomalie) {
		this.informationAnomalie = informationAnomalie;
	}

	public Set<String> getEtats() {
		return etats;
	}

	public void setEtats(Set<String> etats) {
		this.etats = etats;
	}

	public List<ClearingDto> getList() {
		return list;
	}

	public void setList(List<ClearingDto> list) {
		this.list = list;
	}

	public ResponseCl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseCl(List<ItemInfo> infrmationClearing, List<ItemInfo> informationAnomalie, Set<String> etats,
			List<ClearingDto> list) {
		super();
		this.infrmationClearing = infrmationClearing;
		this.informationAnomalie = informationAnomalie;
		this.etats = etats;
		this.list = list;
	}

	

	

}
