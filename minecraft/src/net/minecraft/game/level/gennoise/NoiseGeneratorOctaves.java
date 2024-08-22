package net.minecraft.game.level.gennoise;

import java.util.Random;

import net.minecraft.game.level.generator.noise.NoiseGenerator;
import net.minecraft.game.level.generator.noise.NoiseGeneratorPerlin;

public final class NoiseGeneratorOctaves extends NoiseGenerator {
	
	private NoiseGeneratorPerlin[] generatorCollection;
	private int numOctaves;

	public NoiseGeneratorOctaves(Random random, int numOctaves) {
		
		this.numOctaves = numOctaves;
		this.generatorCollection = new NoiseGeneratorPerlin[numOctaves];

		for (int i = 0; i < numOctaves; i++) {
			this.generatorCollection[i] = new NoiseGeneratorPerlin(random);
		}

	}

	public final double generateNoise(double a, double b) {
		
		double value = 0.0D;
		double scale = 1.0D;

		for (int i = 0; i < this.numOctaves; i++) {
			value += this.generatorCollection[i].generateNoise(a / scale, b / scale) * scale;
			scale *= 2.0D;
		}

		return value;
	}
}
