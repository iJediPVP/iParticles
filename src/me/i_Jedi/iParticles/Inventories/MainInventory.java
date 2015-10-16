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

public class MainInventory {

    //Variables
    private Inventory mainInv = Bukkit.createInventory(null, 27, "Particles Menu");

    //Constructor
    public MainInventory(){
        //Particles options
        mainInv.setItem(12, createItem(Material.BOW, (short) 0, ChatColor.GREEN + "" + ChatColor.BOLD + "Arrow Particles", Arrays.asList(ChatColor.GOLD + "Change bow particles"), false));
        mainInv.setItem(14, createItem(Material.ARMOR_STAND, (short) 0, ChatColor.AQUA + "" + ChatColor.BOLD + "Player Particles", Arrays.asList(ChatColor.GOLD + "Change player particles"), false));

        //Exit
        mainInv.setItem(22, createItem(Material.ARROW, (short) 0, ChatColor.RED + "" + ChatColor.BOLD + "EXIT", Arrays.asList(ChatColor.GOLD + "Close this menu"), false));
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

    //Get inventory
    public Inventory getInventory(){
        return mainInv;
    }

    //Get name
    public String getName(){
        return mainInv.getName();
    }
}
