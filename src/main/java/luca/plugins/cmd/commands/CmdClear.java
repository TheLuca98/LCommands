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
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClear extends CustomCommand {

    public CmdClear() {
        super("clear", "lcommands.clear", "ci");
        setDescription("Clears an inventory.");
        setUsage("[player] [item]");
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        Player target;
        if (args.length() > 0) {
            target = args.getPlayer(0);
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            throw new UsageException();
        }
        if (args.length() > 1) {
            Material material = Material.matchMaterial(args.get(1));
            if (material == null) {
                throw new CommandException(LCommands.getMP().getMessage("commands.clear.nomaterial"));
            }
            target.getInventory().remove(material);
        } else {
            target.getInventory().clear();
            sender.sendMessage(LCommands.getMP().formatMessage("commands.clear.done", target.getName()));
        }
    }

}