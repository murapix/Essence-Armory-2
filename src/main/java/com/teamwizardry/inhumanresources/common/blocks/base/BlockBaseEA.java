package com.teamwizardry.inhumanresources.common.blocks.base;

import net.minecraft.block.material.Material;
import com.teamwizardry.inhumanresources.common.utils.Util;
import com.teamwizardry.librarianlib.common.base.block.BlockMod;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class BlockBaseEA extends BlockMod
{
    public BlockBaseEA(String name) {
        this(name, Material.ROCK);
    }

    public BlockBaseEA(String name, Material material) {
        super(name, material);
        setCreativeTab(Util.tabEssence);
    }
}
