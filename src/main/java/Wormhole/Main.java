package Wormhole;

import Wormhole.commands.WorldTpCommand;
import Wormhole.commands.WorldlistCommand;
import Wormhole.events.FormWindowEvent;
import cn.nukkit.command.CommandMap;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.io.File;

public class Main extends PluginBase {

    public FormWindow formWindow;
    public String prefix = TextFormat.BOLD+""+TextFormat.DARK_RED+"["+TextFormat.AQUA+ "Wormhole" +TextFormat.DARK_RED+"]"+TextFormat.RESET;

    @Override
    public void onLoad() {
        CommandMap commandMap = this.getServer().getCommandMap();
        commandMap.register("Wormhole", new WorldlistCommand("worlds", this));
        commandMap.register("Wormhole", new WorldTpCommand("wormhole", this));
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new FormWindowEvent(this), this);

        FormWindowSimple formWindow = new FormWindowSimple(prefix, "");
        File worldPath = new File(this.getServer().getPluginPath()+"../worlds");
        File[] worlds = worldPath.listFiles();
        assert worlds != null;
        for (File world : worlds) {
            if (world.isDirectory()) {
                ElementButton button = new ElementButton(world.getName());
                formWindow.addButton(button);
            }
        }
        this.formWindow = formWindow;
    }
}
