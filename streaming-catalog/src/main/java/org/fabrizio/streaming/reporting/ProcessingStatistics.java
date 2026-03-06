package org.fabrizio.streaming.reporting;

public class ProcessingStatistics {
    private int totalInput;
    private int totalOutput;
    private int discarded;
    private int corrected;
    private int duplicates;

    public void incrementTotalInput() {
        totalInput++;
    }

    public void incrementDiscarded() {
        discarded++;
    }

    public void incrementCorrected() {
        corrected++;
    }

    public void incrementDuplicates() {
        duplicates++;
    }

    public void setTotalOutput(int totalOutput) {
        this.totalOutput = totalOutput;
    }

    public int getTotalInput() {
        return totalInput;
    }

    public int getTotalOutput() {
        return totalOutput;
    }

    public int getDiscarded() {
        return discarded;
    }

    public int getCorrected() {
        return corrected;
    }

    public int getDuplicates() {
        return duplicates;
    }
}
