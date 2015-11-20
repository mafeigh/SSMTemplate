package com.pingan.controller;

import com.pingan.util.PropertyReader;

import javax.annotation.Resource;
import java.util.Map;
import java.util.TreeMap;

public abstract class BaseController {
	protected Map<String, Object> jsonMap = new TreeMap<String, Object>();

	@Resource
	protected PropertyReader propertyReader;
}
