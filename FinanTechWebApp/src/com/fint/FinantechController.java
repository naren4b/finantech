package com.fint;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScripInfo;
import service.FinanTechService;

/**
 * Servlet implementation class FinantechController
 */
@WebServlet("/welcome.do")
public class FinantechController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FinantechController() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        StringBuffer options = new StringBuffer();

        FinanTechService finanTechService = new FinanTechService();

        List<ScripInfo> allScripDetails = finanTechService
                .getAllScripDetails();
        for (ScripInfo scripInfo : allScripDetails) {
            options.append("<option value=\"" + scripInfo.getScripName()
                    + "\">" + scripInfo.getScripName() + "</option>");
        }
        request.setAttribute("options", options.toString());
        RequestDispatcher reqDispatcher = getServletConfig()
                .getServletContext().getRequestDispatcher("/index.jsp");
        reqDispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
