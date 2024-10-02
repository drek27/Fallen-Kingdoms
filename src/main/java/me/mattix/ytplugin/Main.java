package me.mattix.ytplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import me.mattix.ytplugin.listeners.ListenersManager;
import me.mattix.ytplugin.runnables.LobbyRunnable;
import me.mattix.ytplugin.utils.RegionManager;

public class Main extends JavaPlugin {
	
	private static Main INSTANCE;
	public LobbyRunnable lobbyRunnable;
	
	public RegionManager redBase, blueBase, greenBase, yellowBase, orangeBase;
	
	public List<UUID> red_team = new ArrayList<>();
	public List<UUID> blue_team = new ArrayList<>();
	public List<UUID> green_team = new ArrayList<>();
	public List<UUID> yellow_team = new ArrayList<>();
	public List<UUID> orange_team = new ArrayList<>();
	
	public Map<UUID, Villager> villagers = new HashMap<>();
	
	// déroulement de la partie
	public boolean assaut, pvp, deathmatch = false;
	
	public Random random = new Random();
	
	@Override
	public void onLoad() { INSTANCE = this; }
	
	@Override
	public void onEnable() {
		this.getCommand("test").setExecutor(new TestCommand());
		
		lobbyRunnable = new LobbyRunnable();
		random = new Random();
		
		blueBase = new RegionManager(new Location(Bukkit.getWorld("world"), 77.5, 66.0, -27.5), new Location(Bukkit.getWorld("world"), 45.5, 93.0, 4.3));
		redBase = new RegionManager(new Location(Bukkit.getWorld("world"), 161.5, 64.0, 119.5), new Location(Bukkit.getWorld("world"), 129.5, 94.0, 87.4));
		greenBase = new RegionManager(new Location(Bukkit.getWorld("world"), -6.6, 63.0, 43.4), new Location(Bukkit.getWorld("world"), 25.4, 95.0, 75.3));
		yellowBase = new RegionManager(new Location(Bukkit.getWorld("world"), 129.4, 94.0, -0.4), new Location(Bukkit.getWorld("world"), 161.4, 63.0, 31.4));
		orangeBase = new RegionManager(new Location(Bukkit.getWorld("world"), 77.3, 96.0, 114.5), new Location(Bukkit.getWorld("world"), 45.3, 64.0, 146.5));
		
		new ListenersManager(this).registerListeners();
		GameStatus.setStatus(GameStatus.ATTENTE);
		
	}
	
	public static Main getINSTANCE() {
		return INSTANCE;
	}
	
	public String getPrefix() {
		return "§6Fallen Kingdoms §f| ";
	}
	
	@Override
	public void onDisable() { 
		
		for(Entity entity : Bukkit.getWorld("world").getEntities()) {
			if ((entity instanceof EnderCrystal) || (entity instanceof Villager)) {
				entity.remove();
			}
		}
	}
}