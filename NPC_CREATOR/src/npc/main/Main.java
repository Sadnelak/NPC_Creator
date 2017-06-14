package npc.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import npc.importData.ImportFile;
import npc.model.Aspect;
import npc.model.Caractere;
import npc.model.Personna;
import npc.utils.Constantes;

public class Main {
	
//	 static JTextField adresseFichier = new JTextField();
//	 static JTextArea motsTries = new JTextArea();
	static List<Personna> personalite;
	public static void main(String [] arg0){
		JFrame fenetre = new JFrame();
		JPanel mainPanel = new JPanel();
		LayoutManager lay =new GridLayout(1,2);
		mainPanel.setLayout(lay);
		
		JPanel panel = new JPanel();
		personalite=processFile("C:/Users/Felicien/workspace/generateurPersonaliteV1/test/","test.txt");
		panel.setLayout(new GridLayout(personalite.size(), 1));
		setContent(panel);
		JButton bouton = new JButton("BOUTON !!!!");
		bouton.setSize(20, 60);
		bouton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e)
		  {
			  launchProba();
			  selectAllSorted(panel);
		  }

		

		
		});
		mainPanel.add(panel);
		mainPanel.add(bouton);
		fenetre.add(mainPanel);
		fenetre.setSize(840,780);
		fenetre.setVisible(true);
	}

	public static void selectAllSorted(JPanel panel) {
		int nbComp=panel.getComponentCount();
		//Panel
		for(int i=0; i< nbComp;i++){
			Component comp = panel.getComponent(i);
			//panelPers
			if(comp.getClass() == JPanel.class){
				JPanel subPanel = (JPanel) comp;
				compIsJComboBoxProcess(i, panel);			
				int nbSubComp=subPanel.getComponentCount();
				for(int y=0; y< nbSubComp;y++){
					Component subComp = subPanel.getComponent(y);
					compIsJComboBoxProcess(y, subPanel);


					if(subComp.getClass() == JPanel.class){
						JPanel sub2Panel = (JPanel) subComp;
						int nbSub2Comp=subPanel.getComponentCount();
						for(int z=0; z< nbSub2Comp;z++){
							compIsJComboBoxProcess(z, sub2Panel);
						}
							
					}
					
					
					
				}
					
			}
		}
	}

	private static void compIsJComboBoxProcess(int i, JPanel Panel) {
		Component finalComp = Panel.getComponent(i);
		if(finalComp.getClass() == JComboBox.class){
			@SuppressWarnings("unchecked")
			JComboBox<String> combo =  (JComboBox<String>) finalComp;
			JLabel lab = (JLabel) Panel.getComponent(i-1);
			combo.setSelectedItem(getSelectedCaractere(lab.getText()));
		}
	}
	public static String getSelectedCaractere(String name) {

		for(Personna partPersonna : personalite){
			for(Aspect asp : partPersonna.getListAspects()){
				if(asp.getName().equals(name)){
					for(Caractere carac : asp.getCaractere()){
						if(carac.isSelected()){
							return carac.getName();
						}
					}
					
				}
			}
		}
		return "";
	}

	private static void setContent(JPanel panel) {
		for(Personna partPersonna : personalite){
			JPanel panelPers = new JPanel();
			panelPers.setLayout(new GridLayout(partPersonna.getListAspects().size()/2+1, 2));
			JLabel label = new JLabel(partPersonna.getName());
			panel.add(label);
			for(Aspect asp : partPersonna.getListAspects()){
				JComboBox<String> combo = new JComboBox<String>();
				JLabel labelAspect = new JLabel(asp.getName());
				combo.addItem("");
				combo.setSelectedIndex(0);
				for(Caractere carac : asp.getCaractere()){
					combo.addItem(carac.getName());
					if(carac.isSelected()){
						combo.setSelectedItem(carac.getName());
					}
				}

				panelPers.add(labelAspect);
				panelPers.add(combo);
			}
			panel.add(panelPers);
		    
		}
	}
	
	
	public static void launchProba(){
		
		for(Personna pers : personalite){
			for(Aspect asp : pers.getListAspects()){
				if(!asp.isSelected()){
					int randomMax =0;
					for(Caractere carac : asp.getCaractere()){
						randomMax+=carac.getProba().intValue();
					}
					
				    Random rn = new Random();
				    int nbRandom = rn.nextInt(randomMax+1);
				    int verif = 0;
					boolean verifie = false;
					for(Caractere carac : asp.getCaractere()){
						verif+=carac.getProba().intValue();
						if(!verifie && verif >= nbRandom){
							carac.setSelected(true);
							verifie = true;
						}else{
							carac.setSelected(false);
						}
					}
				}
			}
		}
	}
	
	
	
	
	public static List<Personna> processFile(String path,String fileName){
		
		
		String totalFile = ImportFile.importStream(path, fileName);
		
		String[] mainAspects = totalFile.split(Constantes.MAIN_ASPECT);
		List<Personna> personalite = new ArrayList<Personna>();
		
		for(String mainAspect: mainAspects){
			setPersonnalite(personalite, mainAspect);
		}
		return personalite;
	}

	
	
	
	
	
	private static void setPersonnalite(List<Personna> personalite, String mainAspect) {
		if(mainAspect != null && !mainAspect.equals("")){
			String[] aspects = mainAspect.split(Constantes.ASPECT);
			Personna partPersonna = new Personna();
			for(int i = 0; i< aspects.length;i++){
				String [] list = aspects[i].split(Constantes.SEPARATOR);
				if(i==0){
					if(list != null && list.length>=2){
						partPersonna.setName(list[1].trim());
					}
					if(list != null && list.length>=3){
						partPersonna.setDescriptif(list[2]);
					}
				}else{
					setAspect(partPersonna, aspects[i]);
				}
			}
			personalite.add(partPersonna);
		}
	}
	private static void setAspect(Personna partPersonna, String aspect) {
		Aspect newAspect = new Aspect();
		String [] lignes = aspect.split(System.lineSeparator());				
		for(String sub : lignes){
			if (sub.startsWith(Constantes.ASPECT) || sub.startsWith(Constantes.SEPARATOR)){
				String [] list = sub.split(Constantes.SEPARATOR);
				if(list != null && list.length>=2){
					newAspect.setName(list[1]);
				}
				if(list != null && list.length>=3){
					newAspect.setDescriptif(list[2]);
				}
				
			}else{
				newAspect.getCaractere().add( new Caractere(sub.split(Constantes.SEPARATOR)));
			}
			
		}
		partPersonna.getListAspects().add(newAspect);
	}
	
}
