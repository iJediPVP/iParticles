package me.i_Jedi.iParticles.Particles;

import me.i_Jedi.iParticles.PlayerInfo;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParticleManager {

    //Variables
    private final JavaPlugin plugin;
    private static boolean hasStarted = false;

    //Arrow
    private static List<Projectile> projectileList = new ArrayList<>();
    private static HashMap<Player, PlayerInfo> arrowPlayerHM = new HashMap<>();

    //Player
    private static HashMap<Player, PlayerInfo> playerParticleHM = new HashMap<>();

    //Constructor
    public ParticleManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    // ******************* ARROW STUFF *******************
    //Remove projectile
    public void removeProjectile(Projectile projectile){
        if(projectileList.contains(projectile)){
            projectileList.remove(projectile);
        }
    }

    //Add projectile
    public void addProjectile(Projectile projectile){
        if(!projectileList.contains(projectile)){
            projectileList.add(projectile);
        }
    }

    //Add player
    public void addArrowPlayer(Player player, PlayerInfo playerInfo){
        arrowPlayerHM.put(player, playerInfo);

    }

    //Remove player
    public void removeArrowPlayer(Player player){
        if(arrowPlayerHM.containsKey(player)){
            arrowPlayerHM.remove(player);
        }
    }

    // ******************* PLAYER STUFF *******************
    //Remove from body list
    public void removeParticlePlayer(Player player){
        if(playerParticleHM.containsKey(player)){
            playerParticleHM.remove(player);
        }
    }

    //Add player to body list
    public void addParticlePlayer(Player player,PlayerInfo playerInfo){
        playerParticleHM.put(player, playerInfo);
    }

    // ******************* TIMER STUFF *******************
    public void start() throws UnsupportedOperationException{
        //Check that it has already started.
        if(!hasStarted){
            hasStarted = true;
            final Particles particles = new Particles(plugin);

            //Configs
            final FileConfiguration arrowConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder() + "/arrowConfig.yml"));
            final FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder() + "/playerConfig.yml"));

            //Arrow Particles
            new BukkitRunnable(){
                @Override
                public void run() {
                    //Loop through projectiles
                    for (Projectile arrow : projectileList) {

                        //Get shooter particle
                        Player shooter = (Player) arrow.getShooter(); //Checked for player in event..

                        //Check if the player has a particle
                        if (arrowPlayerHM.containsKey(shooter)) {
                            PlayerInfo pInfo = arrowPlayerHM.get(shooter);
                            if (pInfo.hasArrowParticle()) {
                                EnumParticle shooterParticle = pInfo.getArrowParticle();

                                //Get particle config
                                float mod = pInfo.getArrowMod();
                                int count = pInfo.getArrowCount();
                                PacketPlayOutWorldParticles particlePacket = new PacketPlayOutWorldParticles(shooterParticle,
                                        true, (float) arrow.getLocation().getX(), (float) (arrow.getLocation().getY() + .25), (float) arrow.getLocation().getZ(),
                                        0f, 0f, 0f, mod, count);

                                //Send packet to nearby players
                                for (Entity e : arrow.getNearbyEntities(50, 50, 50)) {
                                    if (e instanceof Player) {
                                        Player p = (Player) e;
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(particlePacket);
                                    }
                                }
                            }else { //Else go to next arrow
                                removeArrowPlayer(shooter);
                            }
                        } //Else go to next arrow
                    }
                }
            }.runTaskTimer(plugin, 0l, 2l);

            //Player particles
            new BukkitRunnable(){
                int count = 0;
                @Override
                public void run() {
                    //Loop through players in playerlist
                    for (Player player : playerParticleHM.keySet()) {
                        PlayerInfo pInfo = playerParticleHM.get(player);
                        //Try to get player's particle
                        try {

                            EnumParticle particle = pInfo.getPlayerParticle();

                            //Get location
                            Location location;
                            try {
                                location = pInfo.getNextLocationPlayer(count);
                                count++;
                            } catch (IndexOutOfBoundsException e) {

                                count = 0;
                                location = pInfo.getNextLocationPlayer(count);
                            }

                            //Create packet
                            float mod = pInfo.getPlayerMod();
                            int count = pInfo.getPlayerCount();
                            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) location.getX(), (float) location.getY(),
                                    (float) location.getZ(), 0f, 0f, 0f, mod, count);
                            //Send to player's withing 32 blocks
                            for (Entity e : player.getNearbyEntities(32, 32, 32)) {
                                if (e instanceof Player) {
                                    Player p = (Player) e;
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                                }
                            }
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                        } catch (NullPointerException npe) {
                        }
                    }
                }
            }.runTaskTimer(plugin, 0l, 3l);

        }else{
            throw new UnsupportedOperationException("ParticleManager - Timer is already started.");
        }
    }

}
