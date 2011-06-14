package Xtra.PlayerList;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.util.config.Configuration;
//import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class PlayerShowCommandExecutor implements CommandExecutor {

	private PlayerList plugin;
	public Configuration config;
	public boolean tellAll;
	public boolean hideNumbers;
	public List<String> hidden = new ArrayList<String>();	
	public PlayerShowCommandExecutor(PlayerList plugin) {
		this.plugin = plugin;
	}

	
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("pshow")){
						
			if (sender instanceof Player)
			{
				String toadd;
				toadd = ((Player) sender).getDisplayName();
				
				if(plugin.hidden.contains(toadd)==true)
				{
				System.out.println("Started");
				plugin.hidden.remove(toadd);
		    	plugin.saveConfig();
		    	sender.sendMessage("You are now unhidden.");
				System.out.println("Removed " + toadd + " from hidden list");
				plugin.getServer().broadcastMessage(toadd + " has joined the game.");
				return true;
			
				}
				
				else
				{
					System.out.println("Command can only be used by a player which is hidden");
					return true;
				}			
			}
			else
			{
				System.out.println("Command can only be used by a player which is hidden");
				return true;
			}
			
			
		}
		return true;
		
}



}
