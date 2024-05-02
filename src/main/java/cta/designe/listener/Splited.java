package cta.designe.listener;

public class Splited {
	private String splitedString;
	private int indexSplitedString;
	
	public Splited(String splitedString, int indexSplitedString) {
		this.splitedString = splitedString;
		this.indexSplitedString = indexSplitedString;
	}

	public String getSplitedString() {
		return splitedString;
	}

	public void setSplitedString(String splitedString) {
		this.splitedString = splitedString;
	}

	public int getIndexSplitedString() {
		return indexSplitedString;
	}

	public void setIndexSplitedString(int indexSplitedString) {
		this.indexSplitedString = indexSplitedString;
	}

	@Override
	public String toString() {
		return "[splitedString=" + splitedString + ", indexSplitedString=" + indexSplitedString + "]";
	}
	
	
	
}
