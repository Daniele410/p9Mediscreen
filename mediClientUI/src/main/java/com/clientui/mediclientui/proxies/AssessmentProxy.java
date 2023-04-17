package com.clientui.mediclientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mediAssessment", url = "localhost:9003")
public interface AssessmentProxy {
}
