package com.teamwizardry.inhumanresources.common.utils;

import java.util.List;

import javax.annotation.Nonnull;

import com.teamwizardry.librarianlib.features.math.interpolate.InterpFunction;

public class Interp1dLine implements InterpFunction<Float>
{
	private float start;
	private float finish;

	public Interp1dLine(float start, float finish)
	{

		this.start = start;
		this.finish = finish;
	}

	@Override
	public Float get(float v)
	{
		return Math.abs((start * (1 - v)) + (finish * v));
	}

	@Nonnull
	@Override
	public InterpFunction<Float> reverse()
	{
		return new Interp1dLine(finish, start);
	}

	@Nonnull
	@Override
	public List<Float> list(int i)
	{
		return null;
	}
}
