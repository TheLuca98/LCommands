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

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class LCommands extends JavaPlugin {

    private static LCommands instance;
    private MessageProvider provider;

    public static LCommands getInstance() {
        return instance;
    }

    public static MessageProvider getMP() {
        return instance.provider;
    }

    @Override
    public void onEnable() {
        instance = this;
        if (!getConfig().isSet("language")) {
            getConfig().set("language", "en");
        }
        saveConfig();
        this.provider = new MessageProvider(getConfig().getString("language", "en"));
        try {
            this.provider.loadFromJson();
        } catch (Exception e) {
            logAndDisable("Failed to load messages", e);
            return;
        }
        try {
            registerCommands();
        } catch (Exception e) {
            logAndDisable("Failed to register commands", e);
        }
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerCommands() throws Exception {
        Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);
        CommandMap map = (CommandMap) field.get(Bukkit.getPluginManager());
        getLogger().info("Starting dynamic command registration...");
        int i = 0;
        for (ClassPath.ClassInfo info : ClassPath.from(getClassLoader()).getTopLevelClasses("luca.plugins.cmd.commands")) {
            CustomCommand cmd = (CustomCommand) info.load().getConstructor().newInstance();
            map.register(getName().toLowerCase(), new BukkitExecutor(cmd));
            i++;
        }
        getLogger().info(i + " commands have been registered.");
    }

    public void logAndDisable(String msg, Throwable e) {
        getLogger().log(Level.SEVERE, msg, e);
        setEnabled(false);
    }

}
