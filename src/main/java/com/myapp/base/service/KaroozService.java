package com.myapp.base.service;

import java.util.List;

import com.myapp.base.model.KaroozModel;

public interface KaroozService {

	public KaroozModel addKarooz(KaroozModel karoozModel);
	public List<KaroozModel> getAllKarooz();
	public KaroozModel deleteKarooz(Long id);
	
}
