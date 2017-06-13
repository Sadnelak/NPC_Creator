package npc.model;

import java.util.ArrayList;
import java.util.List;

public class Aspect {
	private List<Caractere> caracteres;
	private String name;
	private String descriptif;
	private boolean selected;
	public Aspect(List<Caractere> carac, String nom, String desc){
		this.caracteres = carac;
		this.name=nom;
		this.descriptif=desc;
		this.selected = false;
	}
	public Aspect(List<Caractere> carac, String nom){
		this.caracteres = carac;
		this.name=nom;
		this.descriptif="";
		this.selected = false;
	}
	public Aspect(){
		this.caracteres = new ArrayList<Caractere>();
		this.name="";
		this.descriptif="";
		this.selected = false;
	}
	public List<Caractere> getCaractere() {
		return caracteres;
	}
	public void setCaractere(List<Caractere> caractere) {
		this.caracteres = caractere;
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
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
