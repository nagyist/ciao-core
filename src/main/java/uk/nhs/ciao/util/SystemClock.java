package uk.nhs.ciao.util;

/**
 * A clock instance backed by the system clock: {@link System#currentTimeMillis()}
 */
public class SystemClock extends Clock {
	private static final SystemClock INSTANCE = new SystemClock();
	
	/**
	 * Gets an instance of {@link SystemClock}
	 */
	public static SystemClock getInstance() {
		return INSTANCE;
	}
	
	@Override
	public long getMillis() {
		return System.currentTimeMillis();
	}
}
