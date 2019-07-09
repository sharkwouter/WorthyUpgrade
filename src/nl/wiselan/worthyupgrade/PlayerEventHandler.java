package nl.wiselan.worthyupgrade;

import java.util.ListIterator;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;


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
	 * Slots after 35 are armor and the offhand, we ignore those as well
	 */
	
	@EventHandler
	public void OnPlayerDeathEvent(PlayerDeathEvent e) {
		Player player = e.getEntity();
		e.setKeepInventory(true);
		ListIterator<ItemStack> inventory = player.getInventory().iterator();
		
		int slot = 0;
		while (inventory.hasNext() && slot < 36) {
			ItemStack item = inventory.next();

			//Ignore the hotbar and empty slots
			if (slot < 9 || item == null) {
				slot++;
				continue;
			}
			player.getInventory().remove(item);
			player.getWorld().dropItem(player.getLocation(), item);
			
			slot++;
		}
	}

	/*
	 * Set the damage to an item to 0 when it is picked up by a player
	 */

	@EventHandler
	public void OnPlayerPickupEvent(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Item item = e.getItem();
			ItemStack itemstack = item.getItemStack();

			if(itemstack.hasItemMeta()) {
				if (itemstack.getItemMeta() instanceof Damageable) {
					Damageable meta = (Damageable) itemstack.getItemMeta();
					meta.setDamage(0);
					itemstack.setItemMeta((ItemMeta) meta);
				}
			}
		}
	}
}
