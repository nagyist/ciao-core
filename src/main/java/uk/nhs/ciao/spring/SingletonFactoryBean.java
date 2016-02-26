package uk.nhs.ciao.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Utility factory bean which always returns a pre-configured
 * instance.
 *
 * @param <T> The type of object provided by this factory
 */
public class SingletonFactoryBean<T> implements FactoryBean<T> {
	private final Class<T> objectType;
	private final T object;
	
	/**
	 * Constructs a new factory bean to supply the specified instance
	 * @param objectType 
	 * @param object 
	 */
	public SingletonFactoryBean(final Class<T> objectType, final T object) {
		this.objectType = objectType;
		this.object = object;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getObject() {
		return object;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getObjectType() {
		return objectType;
	}
	
	/**
	 * Utility method to create a spring bean definition which will always return the
	 * specified instance
	 * @param objectType 
	 * @param object 
	 * @return BeanDefinition
	 */
	public static <T> BeanDefinition defineSingletonBean(final Class<T> objectType, final T object) {
		final RootBeanDefinition def = new RootBeanDefinition(SingletonFactoryBean.class);
		
		final ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addIndexedArgumentValue(0, objectType);
		constructorArgumentValues.addIndexedArgumentValue(1, object);
		def.setConstructorArgumentValues(constructorArgumentValues);
		return def;
	}
}
