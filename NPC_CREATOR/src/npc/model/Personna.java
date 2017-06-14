package npc.model;

import java.util.ArrayList;
import java.util.List;

public class Personna {
	private String name;
	private String descriptif;
	private List<Aspect> listAspects;
	
	public Personna(){
		name="";
		descriptif="";
		listAspects = new ArrayList<>();
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public List<Aspect> getListAspects() {
		return listAspects;
	}
	public void setListAspects(List<Aspect> listAspects) {
		this.listAspects = listAspects;
	}
	
}
