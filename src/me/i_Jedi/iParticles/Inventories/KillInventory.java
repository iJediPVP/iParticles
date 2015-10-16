package me.i_Jedi.iParticles.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class KillInventory {

    //Variables
    private Inventory killInv = Bukkit.createInventory(null, 54, "Kill Particle List");

    //Constructor
    public KillInventory(){

        //First row

        //Second row
        killInv.setItem(11, createItem(Material.EMERALD, (short) 0, ChatColor.LIGHT_PURPLE + "Villager Happy", Arrays.asList(ChatColor.GOLD + "Green villager particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bNone")), false));

        //Third row

        //Fourth row

        //Fifth row

        //Sixth row
        killInv.setItem(48, createItem(Material.ARROW, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "BACK", Arrays.asList(ChatColor.GOLD + "Return the main menu"), false));
        killInv.setItem(50, createItem(Material.GLASS, (short) 0, ChatColor.RED + "" + ChatColor.BOLD + "CLEAR", Arrays.asList(ChatColor.GOLD + "Remove your kill particles"), false));
    }

    //Create items
    private ItemStack createItem(Material material, short ID, String displayName, List<String> loreList, boolean enchant){
        ItemStack iStack = new ItemStack(material, 1, ID);
        ItemMeta iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(displayName);
        iMeta.setLore(loreList);
        if(enchant){
            iMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            iMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            iMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        }
        iStack.setItemMeta(iMeta);
        return iStack;
    }

    //Get name
    public String getName(){
        return killInv.getName();
    }

    //Get inventory
    public Inventory getInventory() {
        return killInv;
    }
}
