package com.yuhan.booking.service;

import com.yuhan.booking.entity.Booking;
import com.yuhan.booking.request.BookingRequest;
import com.yuhan.rent.entity.AvailableCars;

import java.util.List;

/**
 * @author yuhan
 * @date 09.05.2021 - 15:26
 * @purpose
 */
public interface BookingService {

    public List<Booking> findAll();

    public Booking findByBookingUid(int bookingUid);

    public Booking bookingCar(BookingRequest bookingRequest);

    public Booking save(Booking booking);

    public void deleteBooking(int bookingUid);
    public String dealTime(int bookingUid);

    public void finishBooking(int bookingUid);
}
