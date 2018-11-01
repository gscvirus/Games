package com.games.bowling;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class Frame {
	
	private List<Integer> _throws;
	private FrameType frameType;
	private int maxThrows;
	
	public Frame() {
		_throws = new ArrayList<>();
		frameType = FrameType.NORMAL;
		maxThrows = 2;
	}
	
	public Frame(int maxThrows) {
		this();
		this.maxThrows = maxThrows;
	}
	
	public void scoreThrow(int pins) {
		//TODO: Add guards
		if (pins < 0) {
			throw new IllegalArgumentException("Pins should be greater or equal to zero");
		}
		if (numberOfPins() + pins > 10) {
			throw new IllegalArgumentException("Number of pins knocked down can not be greater than 10");
		}
		if (_throws.size() > maxThrows) {
			throw new  IllegalStateException("Max number of throws reached");
		}
		_throws.add(pins);
		if (isFirstThrow()) {
			if (pins == 10) {
				frameType = FrameType.STRIKE;
			}
		} else if (numberOfPins() == 10){
			frameType = FrameType.SPARE;
		}
	}
	
	public FrameType getFrameType() {
		return frameType;
	}
	
	public int getNumberOfThrows() {
		return _throws.size();
	}
	
	public int getNumberPinsThrowAt(ThrowType _throw) {
		return _throws.get(_throw.ordinal());
	}
	
	public boolean isStrike() {
		return frameType.equals(FrameType.STRIKE);
	}
	
	public boolean isSpare() {
		return frameType.equals(FrameType.SPARE);
	}
	
	private boolean isFirstThrow() {
		return CollectionUtils.isEmpty(_throws);
	}
	
	private int numberOfPins() {
		int sum = 0;
		
		for (Integer pins : _throws) {
			sum += pins;
		}
		
		return sum;
	}
	
}
