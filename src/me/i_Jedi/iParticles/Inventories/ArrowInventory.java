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

public class ArrowInventory {

    //Variables
    private Inventory arrowInv = Bukkit.createInventory(null, 54, "Arrow Particles List");

    //Constructor
    public ArrowInventory(){

        //First row
        arrowInv.setItem(11, createItem(Material.FIREWORK, (short) 0, ChatColor.GOLD + "Firework Spark", Arrays.asList(ChatColor.GOLD + "Firework spark particles"), false));
        arrowInv.setItem(12, createItem(Material.TNT, (short) 0, ChatColor.GOLD + "Explosion", Arrays.asList(ChatColor.GOLD + "Explosion particles"), false));
        arrowInv.setItem(13, createItem(Material.RED_ROSE, (short) 0, ChatColor.LIGHT_PURPLE + "Heart", Arrays.asList(ChatColor.GOLD + "Heart particles"), false));
        arrowInv.setItem(14, createItem(Material.FIREWORK_CHARGE, (short) 0, ChatColor.GRAY + "Smoke", Arrays.asList(ChatColor.GOLD + "Smoke particles"), false));
        arrowInv.setItem(15, createItem(Material.SLIME_BALL, (short) 0, ChatColor.GREEN + "Slime", Arrays.asList(ChatColor.GOLD + "Slime particles"), false));

        //Second row
        arrowInv.setItem(20, createItem(Material.WATER_BUCKET, (short) 0, ChatColor.BLUE + "Water", Arrays.asList(ChatColor.GOLD + "Water particles"), false));
        arrowInv.setItem(21, createItem(Material.FLINT_AND_STEEL, (short) 0, ChatColor.RED + "Flame", Arrays.asList(ChatColor.GOLD + "Flame particles"), false));
        arrowInv.setItem(22, createItem(Material.NOTE_BLOCK, (short) 0, ChatColor.GREEN + "Note", Arrays.asList(ChatColor.GOLD + "Music note particles"), false));
        arrowInv.setItem(23, createItem(Material.SNOW_BLOCK, (short) 0, ChatColor.AQUA + "Snow", Arrays.asList(ChatColor.GOLD + "Snow break particles"), false));
        arrowInv.setItem(24, createItem(Material.STAINED_GLASS, (short) 0, ChatColor.WHITE + "Cloud", Arrays.asList(ChatColor.GOLD + "Cloud particles"), false));

        //Third row
        arrowInv.setItem(29, createItem(Material.POTION, (short) 0, ChatColor.DARK_PURPLE + "Potion", Arrays.asList(ChatColor.GOLD + "Potion particles"), false));
        arrowInv.setItem(30, createItem(Material.POTION, (short) 0, ChatColor.DARK_PURPLE + "Potion " + ChatColor.translateAlternateColorCodes('$', "$9R$aa$bi$cn$db$eo$fw"), Arrays.asList(ChatColor.GOLD + "Rainbow potion particles"), true));
        arrowInv.setItem(32, createItem(Material.POTION, (short) 16385, ChatColor.LIGHT_PURPLE + "Splash Potion " + ChatColor.WHITE + "White", Arrays.asList(ChatColor.GOLD + "White splash potion particles"), true));
        arrowInv.setItem(33, createItem(Material.POTION, (short) 16430, ChatColor.LIGHT_PURPLE + "Splash Potion " + ChatColor.DARK_PURPLE + "Purple", Arrays.asList(ChatColor.GOLD + "Purple splash potion particles"), true));

        //Fourth row

        //Fifth row
        arrowInv.setItem(48, createItem(Material.ARROW, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "BACK", Arrays.asList(ChatColor.GOLD + "Return the main menu"), false));
        arrowInv.setItem(50, createItem(Material.GLASS, (short) 0, ChatColor.RED + "" + ChatColor.BOLD + "CLEAR", Arrays.asList(ChatColor.GOLD + "Remove your arrow particles"), false));

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

    //Get inv
    public Inventory getInventory(){
        return arrowInv;
    }

    //Get name
    public String getName(){
        return arrowInv.getName();
    }

}
