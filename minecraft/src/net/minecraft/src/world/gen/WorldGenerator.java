package net.minecraft.src.world.gen;

import java.util.Random;

import net.minecraft.src.world.World;

public abstract class WorldGenerator {
	
	public abstract boolean generate(World world, Random rand, int x, int y, int z);

}
