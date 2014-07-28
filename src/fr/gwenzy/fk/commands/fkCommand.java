package fr.gwenzy.fk.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import fr.gwenzy.fk.main.Main;
import fr.gwenzy.fk.main.MainMethods;

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
				
				
				
				
				
				p.openInventory(MainMethods.getTeamsInventory());
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

