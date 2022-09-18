package acm.Main.konvoy;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class sendNKVD {
	Logger log = Bukkit.getLogger();
	GetAddNKVD nkvd=new GetAddNKVD();
	@SuppressWarnings("deprecation")
	//sends nkvd to all players in enemy list
	public void NKVD()  {
		try {
			for(String name : GetAddNKVD.getEnemies()) {
				try {
					log.info(name);
					Player p1 = Bukkit.getPlayer(name);
					LivingEntity zombie = (LivingEntity) p1.getWorld().spawnEntity(p1.getLocation().add(p1.getLocation().getDirection().multiply(-2)), EntityType.ZOMBIE);
					zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD, 1));
					zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
					zombie.setCustomName(ChatColor.GREEN+"воин добра(НКВД)");
				}catch(Exception e) {
					
				}
			}
		}catch(Exception e) {
			
		}
	}
	//checks if player tries to escape from USSR 
		
	public BukkitRunnable runnable = new BukkitRunnable() {
		@Override
		public void run() {
			try {
				NKVD();
				log.info("sending NKVD");
			}catch(Exception e) {
				
			}
		}
	};
}
