package code.encryptor;

import code.MyApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class EncryptorUtil {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void stringEncryptorTest() {
        System.out.println("*****************************************");
        System.out.println(stringEncryptor.encrypt("liuweijw"));
        System.out.println(stringEncryptor.encrypt("root"));
        System.out.println(stringEncryptor.encrypt("root"));
        System.out.println("eZLNwFxeSKENBh1pu/M/rMMB76nxo/RLhWSaSLHa8+0=:"+stringEncryptor.decrypt("eZLNwFxeSKENBh1pu/M/rMMB76nxo/RLhWSaSLHa8+0="));
        System.out.println("sKbpJGHz9JZmdydv1WOyAYjyXm2irxc0xYqvQ6VpF31uEw/FVNxYeA=="+stringEncryptor.decrypt("sKbpJGHz9JZmdydv1WOyAYjyXm2irxc0xYqvQ6VpF31uEw/FVNxYeA=="));
        System.out.println(stringEncryptor.decrypt("rC/X/8UBBH2bn9Tgfuu7aw=="));
        System.out.println(stringEncryptor.decrypt("IVTzs5LDfVEsblAFWFgA9w=="));
    }
}
