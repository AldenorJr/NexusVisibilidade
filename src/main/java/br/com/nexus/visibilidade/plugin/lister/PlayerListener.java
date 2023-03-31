package br.com.nexus.visibilidade.plugin.lister;

import br.com.nexus.visibilidade.plugin.cache.Cache;
import br.com.nexus.visibilidade.plugin.command.CommandVisibilidadeOff;
import br.com.nexus.visibilidade.plugin.command.CommandVisibilidadeOn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void toJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for(Player player2 : Cache.listPlayerOn) {
            if(player.hasPermission("Nexus.visibilidade.admin")) continue;
            player2.hidePlayer(player);
        }
    }

    @EventHandler
    public void toCommandEnter(PlayerCommandPreprocessEvent e) {
        if(e.getMessage().equalsIgnoreCase("/off")) {
            new CommandVisibilidadeOff().executeCommand(e.getPlayer());
            e.setCancelled(true);
        }
        if(e.getMessage().equalsIgnoreCase("/on")) {
            new CommandVisibilidadeOn().executeCommand(e.getPlayer());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void toDamagePlayer(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if(!Cache.listPlayerOn.contains(player)) return;
        Cache.listPlayerOn.remove(player);
        for(Player p2 : Bukkit.getOnlinePlayers()) {
            if(p2.hasPermission("Nexus.visibilidade.admin")) continue;
            player.showPlayer(p2);
        }
        player.sendMessage("§aAgora todos os jogadores estão visíveis.");
    }

    @EventHandler
    public void toDisconnect(PlayerQuitEvent e) {
        Cache.listPlayerOn.remove(e.getPlayer());
    }

}
