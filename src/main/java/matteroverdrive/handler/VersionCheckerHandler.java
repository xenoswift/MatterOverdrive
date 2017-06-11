/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

//package matteroverdrive.handler;
/*
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.gameevent.TickEvent;
import matteroverdrive.MatterOverdrive;
import matteroverdrive.Reference;
import matteroverdrive.handler.thread.VersionCheckThread;
import matteroverdrive.util.IConfigSubscriber;
import matteroverdrive.util.MOStringHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import org.apache.logging.log4j.Level;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
*/
/**
 * Created by Simeon on 5/7/2015.
 */
/*
public class VersionCheckerHandler implements IConfigSubscriber {
    private boolean updateInfoDisplayed = false;
    public Future<String> download;
    public static final String[] mirrors = new String[]{Reference.VERSIONS_CHECK_URL};
    private int currentMirror = 0;
    int lastPoll = 400;
    private boolean checkForUpdates;

    //Called when a player ticks.
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.START || !checkForUpdates) {
            return;
        }

        if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isServerRunning()) {
            if (!MinecraftServer.getServer().getConfigurationManager().func_152596_g(event.player.getGameProfile())) {
                return;
            }
        }

        if (lastPoll > 0) {
            --lastPoll;
            return;
        }
        lastPoll = 400;

        if (updateInfoDisplayed)
            return;

        if (currentMirror < mirrors.length) {
            if (download == null) {
                download = MatterOverdrive.threadPool.submit(new VersionCheckThread(mirrors[currentMirror]));
                currentMirror++;
            }
        }

        if (download != null && download.isDone()) {
            String result = null;

            try {
                result = download.get();
            } catch (InterruptedException e) {
				MatterOverdrive.log.log(Level.ERROR,e,"Version checking from '%1$s' was interrupted", mirrors[currentMirror - 1]);
            } catch (ExecutionException e) {
				MatterOverdrive.log.log(Level.ERROR,e,"Version checking from '%1$s' has failed", mirrors[currentMirror - 1]);
            } finally {
                if (result != null)
                {
                    try {
                        updateInfoDisplayed = constructVersionAndCheck(result, event.player);
                    }catch (Exception e)
                    {
                        MatterOverdrive.log.log(Level.ERROR,e,"There was a problem while decoding the update info from website.");
                    }
                }

                download.cancel(false);
                download = null;
            }
        }
    }

    private boolean constructVersionAndCheck(String jsonText,EntityPlayer player)
    {
        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(jsonText).getAsJsonArray().get(0).getAsJsonObject();
        SimpleDateFormat websiteDatePraser = new SimpleDateFormat("y-M-d");
        SimpleDateFormat modDateFormat = new SimpleDateFormat("d.M.y");
        String websiteDateString = root.get("date").getAsString();
        websiteDateString = websiteDateString.substring(0, websiteDateString.indexOf('T'));
        Date websiteDate = null;
        Date modDate = null;
        try {
            websiteDate = websiteDatePraser.parse(websiteDateString);
        } catch (ParseException e)
        {
			MatterOverdrive.log.warn("Website date was incorrect", e);
        }
        try {
            modDate = modDateFormat.parse(Reference.VERSION_DATE);
        } catch (ParseException e) {
			MatterOverdrive.log.warn("Mod version date was incorrect", e);
        }

        if (modDate != null && websiteDate != null ) {

            if (modDate.before(websiteDate))
            {
                ChatComponentText chat = new ChatComponentText(EnumChatFormatting.GOLD + "[Matter Overdrive] " + EnumChatFormatting.WHITE + MOStringHelper.translateToLocal("alert.new_update"));
                ChatStyle style = new ChatStyle();
                player.addChatMessage(chat);

                chat = new ChatComponentText("");
                IChatComponent versionName = new ChatComponentText(root.get("title").getAsString() + " ").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA));
                chat.appendSibling(versionName);
                chat.appendText(EnumChatFormatting.WHITE + "[");
                style.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Reference.DOWNLOAD_URL));
                style.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentTranslation("info." + Reference.MOD_ID + ".updater.hover").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW))));
                style.setColor(EnumChatFormatting.GREEN);
                chat.appendSibling(new ChatComponentTranslation("info." + Reference.MOD_ID + ".updater.download")).setChatStyle(style);
                chat.appendText(EnumChatFormatting.WHITE + "]");
                player.addChatMessage(chat);

                chat = new ChatComponentText(root.get("excerpt").getAsString().replaceAll("\\<.*?\\>", ""));
                style = new ChatStyle();
                style.setColor(EnumChatFormatting.GRAY);
                chat.setChatStyle(style);
                player.addChatMessage(chat);
                return true;

            } else
            {
				MatterOverdrive.log.info("Matter Overdrive Version %1$s is up to date. From '%2$s'", root.get("title").getAsString(), mirrors[currentMirror - 1]);
            }
        }
        return false;
    }

    @Override
    public void onConfigChanged(ConfigurationHandler config)
    {
        String comment = "Should Matter Overdrive check for newer versions, every time the world starts";
        checkForUpdates = config.getBool(ConfigurationHandler.KEY_VERSION_CHECK, ConfigurationHandler.CATEGORY_CLIENT,true,comment);
        config.config.get(ConfigurationHandler.CATEGORY_CLIENT, ConfigurationHandler.KEY_VERSION_CHECK,true).comment = comment;
    }
}
*/