package net.simplyrin.rawmessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RawMessage extends JavaPlugin implements Listener {

	private static RawMessage plugin;

	@Override
	public void onEnable() {
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents(this, this);
		plugin.getCommand("rawmessage").setExecutor(this);
		plugin.getCommand("rawmsg").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("rawmessage.send")) {
			sender.sendMessage(getPrefix() + "§cYou do not have access to this command");
			// sender.sendMessage(getPrefix() + "§cPlease contact to SimplyRin!");
			return true;
		}

		if(args.length > 0) {
			Player player = Bukkit.getPlayer(args[0]);

			if(player != null) {
				if(args.length > 1) {
					String message = "";
			        	for (int i = 1; i < args.length; i++) {
			        		message = message + args[i] + " ";
			        	}
			        	message = ChatColor.translateAlternateColorCodes('&', message);

			        	player.sendMessage(message);
				}
				return true;
			}
			sender.sendMessage(getPrefix() + "§cThat player is not online!");
			return true;
		}
		sender.sendMessage(getPrefix() + "§cUsage: /" + cmd.getName() + " <player> <msg>");
		return true;
	}

	public static String getPrefix() {
		return "§7[§cRawMessage§7] §r";
	}

}
