package com.teamwizardry.inhumanresources.common.blocks.tile;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.teamwizardry.inhumanresources.InhumanResources;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityBeacon;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityBeacon.EnumAttunement;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityUpgrade;
import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityUpgrade.EnumUpgrade;
import com.teamwizardry.inhumanresources.common.utils.Interp1dLine;
import com.teamwizardry.inhumanresources.init.BlockRegistry;
import com.teamwizardry.librarianlib.core.LibrarianLib;
import com.teamwizardry.librarianlib.features.autoregister.TileRegister;
import com.teamwizardry.librarianlib.features.base.block.tile.TileModTickable;
import com.teamwizardry.librarianlib.features.math.interpolate.StaticInterp;
import com.teamwizardry.librarianlib.features.particle.ParticleBuilder;
import com.teamwizardry.librarianlib.features.particle.ParticleSpawner;
import com.teamwizardry.librarianlib.features.saving.Save;
import com.teamwizardry.librarianlib.features.utilities.client.ClientRunnable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@TileRegister("probability_beacon")
public class TEProbabilityBeacon extends TileModTickable
{
	public static final int NUM_UPGRADE_SLOTS = 4;

	public static final HashMap<BlockPos, Block> structure = new HashMap<>();

	static
	{
		structure.put(new BlockPos(3, -2, 3), Blocks.BEACON);
		structure.put(new BlockPos(3, -2, -3), Blocks.BEACON);
		structure.put(new BlockPos(-3, -2, 3), Blocks.BEACON);
		structure.put(new BlockPos(-3, -2, -3), Blocks.BEACON);

		structure.put(new BlockPos(5, 1, 0), BlockRegistry.blockProbabilityUpgrade);
		structure.put(new BlockPos(-5, 1, 0), BlockRegistry.blockProbabilityUpgrade);
		structure.put(new BlockPos(0, 1, 5), BlockRegistry.blockProbabilityUpgrade);
		structure.put(new BlockPos(0, 1, -5), BlockRegistry.blockProbabilityUpgrade);

		structure.put(new BlockPos(1, -1, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(2, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(3, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(4, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(5, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(5, -1, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(5, 0, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-1, -1, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-2, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-3, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-4, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-5, -2, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-5, -1, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(-5, 0, 0), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -1, 1), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, 2), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, 3), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, 4), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, 5), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -1, 5), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, 0, 5), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -1, -1), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, -2), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, -3), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, -4), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -2, -5), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, -1, -5), Blocks.PURPUR_BLOCK);
		structure.put(new BlockPos(0, 0, -5), Blocks.PURPUR_BLOCK);

		structure.put(new BlockPos(0, -1, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(0, -2, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(0, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(0, -2, -1), Blocks.OBSIDIAN);

		structure.put(new BlockPos(1, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(2, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(3, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(4, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(5, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(6, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(6, -2, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(6, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(5, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(4, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(3, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(2, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -2), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -3), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -4), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -5), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, -6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(0, -2, -6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -5), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -4), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -3), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -2), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-2, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-3, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-4, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-5, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-6, -2, -1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-6, -2, 0), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-6, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-5, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-4, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-3, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-2, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 1), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 2), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 3), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 4), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 5), Blocks.OBSIDIAN);
		structure.put(new BlockPos(-1, -2, 6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(0, -2, 6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 6), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 5), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 4), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 3), Blocks.OBSIDIAN);
		structure.put(new BlockPos(1, -2, 2), Blocks.OBSIDIAN);
	}

	private String structureError;

	@Save
	public EnumAttunement attunement;

	@Save
	public boolean isFormed;

	@Save
	public EnumUpgrade[] upgrades = new EnumUpgrade[NUM_UPGRADE_SLOTS];

	public TEProbabilityBeacon()
	{
	}

	public TEProbabilityBeacon(EnumAttunement attunement)
	{
		this();
		this.attunement = attunement;
	}

	@Override
	public void tick()
	{
		if (world.getTotalWorldTime() % 20L == 0L)
		{
			isFormed = checkStructure();

			if (structureError != null)
			{
				EntityPlayer player = LibrarianLib.PROXY.getClientPlayer();
				if (player != null) player.sendMessage(new TextComponentString(structureError));
			}

			IBlockState state = world.getBlockState(pos);
			boolean active = state.getValue(BlockProbabilityBeacon.ACTIVE);
			if (isFormed && !active)
				world.setBlockState(pos, state.withProperty(BlockProbabilityBeacon.ACTIVE, true), 2);
			else if (!isFormed && active) world.setBlockState(pos, state.withProperty(BlockProbabilityBeacon.ACTIVE, false), 2);
		}

		if (isFormed && world.isRemote)
		{
			ClientRunnable.run(new ClientRunnable()
			{
				@Override
				@SideOnly(Side.CLIENT)
				public void runIfClient()
				{
					ParticleBuilder builder = new ParticleBuilder(200);
					builder.disableRandom();
					builder.setMotion(new Vec3d(0, 0.05, 0));
					builder.setColor(Color.MAGENTA);
					builder.setScaleFunction(new Interp1dLine(1, 0));
					builder.setRender(new ResourceLocation(InhumanResources.MOD_ID, "particles/blur"));

					Vec3d center = new Vec3d(pos).addVector(0.5, 1, 0.5);
					Vec3d left = new Vec3d(-0.25, 0, 0).rotateYaw((world.getTotalWorldTime() / 360.0F * 30) % 360L).add(center);
					Vec3d right = new Vec3d(0.25, 0, 0).rotateYaw((world.getTotalWorldTime() / 360.0F * 30) % 360L).add(center);

					ParticleSpawner.spawn(builder, world, new StaticInterp<Vec3d>(left), 1);
					ParticleSpawner.spawn(builder, world, new StaticInterp<Vec3d>(right), 1);
				}
			});
		}
	}

	private boolean checkStructure()
	{
		List<EnumUpgrade> upgrades = new LinkedList<>();
		for (Entry<BlockPos, Block> entry : structure.entrySet())
		{
			IBlockState state = world.getBlockState(pos.add(entry.getKey()));
			Block block = state.getBlock();
			if (block != entry.getValue())
			{
				structureError = "Invalid block " + block.getLocalizedName() + " at " + entry.getKey() + ", should be " + entry.getValue().getLocalizedName();
				return false;
			}
			if (block == Blocks.BEACON)
			{
				TileEntity te = world.getTileEntity(pos.add(entry.getKey()));
				if (!(te instanceof TileEntityBeacon))
				{
					structureError = "Beacon at " + entry.getKey() + " has an invalid Tile Entity";
					return false;
				}
				if (((TileEntityBeacon) te).getLevels() < 1)
				{
					structureError = "Beacon at " + entry.getKey() + " does not have a base";
					return false;
				}
			}
			if (block == BlockRegistry.blockProbabilityUpgrade)
			{
				upgrades.add(state.getValue(BlockProbabilityUpgrade.UPGRADE));
				if (upgrades.size() > NUM_UPGRADE_SLOTS)
				{
					structureError = "Too many upgrades were found: " + upgrades;
					return false;
				}
			}
		}
		if (upgrades.size() < NUM_UPGRADE_SLOTS)
		{
			structureError = "Not enough upgrades were found: " + upgrades;
			return false;
		}
		this.upgrades = upgrades.toArray(this.upgrades);
		structureError = null;
		return true;
	}
}
