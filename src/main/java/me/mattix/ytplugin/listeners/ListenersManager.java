package me.mattix.ytplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.mattix.ytplugin.listeners.entity.CreaturesSpawnListener;
import me.mattix.ytplugin.listeners.entity.EntityDamageListener;
import me.mattix.ytplugin.listeners.player.FurnasePropertyListener;
import me.mattix.ytplugin.listeners.player.PlayerBlockListener;
import me.mattix.ytplugin.listeners.player.PlayerJoinListener;
import me.mattix.ytplugin.listeners.player.PlayerMoveListener;
import me.mattix.ytplugin.listeners.player.PlayerQuitListener;
import me.mattix.ytplugin.menus.TeamsMenu;

public class ListenersManager {

	public Plugin plugin;
	public PluginManager pm;

	public ListenersManager(Plugin plugin) {
		this.plugin = plugin;
		this.pm = Bukkit.getPluginManager();
	}
	
	public void registerListeners() {
		pm.registerEvents(new PlayerJoinListener(), this.plugin);
		pm.registerEvents(new PlayerMoveListener(), this.plugin);
		pm.registerEvents(new PlayerBlockListener(), this.plugin);
		pm.registerEvents(new CreaturesSpawnListener(), this.plugin);
		pm.registerEvents(new FurnasePropertyListener(), this.plugin);
		pm.registerEvents(new PlayerQuitListener(), this.plugin);
		pm.registerEvents(new EntityDamageListener(), this.plugin);
		
		pm.registerEvents(new TeamsMenu(), this.plugin);
	}
}