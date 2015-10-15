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
	
	public URIBuilder(final String uri) throws URISyntaxException {
		reset(uri);
	}
	
	public URIBuilder(final URI uri) throws URISyntaxException {
		reset(uri);
	}
	
	public final void reset(final String uri) throws URISyntaxException {
		reset(new URI(uri));
	}
	
	public final void reset(final URI uri) throws URISyntaxException {
		this.base = uri;
		this.queryParameters = URISupport.parseQuery(this.base.getQuery());
	}
	
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
	
	public URIBuilder set(final String name, final String value) {
		queryParameters.put(name, value);
		return this;
	}
	
	public URIBuilder remove(final String name) {
		queryParameters.remove(name);
		return this;
	}
	
	public Set<String> getNames() {
		return queryParameters.keySet();
	}
	
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
	
	public String getScheme() {
		return base.getScheme();
	}
	
	public String getPath() {
		return base.getPath();
	}
	
	public String getQuery() throws URISyntaxException {
		final String query = URISupport.createQueryString(queryParameters);
		return query.isEmpty() ? null : query;
	}
	
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
