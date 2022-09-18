package acm.Main.konvoy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

public class GetAddNKVD {
	private static ArrayList<String> enemies = new ArrayList<>();
	static Logger log = Bukkit.getLogger();

	//adds players name to the enemy list 
	public static void writeEnemy(String text) {
		
		try {
			FileWriter fw=new FileWriter("NKVDenemies.txt",true);
			fw.write(text+"\n");
			fw.close();
			setEnemies();	
		}catch(Exception e ) {
			log.info(e.getMessage());
		}
		
	}
	static boolean removeName(String name)  {
		try {
			//copy the NKVDenemies file without players name
			File inputFile = new File("NKVDenemies.txt");
			File tempFile = new File("NKVDenemies1.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = name;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			
			
			return  tempFile.renameTo(inputFile);
		//if args[0] does not exist 
		}catch(Exception e) {
			Log.info(e.getMessage());
			return false;
		}
	}
	//returns enemies from the file 
	public static ArrayList<String> readEnemies() throws Exception {
		FileReader fr=new FileReader("NKVDenemies.txt");
		Scanner scan = new Scanner(fr);
		ArrayList<String> list=new ArrayList<>();
		while(scan.hasNextLine()) {
			list.add(scan.nextLine());
		}
		fr.close();
		scan.close();
		return list;
	}
	//makes player an enemy 
	
	//sends title to all players
	@SuppressWarnings("deprecation")
	public static void sendTitleToAll(String title,String subtitle) {
		for(Player p:Bukkit.getServer().getOnlinePlayers()) { 
			p.sendTitle(title,subtitle);
		}
	}
	public static ArrayList<String> getEnemies() {
		
		return enemies;
		
	}
	public static void setEnemies() throws Exception {
		
		enemies = readEnemies();
		
	}
	
	public static boolean addEnemie(String name) throws Exception {
		if(getEnemies().contains(name)) {
			return true;
		}
		writeEnemy(name);
		sendTitleToAll(ChatColor.RED+"Гражданин "+name,"был признан врагом СССР");
		return false;
	}
	public static boolean makeAlly(String name) {
		try {
			if(getEnemies().contains(name)) {
				enemies.remove(name); 
				removeName(name);
				return true;
			}else {
				
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
