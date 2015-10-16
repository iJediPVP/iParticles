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

public class PlayerOptionsInventory {

    //Variables
    private Inventory playerOptionsInv = Bukkit.createInventory(null, 27, "Player Pattern List");

    //Constructor
    public PlayerOptionsInventory(){
        //First row

        //Second row
        playerOptionsInv.setItem(12, createItem(Material.DOUBLE_PLANT, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Circle", Arrays.asList(ChatColor.GOLD + "Circle pattern"), false));
        playerOptionsInv.setItem(14, createItem(Material.STRING, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Spiral", Arrays.asList(ChatColor.GOLD + "Spiral pattern"), false));

        //Third row
        playerOptionsInv.setItem(22, createItem(Material.ARROW, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "BACK", Arrays.asList(ChatColor.GOLD + "Return the main menu."), false));
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
        return playerOptionsInv;
    }

    //Get name
    public String getName(){
        return playerOptionsInv.getName();
    }
}
