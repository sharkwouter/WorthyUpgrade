package nl.wiselan.worthyupgrade;

import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class EnchantEventHandler implements Listener {
	
	@EventHandler
	public void OnPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {
		if (e == null || e.isCancelled()) {
			return;
		}
		
		Player player = e.getEnchanter();
		EnchantmentOffer[] offers = e.getOffers();
		
		for(EnchantmentOffer offer : offers) {
			player.sendMessage(offer.getEnchantment().toString());
		}
		
	}
	
}
