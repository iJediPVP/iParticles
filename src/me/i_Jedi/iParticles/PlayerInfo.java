package me.i_Jedi.iParticles;

import me.i_Jedi.iParticles.Particles.Particles;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfo {

    //Variables
    private JavaPlugin plugin;
    private Player player;
    private File file;
    private FileConfiguration config;
    Particles particles;

    //Arrow Trail Particles Vars
    private String arrowParticle;
    private float arrowMod = 0;
    private int arrowCount = 0;

    //Player Particles Vars
    private String playerParticle;
    private float playerMod = 0;
    private int playerCount = 0;
    private boolean isPlayerCircle, isPlayerSpiral;

    //Player Kill Particles Vars
    private String killParticle;
    private float killMod = 0;
    private int killCount = 0;

    //Constructor
    public PlayerInfo(Player player, JavaPlugin plugin){
        this.player = player;
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder() + "/playerData/" + player.getUniqueId() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        this.particles = new Particles(plugin);

        //Get arrow stuff
        try{
            this.arrowParticle = config.getString("arrowParticle.particle");
            this.arrowCount = particles.getArrowCount(arrowParticle);
            this.arrowMod = particles.getArrowMod(arrowParticle);
        }catch(NullPointerException npe){} //Do nothing

        //Get player stuff
        try{
            this.playerParticle = config.getString("playerParticle.particle");
            this.playerCount = particles.getPlayerCount(playerParticle);
            this.playerMod = particles.getPlayerMod(playerParticle);
            this.isPlayerCircle = config.getBoolean("playerParticle.isCircle");
            this.isPlayerSpiral = config.getBoolean("playerParticle.isSpiral");
        }catch(NullPointerException npe){} //Do nothing

        //Get kill stuff
        try{
            this.killParticle = config.getString("killParticle.particle");
            this.killCount = particles.getKillCount(killParticle);
            this.killMod = particles.getKillMod(killParticle);
        }catch(NullPointerException npe){} //Do nothing

    }

    //Save file
    public void save(){
        try{
            config.save(file);
        }catch(IOException ioe){
            plugin.getLogger().info("PlayerInfo - Error saving file.");
        }
    }

    // ******************** ARROW TRAILS PARTICLES ********************
    //Get arrow particle
    public EnumParticle getArrowParticle(){
        return particles.arrowToParticle(arrowParticle);
    }

    //Set arrow particle
    public void setArrowParticle(String name){
        config.set("arrowParticle.particle", name);
        arrowParticle = name;
        arrowCount = particles.getArrowCount(name);
        arrowMod = particles.getArrowMod(name);
        save();
    }

    //Has arrow particle
    public boolean hasArrowParticle(){
        if(arrowParticle.isEmpty() || arrowParticle.equals("CLEAR")){
            return false;
        }else{
            return true;
        }
    }

    //Get arrow mod
    public float getArrowMod() {
        return arrowMod;
    }

    //Get arrow count
    public int getArrowCount(){
        return arrowCount;
    }

    // ******************** PLAYER PARTICLES ********************
    //Get body particle
    public EnumParticle getPlayerParticle(){
        return particles.playerToParticle(playerParticle);
    }

    //Set body particle
    public void setPlayerParticle(String name){
        config.set("playerParticle.particle", name);
        playerParticle = name;
        playerCount = particles.getPlayerCount(name);
        playerMod = particles.getPlayerMod(name);
        save();
    }

    //Has body particle
    public boolean hasPlayerParticle(){
        if(playerParticle.equals("CLEAR") || playerParticle.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    //Set circle
    public void setPlayerCircle(){
        isPlayerCircle = true;
        isPlayerSpiral = false;
        config.set("playerParticle.isCircle", true);
        config.set("playerParticle.isSpiral", false);
        save();
    }

    //Set spiral
    public void setPlayerSpiral(){
        isPlayerSpiral = true;
        isPlayerCircle = false;
        config.set("playerParticle.isCircle", false);
        config.set("playerParticle.isSpiral", true);
        save();
    }

    //Get next location
    public Location getNextLocationPlayer(int x){
        List<Location> locList;


        //Check for particles that only have one specified pattern
        EnumParticle particle = particles.playerToParticle(playerParticle);
        if(particles.getStillPlayerList().contains(particle)){
            Location loc = player.getLocation();
            return new Location(loc.getWorld(), loc.getX(), loc.getY() + .75, loc.getZ());
        }else if(particles.getCirclePlayerList().contains(particle)){
            locList = makePlayerCircle(player.getLocation(), .75);
            return locList.get(x);
        }else if(particles.getSpiralPlayerList().contains(particle)){
            locList = makePlayerSpiral(player.getLocation(), .75);
            return locList.get(x);
        }

        //Use Circle as default pattern
        if(isPlayerSpiral){
            locList = makePlayerSpiral(player.getLocation(), .75);
        }else{
            locList = makePlayerCircle(player.getLocation(), .75);
        }

        return locList.get(x);
    }

    //Get body mod
    public float getPlayerMod(){
        return playerMod;
    }

    //Get body count
    public int getPlayerCount(){
        return playerCount;
    }

    //Spiral
    public List<Location> makePlayerSpiral(Location loc, double radius){
        List<Location> locList = new ArrayList<>();
        double y = loc.getY() + .10;

        for(double i = 0.0; i < 720; i += 20){
            double angle = i * Math.PI / 180;
            double x = (loc.getX() + radius * Math.cos(angle));
            double z = (loc.getZ() + radius * Math.sin(angle));
            if(i <= 360){
                y += .075;
            }else{
                y -= .075;
            }

            locList.add(new Location(loc.getWorld(), x, y, z));
        }

        return locList;
    }

    //Circle
    public List<Location> makePlayerCircle(Location loc, double radius){
        List<Location> locList = new ArrayList<>();

        for(double i = 0.0; i < 360; i += 30){
            double angle = i * Math.PI / 180;
            double x = (loc.getX() + radius * Math.cos(angle));
            double z = (loc.getZ() + radius * Math.sin(angle));
            locList.add(new Location(loc.getWorld(), x, loc.getY() + .5, z));
        }
        return locList;
    }

    // ******************** KILL PARTICLES ********************
    //Get kill particle
    public EnumParticle getKillParticle(){
        return particles.killToParticle(killParticle);
    }

    //Set kill particle
    public void setKillParticle(String name){
        config.set("killParticle.particle", name);
        killParticle = name;
        killCount = particles.getKillCount(name);
        killMod = particles.getKillMod(name);
        save();
    }

    //Has kill particle
    public boolean hasKillParticle(){
        if(killParticle.equals("CLEAR") || killParticle.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    //Get particle location list
    // Use makePlayerSpiral & makePlayerCircle for these
    public List<Location> getKillLocations(Location location){
        //Check for pattern
        EnumParticle particle = particles.killToParticle(killParticle);
        if(particles.getStillKillList().contains(particle)){
            List<Location> locList = new ArrayList<>();
            locList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
            return locList;

        }else if(particles.getSpiralKillList().contains(particle)){
            return makePlayerSpiral(location, .75);

        }else{ //Default is circle
            return makePlayerCircle(location, .75);
        }

    }

    //Get kill mod
    public float getKillMod(){
        return killMod;
    }

    //Get kill cound
    public int getKillCount(){
        return killCount;
    }





}
