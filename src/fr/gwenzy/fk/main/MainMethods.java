package fr.gwenzy.fk.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

public class MainMethods {

	public static WorldGuardPlugin getWorldGuard()
	{
		Plugin plugin = Main.s.getPluginManager().getPlugin("WorldGuard");
		
		if(plugin == null || !(plugin instanceof WorldGuardPlugin))
		{
			return null;
			
		}
		
		return (WorldGuardPlugin) plugin;
		
	}
	public static WorldEditPlugin getWorldEdit()
	{
		Plugin plugin = Main.s.getPluginManager().getPlugin("WorldEdit");
		
		if(plugin == null || !(plugin instanceof WorldEditPlugin))
		{
			return null;
			
		}
		
		return (WorldEditPlugin) plugin;
		
	}
	
	public static boolean loadPlugins()
	{
		Main.log.info(""+Main.fk+"Vérification de la présence de WorldGuard en cours...");
		Main.wgp = MainMethods.getWorldGuard();
		Main.log.info(""+Main.fk+"Vérification de la présence de WorldGuard terminée.");
		Main.log.info(""+Main.fk+"Vérification de la présence de WorldEdit en cours...");
		Main.wep = MainMethods.getWorldEdit();
		Main.log.info(""+Main.fk+"Vérification de la présence de WorldEdit terminée.");
		if(Main.wgp==null)
		{
			Main.log.warning(""+Main.fk+"WorldGuard non trouvé, le plugin ne peux pas se lancer");		
			
		}
		else
		{
			Main.log.info(""+Main.fk+"WorldGuard OK");		
		}
		if(Main.wep==null)
		{
			Main.log.warning(""+Main.fk+"WorldEdit non trouvé, le plugin ne peux pas se lancer");		
			
		}
		else
		{
			Main.log.info(""+Main.fk+"WorldEdit OK");		
		}
		if(Main.wgp==null||Main.wep==null)
		{
			Main.log.info(""+Main.fk+"Le plugin s'éteind (voir logs au dessus).");
			return false;
		}
		Main.log.info(""+Main.fk+"Plugin activé");
		return true;
		
	}
	
	//Ajout d'une région WG
	public static void addWGRegion (String name, Location b1, Location b2, World w) throws ProtectionDatabaseException
	{
		//Récupération du RegionManager pour gérer les protections
		RegionManager rm = Main.wgp.getRegionManager(w);
		
		//Blocks pour la sélection
		BlockVector v1 = new BlockVector(b1.getX(), b1.getY(), b1.getZ());
		BlockVector v2 = new BlockVector(b2.getX(), b2.getY(), b2.getZ());
		
		//On fait le cuboid
		ProtectedCuboidRegion cr = new ProtectedCuboidRegion(name, v1, v2);
		
		//On crée et on sauvegarde
		rm.addRegion(cr);
		rm.save();
	}
	
	public static void configSave()
	{
		try {
			Main.config.save("plugins/FallenKingdoms/game.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Inventory getTeamsInventory()
	{

		int nbTeams = Main.config.getInt("Teams.Number");
		int in = 9;
		if(nbTeams%9 != 0)
		{
			in = ((nbTeams/9)+1)*9;
		}
		else
		{
			in = nbTeams;
		}
		Inventory i = Main.s.createInventory(null, in, "Joueurs de la partie.");
		ItemStack team1 = new ItemStack(Material.BEACON);
		ItemStack team2 = new ItemStack(Material.BEACON);
		ItemStack team3 = new ItemStack(Material.BEACON);
		ItemStack team4 = new ItemStack(Material.BEACON);
		ItemStack team5 = new ItemStack(Material.BEACON);
		ItemStack team6 = new ItemStack(Material.BEACON);
		
		ItemMeta team1Meta = team1.getItemMeta();
		team1Meta.setDisplayName(ChatColor.GREEN+"Team 1");
		ArrayList<String> playersT1 = new ArrayList<>();
		playersT1.add("Joueurs présents");
		playersT1.addAll(Main.config.getStringList("Teams.Team1.players"));
		team1Meta.setLore(playersT1);
		ItemMeta team2Meta = team2.getItemMeta();
		team2Meta.setDisplayName(ChatColor.GREEN+"Team 2");
		ArrayList<String> playersT2 = new ArrayList<>();
		playersT2.add("Joueurs présents");
		playersT2.addAll(Main.config.getStringList("Teams.Team2.players"));
		team2Meta.setLore(playersT2);
		ItemMeta team3Meta = team3.getItemMeta();
		team3Meta.setDisplayName(ChatColor.GREEN+"Team 3");
		ArrayList<String> playersT3 = new ArrayList<>();
		playersT3.add("Joueurs présents");
		playersT3.addAll(Main.config.getStringList("Teams.Team3.players"));
		team3Meta.setLore(playersT3);
		ItemMeta team4Meta = team4.getItemMeta();
		team4Meta.setDisplayName(ChatColor.GREEN+"Team 4");
		ArrayList<String> playersT4 = new ArrayList<>();
		playersT4.add("Joueurs présents");
		playersT4.addAll(Main.config.getStringList("Teams.Team4.players"));
		team4Meta.setLore(playersT4);
		ItemMeta team5Meta = team5.getItemMeta();
		team5Meta.setDisplayName(ChatColor.GREEN+"Team 5");
		ArrayList<String> playersT5 = new ArrayList<>();
		playersT5.add("Joueurs présents");
		playersT5.addAll(Main.config.getStringList("Teams.Team5.players"));
		team5Meta.setLore(playersT5);
		ItemMeta team6Meta = team6.getItemMeta();
		team6Meta.setDisplayName(ChatColor.GREEN+"Team 6");
		ArrayList<String> playersT6 = new ArrayList<>();
		playersT6.add("Joueurs présents");
		playersT6.addAll(Main.config.getStringList("Teams.Team6.players"));
		team6Meta.setLore(playersT6);
		
		team1.setItemMeta(team1Meta);
		team2.setItemMeta(team2Meta);
		team3.setItemMeta(team3Meta);
		team4.setItemMeta(team4Meta);
		team5.setItemMeta(team5Meta);
		team6.setItemMeta(team6Meta);

		if(nbTeams>0)
		{
			i.addItem(team1);
		}
		if(nbTeams>1)
		{
			i.addItem(team2);
		}
		if(nbTeams>2)
		{
			i.addItem(team3);
		}
		if(nbTeams>3)
		{
			i.addItem(team4);
		}
		if(nbTeams>4)
		{
			i.addItem(team5);
		}
		if(nbTeams>5)
		{
			i.addItem(team6);
		}
		
		return i;
	}
	
	public void a()
	{
		
	}
}
