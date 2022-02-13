package acm.Main.maincommands;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
public class nkvdCommand extends abstractCommand{
	Logger log = Bukkit.getLogger();
	GetAddNKVD nkvd=new GetAddNKVD();
	public nkvdCommand() {
		super("nkvd");
	}
	
	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		try {
			if(sender.isOp()) {
				try {
					if(!Boolean.parseBoolean(args[1])) {
						if(!nkvd.makeAlly(args[0])) {
							sender.sendMessage("Гражданина не существует");
						}else {
							sender.sendMessage("Гражданин больше не является врагом госудраства");
						}
					}else {
						nkvd.makeEnemy(args[0]);
					}
				}catch(Exception e) {
					nkvd.makeEnemy(args[0]);
				}
			}else {
				nkvd.makeEnemy(sender.getName());
			}
		}catch(Exception e) {
			
		}
		try {
			for(String st:nkvd.getEnemies()) {
				sender.sendMessage(st);
			}
		}catch(Exception e) {
			
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}
}
