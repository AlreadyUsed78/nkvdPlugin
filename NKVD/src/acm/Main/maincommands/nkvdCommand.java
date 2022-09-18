package acm.Main.maincommands;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import acm.Main.konvoy.*;

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
					//checks if arguement true or false
					if(!Boolean.parseBoolean(args[1])) {
						//if player does not exist
						if(!GetAddNKVD.makeAlly(args[0])) {
							sender.sendMessage("Гражданина не существует");
						}else {
							//if player exist and is not in enemy list anymore 
							sender.sendMessage("Гражданин больше не является врагом госудраства");
						}
					}else {
						//if args[0]=="true" or another text
						
						throw new ArithmeticException();
						
					}
				}catch(Exception e) {
					//if args[0] does not exist 
					GetAddNKVD.addEnemie(args[0]);
				}
			}else {
				//if sender is not op make him an enemy
				GetAddNKVD.addEnemie(sender.getName());
			}
		
	}catch(Exception e) {
					
		
		try {
			//send list of enemies 
			for(String st:GetAddNKVD.getEnemies()) {
				sender.sendMessage(st);
			}
		}catch(Exception a) {
			
		}
}
			
}


	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}
}
