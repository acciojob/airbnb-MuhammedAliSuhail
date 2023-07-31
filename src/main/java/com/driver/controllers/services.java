package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class services {

    private repository repo=new repository();
public String addHotel(Hotel h){
    for(String s:repo.HDB.keySet()){
        if(repo.HDB.get(s).getHotelName().equals(h.getHotelName())){
            return "FAILURE";
        }
    }

    repo.HDB.put(h.getHotelName(),h);
    return "SUCCESS";
}

public int addUser(User u){
   repo.UDB.put(u.getaadharCardNo(),u);
   return u.getaadharCardNo();
}

public String getHotelWithMostF(){
    ArrayList<String> st=new ArrayList<>();
int max=0;
String ans="";
    for(String s:repo.HDB.keySet()){
        if(repo.HDB.get(s).getFacilities().size()>max){
            max=repo.HDB.get(s).getFacilities().size();
//            ans=repo.HDB.get(s).getHotelName();
        }
    }
    if(max==0)return "";
    for(String s:repo.HDB.keySet()){
        if(repo.HDB.get(s).getFacilities().size()==max){
           st.add(s);
        }
    }
    Collections.sort(st);
for(int i=0;i<st.size()-1;i++){
    ans+=st.get(i)+" ";
}
ans+=st.get(st.size()-1);
return ans;
}

public int BookARoom(Booking b){
    String hotelname=b.getHotelName();
if(repo.HDB.get(hotelname).getAvailableRooms()<b.getNoOfRooms() )return -1;

    int prize=b.getNoOfRooms()*repo.HDB.get(hotelname).getPricePerNight();
    int availableRooms=repo.HDB.get(hotelname).getAvailableRooms()-b.getNoOfRooms();
    repo.HDB.get(hotelname).setAvailableRooms(availableRooms);
    int Bid=repo.getBookingId();
    repo.setBookingId(Bid+1);

    repo.BDB.put(String.valueOf(Bid),b);
    return prize;
}

public int getBookings(Integer ad){
    int count=0;
    for(String s:repo.BDB.keySet()){
        if(repo.BDB.get(s).getBookingAadharCard()==ad){
            count++;
        }
    }
    return count;
}

public Hotel UpdateFaciltys(List<Facility> f,String HotelName){
    List<Facility> old=repo.HDB.get(HotelName).getFacilities();

    for (int i = 0; i < f.size(); i++) {
        Facility temp = f.get(i);
        String t = temp.toString();
        boolean facilityExists = false;

        for (int j = 0; j < old.size(); j++) {
            String T = old.get(j).toString();
            if (t.equals(T)) {
                facilityExists = true;
                // Update the existing facility with new information (if applicable).
                old.set(j, temp);
                break;
            }
        }

        // If the facility doesn't exist, add it to the list.
        if (!facilityExists) {
            old.add(temp);
        }
    }
  repo.HDB.get(HotelName).setFacilities(old);

    return repo.HDB.get(HotelName);
}
}
