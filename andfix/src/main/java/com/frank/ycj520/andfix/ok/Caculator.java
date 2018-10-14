package com.frank.ycj520.andfix.ok;


import com.frank.ycj520.andfix.Replace;

public class Caculator {

    @Replace(clazz = "com.frank.ycj520.andfix.Caculator",method = "caculate")
    public int caculate(){
        int i=10,j=100;
        return j/i;
    }
}
