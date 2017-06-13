package npc.model;

public class Caractere {
	private Integer proba;
	private String name;
	private Aspect ineritedAspect;
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
	public Caractere(String[] list){
		if(list.length >= 2){
			this.proba = Integer.parseInt(list[0]);
			this.name = list[1];
		}
		
	}
	public Aspect getIneritedAspect() {
		return ineritedAspect;
	}
	public void setIneritedAspect(Aspect ineritedAspect) {
		this.ineritedAspect = ineritedAspect;
	}
}
