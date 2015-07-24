package uk.nhs.ciao.util;

import java.util.Map.Entry;

/**
 * A simple stand-alone {@link Entry} implementation.
 * <p>
 * Null keys and/or null values are permitted.
 *
 * @param <K> The type of key stored in the entry
 * @param <V> The type of value stored in the entry
 */
public class SimpleEntry<K, V> implements Entry<K, V> {
	private final K key;
	private V value;
	
	/**
	 * Constructs a new entry
	 */
	public SimpleEntry(final K key, final V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Constructs a new entry
	 */
	public SimpleEntry(final Entry<? extends K, ? extends V> entry) {
		this.key = entry.getKey();
		this.value = entry.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public K getKey() {
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V setValue(final V value) {
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return key + ": " + value;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * Entries are considered equal if they both implement Entry and their keys and values are equal
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof Entry<?,?>)) {
			return false;
		}
		
		final Entry<?,?> other = (Entry<?,?>)obj;
		return equals(key, other.getKey()) && 
				equals(value, other.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	
	/**
	 * Factory method
	 */
	public static <K, V> SimpleEntry<K, V> valueOf(final K key, final V value) {
		return new SimpleEntry<K, V>(key, value);
	}

	/**
     * Null-safe test for equality
     */
    private static boolean equals(final Object o1, final Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }
}
