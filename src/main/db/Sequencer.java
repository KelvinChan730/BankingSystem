package main.db;

import main.utility.Formatter;

public abstract class Sequencer {
	private int sequence = 1;

	public String getAndIncrementSequence() {
		return Formatter.formatSequence(sequence++);
	}

	public String getSequence() {
		return Formatter.formatSequence(sequence);
	}
}