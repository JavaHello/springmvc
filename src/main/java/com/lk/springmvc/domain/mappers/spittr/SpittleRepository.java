package com.lk.springmvc.domain.mappers.spittr;

import com.lk.springmvc.domain.model.spittr.Spittle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokai on 17-6-21.
 */
@Repository
public interface SpittleRepository {
    List<Spittle> findSpittles(Long max, int count);
    Spittle findOne(Integer id);
    Spittle save(Spittle spittle);
}
