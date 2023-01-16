package com.pos.Generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerUtils
{
	public static List<Map<String, Object>> fetchClassFieldsInfo(Class clazz)
	{
		Class current = clazz;
		List<Map<String, Object>> result = new ArrayList<>();
		do
		{
			appendClassFieldsInfo(current, result);
		}
		while ((current = current.getSuperclass()) != null);
		return result;
	}

	private static void appendClassFieldsInfo(Class current, List<Map<String, Object>> result)
	{
		for (Field field : current.getDeclaredFields())
		{
			field.setAccessible(true);
			if (field.isAnnotationPresent(FieldInfo.class) && field.getAnnotation(FieldInfo.class).system())
				continue;
			Map<String, Object> fieldInfo = new HashMap<>();
			fieldInfo.put("name", field.getName());
			fieldInfo.put("text", humanize(field.getName()));
			fieldInfo.put("required", field.isAnnotationPresent(FieldInfo.class) && field.getAnnotation(FieldInfo.class).required());
			fieldInfo.put("inputType",
					field.isAnnotationPresent(FieldInfo.class) ? field.getAnnotation(FieldInfo.class).inputType().value : HTMLInputType.TEXT.value);
			fieldInfo.put("order", field.isAnnotationPresent(FieldInfo.class) ? field.getAnnotation(FieldInfo.class).order() : Integer.MAX_VALUE);
			fieldInfo.put("embeddable", field.isAnnotationPresent(FieldInfo.class) && field.getAnnotation(FieldInfo.class).embeddable());
			if (field.isAnnotationPresent(FieldInfo.class) && field.getAnnotation(FieldInfo.class).embeddable())
			{
				appendClassFieldsInfoForEmbeddable(field, result);
			}
			result.add(fieldInfo);
		}
	}

	private static void appendClassFieldsInfoForEmbeddable(Field field, List<Map<String, Object>> result)
	{
		appendClassFieldsInfo(field.getType(), result);
		for (Field declaredField : field.getType().getDeclaredFields())
		{
			result.stream().filter(l -> l.get("name") == declaredField.getName()).findFirst().ifPresent(map -> map.put("parent", field.getName()));
		}
	}

	public static String humanize(String s)
	{
		String splitCamelCase = s.replaceAll(
				String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
		return splitCamelCase.substring(0, 1).toUpperCase() + splitCamelCase.substring(1);
	}
}
