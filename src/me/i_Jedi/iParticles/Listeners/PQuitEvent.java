package me.i_Jedi.iParticles.Listeners;

import me.i_Jedi.iParticles.Particles.ParticleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PQuitEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PQuitEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void pQuitEvent(PlayerQuitEvent event){
        //Get player and remove them from particle manager HMs
        Player player = event.getPlayer();
        ParticleManager pm = new ParticleManager(plugin);

        pm.removeArrowPlayer(player);
        pm.removeParticlePlayer(player);
    }
}
