package com.xq.Railway.service.impl;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xq.Railway.dao.OperateLogMapper;
import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.Syslog;
import com.xq.Railway.util.StringUtil;

/**
 * 日志切面实现
 */
@Service
@Component
@Aspect
public class LogService {

    @Autowired
    private OperateLogMapper dao;

    public LogService() {
        System.out.println("Aop");
    }

    /**
     * 切点
     */
    @Pointcut("@annotation(com.xq.Railway.logAop.MethodLog)")
    public void methodCachePointcut() { }


    
    
    
    
    
    /**
     * 切面
     *
     *
     *public interface JoinPoint {  
    String toString();         //连接点所在位置的相关信息  
    String toShortString();     //连接点所在位置的简短相关信息  
    String toLongString();     //连接点所在位置的全部相关信息  
    Object getThis();         //返回AOP代理对象  
    Object getTarget();       //返回目标对象  
    Object[] getArgs();       //返回被通知方法参数列表  
    Signature getSignature();  //返回当前连接点签名  
    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置  
    String getKind();        //连接点类型  
    StaticPart getStaticPart(); //返回连接点静态部分  
} 
     *
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        Calendar ca = Calendar.getInstance();
        String operDate = df.format(ca.getTime());
        String ip = getIpAddr(request);
//        String staffId = (String) request.getSession().getAttribute("staffId");
//        String username = (String) request.getSession().getAttribute("login");
//        System.out.println("1=="+staffId);
        String username="";
        String staffId="";
        
        
        String methodRemark = getMthodRemark(point);
        String methodName = point.getSignature().getName();
        String packages = point.getThis().getClass().getName();
        if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
            try {
                packages = packages.substring(0, packages.indexOf("$$"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        String operatingcontent = "";
        Object[] method_param = null;

        Object object;
        try {
            method_param = point.getArgs(); //获取方法参数
            // String param=(String) point.proceed(point.getArgs());
            object = point.proceed();
        } catch (Exception e) {
            // 异常处理记录日志..log.error(e);
            throw e;
        }
        
        Syslog sysLog = new Syslog();
        sysLog.setOptId(StringUtil.getRandNum(11)+Math.random()*1000);
        sysLog.setLoginId(staffId);
        sysLog.setLoginName(username);
        sysLog.setIpAddress(ip);
        sysLog.setMethodName(packages + "." + methodName);
        sysLog.setMethodRemark(methodRemark);
        sysLog.setOptDate(operDate);

        /**
         		* 登陆
         */
        if("新增管理员".equals(methodRemark)){
            sysLog.setOperatingcontent("账户登陆: 用户名为 " + method_param[0]);
        }
        
        /**
         * 空参数
         */
        if("".equals(methodRemark) || null == methodRemark){
            sysLog.setOperatingcontent("操作参数: " + method_param[0]);
        }

//        dao.saveSysLog(sysLog);
      //  System.out.println("日志实体："+sysLog.toString());
        return object;

    }

    /**
     * 方法异常时调用
     *
     * @param ex
     */
    public void afterThrowing(Exception ex) {
        System.out.println("afterThrowing");
        System.out.println(ex);
    }

    /**
     * 获取方法中的中文备注
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getMthodRemark(ProceedingJoinPoint joinPoint) throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    MethodLog methodCache = m.getAnnotation(MethodLog.class);
                    if (methodCache != null) {
                        methode = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methode;
    }

    /**
     * 获取请求ip
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip.substring(0, index);
            } else {
                return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        
     
        
        
        return request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}