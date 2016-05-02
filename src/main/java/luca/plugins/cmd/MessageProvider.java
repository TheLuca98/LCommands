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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import luca.plugins.cmd.utils.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MessageProvider {

    private final String id;
    private final Map<String, String> map;

    public MessageProvider(String id) {
        this.id = id.toLowerCase();
        this.map = new HashMap<>();
    }

    public void loadFromJson() throws Exception {
        InputStream is = LCommands.getInstance().getResource("messages_" + id + ".json");
        if (is == null) {
            is = LCommands.getInstance().getResource("messages_en.json");
        }
        JsonObject json = new JsonParser().parse(new InputStreamReader(is)).getAsJsonObject();
        json.entrySet().forEach(entry -> map.put(entry.getKey(), entry.getValue().getAsString()));
    }

    public String getMessage(String key) {
        return StringUtils.convertColors(map.getOrDefault(key, "Missing key: " + key));
    }

    public String formatMessage(String key, Object... params) {
        return String.format(getMessage(key), params);
    }

}