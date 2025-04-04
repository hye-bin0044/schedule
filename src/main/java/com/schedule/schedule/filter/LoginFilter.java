package com.schedule.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    // 상수 선언
    private static final String[]  WHITE_LIST = {"/", "/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // request 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // response 다운캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        // WHITE_LIST에 포함되지 않은 URI의 경우 해당 로직 수행
        if(!isWhiteList(requestURI)) {

            // 세션이 없는 경우, 새로운 세션 만들지 않게 해줌
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인 실패!");
            }

            // 로그인 성공
            log.info("로그인 성공!");

        }

        // WHITE_LIST에 등록된 URI 요청 -> chain.doFilter() 호출
        // WHITE_LIST에 등록 X -> 위 필터 로직 통과 -> chain.doFilter() 다음 필터, Servlet 호출
        // 다음 필터가 없으면 Servlet -> Controller
        // 다음 필터가 있으면 Filter 호출
        chain.doFilter(request, response);

    }

    // WHITE_LIST에 포함되는 URL인지 검사
    // 포함 안되어 있다면 false 반환
    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
