/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.xiaoqiaotq.util.persistence;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;

public class SearchFilter {

	public enum Operator {
		EQ,NEQ, LIKE, GT, LT, GTE, LTE
	}
	public enum ValueType {
		STRING,DATE,INTEGER
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
//			if (names.length != 2) {
//				throw new IllegalArgumentException(key + " is not a valid search filter name");
//			}
	    		String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);
			//add param type resolve
			if(names.length == 3){
				switch (ValueType.valueOf(names[2])) {
				case DATE:
					try {
						value=new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e.getMessage(),e);
					}
					break;
				default:
					break;
				}
			}

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
}
