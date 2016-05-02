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
import luca.plugins.cmd.LCommands;
import luca.plugins.cmd.exceptions.CommandException;
import luca.plugins.cmd.exceptions.UsageException;
import luca.plugins.cmd.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdRename extends CustomPlayerCommand {

    public CmdRename() {
        super("rename", "lcommands.rename");
        setDescription("Renames the currently held item.");
        setUsage("<name...>");
    }

    @Override
    public void execute(Player player, CommandArgs args) {
        if (args.length() > 0) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null || item.getType().equals(Material.AIR)) {
                throw new CommandException(LCommands.getMP().getMessage("commands.rename.noitem"));
            } else {
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(StringUtils.convertColors(args.toString()));
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
            }
        } else {
            throw new UsageException();
        }
    }

}
