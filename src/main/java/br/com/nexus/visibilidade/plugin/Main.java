package br.com.nexus.visibilidade.plugin;

import br.com.nexus.visibilidade.plugin.command.CommandVisibilidadeOn;
import br.com.nexus.visibilidade.plugin.command.CommandVisibilidadeOff;
import br.com.nexus.visibilidade.plugin.lister.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private String prefix = "§6[NexusVisibilidade] ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(prefix+"§aPlugin iniciado.");
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(prefix+"§aPlugin configuração carregada.");
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("saudade").setExecutor(new CommandVisibilidadeOn());
        getCommand("felicidade").setExecutor(new CommandVisibilidadeOff());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix+"§cPlugin desligado.");
    }
}
