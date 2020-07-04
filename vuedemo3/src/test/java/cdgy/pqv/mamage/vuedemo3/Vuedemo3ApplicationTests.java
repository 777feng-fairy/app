package cdgy.pqv.mamage.vuedemo3;

import cdgy.pqv.mamage.vuedemo3.services.ShopService;
import cdgy.pqv.mamage.vuedemo3.services.UserOrderService;
import cdgy.pqv.mamage.vuedemo3.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static com.alibaba.druid.filter.config.ConfigTools.encrypt;
import static com.alibaba.druid.filter.config.ConfigTools.genKeyPair;

@SpringBootTest
class Vuedemo3ApplicationTests {


    @Test
    void contextLoads() {

        String password = "feng^7";
        String[] arr = new String[0];
        try {
            arr = genKeyPair(512);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        try {
            System.out.println("password:" + encrypt(arr[0], password));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Autowired
    private UserService service;

    @Autowired
    ShopService shopService;

    @Autowired
    UserOrderService orderService;

    @Test
    void setTestConection(){




    }



}
