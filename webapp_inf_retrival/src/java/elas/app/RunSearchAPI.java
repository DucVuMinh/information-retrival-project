/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elas.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;


/**
 *
 * @author ducvu
 */
@Controller
public class RunSearchAPI {
    @RequestMapping(value = "http://localhost:9200/yahoo/news/_search?search_type=dfs_query_then_fetch", method = RequestMethod.GET)
    public String testSearch(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        return null;
    }
}
