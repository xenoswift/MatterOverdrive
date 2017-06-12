/*
package matteroverdrive.handler;

import cofh.api.energy.IEnergyContainerItem;
import com.google.common.base.Throwables;
import cpw.mods.fml.relauncher.ReflectionHelper;
import matteroverdrive.init.MatterOverdriveItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.lang.reflect.Field;
*/
/**
 * Created by Fagballs on 11/6/2017.
 */
/*
public class MatterCraftingHandler implements IRecipe
{

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        EntityPlayer player = findPlayer(inv);
        return player != null && hasBattery(inv);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
    {
        ItemStack stack = getRecipeOutput().copy();
        for (int i = 0;i < inventoryCrafting.getSizeInventory();i++)
        {
            if (inventoryCrafting.getStackInSlot(i) != null && inventoryCrafting.getStackInSlot(i).getItem() instanceof IEnergyContainerItem)
            {
                int energyStored = ((IEnergyContainerItem) inventoryCrafting.getStackInSlot(i).getItem()).getEnergyStored(inventoryCrafting.getStackInSlot(i));
                int packEnergy = MatterOverdriveItems.energyPack.getEnergyAmount(inventoryCrafting.getStackInSlot(i));
                if (energyStored > 0)
                {
                    stack.stackSize = energyStored / packEnergy;
                    return stack;
                }
            }
        }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(MatterOverdriveItems.battery);
    }

    private static boolean hasBattery(InventoryCrafting inv)
    {
        Item batt = MatterOverdriveItems.battery;
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack istack = inv.getStackInSlot(i);
            if (istack != null && istack.getItem() == batt)
            {
                return true;
            }
        }
        return false;

    }


    private static final Field eventHandlerField = ReflectionHelper.findField(InventoryCrafting.class, "eventHandler");
//    private static final Field containerPlayerField = ReflectionHelper.findField(ContainerPlayer.class, "thePlayer");
    private static final Field slotCraftingPlayerField = ReflectionHelper.findField(SlotCrafting.class, "thePlayer");

    private static EntityPlayer findPlayer(InventoryCrafting inv)
    {
        try
        {
            Container con = (Container) eventHandlerField.get(inv);
            if (con instanceof ContainerWorkbench)
            {
                return (EntityPlayer) slotCraftingPlayerField.get(con.getSlot((0)));
            }
            else
            {
                return null;
            }

        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

    }
}
*/