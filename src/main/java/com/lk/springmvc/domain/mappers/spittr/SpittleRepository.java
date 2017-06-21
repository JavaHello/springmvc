package com.lk.springmvc.service.spittr;

import com.lk.springmvc.domain.spittr.Spittle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokai on 17-6-21.
 */
@Repository
public interface SpittleRepository {
    List<Spittle> findSpittles(Long max,int count);
}
