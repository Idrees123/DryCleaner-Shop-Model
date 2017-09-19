/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

/**
 *
 * @author Idrees
 */
public class serviceTable {
    int suitNo;
    double arrival,service,delay,departure,total;

    public serviceTable(int suitNo, double arrival, double service, double delay, double departure, double total) {
        this.suitNo = suitNo;
        this.arrival = arrival;
        this.service = service;
        this.delay = delay;
        this.departure = departure;
        this.total = total;
    }

    public int getSuitNo() {
        return suitNo;
    }

    public double getArrival() {
        return arrival;
    }

    public double getService() {
        return service;
    }

    public double getDelay() {
        return delay;
    }

    public double getDeparture() {
        return departure;
    }

    public double getTotal() {
        return total;
    }
    
    
}
