package npc.model;

public class Caractere {
	private Integer proba;
	private String name;
	public Integer getProba() {
		return proba;
	}
	public void setProba(Integer proba) {
		this.proba = proba;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Caractere(Integer probaP, String nameP){
		this.proba = probaP;
		this.name = nameP;
	}

}
