/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

import java.util.ArrayList;

public class Server {

    String name;
    boolean isIdle;
    int queueLength;
    double avgQL,avgService,avgdelay;
    double lastEventTime,meanServiceTime;
    

    Server(String n) {
        name = n;
        isIdle = true;
        queueLength=0;
        avgQL=0;
        avgService=0;
        lastEventTime=0;
        avgdelay=0;
        
        
    }
}

