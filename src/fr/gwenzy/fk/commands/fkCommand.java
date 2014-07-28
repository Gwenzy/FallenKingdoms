package fr.gwenzy.fk.commands;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.gwenzy.fk.main.Main;
import fr.gwenzy.fk.main.MainMethods;

public class fkCommand implements CommandExecutor {

	public static NumberFormat f = new DecimalFormat("00");
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
				final Player p = (Player )sender;

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
			else if(args[0].equalsIgnoreCase("start"))
			{
				//On prépare le scoreboard
				Scoreboard sb = Main.sbm.getNewScoreboard();
				Objective obj = sb.registerNewObjective("dummy", "dummy");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
				obj.setDisplayName("Fallen Kingdoms");
				Score score1 = obj.getScore(Main.s.getOfflinePlayer("Jour 1"));
				score1.setScore(-4);
				Score score2 = obj.getScore(Main.s.getOfflinePlayer("20:00"));
				score2.setScore(-5);
				p.setScoreboard(sb);
				
				 Timer timer = new Timer();
				 timer.schedule( 
						 new TimerTask() {
							 public void run() {
								 Iterator<OfflinePlayer> it = p.getScoreboard().getPlayers().iterator();  
							        
							    	int jourA = 0;
							        int minutesA = 0;
							        int secondsA = 0;
							        int jour = 0;
							        String minutes = "0";
							        String seconds = "0";
							        
							    	
							    	
							    	while (it.hasNext())  
							        {
							    		OfflinePlayer a = it.next();
							    		if((a.getName().contains("Jour")))
							            {
							            	jourA = Integer.valueOf(a.getName().replaceAll("Jour ", ""));
							            	
							            }
							    		if((a.getName().contains(":")))
							            {
							            	minutesA = Integer.valueOf(a.getName().split(":")[0]);
							            	secondsA = Integer.valueOf(a.getName().split(":")[1]);
							            }
							    		
							        
							            
							        }  

							    	p.setScoreboard(Main.sbm.getNewScoreboard());
							    	Scoreboard sb = Main.sbm.getNewScoreboard();
									Objective obj = sb.registerNewObjective("dummy", "dummy");
									obj.setDisplaySlot(DisplaySlot.SIDEBAR);
									obj.setDisplayName("Fallen Kingdoms");
									
									if(minutesA==0&&secondsA==0)
									{

										jour = jourA+1;
										minutes = f.format(20);
										seconds = f.format(00);
										Main.s.broadcastMessage(ChatColor.GOLD+"---------------Fin du jour "+jourA+"---------------");
										
									}
									else if(secondsA==0&&minutesA!=0)
									{

										jour = jourA;
										minutes = f.format(minutesA-1);
										seconds = f.format(59);
									}
									else
									{

										jour = jourA;
										minutes = f.format(minutesA);
										seconds = f.format(secondsA-1);
									}
									Score score1 = obj.getScore(Main.s.getOfflinePlayer("Jour "+jour));
									score1.setScore(-4);
									Score score2 = obj.getScore(Main.s.getOfflinePlayer(minutes+":"+seconds));
									score2.setScore(-5);
									p.setScoreboard(sb);
								 } 
							 
							 }, 0, 1000); 
							 

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

