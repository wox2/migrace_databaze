/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

/**
 *
 * @author já
 */
public class Salesman {

    private float payment = 30;

    public void saleLSD(Customer customer) {
        float amount = 30;
        if (customer.getPayment(payment) >= payment) {
            System.out.println("LSD bylo prodano zakazniku ");
        } else {
            System.out.println("nic nedostanes");
        }
    }
}
