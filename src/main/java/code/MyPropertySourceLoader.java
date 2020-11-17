package code;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class MyPropertySourceLoader implements PropertySourceLoader {

    /**
     * 文件名称只能限制为application
     * 文件后缀无限制，
     * 且文件只能放在classpath下
     * 扩展名不含.
     * @return
     */
    @Override
    public String[] getFileExtensions() {
        return new String[]{"abc"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        System.out.println("PropertySourceLoader load。。。");
        String splitChar = "=";
        InputStream is = resource.getInputStream();
        List<String> lines = IOUtils.readLines(is);
        Properties prop = new Properties();
        lines.stream().forEach(line -> {
            String[] kv = line.split(splitChar);
            prop.setProperty(kv[0], kv[1]);
        });
        //环境名称随意取，但尽量不能和其他环境名称相同，避免不生效
        PropertySource propertySource = new PropertiesPropertySource("propertySourceLoader", prop);
        return Arrays.asList(propertySource);
    }
}
