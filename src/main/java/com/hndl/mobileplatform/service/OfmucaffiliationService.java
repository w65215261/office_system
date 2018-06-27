package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Ofmucaffiliation;
import com.hndl.mobileplatform.model.OfmucaffiliationExample;

public interface OfmucaffiliationService {
	int insert(Ofmucaffiliation record);
	List<Ofmucaffiliation> selectByExample(OfmucaffiliationExample example);
}
