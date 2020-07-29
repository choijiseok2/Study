package com.inittest.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MillTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long mills = 1595453919368L; //long형 이므로 대문자 L을 붙여둬야함.
		  
		  String pattern = "yyyy-MM-dd (a) HH:mm:ss.SSS";
		  SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		  String date = (String)formatter.format(new Timestamp(mills));
		  System.out.println(date);
	}

}
