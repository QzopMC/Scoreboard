package Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Kills;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.border;
import me.fairuhc.UHC.DeathManager.FFAWinner;
import me.fairuhc.UHC.DeathManager.TeamsWinner;
import me.fairuhc.UHC.scenarios.ScenarioEvents;
import me.fairuhc.UHC.teams.CreateTeam;

public class Sidebar implements Listener
{
	public Scoreboard scoreboard;
	public static int time = 0;
	
	public static Team a;
	public static Team owner;
	public static Team admin;
	public static Team srmod;
	public static Team mod;
	public static Team trial;
	public static Team def;

	Gamemanager gm;
	
	Main plugin;
	
	public Sidebar(Main plugin)
	{
		this.plugin = plugin;
		gm = new Gamemanager(plugin);
	}
	
	public void tablist(Player p)
	{
		Objective objective = scoreboard.registerNewObjective("tab", "list");
		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		
		a = scoreboard.getTeam("Anonymous");
		owner = scoreboard.getTeam("Owner");
		admin = scoreboard.getTeam("Admin");
		srmod = scoreboard.getTeam("SrMod");
		mod = scoreboard.getTeam("Mod");
		trial = scoreboard.getTeam("Trial");
		def = scoreboard.getTeam("Default");
		
		
		 new BukkitRunnable()
	     {
	    	 public void run()
	    	 {
	    		 if(owner == null)
				 {
					 owner = scoreboard.registerNewTeam("Owner");
					 owner.setPrefix(ChatColor.DARK_RED + "");
				 }
				 else if(admin == null)
				 {
					 admin = scoreboard.registerNewTeam("Admin");
					 admin.setPrefix(ChatColor.RED + "");
				 }
				 else if(srmod == null)
				 {
					 srmod = scoreboard.registerNewTeam("SrMod");
					 srmod.setPrefix(ChatColor.LIGHT_PURPLE + "");
				 }
				 else if(mod == null)
				 {
					 mod = scoreboard.registerNewTeam("Mod");
					 mod.setPrefix(ChatColor.AQUA + "");
				}
				else if(trial == null)
				{
					 trial = scoreboard.registerNewTeam("Trial");
					 trial.setPrefix(ChatColor.YELLOW + "");
				}
				else if(def == null)
				{
					 def = scoreboard.registerNewTeam("Default");
					 def.setPrefix(ChatColor.WHITE + "");
				}
				
				if(a == null)
				{
					a = scoreboard.registerNewTeam("Anonymous");
					a.setPrefix(ChatColor.MAGIC + "");
				}
				
				if(ScenarioEvents.anon == true)
				{
					for(Player player : Bukkit.getOnlinePlayers())
					{
					    if(owner.hasPlayer(player))
					    {
					    	owner.removeEntry(player.getName());
					    }
					    else if(admin.hasPlayer(player))
					    {
					    	admin.removeEntry(player.getName());
					    }
					    else if(srmod.hasPlayer(player))
					    {
					    	srmod.removeEntry(player.getName());
					    }
					    else if(mod.hasPlayer(player))
					    {
					    	mod.removeEntry(player.getName());
					    }
					    else if(trial.hasPlayer(player))
					    {
					    	trial.removeEntry(player.getName());
					    }
					    else if(def.hasPlayer(player))
					    {
					    	def.removeEntry(player.getName());
					    }
					    					
					    a.addEntry(player.getPlayerListName());
					    a.setPrefix(ChatColor.MAGIC + "");
					}
				}
				
				if(Gamemanager.teamsize == 1)
				{
					for(Player player : Bukkit.getOnlinePlayers())
					{
						if(player.hasPermission("tab.owner"))
						{
							owner.setPrefix(ChatColor.DARK_RED + "");
							
							if(!owner.hasEntry(player.getDisplayName()))
							{
								owner.addEntry(player.getDisplayName());
							}
						}
						else if(player.hasPermission("tab.admin"))
						{
							admin.setPrefix(ChatColor.RED + "");
							
							if(!admin.hasEntry(player.getDisplayName()))
							{
								admin.addEntry(player.getDisplayName());
							}
						}
						else if(player.hasPermission("tab.srmod"))
						{
							if(!srmod.hasEntry(player.getDisplayName()))
							{
								srmod.addEntry(player.getDisplayName());
							}
						}
						else if(player.hasPermission("tab.mod"))
						{
							if(!mod.hasEntry(player.getDisplayName()))
							{
								mod.addEntry(player.getDisplayName());
							}
						}
						else if(player.hasPermission("tab.trial"))
						{
							if(!trial.hasEntry(player.getDisplayName()))
							{
								trial.addEntry(player.getDisplayName());
							}
						}
						else if(player.hasPermission("tab.default"))
						{
							if(!def.hasEntry(player.getDisplayName()))
							{
								def.addEntry(player.getDisplayName());
							}
						}
					}
				}
				else
				{
					for(Player player : Bukkit.getOnlinePlayers())
					{
						if(CreateTeam.playerTeams.containsKey(player.getDisplayName()))
						{
							if(owner.hasPlayer(player))
					    	{
					    		owner.removeEntry(player.getName());
					    	}
					    	else if(admin.hasPlayer(player))
					    	{
					    		admin.removeEntry(player.getName());
					    	}
					    	else if(srmod.hasPlayer(player))
					    	{
					    		srmod.removeEntry(player.getName());
					    	}
					    	else if(mod.hasPlayer(player))
					    	{
					    		mod.removeEntry(player.getName());
					    	}
					    	else if(trial.hasPlayer(player))
					    	{
					    		trial.removeEntry(player.getName());
					    	}
					    	else if(def.hasPlayer(player))
					    	{
					    		def.removeEntry(player.getName());
					    	}
						}
					}
				}
				
				p.setScoreboard(scoreboard);
	    	 }
	    	 
	     }.runTaskTimer(plugin, 0, 20);
	}
	
