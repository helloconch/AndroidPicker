package com.conch.timepicker;


/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter implements WheelAdapter {
	
	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 9;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;
	
	// Values
	private int minValue;
	private int maxValue;
	
	// format
	private String format;
	
	/**
	 * Default constructor
	 */
	public NumericWheelAdapter() {
		this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	/**
	 * Constructor
	 * @param minNumberValue the wheel min value
	 * @param maxNumberValue the wheel max value
	 */
	public NumericWheelAdapter(int minNumberValue, int maxNumberValue) {
		this(minNumberValue, maxNumberValue, null);
	}

	/**
	 * Constructor
	 * @param minNumberValue the wheel min value
	 * @param maxNumberValue the wheel max value
	 * @param formatNumber the format string
	 */
	public NumericWheelAdapter(int minNumberValue, int maxNumberValue, String formatNumber) {
		this.minValue = minNumberValue;
		this.maxValue = maxNumberValue;
		this.format = formatNumber;
	}

	@Override
	public String getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			int value = minValue + index;
			return format != null ? String.format(format, value) : Integer.toString(value);
		}
		return null;
	}

	@Override
	public int getItemsCount() {
		return maxValue - minValue + 1;
	}
	
	@Override
	public int getMaximumLength() {
		int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
		int maxLen = Integer.toString(max).length();
		if (minValue < 0) {
			maxLen++;
		}
		return maxLen;
	}
}
