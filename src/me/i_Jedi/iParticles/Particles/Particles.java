package me.i_Jedi.iParticles.Particles;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Particles {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public Particles(JavaPlugin plugin){
        this.plugin = plugin;

    }

    //*************** ARROW ***************
    private HashMap<String, EnumParticle> arrowList = new HashMap<String, EnumParticle>(){{
        put("Firework Spark", EnumParticle.FIREWORKS_SPARK);
        put("Water", EnumParticle.WATER_SPLASH);
        put("Cloud", EnumParticle.CLOUD);
        put("Flame", EnumParticle.FLAME);
        put("Explosion", EnumParticle.EXPLOSION_NORMAL);
        put("Heart", EnumParticle.HEART);
        put("Note", EnumParticle.NOTE);
        put("Slime", EnumParticle.SLIME);
        put("Smoke", EnumParticle.SMOKE_NORMAL);
        put("Snow", EnumParticle.SNOW_SHOVEL);
        put("Potion", EnumParticle.SPELL);
        put("Potion Rainbow", EnumParticle.SPELL_MOB);
        put("Splash Potion White", EnumParticle.SPELL_INSTANT);
        put("Splash Potion Purple", EnumParticle.SPELL_WITCH);
        put("CLEAR", null);

    }};
    private HashMap<String, Float> arrowMod = new HashMap<String, Float>(){{
        put("Firework Spark", 0f);
        put("Water", 0f);
        put("Cloud", 0f);
        put("Flame", 0f);
        put("Explosion", 0f);
        put("Heart", 0f);
        put("Note", 0f);
        put("Slime", 0f);
        put("Smoke", 0f);
        put("Snow", 0f);
        put("Potion", 0f);
        put("Potion Rainbow", .5f);
        put("Splash Potion White", 0f);
        put("Splash Potion Purple", 0f);
        put("CLEAR", 0f);
    }};
    private HashMap<String, Integer> arrowCount = new HashMap<String, Integer>(){{
        put("Firework Spark", 9);
        put("Water", 10);
        put("Cloud", 1);
        put("Flame", 5);
        put("Explosion", 2);
        put("Heart", 1);
        put("Note", 1);
        put("Slime", 5);
        put("Smoke", 5);
        put("Snow", 5);
        put("Potion", 3);
        put("Potion Rainbow", 3);
        put("Splash Potion White", 3);
        put("Splash Potion Purple", 3);
        put("CLEAR", 0);
    }};
    public HashMap<String, EnumParticle> getArrowList(){
        return arrowList;
    }

    public Float getArrowMod(String name){
        return arrowMod.get(name);
    }
    public int getArrowCount(String name){
        return arrowCount.get(name);
    }
    public EnumParticle arrowToParticle(String name){
        if(arrowList.containsKey(name)){
            return arrowList.get(name);
        }else{

            return null;
        }
    }


    //*************** PLAYER ***************
    private HashMap<String, EnumParticle> playerList = new HashMap<String, EnumParticle>(){{
        put("Villager Happy", EnumParticle.VILLAGER_HAPPY);
        put("Enchanting", EnumParticle.ENCHANTMENT_TABLE);
        put("Redstone", EnumParticle.REDSTONE);
        put("Redstone Rainbow", EnumParticle.REDSTONE);
        put("Cloud", EnumParticle.CLOUD);
        put("Flame", EnumParticle.FLAME);
        put("Note", EnumParticle.NOTE);
        put("Water Drip", EnumParticle.DRIP_WATER);
        put("Lava Drip", EnumParticle.DRIP_LAVA);
        put("Heart", EnumParticle.HEART);
        put("Potion", EnumParticle.SPELL);
        put("Potion Rainbow", EnumParticle.SPELL_MOB);
        put("Nether Portal", EnumParticle.PORTAL);
        put("CLEAR", null);
    }};
    private HashMap<String, Float> playerMod = new HashMap<String, Float>(){{
        put("Villager Happy", 0f);
        put("Enchanting", 1.5f); //Radius
        put("Redstone", 0f);
        put("Redstone Rainbow", 2f); //Color
        put("Cloud", 0f);
        put("Flame", 0f);
        put("Note", 1f);
        put("Water Drip", 0f);
        put("Lava Drip", 0f);
        put("Heart", 0f);
        put("Potion", 0f);
        put("Potion Rainbow", 1f); //Color
        put("Nether Portal", 1.5f);
        put("CLEAR", 0f);
    }};
    private HashMap<String, Integer> playerCount = new HashMap<String, Integer>(){{
        put("Villager Happy", 1);
        put("Enchanting", 2);
        put("Redstone", 1);
        put("Redstone Rainbow", 1);
        put("Cloud", 1);
        put("Flame", 1);
        put("Note", 1);
        put("Water Drip", 1);
        put("Lava Drip", 1);
        put("Heart", 1);
        put("Potion", 1);
        put("Potion Rainbow", 1);
        put("Nether Portal", 2);
        put("CLEAR", 0);
    }};
    private List<EnumParticle> stillPlayerList = new ArrayList<EnumParticle>(){{ //These particles can only be still
        add(EnumParticle.ENCHANTMENT_TABLE);
        add(EnumParticle.SPELL);
        add(EnumParticle.SPELL_MOB);
        add(EnumParticle.PORTAL);
    }};
    private List<EnumParticle> circlePlayerList = new ArrayList<EnumParticle>(){{ //These particles can only follow the circle pattern
        add(EnumParticle.CLOUD);
        add(EnumParticle.NOTE);
        add(EnumParticle.HEART);

    }};
    private List<EnumParticle> spiralPlayerList = new ArrayList<EnumParticle>(){{ //These particles can only follow the spiral pattern

    }};

    public HashMap<String, EnumParticle> getPlayerList(){
        return playerList;
    }
    public Float getPlayerMod(String name){
        return playerMod.get(name);
    }
    public int getPlayerCount(String name){
        return playerCount.get(name);
    }
    public List<EnumParticle> getStillPlayerList(){
        return stillPlayerList;
    }
    public List<EnumParticle> getCirclePlayerList(){
        return circlePlayerList;
    }
    public List<EnumParticle> getSpiralPlayerList(){
        return spiralPlayerList;
    }
    public EnumParticle playerToParticle(String name){
        if(playerList.containsKey(name)){
            return playerList.get(name);
        }else{
            return null;
        }
    }


    //*************** KILL ***************
    private HashMap<String, EnumParticle> killList = new HashMap<String, EnumParticle>(){{
        put("Villager Happy", EnumParticle.VILLAGER_HAPPY);
        put("Villager Angry", EnumParticle.VILLAGER_ANGRY);
        put("Enchanting", EnumParticle.ENCHANTMENT_TABLE);
        put("Potion", EnumParticle.SPELL);
        put("Potion Rainbow", EnumParticle.SPELL_MOB);
        put("Heart", EnumParticle.HEART);
        put("Redstone", EnumParticle.REDSTONE);
        put("Redstone Rainbow", EnumParticle.REDSTONE);
        put("Firework Spark", EnumParticle.FIREWORKS_SPARK);
        put("CLEAR", null);
    }};
    private HashMap<String, Float> killMod = new HashMap<String, Float>(){{
        put("Villager Happy", 0f);
        put("Villager Angry", 0f);
        put("Enchanting", 20f); //Radius
        put("Potion", 0f);
        put("Potion Rainbow", 1f); //Color
        put("Heart", 0f);
        put("Redstone", 0f);
        put("Redstone Rainbow", 1f); //Color
        put("Firework Spark", 0f);
        put("CLEAR", 0f);
    }};
    private HashMap<String, Integer> killCount = new HashMap<String, Integer>(){{
        put("Villager Happy", 2);
        put("Villager Angry", 1);
        put("Enchanting", 15);
        put("Potion", 2);
        put("Potion Rainbow", 2);
        put("Heart", 1);
        put("Redstone", 1);
        put("Redstone Rainbow", 1);
        put("Firework Spark", 2);
        put("CLEAR", 0);
    }};
    private List<EnumParticle> stillKillList = new ArrayList<EnumParticle>(){{

    }};
    private List<EnumParticle> circleKillList = new ArrayList<EnumParticle>(){{
        add(EnumParticle.SPELL);
        add(EnumParticle.SPELL_MOB);
        add(EnumParticle.ENCHANTMENT_TABLE);
    }};
    private List<EnumParticle> spiralKillList = new ArrayList<EnumParticle>(){{
        add(EnumParticle.VILLAGER_HAPPY);
        add(EnumParticle.VILLAGER_ANGRY);
        add(EnumParticle.HEART);
        add(EnumParticle.REDSTONE);
        add(EnumParticle.FIREWORKS_SPARK);
    }};

    public HashMap<String, EnumParticle> getKillList(){
        return  killList;
    }
    public Float getKillMod(String name){
        return killMod.get(name);
    }
    public int getKillCount(String name){
        return killCount.get(name);
    }
    public List<EnumParticle> getStillKillList(){
        return stillKillList;
    }
    public List<EnumParticle> getCircleKillList(){
        return circleKillList;
    }
    public List<EnumParticle> getSpiralKillList(){
        return spiralKillList;
    }
    public EnumParticle killToParticle(String name){
        if(killList.containsKey(name)){
            return killList.get(name);
        }else{
            return null;
        }
    }







}
