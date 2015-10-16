package me.i_Jedi.iParticles.Listeners;

import me.i_Jedi.iParticles.Particles.ParticleManager;
import me.i_Jedi.iParticles.PlayerInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PJoinEvent implements Listener {

    //Variables
    private JavaPlugin plugin;
    private boolean isArrowEnabled, isPlayerEnable;

    //Constructor
    public PJoinEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        isArrowEnabled = plugin.getConfig().getBoolean("arrowParticles");
        isPlayerEnable = plugin.getConfig().getBoolean("playerParticles");
    }

    //Event
    @EventHandler
    public void pJoinEvent(PlayerJoinEvent event){
        //Get player and store their particles
        Player player = event.getPlayer();
        PlayerInfo pInfo = new PlayerInfo(player, plugin);
        ParticleManager pm = new ParticleManager(plugin);

        try{
            //Arrow particles
            if(isArrowEnabled){
                if(pInfo.hasArrowParticle()){
                    pm.addArrowPlayer(player, pInfo);
                }
            }
        }catch(NullPointerException npe){} //Do nothing

        try{
            //Player particles
            if(isPlayerEnable){
                if(pInfo.hasPlayerParticle()){
                    pm.addParticlePlayer(player, pInfo);
                }
            }
        }catch(NullPointerException npe){} //Do nothing
    }
}
