package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<ItemInfo> infrmationClearing;

	public List<ItemInfo> getInfrmationClearing() {
		return infrmationClearing;
	}

	public void setInfrmationClearing(List<ItemInfo> infrmationClearing) {
		this.infrmationClearing = infrmationClearing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

}
