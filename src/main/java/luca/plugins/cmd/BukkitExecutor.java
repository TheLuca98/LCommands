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
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.Arrays;
import java.util.logging.Level;

import static org.bukkit.ChatColor.RED;

public class BukkitExecutor extends Command {

    private final CustomCommand command;

    public BukkitExecutor(CustomCommand command) {
        super(command.getName());
        setAliases(Arrays.asList(command.getAliases()));
        setDescription(command.getDescription());
        setUsage("/" + command.getName() + " " + command.getUsage());
        setPermission(command.getPermission());
        setPermissionMessage(RED + LCommands.getMP().getMessage("error.command.permission"));
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission(getPermission())) {
            try {
                command.execute(sender, new CommandArgs(args));
            } catch (CommandException e) {
                sender.sendMessage(e.getChatMessage());
            } catch (UsageException e) {
                sender.sendMessage(RED + LCommands.getMP().formatMessage("error.command.usage", getUsage()));
            } catch (Throwable e) {
                sender.sendMessage(RED + LCommands.getMP().getMessage("error.command.internal"));
                LCommands.getInstance().getLogger().log(Level.SEVERE, null, e);
            }
        } else {
            sender.sendMessage(getPermissionMessage());
        }
        return true;
    }

}