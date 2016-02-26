package uk.nhs.ciao.camel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.camel.util.URISupport;

/**
 * URI builder backed by Camel's URI utility methods
 */
public class URIBuilder {
	private URI base;
	private Map<String, Object> queryParameters;
	
	/**
	 * @param uri
	 * @throws URISyntaxException
	 */
	public URIBuilder(final String uri) throws URISyntaxException {
		reset(uri);
	}
	
	/**
	 * @param uri
	 * @throws URISyntaxException
	 */
	public URIBuilder(final URI uri) throws URISyntaxException {
		reset(uri);
	}
	
	/**
	 * @param uri
	 * @throws URISyntaxException
	 */
	public final void reset(final String uri) throws URISyntaxException {
		reset(new URI(uri));
	}
	
	/**
	 * @param uri
	 * @throws URISyntaxException
	 */
	public final void reset(final URI uri) throws URISyntaxException {
		this.base = uri;
		this.queryParameters = URISupport.parseQuery(this.base.getQuery());
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	@SuppressWarnings("unchecked")
	public URIBuilder add(final String name, final String value) {
		if (queryParameters.containsKey(name)) {
			List<Object> list;
			Object previousValue = queryParameters.get(name);
			if (previousValue instanceof List<?>) {
				list = (List<Object>)previousValue;
			} else {
				list = new ArrayList<Object>();
				list.add(previousValue);
				list.add(value);
				queryParameters.put(name, list);
			}
		} else {
			queryParameters.put(name, value);
		}
		
		return this;
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder add(final String name, final boolean value) {
		return add(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder add(final String name, final int value) {
		return add(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder add(final String name, final long value) {
		return add(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder set(final String name, final String value) {
		queryParameters.put(name, value);
		return this;
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder set(final String name, final boolean value) {
		return set(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder set(final String name, final int value) {
		return set(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @param value
	 * @return URIBuilder
	 */
	public URIBuilder set(final String name, final long value) {
		return set(name, String.valueOf(value));
	}
	
	/**
	 * @param name
	 * @return URIBuilder
	 */
	public URIBuilder remove(final String name) {
		queryParameters.remove(name);
		return this;
	}
	
	/**
	 * @return Query parameters
	 */
	public Set<String> getNames() {
		return queryParameters.keySet();
	}
	
	/**
	 * @param name
	 * @return First query parameter
	 */
	public String getFirst(final String name) {
		final Object value = queryParameters.get(name);
		final Object first;
		if (value instanceof List) {
			final List<?> values = (List<?>)value;
			first = values.isEmpty() ? null : values.get(0);
		} else {
			first = value;
		}
		
		return first == null ? null : first.toString();
	}
	
	/**
	 * @param name
	 * @return List of query parameters
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAll(final String name) {
		final List<Object> values;
		
		final Object value = queryParameters.get(name);
		if (value instanceof List) {
			values = (List<Object>)value;
		} else if (value != null || queryParameters.containsKey(name)) {
			values = Collections.singletonList(value);
		} else {
			values = Collections.emptyList();
		}

		return values;
	}
	
	/**
	 * @return Scheme
	 */
	public String getScheme() {
		return base.getScheme();
	}
	
	/**
	 * @return Path
	 */
	public String getPath() {
		return base.getPath();
	}
	
	/**
	 * @return Query string
	 * @throws URISyntaxException
	 */
	public String getQuery() throws URISyntaxException {
		final String query = URISupport.createQueryString(queryParameters);
		return query.isEmpty() ? null : query;
	}
	
	/**
	 * @return URI
	 * @throws URISyntaxException
	 */
	public URI toURI() throws URISyntaxException {
		return URISupport.createURIWithQuery(base, getQuery());
	}
	
	@Override
	public String toString() {
		try {
			return toURI().toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
