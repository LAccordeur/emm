package com.uestc.emm.server.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/11/23
 */
public class TestServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("-------------------------TestServlet init---------------------------");

    }
}
