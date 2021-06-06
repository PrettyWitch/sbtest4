package com.yuhan.booking.service;

import com.yuhan.car.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuhan
 * @date 02.06.2021 - 20:26
 * @purpose
 */
@FeignClient(value = "car-service", url = "localhost:8181")
public interface CarService {
    // 通过carUid获得car实体
    @RequestMapping(value = "/car/{carUid}", method = RequestMethod.GET)
    public Car findByCarUid(@PathVariable int carUid);

}