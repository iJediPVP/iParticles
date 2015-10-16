package me.i_Jedi.iParticles.Listeners;

import me.i_Jedi.iParticles.Inventories.*;
import me.i_Jedi.iParticles.Particles.Particles;
import me.i_Jedi.iParticles.Particles.ParticleManager;
import me.i_Jedi.iParticles.PlayerInfo;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InvClickEvent implements Listener {

    //Variables
    private JavaPlugin plugin;
    private String mainName, arrowName, playerName, playerOpName, killName;

    //Constructor
    public InvClickEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        //Get names
        mainName = new MainInventory().getName();
        arrowName = new ArrowInventory().getName();
        playerName = new PlayerInventory().getName();
        playerOpName = new PlayerOptionsInventory().getName();
        killName = new KillInventory().getName();
    }

    //Event
    @EventHandler
    public void invClick(InventoryClickEvent event){
        //Check for player
        if(event.getWhoClicked() instanceof Player){
            //Store player & inventory name
            Player player = (Player) event.getWhoClicked();
            String invName = ChatColor.stripColor(event.getInventory().getName());

            //Get itemName
            String itemName;
            try{
                itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
            }catch(NullPointerException npe){
                return;
            }

            //Check invName
            //MAIN INVENTORY
            if(invName.equals(mainName)){

                //Check itemName
                if(itemName.equals("Arrow Particles")){
                    //Check for perms
                    if(player.hasPermission("iparticles.arrow")){
                        //Open
                        player.openInventory(new ArrowInventory().getInventory());
                        player.playSound(player.getLocation(), Sound.SHOOT_ARROW, 7, 1);
                    }else{
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " + ChatColor.RED + "You do not have permission to use this.");
                    }

                    event.setCancelled(true);
                }else if(itemName.equals("Player Particles")) {
                    //Check for perms
                    if (player.hasPermission("iparticles.player")) {
                        //Open
                        player.openInventory(new PlayerInventory().getInventory());
                        player.playSound(player.getLocation(), Sound.VILLAGER_IDLE, 7, 1);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " + ChatColor.RED + "You do not have permission to use this.");
                    }

                    event.setCancelled(true);
                }else if(itemName.equals("Kill Particles")){
                    //Check for perms
                    if(player.hasPermission("iparticles.kill")){
                        //Open
                        player.openInventory(new KillInventory().getInventory());
                        //SOUND HERE
                    }else{
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " + ChatColor.RED + "You do not have permission to use this.");
                    }
                    event.setCancelled(true);
                }else if(itemName.equals("EXIT")){
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);

                    event.setCancelled(true);
                }

            //ARROW INVENTORY
            }else if(invName.equals(arrowName)){

                //Check itemName
                if(itemName.equals("BACK")){
                    //Return to mainInv
                    player.openInventory(new MainInventory().getInventory());
                    Location loc = player.getLocation();
                    PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("random.click", loc.getX(), loc.getY(), loc.getZ(), 1, 63);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sound);
                    event.setCancelled(true);

                }else if(itemName.equals("CLEAR")){
                    //Remove arrow particles from player
                    new PlayerInfo(player, plugin).setArrowParticle(itemName);
                    //Remove from particle manager & send message
                    new ParticleManager(plugin).removeArrowPlayer(player);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Your arrow particles have been reset!");
                    player.playSound(player.getLocation(), Sound.SPLASH, 7, 1);
                    event.setCancelled(true);

                }else{
                    PlayerInfo pInfo = new PlayerInfo(player, plugin);
                    pInfo.setArrowParticle(itemName);

                    //Add to particle manager & send message
                    new ParticleManager(plugin).addArrowPlayer(player, pInfo);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Arrow particles set to: " +event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GOLD + "!");
                    player.playSound(player.getLocation(), Sound.ARROW_HIT, 7, 1);
                    event.setCancelled(true);
                }

            //PLAYER INVENTORY
            }else if(invName.equals(playerName)){

                //Check itemName
                if(itemName.equals("BACK")){
                    //Return to mainInv
                    player.openInventory(new MainInventory().getInventory());
                    Location loc = player.getLocation();
                    PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("random.click", loc.getX(), loc.getY(), loc.getZ(), 1, 63);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sound);
                    event.setCancelled(true);
                    return;
                }else if(itemName.equals("OPTIONS")){
                    //Check if the player has a particle selected
                    PlayerInfo pInfo = new PlayerInfo(player, plugin);
                    Particles particles = new Particles(plugin);
                    if(pInfo.hasPlayerParticle()){
                        //Check for stillParticle, circleParticle, and spiralParticle
                        if(!particles.getStillPlayerList().contains(pInfo.getPlayerParticle()) &&
                                !particles.getCirclePlayerList().contains(pInfo.getPlayerParticle()) &&
                                !particles.getSpiralPlayerList().contains(pInfo.getPlayerParticle())){
                            //Open options
                            player.openInventory(new PlayerOptionsInventory().getInventory());
                            player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 7, 1);
                        }else{ //Can't set pattern for particles with a fixed pattern.
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                                    ChatColor.GOLD + "You cannot set the pattern for this particle!");
                            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 7, 1);
                        }

                    }else{
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                                ChatColor.GOLD + "You must select a particle first!");
                    }
                    event.setCancelled(true);
                    return;
                }else if(itemName.equals("CLEAR")){
                    //Remove particles from player
                    new PlayerInfo(player, plugin).setPlayerParticle(itemName);

                    //Remove from ParticleManager & send message
                    new ParticleManager(plugin).removeParticlePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Your particles have been reset!");
                    player.playSound(player.getLocation(), Sound.SPLASH2, 7, 1);
                    event.setCancelled(true);
                    return;
                }

                //ELSE save the particles to the player
                PlayerInfo pInfo = new PlayerInfo(player, plugin);
                pInfo.setPlayerParticle(itemName);

                //Add to ParticleManager & send message
                new ParticleManager(plugin).addParticlePlayer(player, pInfo);
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                        ChatColor.GOLD + "Particles set to: " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GOLD + "!");
                player.playSound(player.getLocation(), Sound.VILLAGER_YES, 7, 1);
                event.setCancelled(true);

            //PLAYER OPTIONS
            }else if(invName.equals(playerOpName)){

                //Check itemName
                if(itemName.equals("BACK")){
                    //Return to playerInv
                    player.openInventory(new PlayerInventory().getInventory());
                    Location loc = player.getLocation();
                    PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("random.click", loc.getX(), loc.getY(), loc.getZ(), 1, 63);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sound);
                }else if(itemName.equals("Circle")){
                    //Store pattern and add to particle manager
                    PlayerInfo pInfo = new PlayerInfo(player, plugin);
                    pInfo.setPlayerCircle();
                    new ParticleManager(plugin).addParticlePlayer(player, pInfo);
                    player.playSound(player.getLocation(), Sound.VILLAGER_YES, 7, 1);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Particle pattern set to: " + event.getCurrentItem().getItemMeta().getDisplayName());
                }else if(itemName.equals("Spiral")){
                    //Store pattern and add it to particle manager
                    PlayerInfo pInfo = new PlayerInfo(player, plugin);
                    pInfo.setPlayerSpiral();
                    new ParticleManager(plugin).addParticlePlayer(player, pInfo);
                    player.playSound(player.getLocation(), Sound.VILLAGER_YES, 7, 1);
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Particle pattern set to: " + event.getCurrentItem().getItemMeta().getDisplayName());
                }
                event.setCancelled(true);

            //KILL INVENTORY
            }else if(invName.equals(killName)){

                //Check itemName
                if(itemName.equals("BACK")){
                    //Return to main inv
                    player.openInventory(new MainInventory().getInventory());
                    Location loc = player.getLocation();
                    PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("random.click", loc.getX(), loc.getY(), loc.getZ(), 1, 63);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sound);
                }else if(itemName.equals("CLEAR")){
                    //Remove particles from player
                    new PlayerInfo(player, plugin).setKillParticle(itemName);

                    //Remove from ParticleManager & send message
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                            ChatColor.GOLD + "Your kill particles have been reset!");
                    player.playSound(player.getLocation(), Sound.SPLASH, 7, 1);
                    event.setCancelled(true);
                    return;
                }
                //ELSE save the particles to the player
                PlayerInfo pInfo = new PlayerInfo(player, plugin);
                pInfo.setKillParticle(itemName);

                //Add to ParticleManager & send message
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " +
                        ChatColor.GOLD + "Kill Particles set to: " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GOLD + "!");
                //SOUND HERE
                event.setCancelled(true);
            }
        }
    }
}
