package com.llc.design.filter;

/**
 * @author lilichuan
 */
//第一关
 class FirstPassHandler {
     int handler(){
        System.out.println("第一关-->FirstPassHandler");
        return 80;
    }
}

//第二关
 class SecondPassHandler {
     int handler(){
        System.out.println("第二关-->SecondPassHandler");
        return 90;
    }
}


//第三关
 class ThirdPassHandler {
     int handler(){
        System.out.println("第三关-->ThirdPassHandler，这是最后一关啦");
        return 95;
    }
}


//客户端
public class HandlerClient {
     public static void main(String[] args) {

        FirstPassHandler firstPassHandler = new FirstPassHandler();//第一关
        SecondPassHandler secondPassHandler = new SecondPassHandler();//第二关
        ThirdPassHandler thirdPassHandler = new ThirdPassHandler();//第三关

        int firstScore = firstPassHandler.handler();
        //第一关的分数大于等于80则进入第二关
        if(firstScore >= 80){
            int secondScore = secondPassHandler.handler();
            //第二关的分数大于等于90则进入第二关
            if(secondScore >= 90){
                thirdPassHandler.handler();
            }
        }
    }
}