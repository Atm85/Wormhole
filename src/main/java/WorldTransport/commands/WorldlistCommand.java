package WorldTransport.commands;

import WorldTransport.Main;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.TextFormat;

import java.io.File;

public class WorldlistCommand extends PluginCommand {

    private Main plugin;

    public WorldlistCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
        this.description = "Gets a list of worlds loaded on the server";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        StringBuilder list = new StringBuilder();
        File worldPath = new File(plugin.getServer().getPluginPath()+"../worlds");
        File[] worlds = worldPath.listFiles();

        assert worlds != null;
        for (File world : worlds) {
            if (world.isDirectory()) {
                if (list.length() > 0) {
                    list.append(TextFormat.WHITE + ", ");
                }
                list.append(plugin.getServer().isLevelLoaded(world.getName()) ? TextFormat.GREEN : TextFormat.RED);
                list.append(world.getName());
            }
        }

        sender.sendMessage("Worlds (" + worlds.length + "): " + list);
        return true;
    }
}
