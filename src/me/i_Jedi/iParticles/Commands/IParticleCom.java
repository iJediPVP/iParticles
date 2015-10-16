package me.i_Jedi.iParticles.Commands;

import me.i_Jedi.iParticles.Inventories.MainInventory;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IParticleCom implements CommandExecutor {

    //Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Check for player
        if(sender instanceof Player){
            //Store player and command.
            Player player = (Player) sender;
            String cmd = command.getName().toUpperCase();

            //Check cmd
            if(cmd.equals("IPARTICLES")){
                //Check for perms
                if(player.hasPermission("iparticles.command")){
                    player.openInventory(new MainInventory().getInventory());
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 7, 1);
                }else{
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[iParticles] " + ChatColor.RED + "You do not have permission to use this command.");
                }
            }

        }else{
            sender.sendMessage("This command is only for players!");
        }
        return true;
    }
}
