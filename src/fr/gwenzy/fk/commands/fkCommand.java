package fr.gwenzy.fk.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import fr.gwenzy.fk.main.Main;

public class fkCommand implements CommandExecutor {

	private Main plugin;
	public fkCommand(Main plugin)
	{
		this.plugin = plugin;
	}
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		
		if(CommandLabel.equalsIgnoreCase("fk"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player )sender;

			if(args[0].equalsIgnoreCase("init"))
			{
				if(sender.hasPermission("FallenKingdoms.*") || sender.hasPermission("FallenKingdoms.init"))
				{
					/**On lance la procédure d'initialisation de la partie*/
					//On donne le baton pour définir les bases et on met le metadata "Bases" à true
					
						
						p.sendMessage(ChatColor.GOLD+""+Main.fk+"Partie 1 de la configuration, veuillez saisir le nombre de bases à configurer (Max 6):");
						p.setMetadata("nbBasesWait", new FixedMetadataValue(plugin, true));
						
						
					}
					
				
				
			}
			else if(args[0].equalsIgnoreCase("resume"))
			{
				if(sender.hasPermission("FallenKingdoms.*") || sender.hasPermission("FallenKingdoms.resume"))
				{
					if(Main.config.get("Teams.Number")==null)
					{
						sender.sendMessage(ChatColor.RED+Main.fk+"Vous devriez effectuer une configuration avant de vouloir son résumé :p");
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD+Main.fk+"Voici le résumé de la configuration :");
						sender.sendMessage(ChatColor.AQUA+"Jour du PVP autorisé : "+ChatColor.YELLOW+Main.config.getInt("DaysConfig.pvp"));
						sender.sendMessage(ChatColor.AQUA+"Jour des assauts autorisés : "+ChatColor.YELLOW+Main.config.getInt("DaysConfig.assaut"));
						sender.sendMessage(ChatColor.AQUA+"Nombre de bases configurées : "+ChatColor.YELLOW+Main.config.getInt("Teams.Number"));
						for(int i=1; i<=Main.config.getInt("Teams.Number"); i++)
						{
							sender.sendMessage(ChatColor.AQUA+"Team "+i+" :");
							sender.sendMessage(ChatColor.AQUA+"    Couleur : "+Main.config.getString("Teams.Team"+i+".Couleur")+"Test de la couleur");
							sender.sendMessage(ChatColor.AQUA+"    Joueurs :");
							List<String> players = Main.config.getStringList("Teams.Team"+1+".Joueurs");
							for(int in=0; in<players.size(); i++)
							{
								sender.sendMessage(ChatColor.YELLOW+"    - "+players.get(in));
							}
						}
					
					}
				}
				
			}
			else if(args[0].equalsIgnoreCase("teams"))
			{
				if(sender.hasPermission("FallenKingdoms.*") || sender.hasPermission("FallenKingdoms.teams"))
				{
					if(Main.config.get("Teams.Number")==null)
					{
						sender.sendMessage(ChatColor.RED+Main.fk+"Vous devriez effectuer une configuration avant de vouloir son résumé :p");
					}
					else
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
				
				p.openInventory(i);
					}
				
			}
			}
		}
		else
		{
			Main.log.warning("Il est impossible de faire cette commande depuis la console");
		}
		}
		return true;
		
	}

}

