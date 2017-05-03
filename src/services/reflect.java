package services;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/5/1/001.
 */
public class reflect {
    public  String getreflect(Object object) {
        String string="";
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                 string=string+"\n"+field.getName() + ":" + field.get(object) ;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
}
