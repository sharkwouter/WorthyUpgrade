package nl.wiselan.worthyupgrade;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantEventHandler implements Listener {
	
	/*
	 * Replace the unbreaking enchant on the enchanting table
	 */
	
	@EventHandler
	public void OnPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {
		if (e == null || e.isCancelled()) {
			return;
		}

		ItemStack item = e.getItem();
		EnchantmentOffer[] offers = e.getOffers();

		for(EnchantmentOffer offer : offers) {
			if(offer.getEnchantment().equals(Enchantment.DURABILITY) ) {
				switch(item.getType()) {
					case LEATHER_HELMET:
					case CHAINMAIL_HELMET:
					case IRON_HELMET:
					case GOLDEN_HELMET:
					case DIAMOND_HELMET:
					case TURTLE_HELMET:

					case LEATHER_LEGGINGS:
					case CHAINMAIL_LEGGINGS:
					case IRON_LEGGINGS:
					case GOLDEN_LEGGINGS:
					case DIAMOND_LEGGINGS:

					case LEATHER_BOOTS:
					case CHAINMAIL_BOOTS:
					case IRON_BOOTS:
					case GOLDEN_BOOTS:
					case DIAMOND_BOOTS:

					case LEATHER_CHESTPLATE:
					case CHAINMAIL_CHESTPLATE:
					case IRON_CHESTPLATE:
					case GOLDEN_CHESTPLATE:
					case DIAMOND_CHESTPLATE:
					case ELYTRA:
						offer.setEnchantment(Enchantment.PROTECTION_FIRE);
						break;

					case WOODEN_SWORD:
					case STONE_SWORD:
					case IRON_SWORD:
					case GOLDEN_SWORD:
					case DIAMOND_SWORD:
						offer.setEnchantment(Enchantment.DAMAGE_UNDEAD);
						break;

					case WOODEN_PICKAXE:
					case STONE_PICKAXE:
					case IRON_PICKAXE:
					case GOLDEN_PICKAXE:
					case DIAMOND_PICKAXE:

					case WOODEN_SHOVEL:
					case STONE_SHOVEL:
					case IRON_SHOVEL:
					case GOLDEN_SHOVEL:
					case DIAMOND_SHOVEL:

					case WOODEN_AXE:
					case STONE_AXE:
					case IRON_AXE:
					case GOLDEN_AXE:
					case DIAMOND_AXE:
						offer.setEnchantment(Enchantment.DIG_SPEED);
						break;

					case BOW:
						offer.setEnchantment(Enchantment.ARROW_KNOCKBACK);
						break;

					case FISHING_ROD:
						offer.setEnchantment(Enchantment.LURE);
						break;

					case TRIDENT:
						offer.setEnchantment(Enchantment.LOYALTY);
						break;

					//case CROSSBOW:
					//	offer.setEnchantment(Enchantment.QUICK_CHARGE);
					//	break;

					default:

				}
			}
		}
		
	}
	
}
