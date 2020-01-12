package WorldTransport.commands;

import WorldTransport.Main;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;

public class WorldTpCommand extends PluginCommand {

    private Main plugin;

    public WorldTpCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
        String[] aliases = {"worldtp"};
        this.setAliases(aliases);
        this.description = "Teleport yourself between other worlds on the server";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("world.tp") || sender.isOp()) {
                ((Player) sender).showFormWindow(plugin.formWindow, 0);
            }
        } else {
            sender.sendMessage("You cannot join worlds silly!");
        }

        return true;
    }
}
