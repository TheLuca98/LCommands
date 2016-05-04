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

import com.google.common.base.Joiner;
import luca.plugins.cmd.exceptions.CommandException;
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandArgs extends ArrayList<String> {

    public CommandArgs(String... args) {
        super(Arrays.asList(args));
    }

    @SuppressWarnings("deprecation")
    public Player getPlayer(int index) {
        Player player = Bukkit.getPlayer(get(index));
        if (player != null) {
            return player;
        } else {
            throw new CommandException(LCommands.getMP().getMessage("error.args.player"), index);
        }
    }

    public int getInt(int index) throws CommandException {
        try {
            return Integer.parseInt(get(index));
        } catch (NumberFormatException e) {
            throw new CommandException(LCommands.getMP().getMessage("error.args.integer"), index);
        }
    }

    public float getFloat(int index) throws CommandException {
        try {
            return Float.parseFloat(get(index));
        } catch (NumberFormatException e) {
            throw new CommandException(LCommands.getMP().getMessage("error.args.number"), index);
        }
    }

    public double getDouble(int index) throws CommandException {
        try {
            return Double.parseDouble(get(index));
        } catch (NumberFormatException e) {
            throw new CommandException(LCommands.getMP().getMessage("error.args.number"), index);
        }
    }

    public boolean getBoolean(int index) throws CommandException {
        try {
            return Boolean.parseBoolean(get(index));
        } catch (Exception e) {
            throw new CommandException(LCommands.getMP().getMessage("error.args.boolean"), index);
        }
    }

    public boolean hasFlag(char flag) {
        return contains("-" + flag);
    }

    public int length() {
        return size();
    }

    @Override
    public String get(int index) {
        try {
            return super.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new UsageException();
        }
    }

    public boolean has(int index) {
        return index < size();
    }

    @Override
    public String toString() {
        return Joiner.on(' ').join(this);
    }

}
