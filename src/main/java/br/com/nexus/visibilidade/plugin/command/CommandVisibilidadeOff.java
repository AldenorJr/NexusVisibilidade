package br.com.nexus.visibilidade.plugin.command;

import br.com.nexus.visibilidade.plugin.cache.Cache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVisibilidadeOff implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {

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
        if(Cache.listPlayerOn.contains(player)) {
            player.sendMessage("§cVocê já está com os jogadores invisíveis, para ativar os jogadores use: /on.");
            return true;
        }
        for(Player hidePlayer : Bukkit.getOnlinePlayers()) {
            if(hidePlayer.hasPermission("Nexus.visibilidade.admin")) continue;
            player.hidePlayer(hidePlayer);
        }
        Cache.listPlayerOn.add(player);
        player.sendMessage("§aTodos os jogadores foram desativados, apenas adminstradores podem ser visto.");
        return false;
    }

}
