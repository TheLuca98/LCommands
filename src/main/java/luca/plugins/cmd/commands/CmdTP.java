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
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTP extends CustomCommand {

    public CmdTP() {
        super("teleport", "lcommands.teleport", "tp");
        setDescription("Teleports to a location/player.");
        setUsage("<destination> [target]");
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        if (args.length() > 2) {
            int x = args.getInt(0);
            int y = args.getInt(1);
            int z = args.getInt(2);
            Player target;
            if (args.length() > 3) {
                target = args.getPlayer(3);
            } else if (sender instanceof Player) {
                target = (Player) sender;
            } else {
                throw new UsageException();
            }
            target.teleport(new Location(target.getWorld(), x, y, z));
        } else {
            Player destination = args.getPlayer(0);
            Player target;
            if (args.length() > 1) {
                target = args.getPlayer(1);
            } else if (sender instanceof Player) {
                target = (Player) sender;
            } else {
                throw new UsageException();
            }
            target.teleport(destination);
        }
    }

}