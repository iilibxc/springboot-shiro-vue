package code.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"fullName", "comment"})
public class JsonZhuJieTest {
    private String id;
    private String name;
    private String fullName;
    private String comment;
    private String mail;

    @JsonIgnore
    private String address;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;

    private Date reg2Date;

    public static void main(String[] args) {
        JsonZhuJieTest jsonZhuJieTest = new JsonZhuJieTest();
        jsonZhuJieTest.setId("t-000001");
        jsonZhuJieTest.setName("Name-1");
        jsonZhuJieTest.setFullName("Name-1-李");
        jsonZhuJieTest.setComment("C=测试");
        jsonZhuJieTest.setMail("Name-1@test.com");
        jsonZhuJieTest.setAddress("address");
        jsonZhuJieTest.setRegDate(new Date());
        jsonZhuJieTest.setReg2Date(new Date());
        System.out.println(jsonZhuJieTest);
    }
}
