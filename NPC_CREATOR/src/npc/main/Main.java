package npc.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
	
	public static void main(String [] arg0){
		JFrame fenetre = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 1));

		JPanel panel = new JPanel();
		List<Personna> personalite=processFile("C:/Users/Felicien/workspace/generateurPersonaliteV1/test/","test.txt");
		panel.setLayout(new GridLayout(personalite.size(), 1));
		
		for(Personna partPersonna : personalite){
			JPanel panelPers = new JPanel();
			panelPers.setLayout(new GridLayout(partPersonna.getListAspects().size()/2+1, 2));
			JLabel label = new JLabel(partPersonna.getName());
			panel.add(label);
			for(Aspect asp : partPersonna.getListAspects()){
				JComboBox<String> combo = new JComboBox<String>();
				JLabel labelAspect = new JLabel(asp.getName());
				for(Caractere carac : asp.getCaractere()){
					combo.addItem(carac.getName());
				}
				panelPers.add(labelAspect);
				panelPers.add(combo);
			}
			

//		    panel.add(adresseFichier);
//		    panel.add(motsTries);
//			JComboBox petList = new JComboBox();
//	        Dimension preferredSize = new Dimension(400,25);
//	        Dimension preferredSizeMotTrie = new Dimension(600,600);
//	        adresseFichier.setPreferredSize(preferredSize);
//	        motsTries.setPreferredSize(preferredSizeMotTrie);
		    panel.add(panelPers);
		    
		}
		JButton bouton = new JButton("BOUTON !!!!");
		mainPanel.add(panel);
		mainPanel.add(bouton);
		fenetre.add(mainPanel);
		fenetre.setSize(840,780);
		fenetre.setVisible(true);
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
