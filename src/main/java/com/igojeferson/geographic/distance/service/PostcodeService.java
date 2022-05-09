package com.igojeferson.geographic.distance.service;

import com.igojeferson.geographic.distance.exception.ServiceValidationException;
import com.igojeferson.geographic.distance.dto.PostcodeDto;
import com.igojeferson.geographic.distance.dto.PostcodeResponse;
import com.igojeferson.geographic.distance.entity.PostcodeEntity;
import com.igojeferson.geographic.distance.exception.NotFoundException;
import com.igojeferson.geographic.distance.repository.PostcodeRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostcodeService {

    private static final Logger logger = LoggerFactory.getLogger(PostcodeService.class);

    private PostcodeRepository repository;

    public PostcodeService(PostcodeRepository repository) {
        this.repository = repository;
    }

    public PostcodeResponse query(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Querying postcodes from page {} and size {}.", pageNumber, pageSize);
        // TODO: Add filters

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<PostcodeEntity> entities = repository.findAll(pageable);

        // get content for page object
        List<PostcodeEntity> listOfPostcodes = entities.getContent();

        List<PostcodeDto> content= listOfPostcodes.stream().map(postcode -> mapToDTO(postcode)).collect(Collectors.toList());

        PostcodeResponse postcodeResponse = new PostcodeResponse();
        postcodeResponse.setContent(content);
        postcodeResponse.setPageNumber(entities.getNumber());
        postcodeResponse.setPageSize(entities.getSize());
        postcodeResponse.setTotalElements(entities.getTotalElements());
        postcodeResponse.setTotalPages(entities.getTotalPages());
        postcodeResponse.setLast(entities.isLast());

        return postcodeResponse;
    }

    public PostcodeDto findById(int id) {
        Optional<PostcodeEntity> entity = repository.findById(id);

        if ( !entity.isPresent() ) {
            throw new NotFoundException("Postcode not registered with Id " + id);
        }

        return mapToDTO(entity.get());
    }

    public PostcodeDto findByPostcode(String postCode) {
        if (StringUtils.isBlank(postCode)){
            throw new ServiceValidationException("Postcode attribute must be informed");
        }

        logger.info("Querying postcode {}", postCode);

        Optional<PostcodeEntity> entity = repository.findByPostcode(postCode);

        if ( !entity.isPresent() ) {
            throw new NotFoundException("Postcode not registered for " + postCode);
        }

        return mapToDTO(entity.get());
    }

    public PostcodeDto saveOrUpdate(PostcodeDto postcodeDto) {
        if (StringUtils.isBlank(postcodeDto.getPostcode())) {
            throw new ServiceValidationException("Postcode attribute must be informed");
        }

        PostcodeEntity saved = repository.save(mapToEntity(postcodeDto));

        return mapToDTO(saved);
    }

    public boolean delete(int id) {
        repository.deleteById(id);

        logger.info("Deleting postcode with id [{}]", id);

        return true;
    }

    // convert Entity into DTO
    private PostcodeDto mapToDTO(PostcodeEntity entity){
        PostcodeDto postcodeDto = new PostcodeDto();
        postcodeDto.setId(entity.getId());
        postcodeDto.setPostcode(entity.getPostcode());
        postcodeDto.setLatitude(entity.getLatitude());
        postcodeDto.setLongitude(entity.getLongitude());
        return postcodeDto;
    }

    // convert DTO to entity
    private PostcodeEntity mapToEntity(PostcodeDto postDto){
        PostcodeEntity entity = new PostcodeEntity();
        entity.setPostcode(postDto.getPostcode());
        entity.setLatitude(postDto.getLatitude());
        entity.setLongitude(postDto.getLongitude());
        return entity;
    }
}  