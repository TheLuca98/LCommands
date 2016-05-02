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

import org.bukkit.command.CommandSender;

public abstract class CustomCommand {

    private String name;
    private String permission;
    private String[] aliases;
    private String description;
    private String usage;

    public CustomCommand(String name) {
        this(name, null);
    }

    public CustomCommand(String name, String permission, String... aliases) {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
        this.description = "A command powered by LCommands.";
        this.usage = "";
    }

    public abstract void execute(CommandSender sender, CommandArgs args);

    public final String getName() {
        return name;
    }

    public final String getPermission() {
        return permission;
    }

    public final String[] getAliases() {
        return aliases;
    }

    public final String getDescription() {
        return description;
    }

    protected final void setDescription(String description) {
        this.description = description;
    }

    public final String getUsage() {
        return usage;
    }

    protected final void setUsage(String usage) {
        this.usage = usage;
    }
}
