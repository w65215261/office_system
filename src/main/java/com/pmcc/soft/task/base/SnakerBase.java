package com.pmcc.soft.task.base;

import org.snaker.engine.DBAccess;
import org.snaker.engine.SnakerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SnakerBase {
	protected String processId;
	@Autowired
	protected SnakerEngine snakerEngine;
	@Autowired
	protected DBAccess access;

	public SnakerEngine getEngine() {
		return snakerEngine;
	}
	
	public DBAccess access() {
		return access;
	}
}