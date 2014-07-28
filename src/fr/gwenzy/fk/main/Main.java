package fr.gwenzy.fk.main;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.gwenzy.fk.commands.fkCommand;
import fr.gwenzy.fk.events.InventoryEvent;
import fr.gwenzy.fk.events.PlayerInteractEventClass;
import fr.gwenzy.fk.events.chatEvent;

public class Main extends JavaPlugin
{
	public static Server s  = Bukkit.getServer();
	public static Logger log = s.getLogger();
	public static WorldGuardPlugin wgp;
	public static WorldEditPlugin wep;
	public static String fk = "[FallenKingdoms] ";
	public static FileConfiguration config = null;
	public fkCommand fkce = new fkCommand(this);
	public static ScoreboardManager sbm = Main.s.getScoreboardManager();
	
	@Override
	public void onEnable()
	{
		
		if(!MainMethods.loadPlugins())
		{
			this.setEnabled(false);
		}
		
		
		//Si malheureusement, une partie est déjà en cours. On va charger la config actuelle
		File file = new File("plugins/FallenKingdoms/");
		if(!file.exists()){ file.mkdir();}
		file = new File("plugins/FallenKingdoms/game.yml");
		if(file.exists())
		{
			config = YamlConfiguration.loadConfiguration(file);
		}
		else
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config = YamlConfiguration.loadConfiguration(file);
		}
		
		getCommand("fk").setExecutor(fkce);
	
		s.getPluginManager().registerEvents(new chatEvent(this), this);
		s.getPluginManager().registerEvents(new PlayerInteractEventClass(this), this);
		s.getPluginManager().registerEvents(new InventoryEvent(), this);
	}
	
	@Override
	public void onDisable()
	{
		log.info(""+Main.fk+"Plugin désactivé");
		
	}
	
}
