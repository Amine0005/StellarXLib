package it.vamxneedev.stellarxlib.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class GuiListener implements Listener {

    private final Plugin plugin;
    public GuiListener(@NotNull Plugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void inventoryClickListener(@NotNull InventoryClickEvent event){
        GuiBuilder guiBuilder = GuiBuilder.guiMap.get(event.getClickedInventory());
        if (guiBuilder != null){
            ActionItem actionItem = guiBuilder.actionItemMap.get(event.getSlot());
            if (actionItem != null){
                actionItem.onClick(event);
            }
        }
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void inventoryCloseListener(@NotNull InventoryCloseEvent event){
        GuiBuilder guiBuilder = GuiBuilder.guiMap.get(event.getInventory());
        if (guiBuilder != null){
            if (guiBuilder.getUnregisterOnClose() && guiBuilder.getViewers().size() == 1){
                GuiBuilder.guiMap.remove(event.getInventory());
            }
        }
    }
}
