package com.esi.support.api;


import com.esi.support.SupportApi;
import com.esi.support.mapper.SupportMapper;
import com.esi.support.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupportApiImpl implements SupportApi {

    private final SupportMapper SupportMapper;
    private final SupportRepository SupportRepository;

}
