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
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

public class CmdGMC extends CustomCommand {

    public CmdGMC() {
        super("gmc", "lcommands.gamemode", "creative");
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        if (args.length() > 0) {
            CmdGameMode.INSTANCE.execute(sender, new CommandArgs(new String[] {GameMode.CREATIVE.name(), args.get(0)}));
        } else {
            CmdGameMode.INSTANCE.execute(sender, new CommandArgs(new String[] {GameMode.CREATIVE.name()}));
        }
    }

}
