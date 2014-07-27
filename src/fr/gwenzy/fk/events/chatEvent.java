package fr.gwenzy.fk.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import fr.gwenzy.fk.main.Main;
import fr.gwenzy.fk.main.MainMethods;

public class chatEvent implements Listener {

	private Main plugin;
	public chatEvent (Main plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void PlayerchatEvent(AsyncPlayerChatEvent event)
	{
		
		Player p = event.getPlayer();
		if(!p.getMetadata("nbBasesWait").isEmpty())
		{
			if (p.getMetadata("nbBasesWait").get(0).asBoolean())
			{
				if(Integer.valueOf(event.getMessage()) != null)
				{
					if(Integer.valueOf(event.getMessage())<7 && Integer.valueOf(event.getMessage())>1)
					{
				event.setCancelled(true);
				p.setMetadata("nbBases", new FixedMetadataValue(plugin, Integer.valueOf(event.getMessage())));
				
				p.setMetadata("Actual", new FixedMetadataValue(plugin, 1));
				p.sendMessage(ChatColor.GOLD+""+Main.fk+"Partie 2 de la configuration, veuillez définir la zone de la base n°1. Rappel : Clic gauche pour une extrémité de la zone, clic droit pour l'autre extrémité.");
				for(int i = 1; i<=Integer.valueOf(event.getMessage());i++)
				{
					p.setMetadata("Base"+i, new FixedMetadataValue(plugin, true));
				
				p.setMetadata("Base"+i+"_1", new FixedMetadataValue(plugin, ""));
				p.setMetadata("Base"+i+"_2", new FixedMetadataValue(plugin, ""));
				}
				ItemStack tool = new ItemStack(Material.STICK);
				ItemMeta toolMeta = tool.getItemMeta();
				toolMeta.setDisplayName(ChatColor.GREEN+"FallenKingdoms Initialization Tool");
				tool.setItemMeta(toolMeta);
				p.getInventory().clear();
				p.getInventory().addItem(tool);
				p.setMetadata("nbBasesWait", new FixedMetadataValue(plugin, false));
				}
				else
				{
					event.setCancelled(true);
					p.sendMessage(ChatColor.RED+Main.fk+"Nombre max de bases : 6 / Nombre minimum : 2. Veuillez resaisir le nombre de bases à configurer :");
				}
				}
		}
		if(!p.getMetadata("assautDayWait").isEmpty())
		{
			if (p.getMetadata("assautDayWait").get(0).asBoolean())
			{
				if(Integer.valueOf(event.getMessage()) != null)
				{
					p.setMetadata("assautDay", new FixedMetadataValue(plugin, Integer.valueOf(event.getMessage())));
					p.sendMessage(ChatColor.GREEN + Main.fk+"Merci, les assauts seront actifs le jour n°"+Integer.valueOf(event.getMessage()));
					p.sendMessage(ChatColor.GOLD+Main.fk+"Toutes les données ont été collectées. Voici le résumé de la configuration du Fallen Kingdoms. Si cela ne vous convient pas, relancez la config via /fk init");
					
					p.setMetadata("assautDayWait", new FixedMetadataValue(plugin, false));
					
					p.sendMessage(ChatColor.AQUA + "Nombre de bases : "+ChatColor.YELLOW+ p.getMetadata("nbBases").get(0).asInt());
					p.sendMessage(ChatColor.AQUA + "Jour du PVP : "+ChatColor.YELLOW+ p.getMetadata("pvpDay").get(0).asInt());
					p.sendMessage(ChatColor.AQUA + "Jour des assauts : "+ChatColor.YELLOW+ p.getMetadata("assautDay").get(0).asInt());
					Main.config.set("Teams.Number", p.getMetadata("nbBases").get(0).asInt());
					Main.config.set("DaysConfig.pvp", p.getMetadata("pvpDay").get(0).asInt());
					Main.config.set("DaysConfig.assaut", p.getMetadata("assautDay").get(0).asInt());
					MainMethods.configSave();
					
					event.setCancelled(true);
					
					//Tout est configuré à part les teams. On va mettre le scoreboard
				}
			}
		}
		if(!p.getMetadata("pvpDayWait").isEmpty())
		{
			if (p.getMetadata("pvpDayWait").get(0).asBoolean())
			{
				if(Integer.valueOf(event.getMessage()) != null)
				{
					p.setMetadata("pvpDay", new FixedMetadataValue(plugin, Integer.valueOf(event.getMessage())));
					p.sendMessage(ChatColor.GREEN + Main.fk+"Merci, le pvp sera actif le jour n°"+Integer.valueOf(event.getMessage()));
					p.sendMessage(ChatColor.GOLD+Main.fk+"Partie 4 : Veuillez saisir le jour où activer les assauts :");
					p.setMetadata("assautDayWait", new FixedMetadataValue(plugin, true));
					p.setMetadata("pvpDayWait", new FixedMetadataValue(plugin, false));
					event.setCancelled(true);
				}
			}
		}
		
	}
}
}
