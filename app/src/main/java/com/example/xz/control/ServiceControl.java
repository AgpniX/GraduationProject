package com.example.xz.control;


import android.content.Context;
import android.content.Intent;

import com.example.xz.service.AutoService;
import com.example.xz.service.CusArroundEnv;
import com.example.xz.service.FitService;
/**
 * Created by xz on 2016/4/27.
 * 服务控制
 */
public class ServiceControl {
    private static AutoService auto = new AutoService();
    private static CusArroundEnv arroundEnv = new CusArroundEnv();
    private static FitService fitSevice = new FitService();
    /**
     * 开启服务
     * @param which
     * @return 成功success 失败fail
     */
    public  static String StartService(Context context ,int which){
        switch (which){
            case 0:
            {
                context.startService(new Intent(context, arroundEnv.getClass()));
                return "success";
            }

            case 1:{
                context.startService(new Intent(context,fitSevice.getClass()));
                return "success";
            }

            case 2:{
                context.startService(new Intent(context,auto.getClass()));
                return "success";
            }
            default:return "fail";
        }
    }

    /**
     *
     * 停止服务
     * @param which
     * @return 成功success 失败fail
     */
    public  static String StopService(Context context, int which){
        switch (which){
            case 0:
            {
                context.stopService(new Intent(context, arroundEnv.getClass()));
                return "success";
            }

            case 1:{
                context.stopService(new Intent(context, fitSevice.getClass()));
                return "success";
            }

            case 2:{
                context.stopService(new Intent(context, auto.getClass()));
                return "success";
            }
            default:return "fail";
        }
    }
}
