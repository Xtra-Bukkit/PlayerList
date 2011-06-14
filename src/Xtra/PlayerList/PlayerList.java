package Xtra.PlayerList;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class PlayerList extends JavaPlugin{
	public Configuration config;
	public boolean tellAll = true;
	public boolean hideNumbers = false;
	public boolean unhideOnLeave = true;
	public List<String> hidden = new ArrayList<String>();
    private PlayerListener playerListener;
    private PluginManager pm;
    
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println("[" + pdfFile.getName() + "] has been disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
	    System.out.println("[" + pdfFile.getName() + "] version [" + pdfFile.getVersion() + "] is enabled!");

	    //On playerleave
	    pm = this.getServer().getPluginManager();
	    	playerListener = new OnPlayerLeavePlayerListener( this ); 
	    	pm.registerEvent( Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this );
        
	    //Printing player list
	    PlayerListCommandExecutor executor = new PlayerListCommandExecutor(this);
			this.getCommand("players").setExecutor(executor);
			
		//Hiding the sender. Can only be used by players	
	    PlayerHideCommandExecutor exehide = new PlayerHideCommandExecutor(this);			
			this.getCommand("phide").setExecutor(exehide);

		//Unhiding the sender. Can only be used by players	
	    PlayerShowCommandExecutor exeshow = new PlayerShowCommandExecutor(this);			
			this.getCommand("pshow").setExecutor(exeshow);			

		//Clearing hidden player list
	    PlayerClearCommandExecutor execlear = new PlayerClearCommandExecutor(this);
			this.getCommand("pclear").setExecutor(execlear);

		//Unhiding a certain person
	    PlayerUnhideCommandExecutor exeunhide = new PlayerUnhideCommandExecutor(this);
			this.getCommand("punhide").setExecutor(exeunhide);			
			
		config = this.getConfiguration();
		this.loadConfig();
	    
	}
	
	private void loadConfig() {
    	config.load();
    	
    	tellAll = config.getBoolean("TellAll", true);
    	hideNumbers = config.getBoolean("HideNumberOfPlayers", false);
    	unhideOnLeave = config.getBoolean("unhideOnLeave", true);    	
		hidden = config.getStringList("HiddenPlayers", new ArrayList<String>());
    	
		this.saveConfig();
	}
    
    public void saveConfig() {
    	
    	config.setProperty("TellAll", tellAll);
    	config.setProperty("HideNumberOfPlayers", hideNumbers);
    	config.setProperty("unhideOnLeave", unhideOnLeave);    	
    	config.setProperty("HiddenPlayers", hidden);
    	
		config.save();
	}
    
}
