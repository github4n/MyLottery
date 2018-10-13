package com.csy.generator;

import com.csy.domain.GamePeriod;

public interface PropertyGenerate {
	boolean handle(long gameId);
	Object createProperty(GamePeriod gamePeriod);
}
