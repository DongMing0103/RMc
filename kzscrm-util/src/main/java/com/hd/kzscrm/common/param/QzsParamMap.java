package com.hd.kzscrm.common.param;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.hd.wolverine.util.ParamMap;

public class QzsParamMap extends ParamMap {

	private static final long serialVersionUID = 1L;

	public QzsParamMap(PageParam param) {
		if (param.getSortConditionMap().size() > 0) {
			LinkedHashMap linkMap = param.getSortConditionMap();
			Iterator iterator = linkMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String orderBy = (String) linkMap.get(key);
				if (orderBy != null) {
					addOrder(key, orderBy);
				}
			}
		}

	}

}
