package me.mattix.ytplugin.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.mattix.ytplugin.GameStatus;
import me.mattix.ytplugin.Main;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(null);
		
		if (!GameStatus.isStatus(GameStatus.ASSAUTS)) createPNJ(player);
	}
	
	@EventHandler
	public void onVillagerAttack(EntityDeathEvent event) {
		
		if (!(event.getEntity() instanceof Villager)) return;
		
		Villager villager = (Villager) event.getEntity();
		Player player = (Player) event.getEntity().getKiller();
		
		me.mattix.ytplugin.Villager v = Main.getINSTANCE().villagers.get(player.getUniqueId());
		
		if (villager.getCustomName().equalsIgnoreCase(v.getVillagerID())) {
			
			for (ItemStack i : v.getInventory()) {
				Bukkit.getWorld("world").dropItemNaturally(player.getLocation(), i);
			}
			Main.getINSTANCE().villagers.remove(player.getUniqueId());
		}
	}
	
	private void createPNJ(Player player) {
		
		Villager villager = (Villager) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.VILLAGER);
		// name floating text
		villager.setCustomName(player.getName());
		villager.setCustomNameVisible(true);
		
		// inventory
		Main.getINSTANCE().villagers.put(player.getUniqueId(), new me.mattix.ytplugin.Villager(player.getName(), player));
	}
}