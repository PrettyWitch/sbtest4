//package com.yuhan.rent.service;
//
//import com.yuhan.rent.entity.AvailableCars;
//import com.yuhan.rent.entity.Office;
//import com.yuhan.rent.entity.OfficeCars;
//import com.yuhan.rent.repository.OfficeCarsRepository;
//import com.yuhan.rent.response.OfficeCarResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author yuhan
// * @date 01.06.2021 - 16:28
// * @purpose
// */
//@Service
//public class OfficeCarsServiceImpl implements OfficeCarsService {
//    @Autowired
//    OfficeCarsRepository officeCarsRepository;
//
//    @Autowired
//    AvailableCarsService availableCarsService;
//
//    @Autowired
//    OfficeService officeService;
//
//    private static final Logger logger = LoggerFactory.getLogger(OfficeCarsServiceImpl.class);
//
//
//    @Override
//    public List<OfficeCars> findByOfficeUid(int officeUid) {
//        return officeCarsRepository.findByOfficeUid(officeUid);
//    }
//
//    @Override
//    public List<OfficeCars> findByCarUid(int carUid) {
//        return officeCarsRepository.findByCarUid(carUid);
//    }
//
//    @Override
//    public Integer findByOfficeUidAndCarUid(int officeUid, int carUid) {
//        logger.info("Take car (officeUid: {}, carUid: {})", officeUid, carUid);
//        //报500异常
//        OfficeCars officeCars = officeCarsRepository.findByOfficeUidAndCarUid(officeUid,carUid)
//                .orElseThrow(()->new EntityNotFoundException(String.format("Car not found for officeUid %s and carUid %s",officeUid,carUid)));
//        return officeCars.getRegistrationNumber();
//    }
//
//    @Override
//    public void addCarToOffice(OfficeCars officeCars) {
//        officeCarsRepository.save(officeCars);
//    }
//
////    ’没有事务支持，不能执行更新和删除操作
////    就是在Service层或者Repository层上必须加@Transactional，来代表这是一个事务级别的操作，增删改查除了查都是事务级别的
//    @Transactional
//    @Override
//    public void deleteCarFromOffice(int officeUid, int carUid) {
//        officeCarsRepository.deleteCar(officeUid, carUid);
//        //异常处理
//    }
//
//    //3.获取有关办公室中所有汽车的信息。[G]GET /办公室/ {officeUid} /汽车
//    @Override
//    public List<OfficeCarResponse> findAllCars(int officeUid) {
//        List<OfficeCarResponse> officeCarResponses = new ArrayList<>();
//        List<OfficeCars> officeCars = officeCarsRepository.findByOfficeUid(officeUid);
////        List<AvailableCars> availableCars = new ArrayList<>();
//        for (OfficeCars car: officeCars) {
//            Office office = officeService.findById(car.getOfficeUid());
//            AvailableCars availableCar = availableCarsService.findByRegistrationNumber(car.getRegistrationNumber());
//            officeCarResponses.add(new OfficeCarResponse(office, availableCar, car.getAvailabilitySchedules()));
////            availableCars.add(availableCarsService.findByRegistrationNumber(cars.getRegistrationNumber()));
//        }
//        return officeCarResponses;
//    }
//
//    //5.获取有关汽车及其在所有办公室的可用性的信息。[G]GET /办公室/汽车/ {carUid}
//    @Override
//    public List<OfficeCarResponse> findAllOffice(int carUid) {
//        List<OfficeCarResponse> officeCarResponses = new ArrayList<>();
//        List<OfficeCars> officeCars = officeCarsRepository.findByCarUid(carUid);
//        for (OfficeCars car:officeCars){
//            Office office = officeService.findById(car.getOfficeUid());
//            AvailableCars availableCars = availableCarsService.findByRegistrationNumber(car.getRegistrationNumber());
//            officeCarResponses.add(new OfficeCarResponse(office, availableCars, car.getAvailabilitySchedules()));
//        }
//        return officeCarResponses;
//    }
//}
