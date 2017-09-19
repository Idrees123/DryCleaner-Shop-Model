/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Idrees
 */
public class Simulation {

    static ReportClass reportObj = new ReportClass();
    double serviceTime;
    Problem p;
    double simulationClock = 0;
    ArrayList<Event> EventList = new ArrayList<>();

    double avgDamagesuittime = 0, avgsuittime = 0, avgundamagesuittime = 0, maxsuittime = 0;
    Server[] servers = new Server[5];
    double lastarrivalonserver[] = new double[5];

    ArrayList<suit> customers = new ArrayList<>();
    int max = 0;
    int damagedJ = 0;
    int damagedP = 0;

    public Simulation(Problem p) {

        for (int i = 0; i < 5; i++) {
            servers[i] = new Server("Server" + "" + (i + 1));
        }
        this.p = p;

    }

    Stage stage;

    void Simulator() {
        runSimulation();
      

        //************************load gif image
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Animation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Animation View");
            stage.show();
        } catch (Exception e) {
        }

        customers.stream().map((cust) -> {
            cust.arrival1 = truncDouble(cust.arrival1);
            return cust;
        }).map((cust) -> {
            cust.arrival2 = truncDouble(cust.arrival2);
            return cust;
        }).map((cust) -> {
            cust.arrival3 = truncDouble(cust.arrival3);
            return cust;
        }).map((cust) -> {
            cust.arrival4 = truncDouble(cust.arrival4);
            return cust;
        }).map((cust) -> {
            if (cust.arrival5 != -1) {
                cust.arrival5 = truncDouble(cust.arrival5);
            }
            return cust;
        }).map((cust) -> {
            cust.delay1 = truncDouble(cust.delay1);
            return cust;
        }).map((cust) -> {
            cust.delay2 = truncDouble(cust.delay2);
            return cust;
        }).map((cust) -> {
            cust.delay3 = truncDouble(cust.delay3);
            return cust;
        }).map((cust) -> {
            cust.delay4 = truncDouble(cust.delay4);
            return cust;
        }).map((cust) -> {
            cust.delay5 = truncDouble(cust.delay5);
            return cust;
        }).map((cust) -> {
            cust.departure1 = truncDouble(cust.departure1);
            return cust;
        }).map((cust) -> {
            cust.departure2 = truncDouble(cust.departure2);
            return cust;
        }).map((cust) -> {
            cust.departure3 = truncDouble(cust.departure3);
            return cust;
        }).map((cust) -> {
            cust.departure4 = truncDouble(cust.departure4);
            return cust;
        }).map((cust) -> {
            cust.departure5 = truncDouble(cust.departure5);
            return cust;
        }).map((cust) -> {
            cust.service1 = truncDouble(cust.service1);
            return cust;
        }).map((cust) -> {
            cust.service2 = truncDouble(cust.service2);
            return cust;
        }).map((cust) -> {
            cust.service3 = truncDouble(cust.service3);
            return cust;
        }).map((cust) -> {
            cust.service4 = truncDouble(cust.service4);
            return cust;
        }).forEach((cust) -> {
            cust.service5 = truncDouble(cust.service5);
        });

