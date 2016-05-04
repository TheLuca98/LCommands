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
import luca.plugins.cmd.exceptions.UsageException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CmdTime extends CustomCommand {

    public static final CmdTime INSTANCE = new CmdTime();

    private final Map<String, Long> map;

    public CmdTime() {
        super("time", "lcommands.time");
        setDescription("Sets the in-game time.");
        setUsage("<value> [world]");
        this.map = new HashMap<>();
        map.put("dawn", 0L);
        map.put("day", 1000L);
        map.put("midday", 6000L);
        map.put("dusk", 12000L);
        map.put("night", 14000L);
        map.put("midnight", 18000L);
    }

    @Override
    public void execute(CommandSender sender, CommandArgs args) {
        long time;
        if (map.containsKey(args.get(0).toLowerCase())) {
            time = map.get(args.get(0));
        } else {
            time = (long) args.getInt(0);
        }
        World world;
        if (sender instanceof Player) {
            world = ((Player) sender).getWorld();
        } else if (Bukkit.getWorlds().size() == 1) {
            world = Bukkit.getWorlds().get(1);
        } else {
            throw new UsageException();
        }
        world.setTime(time);
    }

}
