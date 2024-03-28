package it.vamxneedev.stellarxlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiBuilder {

    public static Map<Inventory, GuiBuilder> guiMap = new HashMap<>();
    public static GuiListener guiListener = null;

    @Nullable
    public static GuiBuilder getGUIOfPlayer(@NotNull Player player){
        Inventory inventory = player.getOpenInventory().getTopInventory();
        return guiMap.get(inventory);
    }

    @Contract(pure = true)
    public static @NotNull Boolean isGUI(Inventory inventory){
        return guiMap.containsKey(inventory);
    }

    private final Integer size;
    private final String title;
    private final Plugin plugin;
    private final Inventory inventory;
    private final Boolean unregisterOnClose;
    private Boolean singlePlayer = true;
    //         Slot     ActionItem
    public Map<Integer, ActionItem> actionItemMap = new HashMap<>();

    public GuiBuilder(Plugin plugin, Integer size, String title, Boolean unregisterOnClose) {
        this.size = size;
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.plugin = plugin;
        this.unregisterOnClose = unregisterOnClose;
        this.inventory = Bukkit.createInventory(null, size*9, title);
        guiMap.put(inventory, this);
        if (guiListener == null){
            guiListener = new GuiListener(plugin);
        }
    }

    public Boolean isForSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayerOption(Boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public Boolean getUnregisterOnClose() {
        return unregisterOnClose;
    }

    public Integer getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void open(@NotNull Player player){
        player.openInventory(inventory);
    }

    public void close(@NotNull Player player){
        if (player.getOpenInventory().getTopInventory().equals(inventory)){
            player.closeInventory();
        }
    }

    public List<HumanEntity> getViewers(){
        return this.inventory.getViewers();
    }

    public void addItem(ActionItem actionItem){
        this.actionItemMap.put(actionItem.getSlot(), actionItem);
        this.inventory.setItem(actionItem.getSlot(), actionItem.getItemStack());
    }

    public void removeItem(@NotNull ActionItem actionItem){
        this.actionItemMap.remove(actionItem.getSlot());
        this.inventory.setItem(actionItem.getSlot(), new ItemStack(Material.AIR));
    }

    public void unregisterGUI(){
        guiMap.remove(this.inventory);
    }
}