        //************************load gif image
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        new SuiiDetails(customers).setVisible(true);

                    }
                },
                27000
        );

        //close the animation
        PauseTransition delay = new PauseTransition(Duration.seconds(30));
        delay.setOnFinished(event -> stage.close());
        //

       
        delay.play();


    }
     void runSimulation() {
        int count = 1;
        while (true) {
            suit t = new suit(count);
            if (count == 1) {
                t.arrival1 = getExpTime(p.systemInterArrivalTime);
                lastarrivalonserver[0] = t.arrival1;
                t.delay1 = 0;
                t.service1 = getExpTime(p.serviceTime1);
                t.departure1 = t.arrival1 + t.service1;

                t.arrival2 = t.departure1;
                lastarrivalonserver[1] = t.arrival2;
                t.delay2 = 0;
                t.service2 = getExpTime(p.serviceTime2);
                t.departure2 = t.arrival2 + t.service2;
                t.jacket.isDamage = getDamageRV(p.probabilityJ);

                t.arrival3 = t.departure2;
                lastarrivalonserver[2] = t.arrival3;
                t.delay3 = 0;
                t.service3 = getExpTime(p.serviceTime3);
                t.departure3 = t.arrival3 + t.service3;
                t.pant.isDamage = getDamageRV(p.probabilityP);

                t.arrival4 = t.departure3;
                lastarrivalonserver[3] = t.arrival4;
                t.delay4 = 0;
                if (t.pant.isDamage || t.jacket.isDamage) {
                    t.service4 = getExpTime(p.serviceTime4d);
                } else {
                    t.service4 = getExpTime(p.serviceTime4u);
                }
                t.departure4 = t.arrival4 + t.service4;

                if (t.pant.isDamage || t.jacket.isDamage) {

                    t.arrival5 = t.departure4;
                    lastarrivalonserver[4] = t.arrival5;
                    t.delay5 = 0;
                    t.service5 = getExpTime(p.serviceTime5);
                    t.departure5 = t.arrival5 + t.service5;

                } else {
                    t.arrival5 = -1;
                    t.delay5 = 0;
                    t.service5 = 0;
                    t.departure5 = 0;
                }

            } else {
                t.arrival1 = customers.get(customers.size() - 1).arrival1 + getExpTime(p.systemInterArrivalTime);
                lastarrivalonserver[0] = t.arrival1;
                t.delay1 = customers.get(customers.size() - 1).departure1 - t.arrival1;
                if (t.delay1 < 0) {
                    t.delay1 = 0;
                }
                t.service1 = getExpTime(p.serviceTime1);
                t.departure1 = t.arrival1 + t.service1 + t.delay1;

                t.arrival2 = t.departure1;
                lastarrivalonserver[1] = t.arrival2;
                t.delay2 = customers.get(customers.size() - 1).departure2 - t.arrival2;
                if (t.delay2 < 0) {
                    t.delay2 = 0;
                }
                t.service2 = getExpTime(p.serviceTime2);
                t.departure2 = t.arrival2 + t.service2 + t.delay2;
                t.jacket.isDamage = getDamageRV(p.probabilityJ);

                t.arrival3 = t.departure2;
                lastarrivalonserver[2] = t.arrival3;
                t.delay3 = customers.get(customers.size() - 1).departure3 - t.arrival3;
                if (t.delay3 < 0) {
                    t.delay3 = 0;
                }
                t.service3 = getExpTime(p.serviceTime3);
                t.departure3 = t.arrival3 + t.service3 + t.delay3;
                t.pant.isDamage = getDamageRV(p.probabilityP);

                t.arrival4 = t.departure3;
                lastarrivalonserver[3] = t.arrival4;
                t.delay4 = customers.get(customers.size() - 1).departure4 - t.arrival4;
                if (t.delay4 < 0) {
                    t.delay4 = 0;
                }
                if (t.pant.isDamage || t.jacket.isDamage) {
                    t.service4 = getExpTime(p.serviceTime4d);
                } else {
                    t.service4 = getExpTime(p.serviceTime4u);
                }
                t.departure4 = t.arrival4 + t.service4 + t.delay4;

                if (t.pant.isDamage || t.jacket.isDamage) {

                    t.arrival5 = t.departure4;
                    lastarrivalonserver[4] = t.arrival5;
                    t.delay5 = customers.get(customers.size() - 1).departure5 - t.arrival5;
                    if (t.delay5 < 0) {
                        t.delay5 = 0;
                    }
                    t.service5 = getExpTime(p.serviceTime5);
                    t.departure5 = t.arrival5 + t.service5 + t.delay5;

                } else {
                    t.arrival5 = -1;
                    t.delay5 = 0;
                    t.service5 = 0;
                    t.departure5 = 0;
                }

            }
            if (t.departure4 > p.simulationTime && (!t.jacket.isDamage || !t.pant.isDamage)) {
                break;
            } else if (t.departure5 > p.simulationTime && (t.jacket.isDamage || t.pant.isDamage)) {
                break;
            }
            customers.add(t);
            Event e = new Event();
            e.server = 1;
            e.time = t.arrival1;
            e.type = 1;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 1;
            e.time = t.departure1;
            e.type = 2;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 2;
            e.time = t.arrival2;
            e.type = 1;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 2;
            e.time = t.departure2;
            e.type = 2;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 3;
            e.time = t.arrival3;
            e.type = 1;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 3;
            e.time = t.departure3;
            e.type = 2;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 4;
            e.time = t.arrival4;
            e.type = 1;
            EventList.add(e);
            run();

            e = new Event();
            e.server = 4;
            e.time = t.departure4;
            e.type = 2;
            EventList.add(e);
            run();

            if (t.arrival5 != -1) {
                e = new Event();
                e.server = 5;
                e.time = t.arrival5;
                e.type = 1;
                EventList.add(e);
                run();

                e = new Event();
                e.server = 5;
                e.time = t.departure5;
                e.type = 2;
                EventList.add(e);
                run();

            }

            if (t.pant.isDamage) {
                damagedP++;
            }
            if (t.jacket.isDamage) {
                damagedJ++;
            }
            if (t.jacket.isDamage || t.pant.isDamage) {
                damagecount++;
            }

            totalcount++;
            count++;

        }
    }

    void run() {

        int queueL = servers[EventList.get(EventList.size() - 1).server - 1].queueLength;
        simulationClock = EventList.get(EventList.size() - 1).time;
        if (EventList.get(EventList.size() - 1).type == 2 && servers[EventList.get(EventList.size() - 1).server - 1].queueLength == 0) {
            servers[EventList.get(EventList.size() - 1).server - 1].isIdle = true;

        }
        if (EventList.get(EventList.size() - 1).type == 1 && !servers[EventList.get(EventList.size() - 1).server - 1].isIdle) {
            servers[EventList.get(EventList.size() - 1).server - 1].queueLength++;

        } else if (EventList.get(EventList.size() - 1).type == 1 && servers[EventList.get(EventList.size() - 1).server - 1].isIdle) {
            servers[EventList.get(EventList.size() - 1).server - 1].isIdle = false;

        } else if (EventList.get(EventList.size() - 1).type == 2 && servers[EventList.get(EventList.size() - 1).server - 1].queueLength > 0) {
            servers[EventList.get(EventList.size() - 1).server - 1].queueLength--;

        }

        servers[EventList.get(EventList.size() - 1).server - 1].avgQL += (EventList.get(EventList.size() - 1).time - servers[EventList.get(EventList.size() - 1).server - 1].lastEventTime) * queueL;

        servers[EventList.get(EventList.size() - 1).server - 1].lastEventTime = EventList.get(EventList.size() - 1).time;

    }

    boolean getDamageRV(double prob) {
        if (Math.random() <= prob) {
            return true;
        }
        return false;

    }
    int damagecount = 0, totalcount = 0;

    void getreport() {
        customers.stream().map((st) -> {
            //

            double time;
            if (st.arrival5 == -1) {
                time = st.departure4 - st.arrival1;
                avgundamagesuittime += time;
            } else {
                time = st.departure5 - st.arrival1;
                avgDamagesuittime += time;
            }
            avgsuittime += time;
            if (time > maxsuittime) {
                maxsuittime = time;
            }
            servers[0].avgService += st.service1;
            return st;
        }).map((st) -> {
            servers[1].avgService += st.service2;
            return st;
        }).map((st) -> {
            servers[2].avgService += st.service3;
            return st;
        }).map((st) -> {
            servers[3].avgService += st.service4;
            return st;
        }).map((st) -> {
            servers[4].avgService += st.service5;
            return st;
        }).map((st) -> {
            servers[0].avgdelay += st.delay1;
            return st;
        }).map((st) -> {
            servers[1].avgdelay += st.delay2;
            return st;
        }).map((st) -> {
            servers[2].avgdelay += st.delay3;
            return st;
        }).map((st) -> {
            servers[3].avgdelay += st.delay4;
            return st;
        }).forEach((st) -> {
            servers[4].avgdelay += st.delay5;
        });
        avgsuittime = avgsuittime / totalcount;
        if (damagecount == 0) {
            avgDamagesuittime = 0;
        } else {
            avgDamagesuittime = avgDamagesuittime / damagecount;
        }
        avgundamagesuittime = avgundamagesuittime / (totalcount - damagecount);
        for (int i = 0; i < 5; i++) {
            servers[i].avgService = servers[i].avgService / p.simulationTime;
        }
       sort(EventList);
        EventList.stream().map((EventList1) -> {
            int queueL = servers[EventList1.server - 1].queueLength;
            // simulationClock = EventList.get(i).time;
            if (EventList1.type == 2 && servers[EventList1.server - 1].queueLength == 0) {
                servers[EventList1.server - 1].isIdle = true;
            }
            if (EventList1.type == 1 && !servers[EventList1.server - 1].isIdle) {
                servers[EventList1.server - 1].queueLength++;
            } else if (EventList1.type == 1 && servers[EventList1.server - 1].isIdle) {
                servers[EventList1.server - 1].isIdle = false;
            } else if (EventList1.type == 2 && servers[EventList1.server - 1].queueLength > 0) {
                servers[EventList1.server - 1].queueLength--;
            }
            servers[EventList1.server - 1].avgQL += (EventList1.time - servers[EventList1.server - 1].lastEventTime) * queueL;
            return EventList1;
        }).forEach((EventList1) -> {
            servers[EventList1.server - 1].lastEventTime = EventList1.time;
        });
        for (int i = 0; i < 5; i++) {
            if (lastarrivalonserver[i] != 0) {
                servers[i].avgQL = servers[i].avgQL / lastarrivalonserver[i];
            }

        }

        servers[0].avgdelay = servers[0].avgdelay / totalcount;
        servers[1].avgdelay = servers[1].avgdelay / totalcount;
        servers[2].avgdelay = servers[2].avgdelay / totalcount;
        servers[3].avgdelay = servers[3].avgdelay / totalcount;
        servers[4].avgdelay = servers[4].avgdelay / damagecount;

        double[] st = new double[5];
        st[0] = servers[0].avgQL;
        st[1] = servers[1].avgQL;
        st[2] = servers[2].avgQL;
        st[3] = servers[3].avgQL;
        st[4] = servers[4].avgQL;
        int max = 0;
        double maxs = 0;
        for (int i = 0; i < st.length; i++) {
            if (maxs < st[i]) {
                maxs = st[i];
                max = i + 1;
            }
        }
        

        reportObj.avgUndamageTime = avgundamagesuittime;
        reportObj.avgSuitTime = avgsuittime;
        reportObj.damageSuit = damagecount;
        reportObj.maxSuitTime = maxsuittime;
        reportObj.s1AvgQL = servers[0].avgQL;
        reportObj.s2AvgQL = servers[1].avgQL;
        reportObj.s3AvgQL = servers[2].avgQL;
        reportObj.s4AvgQL = servers[3].avgQL;
        reportObj.s5AvgQL = servers[4].avgQL;
        reportObj.damagedJacket = damagedJ;
        reportObj.damagedPant = damagedP;
        reportObj.totalSuit = totalcount;
        reportObj.avgDamageSuitTime = avgDamagesuittime;
        reportObj.avgUndamageTime = avgundamagesuittime;
        reportObj.suggestion = max;

        reportObj.s1Buzziness = servers[0].avgService;
        reportObj.s2Buzziness = servers[1].avgService;
        reportObj.s3Buzziness = servers[2].avgService;
        reportObj.s4Buzziness = servers[3].avgService;
        reportObj.s5Buzziness = servers[4].avgService;

    }

    Stage stage1;

   

    double truncDouble(Double d) {
        if (d == 0) {
            return 0;
        }
        String str = Double.toString(d);
        String temp = "";
        boolean isdouble = false;
        for (int a = 0; a < str.length(); a++) {
            temp += str.charAt(a);
            if (str.charAt(a) == '.') {
                isdouble = true;
                if (str.length() > a + 3) {
                    temp += str.charAt(a + 1) + "" + str.charAt(a + 2);
                } else {
                    while (a < str.length()) {
                        temp += str.charAt(a);
                        a++;
                    }
                }
                break;
            }

        }

        if (isdouble) {
            return Double.valueOf(temp);
        }

        return d;
    }

    void sort(ArrayList<Event> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).time < list.get(j).time) {
                    Event temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

    }

   

    double getExpTime(double mean) {
        return mean * Math.log(Math.random()) * -1;
    }

}
