package sin.backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

//특정 서비스의 메서드마다 얼마나 시간이걸리는지 알고싶다
@Component// 기능덩어리로 Spring에 등록한다(자동으로 스프링빈 등록
@Aspect// @Aspect 써줘야 AOP를 사용할 수 있다
public class TimeTraceAop {
    //@Around("execution(* sin.backend..*(..))")// 모든 메소드에 대해서 건다
    @Around("execution(* sin.backend.service..*(..))")// 서비스 메소드에 건다, 특정한 메소드에 걸고싶으면 *대신에 이름을 적어준다, (..)은 파라미터
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();// System클래스의 정적 메소드이다
        System.out.println("START: "+ start);
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("##걸린시간: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
