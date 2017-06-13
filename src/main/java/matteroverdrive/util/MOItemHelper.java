package matteroverdrive.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Fagballs on 12/6/2017.
 */
public final class MOItemHelper
{
    private MOItemHelper() {}

    public static ItemStack cloneItem(Item item, int qty)
    {
        if (item == null)
        {
            return null;
        }
        else
        {
            ItemStack copy = new ItemStack(item, qty);
            return copy;
        }
    }


    public static ItemStack cloneBlock(Block block, int qty)
    {
        if (block == null)
        {
            return null;
        }
        else
        {
            ItemStack copy = new ItemStack(block, qty);
            return copy;
        }
    }


    public static ItemStack cloneStack(ItemStack stack, int qty)
    {
        if (stack == null)
        {
            return null;
        }
        else
        {
            ItemStack copy = stack.copy();
            copy.stackSize = qty;
            return copy;
        }
    }

    public static ItemStack cloneStack(ItemStack stack)
    {
        if (stack == null)
        {
            return null;
        }
        else
        {
            ItemStack copy = stack.copy();
            return copy;
        }
    }

}
