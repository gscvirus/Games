package com.games.bowling;

import java.util.Random;

public class Game {

	private Random random = new Random();
	private ScoreCard scoreCard = new ScoreCard();
	
	public void runGame() {
		for (int idx=0; idx<10; idx++) {
			Frame currentFrame = scoreCard.startFrame();
			
			currentFrame.scoreThrow(generateNumberOfPins(10));
			if (!currentFrame.isStrike()) {
				currentFrame.scoreThrow(generateNumberOfPins(10 - currentFrame.getNumberPinsThrowAt(ThrowType.FIRST_THROW)));
			}
		}
		int score = scoreCard.calculateScore();
	}
	
	private int generateNumberOfPins(int maxPins) {
		return random.nextInt(maxPins);
	}
}
