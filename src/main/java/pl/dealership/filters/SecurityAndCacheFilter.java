package pl.dealership.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class SecurityAndCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        HttpServletRequestWrapper noCookieRequest = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public Cookie[] getCookies() {
                return null;
            }

            @Override
            public String getHeader(String name) {
                if ("Cookie".equalsIgnoreCase(name)) {
                    return null;
                }
                return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if ("Cookie".equalsIgnoreCase(name)) {
                    return Collections.emptyEnumeration();
                }
                return super.getHeaders(name);
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                List<String> names = Collections.list(super.getHeaderNames());
                names.removeIf(n -> "Cookie".equalsIgnoreCase(n));
                return Collections.enumeration(names);
            }
        };

        HttpServletResponseWrapper noCookieResponse = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public void addCookie(Cookie cookie) {
            }

            @Override
            public void setHeader(String name, String value) {
                if (!"Set-Cookie".equalsIgnoreCase(name)) {
                    super.setHeader(name, value);
                }
            }

            @Override
            public void addHeader(String name, String value) {
                if (!"Set-Cookie".equalsIgnoreCase(name)) {
                    super.addHeader(name, value);
                }
            }
        };

        chain.doFilter(noCookieRequest, noCookieResponse);
    }

    @Override
    public void destroy() {
    }
}