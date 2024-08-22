package net.minecraft.game.level.gennoise;

import net.minecraft.game.level.generator.noise.NoiseGenerator;

public final class NoiseGeneratorDistort extends NoiseGenerator {
	
	private NoiseGenerator source;
	private NoiseGenerator distort;

	public NoiseGeneratorDistort(NoiseGenerator source, NoiseGenerator distort) {
		this.source = source;
		this.distort = distort;
	}

	public final double generateNoise(double a, double b) {
		return this.source.generateNoise(a + this.distort.generateNoise(a, b), b);
	}
}
