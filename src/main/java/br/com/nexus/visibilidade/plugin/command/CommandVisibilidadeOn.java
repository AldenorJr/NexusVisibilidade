package br.com.nexus.visibilidade.plugin.command;

import br.com.nexus.visibilidade.plugin.cache.Cache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVisibilidadeOn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cApenas jogadores podem executar esse comando.");
            return true;
        }
        Player player = (Player) commandSender;
        executeCommand(player);
        return true;
    }

    public boolean executeCommand(Player player) {
        if(!player.hasPermission("Nexus.visibilidade.use")) {
            player.sendMessage("§cVocê não tem permissão para executar esse comando.");
            return true;
        }
        if(!Cache.listPlayerOn.contains(player)) {
            player.sendMessage("§cVocê já está com os jogadores visíveis.");
            return true;
        }
        Cache.listPlayerOn.remove(player);
        for(Player p2 : Bukkit.getOnlinePlayers()) player.showPlayer(p2);
        player.sendMessage("§aAgora todos os jogadores estão visíveis.");
        return false;
    }
}
