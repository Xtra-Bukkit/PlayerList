package Xtra.PlayerList;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class PlayerClearCommandExecutor implements CommandExecutor {
	private PlayerList plugin;
	public Configuration config;
	public boolean tellAll;
	public boolean hideNumbers;
	public List<String> hidden = new ArrayList<String>();	

	
	public PlayerClearCommandExecutor(PlayerList plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("pclear")){
			if (sender instanceof Player)
			{
				Player player = (Player) sender;				
				if (player.isOp())
				{
					plugin.hidden.clear();
					plugin.saveConfig();
					System.out.println("Cleared hidden player list.");
			    	sender.sendMessage("Cleared hidden player list.");
				}
			}
			
			else
			{
				System.out.println("Started");
				plugin.hidden.clear();
				plugin.saveConfig();
				System.out.println("Cleared");				
			}
		}
		return true;

	}
}
