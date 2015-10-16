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

public class PlayerInventory {

    //Variables
    private Inventory playerInv = Bukkit.createInventory(null, 54, "Player Particles List");

    //Constructor
    public PlayerInventory(){
        //First row

        //Second row
        playerInv.setItem(11, createItem(Material.ENCHANTMENT_TABLE, (short) 0, ChatColor.LIGHT_PURPLE + "Enchanting", Arrays.asList(ChatColor.GOLD + "Enchanting table particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bNone")), false));
        playerInv.setItem(12, createItem(Material.POTION, (short) 0, ChatColor.DARK_PURPLE + "Potion", Arrays.asList(ChatColor.GOLD + "Potion particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bNone")), false));
        playerInv.setItem(13, createItem(Material.POTION, (short) 0, ChatColor.DARK_PURPLE + "Potion " + ChatColor.translateAlternateColorCodes('$', "$l$9R$aa$bi$cn$db$eo$fw"), Arrays.asList(ChatColor.GOLD + "Rainbow potion particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bNone")), false));
        playerInv.setItem(14, createItem(Material.REDSTONE_BLOCK, (short) 0, ChatColor.RED + "Redstone", Arrays.asList(ChatColor.GOLD + "Redstone particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $bSpiral")), false));
        playerInv.setItem(15, createItem(Material.REDSTONE_BLOCK, (short) 0, ChatColor.RED + "Redstone " + ChatColor.translateAlternateColorCodes('$', "$9R$aa$bi$cn$db$eo$fw"), Arrays.asList(ChatColor.GOLD + "Rainbow redstone particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $bSpiral")), false));

        //Third row
        playerInv.setItem(20, createItem(Material.FLINT_AND_STEEL, (short) 0, ChatColor.GOLD + "Flame", Arrays.asList(ChatColor.GOLD + "Flame particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $b Spiral")), false));
        playerInv.setItem(21, createItem(Material.WATER_BUCKET, (short) 0, ChatColor.BLUE + "Water Drip", Arrays.asList(ChatColor.GOLD + "Dripping water particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $bSpiral")), false));
        playerInv.setItem(22, createItem(Material.LAVA_BUCKET, (short) 0, ChatColor.DARK_RED + "Lava Drip", Arrays.asList(ChatColor.GOLD + "Dripping lava particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $bSpiral")), false));
        playerInv.setItem(23, createItem(Material.EMERALD, (short) 0, ChatColor.GREEN + "Villager Happy", Arrays.asList(ChatColor.GOLD + "Green villager particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle$6, $bSpiral")), false));
        playerInv.setItem(24, createItem(Material.STAINED_GLASS, (short) 0, ChatColor.WHITE + "Cloud", Arrays.asList(ChatColor.GOLD + "Cloud particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle")), false));

        //Fourth row
        playerInv.setItem(30, createItem(Material.JUKEBOX, (short) 0, ChatColor.DARK_GREEN + "Note", Arrays.asList(ChatColor.GOLD + "Music note particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle")), false));
        playerInv.setItem(31, createItem(Material.RED_ROSE, (short) 0, ChatColor.RED + "Heart", Arrays.asList(ChatColor.GOLD + "Heart particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bCircle")), false));
        playerInv.setItem(32, createItem(Material.NETHERRACK, (short) 0, ChatColor.DARK_PURPLE + "Nether Portal", Arrays.asList(ChatColor.GOLD + "Nether portal particles", ChatColor.translateAlternateColorCodes('$', "$6Patterns: $bNone")), false));

        //Fifth row

        //Sixth row
        playerInv.setItem(48, createItem(Material.ARROW, (short) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "BACK", Arrays.asList(ChatColor.GOLD + "Return the main menu"), false));
        playerInv.setItem(50, createItem(Material.GLASS, (short) 0, ChatColor.RED + "" + ChatColor.BOLD + "CLEAR", Arrays.asList(ChatColor.GOLD + "Remove your particles"), false));
        playerInv.setItem(53, createItem(Material.COMMAND, (short) 0, ChatColor.YELLOW + "" + ChatColor.BOLD + "OPTIONS", Arrays.asList(ChatColor.GOLD + "Change your particle pattern"), false));

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
        return playerInv;
    }

    //Get name
    public String getName(){
        return playerInv.getName();
    }
}
