package me.mattix.ytplugin.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mattix.ytplugin.Main;
import me.mattix.ytplugin.utils.TeamsTagsManager;

public class TeamsMenu implements Listener {

	public String invName = "Séléction d'une équipe";
	
	private Inventory inventory = Bukkit.createInventory(null, 9 * 1, invName);
	
	public TeamsMenu() {
		inventory.setItem(0, getTeamItem("§cRouge", Main.getINSTANCE().red_team, (byte) 1, ChatColor.RED));
		inventory.setItem(1, getTeamItem("§9Bleue", Main.getINSTANCE().blue_team, (byte) 4, ChatColor.BLUE));
		inventory.setItem(2, getTeamItem("§aVert", Main.getINSTANCE().green_team, (byte) 2, ChatColor.GREEN));
		inventory.setItem(3, getTeamItem("§eJaune", Main.getINSTANCE().yellow_team, (byte) 11, ChatColor.YELLOW));
		inventory.setItem(4, getTeamItem("§6Orange", Main.getINSTANCE().orange_team, (byte) 14, ChatColor.GOLD ));
	}
	
	public ItemStack getTeamItem(String teamName, List<UUID> team, byte data, ChatColor color) {
		
		ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, data);
		ItemMeta itemmeta = itemStack.getItemMeta();
		itemmeta.setDisplayName(color + teamName);
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add("§7Joueur(s) dans l'équipe:");
		
		if (team.size() > 0) {
			for (UUID members : team) {
				lore.add(color + "- " + Bukkit.getPlayer(members).getName());
			}
		} else {
			lore.add("§cVide.");
		}
		lore.add("");
		lore.add("§e-> Rejoindre cette équipe.");
		
		itemmeta.setLore(lore);
		itemStack.setItemMeta(itemmeta);
		
		return itemStack;
		
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
	}
	
	@EventHandler
	public void onPlayerClick(InventoryClickEvent event) {
		
		if (event.getCurrentItem() == null && event.getAction() != null) return;
		
		Player player = (Player) event.getWhoClicked();
		
		if (event.getInventory().getName().equalsIgnoreCase(invName)) {
			event.setCancelled(true);
			
			switch (event.getCurrentItem().getType()) {
			case INK_SACK:
			
				switch (event.getSlot()) {
				case 0:
					if (!Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
						if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) Main.getINSTANCE().blue_team.remove(player.getUniqueId());
							if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) Main.getINSTANCE().green_team.remove(player.getUniqueId());
								if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) Main.getINSTANCE().yellow_team.remove(player.getUniqueId());
									if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) Main.getINSTANCE().orange_team.remove(player.getUniqueId());
						Main.getINSTANCE().red_team.add(player.getUniqueId());
						TeamsTagsManager.setNameTag(player, "§c§1Admin", "§c", "");
						
					} else {
						player.sendMessage("§cVous êtes déjà dans une équipe.");
					}
					
					break;

				case 1:
					if (!Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
						if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) Main.getINSTANCE().red_team.remove(player.getUniqueId());
							if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) Main.getINSTANCE().green_team.remove(player.getUniqueId());
								if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) Main.getINSTANCE().yellow_team.remove(player.getUniqueId());
									if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) Main.getINSTANCE().orange_team.remove(player.getUniqueId());
						Main.getINSTANCE().blue_team.add(player.getUniqueId());
						TeamsTagsManager.setNameTag(player, "§c§2Admin", "§9", "");
					} else {
						player.sendMessage("§cVous êtes déjà dans une équipe.");
					}
					break;
					
				case 2:
					if (!Main.getINSTANCE().green_team.contains(player.getUniqueId())) {
						if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) Main.getINSTANCE().red_team.remove(player.getUniqueId());
							if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) Main.getINSTANCE().blue_team.remove(player.getUniqueId());
								if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) Main.getINSTANCE().yellow_team.remove(player.getUniqueId());
									if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) Main.getINSTANCE().orange_team.remove(player.getUniqueId());
						Main.getINSTANCE().green_team.add(player.getUniqueId());
						TeamsTagsManager.setNameTag(player, "§c§2Admin", "§9", "");
					} else {
						player.sendMessage("§cVous êtes déjà dans une équipe.");
					}
					break;
					
				case 3:
					if (!Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) {
						if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) Main.getINSTANCE().red_team.remove(player.getUniqueId());
							if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) Main.getINSTANCE().blue_team.remove(player.getUniqueId());
								if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) Main.getINSTANCE().green_team.remove(player.getUniqueId());
									if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) Main.getINSTANCE().orange_team.remove(player.getUniqueId());
						Main.getINSTANCE().yellow_team.add(player.getUniqueId());
						TeamsTagsManager.setNameTag(player, "§c§2Admin", "§9", "");
					} else {
						player.sendMessage("§cVous êtes déjà dans une équipe.");
					}
					break;
				case 4:
					if (!Main.getINSTANCE().orange_team.contains(player.getUniqueId())) {
						if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) Main.getINSTANCE().red_team.remove(player.getUniqueId());
							if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) Main.getINSTANCE().blue_team.remove(player.getUniqueId());
								if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) Main.getINSTANCE().green_team.remove(player.getUniqueId());
									if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) Main.getINSTANCE().yellow_team.remove(player.getUniqueId());
						Main.getINSTANCE().orange_team.add(player.getUniqueId());
						TeamsTagsManager.setNameTag(player, "§c§2Admin", "§9", "");
					} else {
						player.sendMessage("§cVous êtes déjà dans une équipe.");
					}
					break;
					
				default:
					break;
				}
				
				break;

			default:
				break;
			}
			
		}
		
	}
	
}