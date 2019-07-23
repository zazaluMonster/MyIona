package iona.test;

import iona.cache.IonaCache;
import iona.logger.IonaLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyIonaApplicationTests {
    @Autowired
    IonaCache ionaCache;

    @Test
    public void contextLoads() {

        String[] cpdomains = new String[]{
                "900 google.mail.com",
                "50 yahoo.com",
                "1 intel.mail.com"
        };

        HashMap<String,Integer> countMap = new HashMap<String,Integer>();
        List<String> resultList = new ArrayList<String>();

        //初始化countMap
        for(String cpdomain : cpdomains){

            String[] splitCpdomain = cpdomain.split(" ");

            String count = splitCpdomain[0];//5

            String domain = splitCpdomain[1];//wiki.org

            String[] splitDomain = domain.split("\\.");

            int l = splitDomain.length;//2

            String topDomain = splitDomain[l - 1];//org

            String secondDomain = splitDomain[l - 2] + "." + splitDomain[l - 1];//wiki.org

            String thirdDomain = null;
            if(l > 2){
                thirdDomain = domain;
            }

            countMap.put(topDomain,(countMap.get(topDomain) == null ? 0 : countMap.get(topDomain)) + Integer.valueOf(count));
            countMap.put(secondDomain,(countMap.get(secondDomain) == null ? 0 : countMap.get(secondDomain)) + Integer.valueOf(count));

            if(thirdDomain != null){
                countMap.put(thirdDomain,(countMap.get(thirdDomain) == null ? 0 : countMap.get(thirdDomain)) + Integer.valueOf(count));
            }

        }

        //根据countMap内容编写输出
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            resultList.add(entry.getValue() + " " + entry.getKey());
        }

        System.out.println(resultList);
    }

}
