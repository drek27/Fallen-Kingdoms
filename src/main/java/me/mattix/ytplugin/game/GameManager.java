package me.mattix.ytplugin.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.mattix.ytplugin.Main;
import me.mattix.ytplugin.player.GamePlayer;
import me.mattix.ytplugin.utils.TitleManager;

public class GameManager {
	
	private Location redSpawn, blueSpawn, greenSpawn, yellowSpawn, orangeSpawn;

	public GameManager() {
		// définir redSpawn et blueSpawn
		this.redSpawn = new Location(Bukkit.getWorld("world"), 143.3, 72.5, 103.4);
		this.blueSpawn = new Location(Bukkit.getWorld("world"), 61.4, 72.8, -9.3);
		this.greenSpawn = new Location(Bukkit.getWorld("world"), 11.8, 72.6, 59.5);
		this.yellowSpawn = new Location(Bukkit.getWorld("world"), 143.5, 72.5, 15.4);
		this.orangeSpawn = new Location(Bukkit.getWorld("world"), 61.4, 72.5, 128.7);
	}
	
	public void loadGame() {
		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setTime(0);
		
		// rouge
		this.create(new Location(Bukkit.getWorld("world"), 145.4, 73.0, 103.4), "rouge");
		// bleu
		this.create(new Location(Bukkit.getWorld("world"), 61.5, 73.0, -11.2), "bleu");
		// vert
		this.create(new Location(Bukkit.getWorld("world"), 9.7, 73.0, 59.4), "vert");
		// jaune
		this.create(new Location(Bukkit.getWorld("world"), 145.3, 73.0, 15.4), "jaune");
		// orange
		this.create(new Location(Bukkit.getWorld("world"), 61.6, 73.0, 130.4), "orange");
		
		// scoreboard
		for (Player player : Bukkit.getOnlinePlayers()) {
			GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
			gp.scoreboard.loadScoreboardGame();
			
			// tp les teams dans leur base
			if (Main.getINSTANCE().red_team.contains(player.getUniqueId())) {
				player.teleport(getRedSpawn());
			} else if (Main.getINSTANCE().blue_team.contains(player.getUniqueId())) {
				player.teleport(getBlueSpawn());
			} else if (Main.getINSTANCE().green_team.contains(player.getUniqueId())) {
				player.teleport(getGreenSpawn());
			} else if (Main.getINSTANCE().yellow_team.contains(player.getUniqueId())) {
				player.teleport(getYellowSpawn());
			} else if (Main.getINSTANCE().orange_team.contains(player.getUniqueId())) {
				player.teleport(getOrangeSpawn());
			}
			
			// message de démarrage de partie
			TitleManager.sendTitle(player, "§6Fallen Kingdoms", "§eLa partie a commencé", 40);
		}
	}

	private void create(Location loc, String name) {
		EnderCrystal enderCrystal = (EnderCrystal) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ENDER_CRYSTAL);
		enderCrystal.setCustomName(name);
		enderCrystal.setCustomNameVisible(false);
		
		new HearthManager(enderCrystal, name);
	}
	
	public Location getBlueSpawn() {
		return blueSpawn;
	}
	
	public Location getRedSpawn() {
		return redSpawn;
	}
	private Location getOrangeSpawn() {
		// TODO Auto-generated method stub
		return orangeSpawn;
	}

	private Location getYellowSpawn() {
		// TODO Auto-generated method stub
		return yellowSpawn;
	}

	private Location getGreenSpawn() {
		// TODO Auto-generated method stub
		return greenSpawn;
	}
}