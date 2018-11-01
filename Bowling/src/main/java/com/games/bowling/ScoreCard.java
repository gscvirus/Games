package com.games.bowling;

public class ScoreCard {
	
	private Frame[] frames = new Frame[10];
	private int currentFrame;
	
	
	public ScoreCard() {
		currentFrame = -1;
	}
	
	public Frame startFrame() {
		currentFrame += 1;
		frames[currentFrame] = isLastFrame() ? new Frame(3) : new Frame();
		
		return frames[currentFrame];
	}
	
	private boolean isLastFrame() {
		return currentFrame == 9;
	}
	
	public int calculateScore() {
		int score = 0;
		
		for (Frame frame : frames) {
			score += frame.getPinsOfThrow(ThrowType.FIRST_THROW);
			if (frame.getFrameType().equals(FrameType.STRIKE)) {
			} else if (frame.getFrameType().equals(FrameType.SPARE)) {
			} else {
				score += frame.getPinsOfThrow(ThrowType.SECOND_THROW);
			}
		}
		
		return score;
	}
	
}
