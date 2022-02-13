package acm.Main.maincommands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetAddNKVD {
	public void addEnemy(String text) throws Exception {
		FileWriter fw=new FileWriter("NKVDenemies.txt",true);
		if(!getEnemies().contains(text)) {
				fw.write(text+"\n");
				fw.close();
		}
	}
	boolean makeAlly(String name)  {
		try {
			if(!getEnemies().contains(name)) {
				return true;
			}else {
				File inputFile = new File("NKVDenemies.txt");
				File tempFile = new File("NKVDenemies1.txt");

				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

				String lineToRemove = name;
				String currentLine;

				while((currentLine = reader.readLine()) != null) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
				    if(trimmedLine.equals(lineToRemove)) continue;
				    writer.write(currentLine + System.getProperty("line.separator"));
				}
				writer.close(); 
				reader.close(); 
				return  tempFile.renameTo(inputFile);
			}
		}catch(Exception e) {
			return false;
		}
	}
	public List<String> getEnemies() throws Exception {
		FileReader fr=new FileReader("NKVDenemies.txt");
		Scanner scan = new Scanner(fr);
		List<String> list=new ArrayList<>();
		while(scan.hasNextLine()) {
			list.add(scan.nextLine());
		}
		fr.close();
		scan.close();
		return list;
	}
	void makeEnemy(String name) {
		try {
			Player p=Bukkit.getPlayer(name);
			p.setDisplayName(ChatColor.RED+name);
			addEnemy(name);
			sendTitleToAll(ChatColor.RED+"Гражданин "+p.getName(),"был признан врагом СССР");
		}catch(Exception e) {
			
		}
	}
	@SuppressWarnings("deprecation")
	void sendTitleToAll(String g,String a) {
		for(Player p:Bukkit.getServer().getOnlinePlayers()) { 
			p.sendTitle(g,a);
		}
	}
}
