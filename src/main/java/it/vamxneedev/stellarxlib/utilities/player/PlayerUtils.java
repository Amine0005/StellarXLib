package it.vamxneedev.stellarxlib.utilities.player;

import com.cryptomorin.xseries.XSound;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

public class PlayerUtils {
    private final Plugin instance;
    public PlayerUtils(Plugin instance) { this.instance = instance; }

    public static @NotNull Location getPlayerLocation(@NotNull Player player) {
        return player.getLocation();
    }
    public static String getPlayerLocationString(@NotNull Player player) {
        return String.format("X: %.2f, Y: %.2f, Z: %.2f", player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    }

    public static void addExperiencePoints(@NotNull Player player, int amount) {
        player.giveExp(amount);
    }
    public static void removeExperiencePoints(@NotNull Player player, int amount) {
        player.giveExp(-amount);
    }
    public static int getPlayerExperienceLevel(@NotNull Player player) {
        return player.getLevel();
    }
    public static void setPlayerExperienceLevel(@NotNull Player player, int level) {
        player.setLevel(level);
    }

    public static void increasePlayerHealth(@NotNull Player player, double amount) {
        double currentHealth = player.getHealth();
        player.setHealth(currentHealth + amount);
    }
    public static void decreasePlayerHealth(@NotNull Player player, double amount) {
        double currentHealth = player.getHealth();
        player.setHealth(currentHealth - amount);
    }

    public static void increasePlayerFoodLevel(@NotNull Player player, int amount) {
        int currentFoodLevel = player.getFoodLevel();
        player.setFoodLevel(currentFoodLevel + amount);
    }
    public static void decreasePlayerFoodLevel(@NotNull Player player, int amount) {
        int currentFoodLevel = player.getFoodLevel();
        player.setFoodLevel(currentFoodLevel - amount);
    }

    public static Location getPlayerSpawnLocation(@NotNull Player player) {
        return player.getBedSpawnLocation() != null ? player.getBedSpawnLocation() : player.getWorld().getSpawnLocation();
    }
    public static void setPlayerSpawnLocation(@NotNull Player player, Location location) {
        player.setBedSpawnLocation(location, true);
    }

    public static boolean isPlayerOnline(@NotNull Player player) {
        return player.isOnline();
    }
    public static boolean isPlayerDead(@NotNull Player player) {
        return player.isDead();
    }
    public static boolean isPlayerFlying(@NotNull Player player) {
        return player.isFlying();
    }

    public static @NotNull String getPlayerPermissionLevel(@NotNull Player player) {
        return player.isOp() ? "op" : "default";
    }

    public static @NotNull GameMode getGameMode(@NotNull Player player) {
        return player.getGameMode();
    }
    public static void setGameMode(@NotNull Player player, GameMode gameMode) {
        player.setGameMode(gameMode);
    }

    public static void giveItem(@NotNull Player player, ItemStack itemStack) {
        player.getInventory().addItem(itemStack);
    }
    public static void removeItem(@NotNull Player player, ItemStack itemStack) {
        player.getInventory().removeItem(itemStack);
    }
    public static void giveItemAll(ItemStack itemStack) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            giveItem(player, itemStack);
        }
    }
    public static void removeItemAll(ItemStack itemStack) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            removeItem(player, itemStack);
        }
    }

    public static void playSound(@Nonnull Player player, @Nonnull String sound, float volume, float pitch, String identifier) {
        try {
            Optional<XSound> soundType = XSound.matchXSound(sound.toUpperCase());
            if(!soundType.isPresent()){
                throw new IllegalArgumentException("&8[&b" + identifier + "&8] " + "Unknown sound: " + sound + "&8(&bERROR_SOUND_ARGS&8)");
            }

            Sound soundMatched = soundType.get().parseSound();
            assert soundMatched != null;
            player.playSound(player.getLocation(), soundMatched, volume, pitch);

        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}
