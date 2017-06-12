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

package matteroverdrive.items.includes;

import cofh.api.energy.IEnergyContainerItem;
import com.google.common.base.Throwables;
import cpw.mods.fml.relauncher.ReflectionHelper;
import matteroverdrive.init.MatterOverdriveItems;
import matteroverdrive.util.MOEnergyHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.List;

import static matteroverdrive.util.MOEnergyHelper.setDefaultEnergyTag;

public class MOItemEnergyContainer extends MOBaseItem implements IEnergyContainerItem
{
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;



	public MOItemEnergyContainer(String name)
	{
		this(name,32000);
	}

	public MOItemEnergyContainer(String name,int capacity)
	{
		this(name,capacity, capacity, capacity);
	}

	public MOItemEnergyContainer(String name,int capacity, int maxTransfer) {

		this(name,capacity, maxTransfer, maxTransfer);
	}

	public MOItemEnergyContainer(String name,int capacity, int maxReceive, int maxExtract) {
		super(name);
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return getMaxEnergyStored(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return getMaxEnergyStored(stack) - getEnergyStored(stack);
	}

//	@Override
//	public int getDisplayDamage(ItemStack stack)
//	{
//		return getMaxEnergyStored(stack) - getEnergyStored(stack);
//	}

	@Override
	public void addDetails(ItemStack itemstack, EntityPlayer player, List infos)
	 {
		this.TagCompoundCheck(itemstack);
		infos.add(EnumChatFormatting.YELLOW + MOEnergyHelper.formatEnergy(getEnergyStored(itemstack), getMaxEnergyStored(itemstack)));
	 }

	@Override
	public boolean hasDetails(ItemStack itemStack)
	{
		return true;
	}

	public MOItemEnergyContainer setCapacity(int capacity) {

		this.capacity = capacity;
		return this;
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		this.TagCompoundCheck(container);

		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate)
		{
            energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if(container.stackTagCompound != null && container.stackTagCompound.hasKey("Energy"))
        {
            int energy = container.stackTagCompound.getInteger("Energy");
            int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

            if (!simulate) {
                energy -= energyExtracted;
                container.stackTagCompound.setInteger("Energy", energy);
            }
            return energyExtracted;
        }
        return 0;
	}

	protected void setEnergyStored(ItemStack container, int amount)
	{
		setDefaultEnergyTag(container, amount);
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		this.TagCompoundCheck(container);
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return capacity;
	}




//    @Override
//    public boolean matches(InventoryCrafting inv, World world) {
//        EntityPlayer player = findPlayer(inv);
//        return player != null && hasBattery(inv);
//    }
//
//    @Override
//    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
//    {
//        ItemStack stack = getRecipeOutput().copy();
//        for (int i = 0;i < inventoryCrafting.getSizeInventory();i++)
//        {
//            if (inventoryCrafting.getStackInSlot(i) != null && inventoryCrafting.getStackInSlot(i).getItem() instanceof IEnergyContainerItem)
//            {
//                int energyStored = ((IEnergyContainerItem) inventoryCrafting.getStackInSlot(i).getItem()).getEnergyStored(inventoryCrafting.getStackInSlot(i));
//                int packEnergy = MatterOverdriveItems.energyPack.getEnergyAmount(inventoryCrafting.getStackInSlot(i));
//                if (energyStored > 0)
//                {
//                    stack.stackSize = energyStored / packEnergy;
//                    return stack;
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int getRecipeSize() {
//        return 1;
//    }
//
//    @Override
//    public ItemStack getRecipeOutput() {
//        return new ItemStack(MatterOverdriveItems.battery);
//    }
//
//    private static boolean hasBattery(InventoryCrafting inv)
//    {
//        Item batt = MatterOverdriveItems.battery;
//        for (int i = 0; i < inv.getSizeInventory(); i++)
//        {
//            ItemStack istack = inv.getStackInSlot(i);
//            if (istack != null && istack.getItem() == batt)
//            {
//                return true;
//            }
//        }
//        return false;
//
//    }
//
//
//    private static final Field eventHandlerField = ReflectionHelper.findField(InventoryCrafting.class, "eventHandler");
//    //    private static final Field containerPlayerField = ReflectionHelper.findField(ContainerPlayer.class, "thePlayer");
//    private static final Field slotCraftingPlayerField = ReflectionHelper.findField(SlotCrafting.class, "thePlayer");
//
//    private static EntityPlayer findPlayer(InventoryCrafting inv)
//    {
//        try
//        {
//            Container con = (Container) eventHandlerField.get(inv);
//            if (con instanceof ContainerWorkbench)
//            {
//                return (EntityPlayer) slotCraftingPlayerField.get(con.getSlot((0)));
//            }
//            else
//            {
//                return null;
//            }
//
//        } catch (Exception e) {
//            throw Throwables.propagate(e);
//        }
//
//    }
}