	public void lobbyscoreboard(Player p) 
	{
		FFAWinner win = new FFAWinner();
        ArrayList<Team> scens = new ArrayList<>();
        
        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Team online = scoreboard.registerNewTeam("online");
        online.addEntry(ChatColor.AQUA + "Players: ");
        online.setSuffix("");
        online.setPrefix("");
        
        Team space = scoreboard.registerNewTeam("space");
        space.addEntry(" ");
        space.setSuffix("");
        space.setPrefix("");
        Team gametype = scoreboard.registerNewTeam("type");
        gametype.addEntry(ChatColor.AQUA + "Size: ");
        gametype.setSuffix("");
        gametype.setPrefix("");
        Team border = scoreboard.registerNewTeam("border");
        border.addEntry(ChatColor.AQUA + "Border: ");
        border.setSuffix("");
        border.setPrefix("");
        Team commands = scoreboard.registerNewTeam("commands");
        commands.addEntry(ChatColor.AQUA + "Commands: ");
        commands.setSuffix("");
        commands.setPrefix("");
        Team host = scoreboard.registerNewTeam("host");
        host.addEntry(ChatColor.AQUA + "Host: ");
        host.setSuffix("");
        host.setPrefix("");
        
        objective.getScore(ChatColor.WHITE + "§m------------------").setScore(10);
        objective.getScore(ChatColor.AQUA + "Players: ").setScore(9);
        objective.getScore("   ").setScore(8);
        objective.getScore(ChatColor.AQUA  + "Host: ").setScore(7);
        objective.getScore(ChatColor.AQUA  + "Size: ").setScore(6);
        objective.getScore(ChatColor.AQUA + "Border: ").setScore(5);
        objective.getScore("").setScore(4);
        objective.getScore(ChatColor.AQUA  + "Commands: ").setScore(3);
        objective.getScore(ChatColor.WHITE+ "/scen /config /helpop").setScore(2);
        objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
        objective.getScore(ChatColor.AQUA  + "fairuhc.us").setScore(0);
        
        String title = ChatColor.AQUA  + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC";
        objective.setDisplayName(title);

        if(Gamemanager.started == false)
        {
        	new BukkitRunnable() 
            {  
        		String hosts = "";
        		
                public void run()
                {
                	online.setSuffix(ChatColor.WHITE + "" + Bukkit.getOnlinePlayers().size());
                	border.setSuffix(ChatColor.WHITE + "" + me.fairuhc.UHC.border.bordersize);
                	
                	if(Host.host.isEmpty())
                  	{
                  		host.setSuffix(ChatColor.RED + "No host.");
                  	}
                  	else
                  	{
                      	hosts = Host.host.get(0);
                      	host.setSuffix(ChatColor.RED + hosts);
                  	}
                	
                	if(Gamemanager.teamsize == 1)
                	{
                		gametype.setSuffix(ChatColor.WHITE + "FFA");
                	}
                	else if(Gamemanager.teamsize > 1)
                	{
                		gametype.setSuffix(ChatColor.WHITE + "To" + Gamemanager.teamsize);
                	}
                	
                	if(Gamemanager.scatter == true && Gamemanager.teamsize == 1)
                	{
                		FFAscatter(p);
                		cancel();
                		return;
                	}
                	else if(Gamemanager.scatter == true && Gamemanager.teamsize > 1)
                	{
                		Teamscatter(p);
                		cancel();
                		return;
                	}
                	
                	p.setScoreboard(scoreboard);
                }
                
            }.runTaskTimer(plugin, 0, 1);
        }
        else
        {
        	scoreboard(p);
        }
	}
	
