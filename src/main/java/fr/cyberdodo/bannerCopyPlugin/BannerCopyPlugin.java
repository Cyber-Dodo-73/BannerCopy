package fr.cyberdodo.bannerCopyPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BannerCopyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack baseItem = inventory.getItem(0); // Bannière(s) de base
        ItemStack additionItem = inventory.getItem(1); // Bannière avec motifs

        if (baseItem == null || additionItem == null) {
            return;
        }


        // Vérifier si les items sont des bannières
        if (baseItem.getType().name().endsWith("_BANNER") && additionItem.getType().name().endsWith("_BANNER")) {
            // Obtenir le nombre de bannières dans le premier slot
            int baseAmount = baseItem.getAmount();

            // Cloner la bannière additionnelle avec tous ses motifs et propriétés
            ItemStack resultBanner = additionItem.clone();

            // Définir le nombre total de bannières en sortie (double de la quantité de bannières dans le slot 1)
            resultBanner.setAmount(baseAmount * 2);

            // Définir l'item de résultat de l'enclume
            event.setResult(resultBanner);

            // Calculer le coût en XP évolutif
            int repairCost = baseAmount*2; // 4 XP par bannière de base

            // Appliquer le coût en XP
            inventory.setRepairCost(repairCost); // Le coût augmente en fonction du nombre de bannières
        }
    }
}
