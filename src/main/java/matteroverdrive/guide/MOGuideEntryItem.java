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

package matteroverdrive.guide;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Simeon on 8/30/2015.
 */
public class MOGuideEntryItem extends MOGuideEntry
{
    public MOGuideEntryItem(Item item)
    {
        super(item.getUnlocalizedName(), (ItemStack) null);
        setStackIcons(item);
    }
    public MOGuideEntryItem(Item itemIcon,String name)
    {
        super(name,new ItemStack(itemIcon));
    }

    @Override
    public String getDisplayName()
    {
        return getStackIcons()[0].getDisplayName();
    }
}
