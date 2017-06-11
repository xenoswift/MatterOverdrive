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

package matteroverdrive.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import matteroverdrive.MatterOverdrive;
import matteroverdrive.client.RenderHandler;
import matteroverdrive.client.render.HoloIcons;
import matteroverdrive.client.resources.data.WeaponMetadataSection;
import matteroverdrive.client.resources.data.WeaponMetadataSectionSerializer;
import matteroverdrive.compat.MatterOverdriveCompat;
import matteroverdrive.gui.GuiAndroidHud;
import matteroverdrive.gui.GuiQuestHud;
//import matteroverdrive.handler.GoogleAnalyticsClient;
import matteroverdrive.handler.KeyHandler;
import matteroverdrive.handler.MouseHandler;
import matteroverdrive.handler.TooltipHandler;
import matteroverdrive.handler.weapon.ClientWeaponHandler;
import matteroverdrive.handler.weapon.CommonWeaponHandler;
import matteroverdrive.init.MatterOverdriveGuides;
import matteroverdrive.init.MatterOverdriveIcons;
import matteroverdrive.init.MatterOverdriveItems;
import matteroverdrive.starmap.GalaxyClient;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    private static ClientProxy clientProxy;
    public static RenderHandler renderHandler;
    public static KeyHandler keyHandler;
    public static MouseHandler mouseHandler;
    public static GuiAndroidHud androidHud;
    public static HoloIcons holoIcons;
    private ClientWeaponHandler weaponHandler;
    public static GuiQuestHud questHud;

    public ClientProxy()
    {
        weaponHandler = new ClientWeaponHandler();
//        googleAnalyticsCommon = new GoogleAnalyticsClient();
    }

    @Override
	public void registerProxies()
	{
        super.registerProxies();

        renderHandler = new RenderHandler(Minecraft.getMinecraft().theWorld,Minecraft.getMinecraft().getTextureManager());
        androidHud = new GuiAndroidHud(Minecraft.getMinecraft());
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        holoIcons = new HoloIcons();
        weaponHandler = new ClientWeaponHandler();
        questHud = new GuiQuestHud();

        Minecraft.getMinecraft().getResourcePackRepository().rprMetadataSerializer.registerMetadataSectionType(new WeaponMetadataSectionSerializer(), WeaponMetadataSection.class);

        registerSubscribtions();

        //region Render Handler Functions
        //region Create
        renderHandler.createBlockRenderers();
        renderHandler.createTileEntityRenderers(MatterOverdrive.configHandler);
        renderHandler.createItemRenderers();
        renderHandler.createEntityRenderers();
        renderHandler.createBioticStatRenderers();
        renderHandler.createStarmapRenderers();
        renderHandler.createModels();
        //endregion
        //region Register
        renderHandler.registerBlockRenderers();
        renderHandler.registerTileEntitySpecialRenderers();
        renderHandler.registerItemRenderers();
        renderHandler.registerEntityRenderers();
        renderHandler.registerBioticStatRenderers();
        renderHandler.registerBionicPartRenderers();
        renderHandler.registerStarmapRenderers();
        renderHandler.registerWeaponModuleModels();
        //endregion
        //endregion

        MatterOverdrive.configHandler.subscribe(androidHud);
	}

    private void registerSubscribtions()
    {
        FMLCommonHandler.instance().bus().register(keyHandler);
        FMLCommonHandler.instance().bus().register(mouseHandler);
        MinecraftForge.EVENT_BUS.register(GalaxyClient.getInstance());
        MinecraftForge.EVENT_BUS.register(new MatterOverdriveIcons());
        MinecraftForge.EVENT_BUS.register(renderHandler);
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        MinecraftForge.EVENT_BUS.register(androidHud);
        MinecraftForge.EVENT_BUS.register(mouseHandler);
        MinecraftForge.EVENT_BUS.register(questHud);
        FMLCommonHandler.instance().bus().register(renderHandler);
        FMLCommonHandler.instance().bus().register(GalaxyClient.getInstance());
        FMLCommonHandler.instance().bus().register(androidHud);
        MinecraftForge.EVENT_BUS.register(weaponHandler);
    }

    @Override
    public void registerCompatModules()
    {
        super.registerCompatModules();
        MatterOverdriveCompat.registerClientModules();
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        weaponHandler.registerWeapon(MatterOverdriveItems.phaserRifle);
        weaponHandler.registerWeapon(MatterOverdriveItems.phaser);
        weaponHandler.registerWeapon(MatterOverdriveItems.omniTool);
        weaponHandler.registerWeapon(MatterOverdriveItems.plasmaShotgun);
        weaponHandler.registerWeapon(MatterOverdriveItems.ionSniper);

        MatterOverdriveGuides.registerGuideElements(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        MatterOverdriveGuides.registerGuides(event);
    }

    public ClientWeaponHandler getClientWeaponHandler()
    {
        return weaponHandler;
    }

    @Override
    public CommonWeaponHandler getWeaponHandler(){return weaponHandler;}

    public static ClientProxy instance()
    {
        if (clientProxy == null)
        {
            clientProxy = (ClientProxy)MatterOverdrive.proxy;
        }
        return clientProxy;
    }
}
