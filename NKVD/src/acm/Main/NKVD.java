package acm.Main;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import acm.Main.maincommands.*;
import acm.Main.maincommands.sendNKVD;

import java.util.*;

public class NKVD extends JavaPlugin{
	Logger log = getLogger();
	private static NKVD instance;
	@Override
	public void onEnable() {
		instance=this;
		new nkvdCommand();
		sendNKVD send = new sendNKVD();
		send.runnable.runTaskTimer(this, 1L, 600);
	}
	public static NKVD getInstance() {
		return instance;
	}
	public List<String> complete(BlockCommandSender sender,String[] args){
		List<String> list = new ArrayList<>();
		for(Player p : getServer().getOnlinePlayers()) {
			if(p.getName().contains(args[0])) {
				list.add(p.getName());
			}
		}
		return list;
	}
}