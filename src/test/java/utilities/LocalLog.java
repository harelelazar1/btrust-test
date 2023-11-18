package utilities;

public class LocalLog {


    public static String logInfo(String log) {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        StackTraceElement stack1 = stacks[1];
        log = "LOG POINT@"
                + "filename:" + stack1.getFileName()
                + ",classname:" + stack1.getClassName()
                + "methodname:" + stack1.getMethodName()
                + ", lineno:" + stack1.getLineNumber();

        System.out.println("LOG POINT@"
                + "filename:" + stack1.getFileName()
                + ",classname:" + stack1.getClassName()
                + "methodname:" + stack1.getMethodName()
                + ",lineno:" + stack1.getLineNumber());
        System.out.println("LOG INFO:" + log);

        return log;
    }

//        public static void main(String[] args){
//            LocalLog.log("this is a test!");
//        }


}
