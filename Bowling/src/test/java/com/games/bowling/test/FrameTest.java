package com.games.bowling.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.games.bowling.Frame;
import com.games.bowling.FrameType;
import com.games.bowling.ThrowType;

class FrameTest {

	@Test
	void noThrowsTest() {
		Frame emptyFrame = new Frame();
		
		assertThat(0, is(equalTo(emptyFrame.getNumberOfThrows())));
	}
	
	@Test
	void scoreAThrowTest() {
		Frame frame = new Frame();
		
		frame.scoreThrow(5);
		
		assertThat(1, is(equalTo(frame.getNumberOfThrows())));
		assertThat(5, is(equalTo(frame.getPinsOfThrow(ThrowType.FIRST_THROW))));
		assertThat(FrameType.SPARE, not(equalTo(frame.getFrameType())));
	}
	
	@Test
	void scoreTwoThrowsTest() {
		Frame frame = new Frame();
		
		frame.scoreThrow(4);
		frame.scoreThrow(5);
		
		assertThat(2, is(equalTo(frame.getNumberOfThrows())));
		assertThat(4, is(equalTo(frame.getPinsOfThrow(ThrowType.FIRST_THROW))));
		assertThat(5, is(equalTo(frame.getPinsOfThrow(ThrowType.SECOND_THROW))));
		assertThat(FrameType.SPARE, not(equalTo(frame.getFrameType())));
		assertThat(FrameType.STRIKE, not(equalTo(frame.getFrameType())));
	}
	
	@Test
	void scoreAStrike() {
		Frame frame = new Frame();
		
		frame.scoreThrow(10);
		assertThat(1, is(equalTo(frame.getNumberOfThrows())));
		assertThat(10, is(equalTo(frame.getPinsOfThrow(ThrowType.FIRST_THROW))));
		assertThat(FrameType.STRIKE, is(equalTo(frame.getFrameType())));
	}
	
	@Test
	void scoreASpare() {
		Frame frame = new Frame();
		
		frame.scoreThrow(5);
		frame.scoreThrow(5);
		assertThat(2, is(equalTo(frame.getNumberOfThrows())));
		assertThat(FrameType.SPARE, is(equalTo(frame.getFrameType())));
	}
	
	@Test
	void shouldThrowExceptionWhenScoringMoreThanTwoThrows() {
		Frame frame = new Frame();

		frame.scoreThrow(5);
		frame.scoreThrow(5);
		assertThrows(IllegalStateException.class, ()-> { frame.scoreThrow(0); });
	}
	
	@Test
	void shouldThrowExceptionWhenNumberOfPinsIsGreaterThanTen() {
		Frame frame = new Frame();
		
		assertThrows(IllegalArgumentException.class, ()->{ frame.scoreThrow(11); });
	}

}
