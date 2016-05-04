/*
 * LCommands - A collection of useful commands for Bukkit.
 * Copyright (c) 2016 TheLuca98 <https://github.com/TheLuca98>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package luca.plugins.cmd.commands;

import luca.plugins.cmd.CommandArgs;
import luca.plugins.cmd.CustomCommand;
import luca.plugins.cmd.LCommands;
import luca.plugins.cmd.exceptions.CommandException;
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CmdGameMode extends CustomCommand {

    public static final CmdGameMode INSTANCE = new CmdGameMode();

    private final Map<String, GameMode> modes;

    @SuppressWarnings("deprecated")
    public CmdGameMode() {
        super("gamemode", "lcommands.gamemode", "gm");
        this.modes = new HashMap<>();
        for (GameMode mode : GameMode.values()) {
            modes.put(mode.name().toLowerCase(), mode);
            modes.put(String.valueOf(mode.getValue()), mode);
        }
        modes.put("c", GameMode.CREATIVE);
        modes.put("s", GameMode.SURVIVAL);
        modes.put("a", GameMode.ADVENTURE);
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        GameMode mode = modes.get(args.get(0).toLowerCase());
        if (mode == null) {
            throw new CommandException(LCommands.getMP().getMessage("commands.gamemode.invalid"));
        }
        Player target;
        if (args.length() > 1) {
            target = args.getPlayer(1);
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            throw new UsageException();
        }
        target.setGameMode(mode);
        sender.sendMessage(LCommands.getMP().formatMessage("commands.gamemode.done", mode.toString(), target.getName()));
    }

}
