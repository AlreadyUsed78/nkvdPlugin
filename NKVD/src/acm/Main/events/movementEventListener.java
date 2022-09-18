package acm.Main.events;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import acm.Main.konvoy.GetAddNKVD;


public class movementEventListener implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e) throws Exception {
		
		
		ArrayList<String> enemies = GetAddNKVD.getEnemies();
		Player p = e.getPlayer();
		if(enemies.contains(p.getDisplayName())) {
		
			nothing();
			
		}else {
			
			IsEscaping(p);
			
		}
		
		
	}
	
	public static void IsEscaping(Player p) throws Exception {
			if(p.getLocation().getBlockY()>150 && !GetAddNKVD.getEnemies().contains(p.getDisplayName())) {
					GetAddNKVD.addEnemie(p.getDisplayName());
				
					GetAddNKVD.sendTitleToAll(ChatColor.RED+"Гражданин "+p.getName(),"пытается покинуть СССР");
					GetAddNKVD.setEnemies();
			}
	}

	public char nothing() {
		return ' ';
	}
	
}
