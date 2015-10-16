package me.i_Jedi.iParticles.Listeners;

import me.i_Jedi.iParticles.Particles.ParticleManager;
import me.i_Jedi.iParticles.PlayerInfo;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ArrowEvents implements Listener{

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public ArrowEvents(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Arrow shoot
    @EventHandler
    public void arrowShoot(EntityShootBowEvent event){
        //Check for player & arrow
        if(event.getEntity() instanceof Player && event.getProjectile() instanceof Arrow){
            //Store player
            Player player = (Player) event.getEntity();
            PlayerInfo pInfo = new PlayerInfo(player, plugin);
            //Get particles for player
            try{
                if(pInfo.hasArrowParticle()){
                    //Store arrow and player in ParticleManager
                    ParticleManager pm = new ParticleManager(plugin);
                    pm.addArrowPlayer(player, pInfo);
                    pm.addProjectile((Projectile) event.getProjectile());
                }else{
                    return;
                }
            }catch(NullPointerException npe){}
        }
    }

    //Arrow land
    @EventHandler
    public void arrowLand(ProjectileHitEvent event){
        //Check for arrow
        if(event.getEntity() instanceof Arrow){
            //Remove arrow from ParticleManager
            new ParticleManager(plugin).removeProjectile(event.getEntity());
        }
    }
}
