package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class repository {
    private int BookingId=0;
  public  HashMap<String, Hotel> HDB= new HashMap<>();
   public HashMap<Integer, User> UDB=new HashMap<>();

   public HashMap<String, Booking> BDB=new HashMap<>();

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }
}
