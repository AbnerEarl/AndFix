package com.frank.ycj520.andfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class DxManager {
    private Context context;

    public DxManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePath) {
        File optFile = new File(context.getCacheDir(), dexFilePath.getName());
        if (optFile.exists()) {
            optFile.delete();
        }
        try {
            DexFile dexFile = DexFile.loadDex(dexFilePath.getAbsolutePath(), optFile.getAbsolutePath(), Context.MODE_PRIVATE);
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()) {
                String className = entry.nextElement();
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());
                Log.d("DexManager:", "找到类  " + className);
                fix(realClazz);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fix(Class realClazz) {
        Method[] methods = realClazz.getDeclaredMethods();
        for (Method method : methods) {
            Replace replace = method.getAnnotation(Replace.class);
            if (realClazz == null) {
                continue;
            }
            try {
                String wrongClazz = replace.clazz();
                String wrongMethod = replace.method();
                Class wrongClass = Class.forName(wrongClazz);
                Method wrongMethodFinal = wrongClass.getMethod(wrongMethod, method.getParameterTypes());

                replaceMethod(wrongMethodFinal, method);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

    private native void replaceMethod(Method wrongMethod, Method rightMethod);
}