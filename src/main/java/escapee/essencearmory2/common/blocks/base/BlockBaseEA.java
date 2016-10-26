package escapee.essencearmory2.common.blocks.base;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.teamwizardry.librarianlib.common.base.block.BlockMod;
import escapee.essencearmory2.common.utils.Util;
import escapee.essencearmory2.init.BlockRegistry;

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

        BlockRegistry.BLOCKS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));
    }
}
