package com.yuhan.rent.service;

import com.yuhan.rent.entity.AvailableCars;
import com.yuhan.rent.entity.OfficeCars;
import com.yuhan.rent.response.OfficeCarResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author yuhan
 * @date 01.06.2021 - 16:25
 * @purpose
 */
public interface OfficeCarsService {
    public List<OfficeCars> findByOfficeUid(int officeUid);

    public List<OfficeCars> findByCarUid(int carUid);

    public Integer findByOfficeUidAndCarUid(int officeUid, int carUid);

    public void addCarToOffice(OfficeCars officeCars);

    public void deleteCarFromOffice(int officeUid, int carUid);

    public List<AvailableCars> findAllCars(int officeUid);

    public List<OfficeCarResponse> findAllOffice(int carUid);
}
