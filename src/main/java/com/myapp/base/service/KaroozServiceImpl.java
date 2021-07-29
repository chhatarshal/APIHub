package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.base.entity.Karooz;
import com.myapp.base.model.KaroozModel;
import com.myapp.base.repository.KaroozRepository;

@Service
public class KaroozServiceImpl implements KaroozService {
	
	@Autowired
	private KaroozRepository karoozRepo;
	
	 @Autowired
	 private ModelMapper modelMapper;

	@Override
	public KaroozModel addKarooz(KaroozModel karoozModel) {
		karoozRepo.save(convertToKarooz(karoozModel));
		return karoozModel;
	}

	private Karooz convertToKarooz(KaroozModel karoozModel) {
		Karooz karooz = modelMapper.map(karoozModel, Karooz.class);	    
	    return karooz;
	}
	
	private KaroozModel convertToKaroozModel(Karooz karooz) {
		KaroozModel KaroozModel = modelMapper.map(karooz, KaroozModel.class);	    
	    return KaroozModel;
	}

	@Override
	public List<KaroozModel> getAllKarooz() {
		return karoozRepo.findAll().stream().map(this::convertToKaroozModel).collect(Collectors.toList());
	}

	@Override
	public KaroozModel deleteKarooz(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
