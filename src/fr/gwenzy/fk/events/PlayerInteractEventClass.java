package fr.gwenzy.fk.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;

import fr.gwenzy.fk.main.Main;
import fr.gwenzy.fk.main.MainMethods;

public class PlayerInteractEventClass implements Listener {
	private Main plugin;
	int actual;
	public PlayerInteractEventClass (Main plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) throws ProtectionDatabaseException
	{
		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			
			Player p = event.getPlayer();
			if(p.getItemInHand()!= null && p.getItemInHand().getType()==Material.STICK)
			{
				ItemMeta meta = p.getItemInHand().getItemMeta();
				if(meta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"FallenKingdoms Initialization Tool"))
				{
					int nbBases = p.getMetadata("nbBases").get(0).asInt();
					
					
					
					
					if(p.getMetadata("Base1").get(0).asBoolean())
					{
						actual = 1;
						if(p.getMetadata("Base1_1").get(0).asString() == "")
						{
							p.setMetadata("Base1_1", new FixedMetadataValue(plugin, ""+event.getClickedBlock().getLocation().getX()+"--"+event.getClickedBlock().getLocation().getY()+"--"+event.getClickedBlock().getLocation().getZ()));
							p.sendMessage(ChatColor.GREEN+""+Main.fk+"Point 1 défini en X:"+event.getClickedBlock().getLocation().getX()+" Y:"+event.getClickedBlock().getLocation().getY()+" Z:"+event.getClickedBlock().getLocation().getZ());
							event.setCancelled(true);
						}
					}
					else
					{
						if(nbBases >= actual)
						{
							if(p.getMetadata("Base"+actual+"_1").get(0).asString() == "")
							{
								p.setMetadata("Base"+actual+"_1", new FixedMetadataValue(plugin, ""+event.getClickedBlock().getLocation().getX()+"--"+event.getClickedBlock().getLocation().getY()+"--"+event.getClickedBlock().getLocation().getZ()));
								p.sendMessage(ChatColor.GREEN+""+Main.fk+"Point 1 défini en X:"+event.getClickedBlock().getLocation().getX()+" Y:"+event.getClickedBlock().getLocation().getY()+" Z:"+event.getClickedBlock().getLocation().getZ());
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Player p = event.getPlayer();
			if(p.getItemInHand()!= null && p.getItemInHand().getType()==Material.STICK)
			{
				ItemMeta meta = p.getItemInHand().getItemMeta();
				if(meta.getDisplayName().equals(ChatColor.GREEN+"FallenKingdoms Initialization Tool"))
				{
					int nbBases = p.getMetadata("nbBases").get(0).asInt();
					
					
					
					if(p.getMetadata("Base1").get(0).asBoolean())
					{
						if(p.getMetadata("Base1_1").get(0).asString() == "")
						{
							p.sendMessage(ChatColor.RED+""+Main.fk+"Le point 1 n'est pas défini");
						}
						else
						{
							if(p.getMetadata("Base1_2").get(0).asString() == "")
							{
							p.setMetadata("Base1_2", new FixedMetadataValue(plugin, ""+event.getClickedBlock().getLocation().getX()+"--"+event.getClickedBlock().getLocation().getY()+"--"+event.getClickedBlock().getLocation().getZ()));
							p.sendMessage(ChatColor.GREEN+""+Main.fk+"Point 2 défini en X:"+event.getClickedBlock().getLocation().getX()+" Y:"+event.getClickedBlock().getLocation().getY()+" Z:"+event.getClickedBlock().getLocation().getZ());
							p.setMetadata("Base1", new FixedMetadataValue(plugin, false));
							p.setMetadata("Actual", new FixedMetadataValue(plugin, 2));
							p.sendMessage(ChatColor.GREEN+""+Main.fk+"Base 1 définie");
							String p1 = p.getMetadata("Base1_1").get(0).asString();
							String p2 = p.getMetadata("Base1_2").get(0).asString();
							Location b1 = new Location(p.getWorld(), Double.valueOf(p1.split("--")[0]), 0, Double.valueOf(p1.split("--")[2]));
							Location b2 = new Location(p.getWorld(), Double.valueOf(p2.split("--")[0]), 256, Double.valueOf(p2.split("--")[2]));
							
							
							MainMethods.addWGRegion("base1", b1, b2, p.getWorld());
							if(nbBases == actual)
							{
								p.sendMessage(ChatColor.GREEN+Main.fk+"Toutes les bases ont été définies !");
								p.setMetadata("pvpDayWait", new FixedMetadataValue(plugin, true));
							}
							}
						}
					}
					else
					{
						if(nbBases >= actual)
						{
							if(p.getMetadata("Base"+actual+"_1").get(0).asString() == "")
							{
								p.sendMessage(ChatColor.RED+""+Main.fk+"Le point 1 n'est pas défini");
							}
							else
							{
								
							if(p.getMetadata("Base"+actual+"_2").get(0).asString() == "")
							{
								p.setMetadata("Base"+actual+"_2", new FixedMetadataValue(plugin, ""+event.getClickedBlock().getLocation().getX()+"--"+event.getClickedBlock().getLocation().getY()+"--"+event.getClickedBlock().getLocation().getZ()));
								p.sendMessage(ChatColor.GREEN+""+Main.fk+"Point 2 défini en X:"+event.getClickedBlock().getLocation().getX()+" Y:"+event.getClickedBlock().getLocation().getY()+" Z:"+event.getClickedBlock().getLocation().getZ());
								p.sendMessage(ChatColor.GREEN+""+Main.fk+"Base "+actual+" definie.");
								String p1 = p.getMetadata("Base"+actual+"_1").get(0).asString();
								String p2 = p.getMetadata("Base"+actual+"_2").get(0).asString();
								String[] parts = p1.split("--");
								
								
								
								double x = Double.parseDouble(parts[0]);
								double z = Double.valueOf(p1.split("--")[2]);
								Location b1 = new Location(p.getWorld(), x, 0, z);
								Location b2 = new Location(p.getWorld(), Double.valueOf(p2.split("--")[0]), 256, Double.valueOf(p2.split("--")[2]));
								MainMethods.addWGRegion("base"+actual, b1, b2, p.getWorld());
								

								if(nbBases == actual)
								{
									p.sendMessage(ChatColor.GREEN+Main.fk+"Toutes les bases ont été définies !");
									p.setMetadata("pvpDayWait", new FixedMetadataValue(plugin, true));
									p.setMetadata("assautDayWait", new FixedMetadataValue(plugin, false));
									p.sendMessage(ChatColor.GOLD+Main.fk+"Partie 3 : Veuillez saisir le jour où activer le pvp :");
								}
								
								event.setCancelled(true);
							}
						}
							}
						}
					actual = actual+1;
					}
				}
			ArrayList<String> players = new ArrayList<>();
			
			Main.config.set("Teams.Team"+(actual-1)+".BaseName", "Base"+actual);
			Main.config.set("Teams.Team"+(actual-1)+".Couleur", "§"+(actual+1));
			Main.config.set("Teams.Team"+(actual-1)+".players", players);
			MainMethods.configSave();
			}
		}
	}


