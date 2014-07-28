package fr.gwenzy.fk.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.gwenzy.fk.main.Main;
import fr.gwenzy.fk.main.MainMethods;

public class InventoryEvent implements Listener {

	@EventHandler
	public void onClickInventory(InventoryClickEvent event) throws IOException
	{
		try
		{
		if(event.getInventory().getName().equalsIgnoreCase("Joueurs de la partie."))
		{
			if(event.getCurrentItem()!= null)
			{
				event.setCancelled(true);
				Player p = (Player) event.getWhoClicked();
				ArrayList<String> alreadyReferenced = new ArrayList<>();
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team1.players"));
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team2.players"));
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team3.players"));
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team4.players"));
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team5.players"));
				alreadyReferenced.addAll(Main.config.getStringList("Teams.Team6.players"));
				int n = 0;
				ArrayList<ItemStack> players = new ArrayList<>();
				for(Player pl : Main.s.getOnlinePlayers())
				{
					if(!alreadyReferenced.contains(pl.getName()))
					{
						ItemStack head = new ItemStack(Material.SKULL_ITEM, 1,(byte) 3);
						ItemMeta im = head.getItemMeta();
						im.setDisplayName(ChatColor.GREEN + pl.getName());
						head.setItemMeta(im);
						players.add(head);
						
						n++;
					}
				}
				int slots = 9;
				if(n%9==0)
				{
					slots = n;
				}
				else
				{
					slots = ((n/9+1)*9);
				}
				Inventory i = Main.s.createInventory(null, slots, "Ajout d'un joueur : "+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())+".");
				for(ItemStack is : players)
				{
					i.addItem(is);
				}
				p.closeInventory();
				p.openInventory(i);
			}
		}
		else if(event.getInventory().getName().contains("Ajout d'un joueur"))
		{
			event.setCancelled(true);
			String team = event.getInventory().getName().replace("Ajout d'un joueur : ", "");
			team = team.replace(".", "");
			team = team.replace(" ", "");
			String pl = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			List<String> players = Main.config.getStringList("Teams."+team+".players");
			players.add(pl);
			Main.config.set("Teams."+team+".players", players);
			Main.config.save("plugins/FallenKingdoms/game.yml");
			int nbTeam = Integer.valueOf(team.replaceAll("Team", ""));
			ProtectedRegion r = Main.wgp.getRegionManager(event.getWhoClicked().getWorld()).getRegion("base"+nbTeam);
			DefaultDomain dd = r.getMembers();
			dd.addPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
			r.setMembers(dd);
			event.getWhoClicked().closeInventory();
			event.getWhoClicked().openInventory(MainMethods.getTeamsInventory());
			
		}
		}
		catch(Exception e)
		{
			
		}
	}
}
