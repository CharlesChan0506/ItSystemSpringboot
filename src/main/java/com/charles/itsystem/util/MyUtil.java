package com.charles.itsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

    //获取文件大小
    public static String getDocumentSize(Long size){
        String fileSize;
        if(size < 1024){
            return fileSize = size + "B";
        } else if ((size/1024) < 1024){
            return fileSize = size/1024 + "KB";
        } else {
            //单位为MB要保留一位小数
            Long newSize = size * 100;
            return fileSize = newSize/100 + "." + newSize%100 + "MB";
        }
    }
    //处理时间格式
    public static String transfoDate(Date date){
        String dateString;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateString = format.format(date);
        return dateString;
    }
}
