package com.summit.whms.inflearn.sample;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService{

    public String getName() {
        return "Jacob";
    }
}
