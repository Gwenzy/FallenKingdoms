package fr.gwenzy.fk.main;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

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
		Main.log.info(""+Main.fk+"V�rification de la pr�sence de WorldGuard en cours...");
		Main.wgp = MainMethods.getWorldGuard();
		Main.log.info(""+Main.fk+"V�rification de la pr�sence de WorldGuard termin�e.");
		Main.log.info(""+Main.fk+"V�rification de la pr�sence de WorldEdit en cours...");
		Main.wep = MainMethods.getWorldEdit();
		Main.log.info(""+Main.fk+"V�rification de la pr�sence de WorldEdit termin�e.");
		if(Main.wgp==null)
		{
			Main.log.warning(""+Main.fk+"WorldGuard non trouv�, le plugin ne peux pas se lancer");		
			
		}
		else
		{
			Main.log.info(""+Main.fk+"WorldGuard OK");		
		}
		if(Main.wep==null)
		{
			Main.log.warning(""+Main.fk+"WorldEdit non trouv�, le plugin ne peux pas se lancer");		
			
		}
		else
		{
			Main.log.info(""+Main.fk+"WorldEdit OK");		
		}
		if(Main.wgp==null||Main.wep==null)
		{
			Main.log.info(""+Main.fk+"Le plugin s'�teind (voir logs au dessus).");
			return false;
		}
		Main.log.info(""+Main.fk+"Plugin activ�");
		return true;
		
	}
	
	//Ajout d'une r�gion WG
	public static void addWGRegion (String name, Location b1, Location b2, World w) throws ProtectionDatabaseException
	{
		//R�cup�ration du RegionManager pour g�rer les protections
		RegionManager rm = Main.wgp.getRegionManager(w);
		
		//Blocks pour la s�lection
		BlockVector v1 = new BlockVector(b1.getX(), b1.getY(), b1.getZ());
		BlockVector v2 = new BlockVector(b2.getX(), b2.getY(), b2.getZ());
		
		//On fait le cuboid
		ProtectedCuboidRegion cr = new ProtectedCuboidRegion(name, v1, v2);
		
		//On cr�e et on sauvegarde
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
}
