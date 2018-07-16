package sample;
import java.net.URL;

public class ResourseLoader {
    public String getResourceFor(Class clazz,String path){
        URL resourseURL=clazz.getResource(path);
        if(resourseURL==null){
            throw new IllegalArgumentException("Not found any resources at "+path+ "relative to "+clazz.getName());
        }
        return resourseURL.toExternalForm();
    }
}
