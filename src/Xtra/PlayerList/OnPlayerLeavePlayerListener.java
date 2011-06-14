package Xtra.PlayerList;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeavePlayerListener extends PlayerListener
{
        public static PlayerList plugin;
 
        public OnPlayerLeavePlayerListener( PlayerList instance )
        {//Constructor
                plugin = instance;
        }
 
        public void onPlayerQuit( PlayerQuitEvent e )
        {//PLAYER_QUIT event handler
                
        		
        	if(plugin.unhideOnLeave==true)
        	{
        		Player ply = e.getPlayer();
				String toadd = ((Player) ply).getDisplayName();
				if(plugin.hidden.contains(toadd))
				{
				plugin.hidden.remove(toadd);
		    	plugin.saveConfig();
		    	ply.sendMessage("Player" + toadd + "disconnected and was removed from hidden list");
				}
        
        	}
        }
}