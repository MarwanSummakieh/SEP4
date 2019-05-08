package com.dataConnection.Sep4.SQL.Controllers;

import com.dataConnection.Sep4.SQL.dao.Co2Repository;
import com.dataConnection.Sep4.SQL.model.Co2;
import com.dataConnection.Sep4.SQL.model.Device;
import com.dataConnection.Sep4.SQL.model.Room;
import com.dataConnection.Sep4.mongo.EUIMongoRepository;
import com.dataConnection.Sep4.mongo.MongoModel.EUIMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class CO2Conttoller {

    @Autowired
    Co2Repository co2;

    @Autowired
    EUIMongoRepository er;

    private List<EUIMongo> EUI;

    Room room;
    Device device;

    public void loadCo2() {
        co2.deleteAll();
        EUI = er.findAll();

        for(int i =0; i< EUI.size(); i++)
        {
            co2.save(new Co2("unknown",EUI.get(i).getTimestamp(),EUI.get(i).getCo2(),room = new Room("Unknown", device=new Device(EUI.get(i).getName(), EUI.get(i).getUie()))));
        }

        co2.findAll().forEach(System.out::println);
        System.out.println("_______________________________");
    }

    @Scheduled(fixedRate = 5000)
    public void updateCO2()
    {
        EUI = er.findAll();

        int value = EUI.size()-co2.findAll().size();


        for(int i =EUI.size()-value; i<EUI.size(); i++)
        {
            co2.save(new Co2("unknown",EUI.get(i).getTimestamp(),EUI.get(i).getCo2(),room = new Room("Unknown", device=new Device(EUI.get(i).getName(), EUI.get(i).getUie()))));
        }
    }
}
