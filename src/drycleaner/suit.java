
package drycleaner;

/**
 *
 * @author Idrees
 */
public class suit {

    Cloth jacket;
    Cloth pant;
    int suitNo;
     double arrival1, arrival2 , arrival3, arrival4, arrival5;
     double service1, service2 , service3, service4, service5;
        double delay1, delay2 , delay3, delay4, delay5;
          double departure1, departure2 , departure3, departure4, departure5;
    

    suit(int s) {
        this.suitNo = s;
        jacket = new Cloth("Jacket");
        pant = new Cloth("Pant");
        
    }
}
