package com.myapp.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.myapp.base.entity.Karooz;
@Component
public interface KaroozRepository  extends JpaRepository<Karooz,Long>  {

}
