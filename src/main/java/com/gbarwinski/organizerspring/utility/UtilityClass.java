package com.gbarwinski.organizerspring.utility;

import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UtilityClass {

    public static User getLoggedInUser() {
        HttpSession session = Objects.requireNonNull(getCurrentHttpRequest()).getSession(false);
        if (session != null)
            return (User) session.getAttribute("appUser");
        else
            return new User();
    }

    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    public static List<String> getListOfIconTitles() throws IOException {
        List<String> result = new ArrayList<>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath*:/**/icons/**/*.png");
        for (Resource resource : resources) {
            result.add(resource.getFilename());
        }
        return result;
    }

    public static List<String> getListOfIconsTitlesWrittenManually() {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            String tmp;
            if (i < 10) {
                tmp = "00" + i + ".png";
            } else {
                tmp = "0" + i + ".png";
                strings.add(tmp);
            }
        }
        return strings;
    }
}

