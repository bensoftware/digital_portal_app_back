package com.monetique.dto;

import java.util.List;
import java.util.Map;

public class RequestReleveDto {

	List<TransactionMc> monetiqueClasses ;
	List<TransactionMc> monetiqueB ;
	Map<String, Object> map;
	Map<String, Object> mapT;
	
	public List<TransactionMc> getMonetiqueClasses() {
		return monetiqueClasses;
	}
	public void setMonetiqueClasses(List<TransactionMc> monetiqueClasses) {
		this.monetiqueClasses = monetiqueClasses;
	}
	public List<TransactionMc> getMonetiqueB() {
		return monetiqueB;
	}
	public void setMonetiqueB(List<TransactionMc> monetiqueB) {
		this.monetiqueB = monetiqueB;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Map<String, Object> getMapT() {
		return mapT;
	}
	public void setMapT(Map<String, Object> mapT) {
		this.mapT = mapT;
	}
	public RequestReleveDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestReleveDto(List<TransactionMc> monetiqueClasses, List<TransactionMc> monetiqueB,
			Map<String, Object> map, Map<String, Object> mapT) {
		super();
		this.monetiqueClasses = monetiqueClasses;
		this.monetiqueB = monetiqueB;
		this.map = map;
		this.mapT = mapT;
	}
	
	
	
	
}
