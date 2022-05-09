package com.igojeferson.geographic.distance.service;

import com.igojeferson.geographic.distance.dto.PostcodeDto;
import com.igojeferson.geographic.distance.dto.PostcodeResponse;
import com.igojeferson.geographic.distance.entity.PostcodeEntity;
import com.igojeferson.geographic.distance.exception.ServiceValidationException;
import com.igojeferson.geographic.distance.repository.PostcodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostcodeServiceTest {

    @InjectMocks
    PostcodeService service;

    @Mock
    PostcodeRepository postcodeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testQueryAllPostcodes() {
        List<PostcodeEntity> list = new ArrayList<>();
        PostcodeEntity entOne = new PostcodeEntity(1, "AB21 0XY", "57.237483", "-2.262400");
        PostcodeEntity entTwo = new PostcodeEntity(2, "AB21 0XW", "57.205923", "-2.288196");

        list.add(entOne);
        list.add(entTwo);

        when(postcodeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));

        //test
        PostcodeResponse postcodeResponse = service.query(1, 10, "id", "asc");

        assertEquals(2, postcodeResponse.getTotalElements());
        assertEquals(0, postcodeResponse.getPageNumber());
        assertNotNull(postcodeResponse.getContent());
        verify(postcodeRepository, times(1)).findAll(any(Pageable.class));

    }

    @Test
    void testFindByPostcode() {
        PostcodeEntity entity = new PostcodeEntity(1, "AB21 0XY", "57.237483", "-2.262400");

        when(postcodeRepository.findByPostcode(any())).thenReturn(java.util.Optional.of(entity));
        PostcodeDto postcodeDto = service.findByPostcode("AB21 0XY");

        assertEquals(entity.getLatitude(), postcodeDto.getLatitude());
        assertEquals(entity.getLongitude(), postcodeDto.getLongitude());

        verify(postcodeRepository, times(1)).findByPostcode(any());
    }

    @Test
    void shouldThrowExceptionWhenPostcodeIsInvalid() {
        Throwable exception = assertThrows(ServiceValidationException.class, () -> {
            service.findByPostcode("   ");
        });
        assertEquals(exception.getMessage(), "Postcode attribute must be informed");
    }

    @Test
    void shouldThrowExceptionWhenPostcodeIsNull() {
        Throwable exception = assertThrows(ServiceValidationException.class, () -> {
            service.findByPostcode(null);
        });
        assertEquals(exception.getMessage(), "Postcode attribute must be informed");
    }

}
