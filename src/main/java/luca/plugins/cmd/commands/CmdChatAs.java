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

import com.google.common.base.Joiner;
import luca.plugins.cmd.CommandArgs;
import luca.plugins.cmd.CustomCommand;
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdChatAs extends CustomCommand {

    public CmdChatAs() {
        super("chatas", "lcommands.chatas");
        setDescription("Sends a chat message on behalf of a player.");
        setUsage("<player> <message...>");
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        if (args.size() > 1) {
            Player target = args.getPlayer(0);
            String message = Joiner.on(' ').join(args.subList(1, args.length()));
            target.chat(message);
        } else {
            throw new UsageException();
        }
    }

}