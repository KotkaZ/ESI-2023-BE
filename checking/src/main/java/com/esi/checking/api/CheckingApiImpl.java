package com.esi.checking.api;


import com.esi.checking.CheckingApi;
import com.esi.checking.mapper.CheckingMapper;
import com.esi.checking.repository.CheckingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckingApiImpl implements CheckingApi {

    private final CheckingMapper checkingMapper;
    private final CheckingRepository checkingRepository;

}
