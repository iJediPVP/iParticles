package me.i_Jedi.iParticles.Listeners;

import me.i_Jedi.iParticles.PlayerInfo;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PKillEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PKillEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void playerKill(EntityDeathEvent event){
        //Check if kill particles are enabled
        if(plugin.getConfig().getBoolean("killParticles")){
            //Check for player kill
            try{
                if(event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof Player){
                    //Check if the player has a kill particle
                    final Entity entity = event.getEntity();
                    Player player = event.getEntity().getKiller();

                    final PlayerInfo pInfo = new PlayerInfo(player, plugin);
                    if(pInfo.hasKillParticle()){
                        //Start timer
                        new BukkitRunnable(){
                            int locCount = 0;
                            List<Location> locList = pInfo.getKillLocations(entity.getLocation());
                            EnumParticle particle = pInfo.getKillParticle();
                            float mod = pInfo.getKillMod();
                            int count = pInfo.getKillCount();

                            @Override
                            public void run() {
                                if(locCount < locList.size()){
                                    Location l = locList.get(locCount);
                                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true,
                                            (float) l.getX(), (float) l.getY(), (float) l.getZ(), 0f, 0f, 0f, mod, count);
                                    //Send to players within 48 blocks
                                    for(Entity e : entity.getNearbyEntities(48, 48, 48)){
                                        if(e instanceof Player){
                                            Player p = (Player) e;
                                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                                        }
                                    }
                                    locCount++;
                                }
                            }
                        }.runTaskTimer(plugin, 0l, 1l);
                    }

                }
            }catch(NullPointerException npe){} //Do nothing
        }
    }
}
