package me.mattix.ytplugin.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.mattix.ytplugin.GameStatus;
import me.mattix.ytplugin.Main;
import me.mattix.ytplugin.player.GamePlayer;
import me.mattix.ytplugin.utils.ArrowTargetUtils;
import me.mattix.ytplugin.utils.TitleManager;

public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		if (GameStatus.isStatus(GameStatus.DEATHMATCH)) {
			
			if (gp.getTeam() != null) {
				TitleManager.sendActionBar(player, "§7Votre base: §f" + (int) player.getLocation().distance(gp.region.getMiddle()) + "b " + getArrowColor(player.getLocation().distance(gp.region.getMiddle())) + ArrowTargetUtils.calculateArrow(player, gp.region.getMiddle()));
			}
			
			if (Main.getINSTANCE().redBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						gp.isInBase = true;
						player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
				
				if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
			
			// bleu
			if (Main.getINSTANCE().blueBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						gp.isInBase = true;
						player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
				
				if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
			// vert
			if (Main.getINSTANCE().greenBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						 gp.isInBase = true;
						 player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
							
				if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
			// jaune
			if (Main.getINSTANCE().yellowBase.isInArea(player.getLocation())) {
				if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) {
					if (!gp.isInBase) {
						 gp.isInBase = true;
						 player.sendMessage("§bVous entrez dans votre base.");
					}
				}
			} else {
							
				if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) {
					if (gp.isInBase) {
						gp.isInBase = false;
						player.sendMessage("§cVous sortez de votre base.");
					}
				}
			}
			// orange
				if (Main.getINSTANCE().orangeBase.isInArea(player.getLocation())) {
					if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) {
						if (!gp.isInBase) {
							 gp.isInBase = true;
							 player.sendMessage("§bVous entrez dans votre base.");
						}
					}
				} else {
							
					if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) {
						if (gp.isInBase) {
							gp.isInBase = false;
							player.sendMessage("§cVous sortez de votre base.");
						}
					}
				}
			
		}
	}
	
	public String getArrowColor(double d) {
		if (d >= 100) return "§c";
		if (d > 20  && d < 100) return "§e";
		if (d <= 20) return "§a";
		return "§a";
	}
}