/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

/**
 *
 * @author já
 */
public class Customer {

    private String name;
    private Wallet wallet;

    public Customer(float amount){
    wallet= new Wallet(amount);

    }
    private Wallet getWallet() {
        return wallet;
    }

    public float getPayment(float amount) {
        if (getWallet().getMoney() >= amount && getWallet() != null) {
            wallet.subtractMoney(amount);
            return amount;
        } else {
            return 0;
        }
    }
}
