/*TODO LIST
Add sounds to mainInv kill click & to particle selection in killInv
Add more kill effects
Clean up code
* */

package me.i_Jedi.iParticles;

import me.i_Jedi.iParticles.Commands.IParticleCom;
import me.i_Jedi.iParticles.Listeners.*;
import me.i_Jedi.iParticles.Particles.ParticleManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    //Enabled
    @Override
    public void onEnable(){
        //Config
        saveDefaultConfig();

        //Check if particles are enabled, if they are call their events and store all online player particles
        ParticleManager pm = new ParticleManager(this);
        boolean isArrow = getConfig().getBoolean("arrowParticles");
        boolean isPlayer = getConfig().getBoolean("playerParticles");
        boolean isKill = getConfig().getBoolean("killParticles");
        for(Player player : Bukkit.getOnlinePlayers()){
            PlayerInfo pInfo = new PlayerInfo(player, this);

            //ARROW
            try{
                if(isArrow){
                    if(pInfo.hasArrowParticle()){
                        pm.addArrowPlayer(player, pInfo);
                    }
                }
            }catch(NullPointerException npe){} //Do nothing
            //PLAYER
            try{
                if(isPlayer){
                    if(pInfo.hasPlayerParticle()){
                        pm.addParticlePlayer(player, pInfo);
                    }
                }
            }catch(NullPointerException npe){} //Do nothing
        }

        //Check if anything is enabled
        if(isArrow || isPlayer){
            pm.start();
        }

        //Get commands
        getCommand("IPARTICLES").setExecutor(new IParticleCom());

        //Call events
        new ArrowEvents(this);
        new InvClickEvent(this);
        new PJoinEvent(this);
        new PKillEvent(this);

        //Log
        getLogger().info("iParticles has been enabled!");
    }

    //Disabled
    @Override
    public void onDisable(){
        //Log
        getLogger().info("iParticles has been enabled!");
    }


}
