package com.igojeferson.geographic.distance.controller;

import com.igojeferson.geographic.distance.dto.MessageDTO;
import com.igojeferson.geographic.distance.dto.PostcodeDto;
import com.igojeferson.geographic.distance.dto.PostcodeResponse;
import com.igojeferson.geographic.distance.entity.PostcodeEntity;
import com.igojeferson.geographic.distance.service.PostcodeService;
import com.igojeferson.geographic.distance.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/postcodes")
public class PostcodeController {

    private PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping
    private PostcodeResponse query(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return postcodeService.query(pageNumber, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PostcodeDto> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(postcodeService.findById(id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable("id") int id) {
        postcodeService.delete(id);
        return ResponseEntity.ok().body(new MessageDTO(HttpStatus.OK.value(), "Postcode removed successfully!" ));
    }

    @PostMapping
    private ResponseEntity<PostcodeDto> save(@RequestBody PostcodeDto postcodeDto) {
        return ResponseEntity.ok(postcodeService.saveOrUpdate(postcodeDto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<PostcodeDto> update(
            @PathVariable("id") int id,
            @RequestBody PostcodeDto postcodeDto) {
        postcodeDto.setId(id);
        return ResponseEntity.ok(postcodeService.saveOrUpdate(postcodeDto));
    }
}  