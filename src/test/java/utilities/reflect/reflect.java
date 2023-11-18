package utilities.reflect;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class reflect
{
    public static void main(String[] args) throws Exception {
        List<Class<?>> classes=getClassesInPackage();
        ArrayList<String> output = new ArrayList<>();
        for (Class<?> c:classes){
            Method methods[] = c.getDeclaredMethods();
            for (Method method : methods){
                String testDesc;
                String allureDesc;
                boolean m_skip=false;
                if (method.isAnnotationPresent(org.testng.annotations.Test.class)){
                    testDesc=method.getAnnotation(org.testng.annotations.Test.class).description();
                    if (!method.getAnnotation(org.testng.annotations.Test.class).enabled()){
                        m_skip=true;
                    }
                    if (method.isAnnotationPresent(io.qameta.allure.Description.class)){
                        allureDesc=method.getAnnotation(io.qameta.allure.Description.class).value();
                    }
                    else{
                        allureDesc="";
                    }
                    output.add(c.getName()+"|"+method.getName()+"|"+testDesc+"|"+allureDesc+"|"+m_skip);
                }
            }
        }
        FileWriter writer = new FileWriter("c:/temp/output.csv");
        for(Object str: output) {
            System.out.println(str);
            writer.write(str + System.lineSeparator());
        }
        writer.close();

    }
    private static List<Class<?>> getClassesInPackage() throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        File myFile = new File("c:/temp/bone_classes1.txt");
        BufferedReader br = new BufferedReader(new FileReader(myFile));
        String st;
        while ((st = br.readLine()) != null)
            classes.add(Class.forName(st));
        return classes;
    }
}
