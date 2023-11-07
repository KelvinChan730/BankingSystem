package main.db;

import main.utility.Formatter;

public abstract class SequencedRecordCreator {
	private int sequence = 1;

	protected String getSequence() {
		return Formatter.formatSequence(sequence++);
	}

	public int getIntSequence() {
		return sequence;
	}
}