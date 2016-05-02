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

package luca.plugins.cmd.utils;

import com.google.common.base.Joiner;
import com.google.common.reflect.ClassPath;
import luca.plugins.cmd.CustomCommand;

import java.util.ArrayList;
import java.util.List;

public class CmdListGenerator {

    private static final String HEADER = "Command | Description | Permission | Aliases\n---|---|---|---\n";
    private static final String ROW = "/%s | %s | %s | %s\n";

    public static void main(String[] args) {
        List<CustomCommand> commands = new ArrayList<>();
        try {
            for (ClassPath.ClassInfo info : ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses("luca.plugins.cmd.commands")) {
                CustomCommand cmd = (CustomCommand) info.load().getConstructor().newInstance();
                commands.add(cmd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        StringBuilder sb = new StringBuilder(HEADER);
        commands.forEach(c -> sb.append(String.format(ROW,
                c.getName() + (c.getUsage().isEmpty() ? "" : " " + c.getUsage().replace("<", "\\<")),
                c.getDescription(),
                c.getPermission(),
                c.getAliases().length == 0 ? "" : Joiner.on(", ").join(c.getAliases())
        )));
        System.out.print(sb.toString());
    }

}