	public void FFAscatter(Player p)
	{
        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Team space = scoreboard.registerNewTeam("space");
        space.addEntry(" ");
        space.setSuffix("");
        space.setPrefix("");
        
        Team scat = scoreboard.registerNewTeam("scat");
        scat.addEntry(ChatColor.AQUA + "Scattering Players... ");
        scat.setSuffix("");
        scat.setPrefix("");
        
        objective.getScore(ChatColor.WHITE + "§m------------------").setScore(6);
        objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "           Scatter").setScore(5);
        objective.getScore(" ").setScore(4);
        objective.getScore(ChatColor.AQUA + "Scattering Players... ").setScore(4);
        objective.getScore("").setScore(2);
        objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
        objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);

        String title = ChatColor.AQUA  + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC";
        objective.setDisplayName(title);
        
        if(Gamemanager.started == false)
        {
        	new BukkitRunnable() 
            {  
                public void run()
                {
                	if(Gamemanager.started == true)
                	{
                		scoreboard(p);
                		cancel();
                		return;
                	}
                	
                	p.setScoreboard(scoreboard);
                }
                
            }.runTaskTimer(plugin, 0, 1);
       }
       else
       {
        	scoreboard(p);
       }
	}
	
	public void Teamscatter(Player p)
	{
        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Objective teams = scoreboard.registerNewObjective("player", "teams");
        teams.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        
        Team space = scoreboard.registerNewTeam("space");
        space.addEntry(" ");
        space.setSuffix("");
        space.setPrefix("");
        
        Team teamscat = scoreboard.registerNewTeam("teamscatter");
        teamscat.addEntry(ChatColor.AQUA + "Scattering Teams... ");
        teamscat.setSuffix("");
        teamscat.setPrefix("");
        
        objective.getScore(ChatColor.WHITE + "§m------------------").setScore(6);
        objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "           Scatter").setScore(5);
        objective.getScore(" ").setScore(4);
        objective.getScore(ChatColor.AQUA + "Scattering Teams...").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
        objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);

        String title = ChatColor.AQUA  + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC";
        objective.setDisplayName(title);
        
        if(Gamemanager.started == false)
        {
        	new BukkitRunnable() 
            {  
                public void run()
                {
                	if(Gamemanager.started == true)
                	{
                		scoreboard(p);
                		cancel();
                		return;
                	}
                	
                	p.setScoreboard(scoreboard);
                }
                
            }.runTaskTimer(plugin, 0, 1);
       }
       else
       {
        	teamScoreboard(p);
       }
	}
	
	public void scoreboard(Player p)
	{
		FFAWinner win = new FFAWinner();
		TeamsWinner teamwin = new TeamsWinner(plugin);
		Kills kill = new Kills();
		border b = new border(plugin);
		Timer timer = new Timer(plugin);
		
        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Team time = scoreboard.registerNewTeam("time");
        Team space = scoreboard.registerNewTeam("space");
        Team kills = scoreboard.registerNewTeam("kills");
        
        space.addEntry("");
        space.setSuffix("");
        space.setPrefix("");
        
        kills.addEntry("Kills: §b");
        kills.setSuffix("");
        kills.setPrefix("");
        
        time.addEntry("GameTime: §b");
        time.setSuffix("");
        time.setPrefix("");
        
        objective.getScore("GameTime: §b").setScore(10);
        objective.getScore("").setScore(9);
        objective.getScore("Kills: §b").setScore(8);
        
        String title = ChatColor.GOLD + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC";
        objective.setDisplayName(title);

        new BukkitRunnable() 
        {
        	@Override
        	public void run() 
        	{
        		if(Kills.topkill.containsKey(p.getDisplayName()))
        		{
        			kills.setSuffix("" + Kills.topkill.get(p.getDisplayName()));
        		}
        		else
        		{
        			kills.setSuffix("0");
        		}
        		
        		time.setSuffix(timer.time);
                
        		if(Gamemanager.teamsize == 1)
        		{
        			b.borderShrink();
        			
        			if(win.isWinner() == true)
        			{
        				win.getWinner();
        				endGame(p);
        				Gamemanager.ended = true;
        				cancel();
        				return;
        			}
        		}
        		
        		p.setScoreboard(scoreboard);
        	}   
        	
        }.runTaskTimer(plugin, 0, 20);
        
      
	}
	
	public void teamScoreboard(Player p)
	{
		ScenarioEvents s = new ScenarioEvents(plugin);
		
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective teams = scoreboard.registerNewObjective("player", "teams");
        teams.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		new BukkitRunnable()
		{				
			@SuppressWarnings({ "unused", "deprecation" })
			@Override
			public void run() 
			{
				p.setScoreboard(scoreboard);
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public void endGame(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Objective teams = scoreboard.registerNewObjective("player", "teams");
        teams.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        
        Team reboot = scoreboard.registerNewTeam("time");
        reboot.addEntry("Reboot: §b");
        reboot.setSuffix("");
        reboot.setPrefix("");
        
        objective.getScore("Reboot: §b").setScore(0);
        
        String title = ChatColor.GOLD + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC";
        objective.setDisplayName(title);

        new BukkitRunnable() 
        {
        	int restarttime = 30;

        	@Override
        	public void run() 
        	{
        		if(restarttime == 30)
        		{
        			p.sendMessage(ChatColor.RED + "Server rebooting in " + restarttime + " seconds.");
        		}
        		else if(restarttime == 15)
        		{
        			p.sendMessage(ChatColor.RED + "Server rebooting in " + restarttime + " seconds.");
        		}
        		else if(restarttime == 1)
        		{
        			for(Player player : Bukkit.getOnlinePlayers())
        			{
        				player.kickPlayer(ChatColor.RED + "Reloading world.");
        			}
        		}
        		else if(restarttime == 0)
        		{
        			reboot.setSuffix(restarttime + "");
        			p.sendMessage(ChatColor.RED + "Server rebooting");
        			Gamemanager.started = false;
        			Bukkit.getServer().shutdown();
        			cancel();
        			return;
        		}
        		
        		reboot.setSuffix(restarttime + "");
        		
        		restarttime--;
        		
        		p.setScoreboard(scoreboard);
        	}   
        	
        }.runTaskTimer(plugin, 0, 20);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		
		if(Gamemanager.started == true)
		{
			scoreboard(p);
		}
	}
}
