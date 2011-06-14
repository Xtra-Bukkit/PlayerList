package Xtra.PlayerList;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.util.config.Configuration;
//import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class PlayerHideCommandExecutor implements CommandExecutor {

	private PlayerList plugin;
	public Configuration config;
	public boolean tellAll;
	public boolean hideNumbers;
	public List<String> hidden = new ArrayList<String>();	
	public PlayerHideCommandExecutor(PlayerList plugin) {
		this.plugin = plugin;
	}

	
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("phide")){
						
			if (sender instanceof Player)
			{
				String toadd;
				toadd = ((Player) sender).getDisplayName();
				
				if(plugin.hidden.contains(toadd)==false)
				{
				plugin.hidden.add(toadd);
		    	plugin.saveConfig();
		    	sender.sendMessage("You are now hidden.");
				System.out.println("Added " + toadd + "to hidden list");
				plugin.getServer().broadcastMessage(toadd + " has left the game.");
				
				return true;
				}
				else
				{sender.sendMessage("Command can only be used by a player which is NOT hidden");}
			
			}
			else
			{
				System.out.println("Command can only be used by a player");
				return true;
			}
			
			
		}
		return true;
		
}



}
