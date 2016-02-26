package uk.nhs.ciao.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Merges nodes from one tree structure into another
 */
public class TreeMerge {
	// Perhaps some configuration options could be added (i.e. whether to merge lists, overwrite defaults etc)
	
	/**
	 * @param source
	 * @param destination
	 */
	public void mergeInto(final Map<String, ?> source, final Map<String, Object> destination) {
		for (final Entry<String, ?> entry: source.entrySet()) {
			final Object destinationValue = destination.get(entry.getKey());
			final Object mergedValue = mergeValue(entry.getValue(), destinationValue);
			if (mergedValue != destinationValue) {
				destination.put(entry.getKey(), mergedValue);
			}
		}
	}
	
	private Object mergeValue(final Object sourceValue, final Object destinationValue) {
		if (destinationValue == null) {
			return sourceValue;
		} else if (destinationValue instanceof Map) {
			@SuppressWarnings("unchecked")
			final Map<String, Object> toMap = (Map<String, Object>)destinationValue;
			mergeValueIntoMap(sourceValue, toMap);
		} else if (destinationValue instanceof List) {
			@SuppressWarnings("unchecked")
			final List<Object> toList = (List<Object>)destinationValue;
			mergeValueIntoList(sourceValue, toList);
		} // else - do not overwrite!
		
		return destinationValue;
	}
	
	private void mergeValueIntoMap(final Object value, final Map<String, Object> destination) {
		if (value instanceof Map) {
			@SuppressWarnings("unchecked")
			final Map<String, ?> fromMap = (Map<String, ?>)value;
			mergeInto(fromMap, destination);
		} // else for not overwrite!
	}
	
	private void mergeValueIntoList(final Object value, final List<Object> destination) {
		if (value instanceof List) {
			destination.addAll((List<?>)value);
		} else {
			destination.add(value);
		}
	}
}
