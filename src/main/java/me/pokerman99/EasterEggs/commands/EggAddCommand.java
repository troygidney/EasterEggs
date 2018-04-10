package me.pokerman99.EasterEggs.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.pokerman99.EasterEggs.data.Data;
import me.pokerman99.EasterEggs.data.ListTypes;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import me.pokerman99.EasterEggs.Main;
import me.pokerman99.EasterEggs.Utils;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

public class EggAddCommand implements CommandExecutor{

    public Main plugin;

    public EggAddCommand(Main pluginInstance) {
        this.plugin = pluginInstance;
    }


    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Player player = (Player) src;
		ListTypes event = args.<ListTypes>getOne("list").get();
		int total = Main.rootNode.getNode("types", event, "total").getInt();

		List<String> temp = new ArrayList<>();
		temp.add(event.toString());
		temp.add(String.valueOf(total + 1));

		Main.rootNode.getNode("types", event, "total").setValue(total + 1);
		try{plugin.save();} catch (IOException e){}

		Main.adding.put(player.getUniqueId(), new Data(temp));

        MessageChannel.TO_ALL.send(Text.of(total));

		Utils.sendMessage(player,"&aRight click a minecraft:skull to set the egg/present!");

        return CommandResult.success();
	}
	

}