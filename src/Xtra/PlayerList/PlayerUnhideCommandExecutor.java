package Xtra.PlayerList;

/*
Future may-need imports
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.World;
*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerUnhideCommandExecutor implements CommandExecutor {
	
	private PlayerList plugin;	
	public PlayerUnhideCommandExecutor(PlayerList plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("punhide"))
		{
			String who = null;
			boolean security = false;
			Player player = null;
			
					if (sender instanceof Player)
					{
						//checking op
						Player securitycheck = (Player) sender;
						if (securitycheck.isOp())
						{
							security = true;
						}
						
						else
						{
							securitycheck.sendMessage("You do not have permissions to do this.");	
						}
					}
					
					else
					{
						//for console
						security = true;
					}
								
					if(security ==true)
					{				
						if (args.length == 0)
						{
							if (sender instanceof Player)
							{
								player = (Player) sender;
							}
							else
							{
								System.out.println("Invalid number of arguments. Please add whoever's name you want to unhide after the command");
								return true;
							}
						who = player.getDisplayName();
						}
						
						else if (args.length == 1)
							{
								who = args[0];
							}
						
						else
							{
								if (sender instanceof Player)
								{
									Player tosend = (Player) sender;
									tosend.sendMessage("Invalid number of arguments. Please use /punhide nameofplayer .");
									return true;
								}
								
								else
								{
									System.out.println("Invalid number of arguments. Please use /punhide nameofplayer .");
									return true;
								}
							}
						}
						
						
						if(plugin.hidden.contains(who)==true)
						{
							System.out.println("Started");
							plugin.hidden.remove(who);
					    	plugin.saveConfig();
					    	player.sendMessage("You are now unhidden.");
					    	player.sendMessage(player  + " is now unhidden.");
					    	System.out.println("Added " + who + "to hidden list");
							return true;
						}
						else
						{
							System.out.println("Command can only be used by on a player which is hidden by an op");
							return true;
						}
				
					}	
			else
			{
			System.out.println("Command can only be used by on a player which is hidden by an op");
			return true;
			}	
		}
		}