package token;

/**
 * Created by Nguyen on 5/31/16.
 */
public class Token {

    public String token;
    public int price;

    public Token(){

    }

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){

        return token;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }



}
