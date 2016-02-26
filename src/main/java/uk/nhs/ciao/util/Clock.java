package uk.nhs.ciao.util;

import java.util.Date;

/**
 * Provides time values
 * <p>
 * Can be used as an alternative to a static dependency on {@link System#currentTimeMillis()} or equivalent.
 */
public abstract class Clock {
	/**
	 * Gets the current time in milliseconds
	 * @return current time
	 */
	public abstract long getMillis();

	/**
	 * Gets the current date as a {@link Date}
	 * @return current date
	 */
	public Date getDate() {
		return new Date(getMillis());
	}
}
