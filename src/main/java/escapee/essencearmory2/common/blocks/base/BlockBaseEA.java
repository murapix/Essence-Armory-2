package escapee.essencearmory2.common.blocks.base;

import escapee.essencearmory2.MainRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class BlockBaseEA extends Block
{
    public BlockBaseEA(String name) {
        this(name, Material.ROCK);
    }

    public BlockBaseEA(String name, Material material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.COMBAT);

        MainRegistry.blockRegistry.BLOCKS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
    }
}
