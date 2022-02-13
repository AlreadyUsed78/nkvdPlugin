package acm.Main.maincommands;
import javax.annotation.*;
import acm.Main.NKVD;
import org.bukkit.command.*;

import java.util.*;
;
public abstract class abstractCommand implements CommandExecutor,TabCompleter {
	public abstractCommand(String command) {
			PluginCommand pluginCommand=NKVD.getInstance().getCommand(command);
			pluginCommand.setExecutor(this);
			pluginCommand.setTabCompleter(this);
		}
	public abstract void execute(CommandSender sender,String label ,String[] args);
	@Override 
	public boolean onCommand(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String label,@Nonnull String[] args) {
			execute(sender,label,args);
			return true;
	}
	public List<String> complete(CommandSender sender,String[] args){
		return null;
	}
	@Override 
	public @Nullable List<String> onTabComplete(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String alias,@Nonnull String[] args){
		return filter(complete(sender,args),args);
	}
	private List<String> filter(List<String> list,String[] args){
		if(list==null) return null;
		String last = args[args.length - 1];
		List<String> result = new ArrayList<>();
		for(String arg : list) {
			if(arg.toLowerCase().startsWith(last.toLowerCase()))  result.add(arg);
		} 
		return result;
	}
}
