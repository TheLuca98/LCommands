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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdItem extends CustomPlayerCommand {

    public CmdItem() {
        super("item", "lcommands.item", "i");
    }

    @Override
    public void execute(Player player, CommandArgs args) {
        Material material = Material.matchMaterial(args.get(0));
        if (material == null) {
            throw new CommandException(LCommands.getMP().getMessage("error.args.material"));
        }
        int amount = 1;
        int data = 0;
        if (args.length() > 1) {
            amount = args.getInt(1);
            if (amount > 64 || amount < 1) {
                throw new CommandException(LCommands.getMP().getMessage("error.args.stack"), 1);
            }
        }
        if (args.length() > 2) {
            data = args.getInt(2);
            if (amount < 0) {
                throw new CommandException(LCommands.getMP().getMessage("error.args.positive"), 2);
            }
        }
        ItemStack item = new ItemStack(material, amount, (short) data);
        player.getInventory().addItem(item);
    }

}
