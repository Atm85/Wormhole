package Wormhole.events;

import Wormhole.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

public class FormWindowEvent implements Listener {

    private Main plugin;

    public FormWindowEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void FormResponseEvent(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        int formId = event.getFormID();
        if ((event.getResponse() instanceof FormResponseSimple) && (formId == 0)) {
            FormResponseSimple response = (FormResponseSimple) event.getResponse();
            String buttonText = response.getClickedButton().getText();
            if (!plugin.getServer().isLevelLoaded(buttonText)) {
                plugin.getServer().loadLevel(buttonText);
            }
            Level level = plugin.getServer().getLevelByName(buttonText);
            Position position = level.getSpawnLocation();
            player.teleport(position);
            player.sendMessage(plugin.prefix + " " + "Transporting to: "+buttonText);
        }
    }
}
