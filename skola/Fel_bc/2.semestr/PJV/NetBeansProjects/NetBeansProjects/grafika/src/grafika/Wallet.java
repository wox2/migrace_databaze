/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

/**
 *
 * @author já
 */
public class Wallet {

    private float value;

    public Wallet() {
        value = 500;
    }

    public Wallet(float amount){
        value=amount;
    }
    
    public void addMoney(float amount) {
        value += amount;
    }

    public void subtractMoney(float amount) {
        value -= amount;
    }

    public float getMoney() {
        return value;
    }
}
