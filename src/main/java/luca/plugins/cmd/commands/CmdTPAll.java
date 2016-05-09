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
import luca.plugins.cmd.CustomPlayerCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CmdTPAll extends CustomPlayerCommand {

    public CmdTPAll() {
        super("tpall", "lcommands.tpall");
        setDescription("Teleports here all players.");
    }

    @Override
    public void execute(Player player, CommandArgs args) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (!p.equals(player)) {
                p.teleport(player);
            }
        });
    }

}