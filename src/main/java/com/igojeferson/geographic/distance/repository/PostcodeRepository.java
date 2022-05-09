package com.igojeferson.geographic.distance.repository;

import com.igojeferson.geographic.distance.entity.PostcodeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostcodeRepository extends PagingAndSortingRepository<PostcodeEntity, Integer> {

    Optional<PostcodeEntity> findByPostcode(String code);
}  