package nl.wiselan.worthyupgrade;

import java.util.ListIterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventHandler implements Listener {
	
	/*
	 * Cancel damage events for items
	 * This makes sure no item is ever damaged
	 */
	@EventHandler
	public void OnPlayerItemDamageEvent(PlayerItemDamageEvent e) {
		e.setCancelled(true);
	}
	
	/*
	 * Only drop the items found in item slots higher than 9
	 * This will allow players to keep what is on their hotbar
	 */
	
	@EventHandler
	public void OnPlayerDeathEvent(PlayerDeathEvent e) {
		Player player = e.getEntity();
		e.setKeepInventory(true);
		ListIterator<ItemStack> inventory = player.getInventory().iterator();
		
		int slot = 0;
		while (inventory.hasNext()) {
			ItemStack item = inventory.next();
			if (slot < 9 || item == null) {
				slot++;
				continue;
			}
			player.getInventory().remove(item);
			player.getWorld().dropItem(player.getLocation(), item);
			
			slot++;
		}
	}
}
