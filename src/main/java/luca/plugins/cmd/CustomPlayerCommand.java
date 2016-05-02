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

package luca.plugins.cmd;

import luca.plugins.cmd.exceptions.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CustomPlayerCommand extends CustomCommand {

    public CustomPlayerCommand(String name) {
        super(name);
    }

    public CustomPlayerCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    public abstract void execute(Player player, CommandArgs args);

    @Override
    public final void execute(CommandSender sender, CommandArgs args) {
        if (sender instanceof Player) {
            execute((Player) sender, args);
        } else {
            throw new CommandException(LCommands.getMP().getMessage("error.command.player"));
        }
    }

}
