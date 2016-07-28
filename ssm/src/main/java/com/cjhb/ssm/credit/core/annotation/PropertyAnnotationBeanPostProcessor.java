package com.cjhb.ssm.credit.core.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Component
public class PropertyAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	@Resource
	private ExtendedPropertyPlaceholderConfigurer propertyConfigurer;

	private SimpleTypeConverter typeConverter = new SimpleTypeConverter();

	@Override
	public boolean postProcessAfterInstantiation(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {

			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				Property property = field.getAnnotation(Property.class);
				if (property != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("@Property annotation is not supported on static fields");
					}

					String key = property.name().length() <= 0 ? field.getName() : property.name();
					Object value = propertyConfigurer.getProperty(key);

					if (value != null) {
						Object _value = typeConverter.convertIfNecessary(value, field.getType());
						ReflectionUtils.makeAccessible(field);
						field.set(bean, _value);
					}
				}
			}
		});

		return true;
	}
}
