package nl.wiselan.worthyupgrade;

import java.util.ListIterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
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
	 * Use the fly button for healing
	 */
	
	@EventHandler
	public void OnPlayerToggleFlightEvent(PlayerToggleFlightEvent e) {
		e.setCancelled(true);
		Player player = e.getPlayer();
		if (player.getHealth() < 10.0) {
			player.setHealth(player.getHealth() + 10.0);
		} else {
			player.setHealth(20.0);
		}
	}
	
	/*
	 * Don't drop everything when the player dies
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
