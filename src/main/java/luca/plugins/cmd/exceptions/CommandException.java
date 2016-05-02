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

package luca.plugins.cmd.exceptions;

import luca.plugins.cmd.LCommands;
import org.bukkit.ChatColor;

public class CommandException extends RuntimeException {

    private final int index;

    public CommandException(String message, Integer index) {
        super(message);
        this.index = index + 1;
    }

    public CommandException(String message) {
        super(message);
        this.index = 0;
    }

    public String getChatMessage() {
        return ChatColor.RED + (index != 0 ? LCommands.getMP().formatMessage("error.args.indexinfo", getMessage(), index) : getMessage());
    }


}