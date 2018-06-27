package com.pmcc.soft.core.organization.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.AppUtils;

public class SessionFilter extends OncePerRequestFilter{
	
	private static final String[] DEFAULT_EXCLUDE_SUFFIXS = { "/","login.html","index.jsp","login.jsp","question.jsp","library.jsp",".js", ".css",".jpg", ".gif","png" };
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 不过滤的uri
				// 请求的uri
				String uri = request.getRequestURI();
				HttpSession session = request.getSession();
				String andriod = request.getParameter("android");
				if("true".equals(andriod)){
					filterChain.doFilter(request, response);
					return;
				}
				if(uri.contains("mobileManager")){
					filterChain.doFilter(request, response);
					return;
				}
				if(uri.contains("sendWeixinInforesult.jsp")){
					filterChain.doFilter(request, response);
					return;
				}
				if(uri.contains("findPassword")){
					filterChain.doFilter(request, response);
					return;
				}
				for (String suffix : DEFAULT_EXCLUDE_SUFFIXS) {
					if (uri.endsWith(suffix)){
						filterChain.doFilter(request,response);
						return;
					}
				}
						
				logger.debug("=======================URL:"+uri);
				// uri中包含background时才进行过滤
				if (!uri.endsWith("login.do")) {
					// 是否过滤
					boolean doFilter = true;
					if (doFilter) {
						// 执行过滤
						// 从session中获取登录者实体
						PersonManage currentpersonManage = (PersonManage)session.getAttribute("loginUser");
						if (null == currentpersonManage) {
							// 如果session中不存在登录者实体，则弹出框提示重新登录
							// 设置request和response的字符集，防止乱码
//							request.setCharacterEncoding("UTF-8");
							response.setCharacterEncoding("UTF-8");
							response.setContentType("text/html;charset=GBK");
							PrintWriter out = response.getWriter();
							StringBuilder builder = new StringBuilder();
							builder.append("<script type=\"text/javascript\">");
							builder.append("alert('对不起，请重新登录！');");
							builder.append("window.top.location='/");
							builder.append("';");
							builder.append("</script>");
							out.print(builder.toString());
//							response.setCharacterEncoding("utf-8");
//							response.sendError(HttpStatus.UNAUTHORIZED.value(),"您已经太长时间没有操作,请刷新页面");
//							response.sendRedirect("../login.jsp");
//							ModelAndView view = new ModelAndView();
//							throw new ModelAndViewDefiningException(view);
						} else {
							// 如果session中存在登录者实体，则继续
							filterChain.doFilter(request, response);
						}
					} else {
						// 如果不执行过滤，则继续
						filterChain.doFilter(request, response);
					}
				} else {
					// 如果uri中不包含background，则继续
					filterChain.doFilter(request, response);
				}
			}

		
	}

