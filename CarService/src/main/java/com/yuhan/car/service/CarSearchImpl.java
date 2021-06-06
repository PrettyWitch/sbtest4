//package com.yuhan.car.service;
//
//import com.yuhan.car.entity.Car;
//import com.yuhan.car.repository.CarRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.xml.ws.ServiceMode;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author yuhan
// * @date 07.05.2021 - 11:11
// * @purpose
// */
//@Service
//public class CarSearchImpl implements CarSearch {
//
//    @Autowired
//    private CarRepository carRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(CarSearchImpl.class);
//    //    3.获取有关办公室中所有汽车的信息。[G]GET /办公室/ {officeUid} /汽车
//    @Override
//    public List<Car> findByOfficeUid(int officeUid) {
//        logger.info("CarSearchImpl: officeUid {}", officeUid);
//        List<Car> result = new ArrayList<>();
//        List<Car> carList = carRepository.findAll();
//        for (Car car : carList) {
//            if (car.getOfficeUid().length()>2){
//                String[] ids = car.getOfficeUid().split(" ");
//                logger.info("CarSearchImpl: ids {}", ids);
//                for (String id:ids){
//                    int i =  Integer.parseInt(id);
//                    if(i == officeUid){
//                        result.add(car);
//                        break;
//                    }
//                }
//            }else {
//                int i =  Integer.parseInt(car.getOfficeUid());
//                logger.info("CarSearchImpl: ids {}", i);
//                if(i == officeUid){
//                    result.add(car);
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public void findOneInfo(int officeUid, int carUid) {
//
//    }
//
//    @Override
//    public void findInfos(int carUid) {
//
//    }
//}
