package com.yuhan.rent.web;

import com.yuhan.car.entity.Car;
import com.yuhan.rent.entity.AvailableCars;
import com.yuhan.rent.entity.Office;
//import com.yuhan.rent.entity.OfficeCars;
import com.yuhan.rent.exception.ErrorResponse;
import com.yuhan.rent.response.OfficeCarResponse;
import com.yuhan.rent.service.AvailableCarsService;
import com.yuhan.rent.service.CarService;
//import com.yuhan.rent.service.OfficeCarsService;
import com.yuhan.rent.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yuhan
 * @date 07.05.2021 - 12:24
 * @purpose
 */

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @Autowired
    CarService carService;

    @Autowired
    AvailableCarsService availableCarsService;

//    @Autowired
//    OfficeCarsService officeCarsService;

    private static final Logger logger = LoggerFactory.getLogger(OfficeController.class);

    @GetMapping("/findall")
    public List<Office> findAll() {
        return officeService.findAll();
    }

    @Operation(summary = "Find office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find office"),
            @ApiResponse(responseCode = "404", description = "Office not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{officeUid}")
    public Office findById(@PathVariable int officeUid) {
        return officeService.findById(officeUid);
    }

    @Operation(summary = "Create office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create office"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Office not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/create")
    public String CreateOffice(@Valid @RequestBody Office office) {
        return officeService.CreateOffice(office);
    }

    @Operation(summary = "Delete office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete office"),
            @ApiResponse(responseCode = "404", description = "Office not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{officeUid}")
    public void deleteOffice(@PathVariable int officeUid) {
        officeService.deleteOffice(officeUid);
    }


    @PutMapping("/update")
    public String update(@Valid @RequestBody Office office) {
        return officeService.update(office);
    }

    /***
     * ???????????????availableCar
     * @return ?????????availableCar
     */
    @Operation(summary = "Find availableCar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find availableCar"),
            @ApiResponse(responseCode = "404", description = "AvailableCar not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/availableCar")
    public List<AvailableCars> findAllAvailableCar() {
        return availableCarsService.findAllAvailableCars();
    }

    /**
     * ??????????????????
     * @param availableCars
     */
    @Operation(summary = "Update availableCar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update availableCar"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "AvailableCar not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/availableCar/update")
    public void updateAvailableCars(@Valid @RequestBody AvailableCars availableCars){
        int registrationNumber = availableCars.getRegistrationNumber();
        String availabilitySchedules = availableCars.getAvailabilitySchedules();
        availableCarsService.updateAvailableCar(registrationNumber, availabilitySchedules);
    }

    /**
     * ????????????????????????
     * <p>
     * //     * @param carUid    ????????????carUid
     *
     * @return ????????????
     */
    @Operation(summary = "Create availableCar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create availableCar"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "AvailableCar not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/availableCar/create")
    public String createAvailableCar(@RequestBody AvailableCars availableCars) {
//        AvailableCars availableCars = new AvailableCars();
        logger.info("create availableCar");
//        int officeUid = availableCars.getOfficeUid();
//        logger.info("officeUid:{}",officeUid);
        Car car = availableCars.getCar();
        int carUid = car.getCarUid();
        Car car2 = carService.findByCarUid(carUid);
        availableCars.setCar(car2);
        logger.info("carUid:{}", carUid);
        return availableCarsService.CreateAvailableCar(availableCars);
    }

    /**
     * 11.???????????????????????????[A] [M] [G]
     *
     * @param availableCar body: { registration_number, available }
     * @param officeUid  POST /offices/{officeUid}/car/{carUid}
     * @param carUid
     */
    @Operation(summary = "Add car to office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add car to office"),
            @ApiResponse(responseCode = "400", description = "Bad request format", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "AvailableCar not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "409", description = "Item not available", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/{officeUid}/car/{carUid}")
    public void addCarToOffice(@Valid @RequestBody AvailableCars availableCar, @PathVariable int officeUid, @PathVariable int carUid) {
        logger.info("availableCar:{}", availableCar);
        availableCar.setOfficeUid(officeUid);
        Car car = carService.findByCarUid(carUid);
        availableCar.setCar(car);
        logger.info("availableCar:{}", availableCar);
        availableCarsService.saveAvailableCar(availableCar);
//        AvailableCars availableCar = availableCarsService.findByRegistrationNumber(officeCars.getRegistrationNumber());
//        if (availableCar.getCar().getCarUid() == carUid) {
//            officeCars.setOfficeUid(officeUid);
//            officeCars.setCarUid(carUid);
//            officeCarsService.addCarToOffice(officeCars);
//            return "success";
//        } else {
//            return "CarUid and RegistrationNumber don't match";
//        }
    }

    /***
     * 12.???????????????????????????[A] [M] [G]
     * @param officeUid DELETE /offices/{officeUid}/car/{carUid}
     * @param carUid
     */
    @Operation(summary = "Delete car form office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete car form office"),
            @ApiResponse(responseCode = "404", description = "Car not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = {"/{officeUid}/car/{carUid}"}, method = RequestMethod.DELETE)
    public void deleteCarFromOffice(@PathVariable("officeUid") int officeUid, @PathVariable("carUid") int carUid){
        availableCarsService.deleteAvailableCar(officeUid, carUid);
//        officeCarsService.deleteCarFromOffice(officeUid, carUid);
    }

    /***
     * 3.????????????????????????????????????????????????[G]GET /?????????/ {officeUid} /??????
     * @param officeUid ????????????officeUid
     * @return ?????????????????????officeUid????????????availableCars??????????????????List<AvailableCars>
     */
    @Operation(summary = "Office cars info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Office cars info"),
            @ApiResponse(responseCode = "404", description = "Office not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{officeUid}/cars")
    public List<OfficeCarResponse> searchAllCars(@PathVariable int officeUid) {
        return availableCarsService.findByOfficeUid(officeUid);
//       return officeCarsService.findAllCars(officeUid);
    }

    /***
     * 4.?????????????????????????????????????????????????????????????????????[G]GET /?????????/ {officeUid} /??????/ {carUid}
     * ??????????????????carUid
     * @param officeUid ????????????officeUid
     * @param carUid ????????????carUid
     * @return ?????????????????????officeUid???carUid????????????availableCars??????????????????AvailableCars
     */
    @Operation(summary = "Office car info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Office car info"),
            @ApiResponse(responseCode = "404", description = "Office not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{officeUid}/cars/{carUid}")
    public List<OfficeCarResponse>  officeCarInfo(@PathVariable int officeUid, @PathVariable int carUid) {
        return availableCarsService.findByOfficeUidCarUid(officeUid,carUid);
//        int number = officeCarsService.findByOfficeUidAndCarUid(officeUid, carUid);
//        return availableCarsService.findByRegistrationNumber(number);
    }

    /***
     * 5.??????????????????????????????????????????????????????????????????[G]GET /?????????/??????/ {carUid}
     * ??????AvailableCarRepository???findByCar
     * @param carUid ????????????carUid
     * @return
     */
    @Operation(summary = "Cars info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars info"),
            @ApiResponse(responseCode = "404", description = "Cars not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "External request failed", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/cars/{carUid}")
    public List<OfficeCarResponse>  AvailableCarsInfo(@PathVariable int carUid) {
        return availableCarsService.findByCarUid(carUid);

//        return officeCarsService.findAllOffice(carUid);
//        logger.info("/car/{carUid}");
//        return availableCarsService.findByCarUid(carUid);
    }

}
