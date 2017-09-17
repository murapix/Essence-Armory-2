package com.teamwizardry.inhumanresources.common.blocks.tile;

import com.teamwizardry.inhumanresources.common.blocks.BlockProbabilityBeacon.EnumAttunement;
import com.teamwizardry.librarianlib.features.autoregister.TileRegister;
import com.teamwizardry.librarianlib.features.base.block.tile.TileMod;
import com.teamwizardry.librarianlib.features.saving.Save;

@TileRegister("probability_beacon")
public class TEProbabilityBeacon extends TileMod
{
	@Save
	public EnumAttunement attunement;
	
	public TEProbabilityBeacon()
	{}
	
	public TEProbabilityBeacon(EnumAttunement attunement)
	{
		this();
		this.attunement = attunement;
	}
}
