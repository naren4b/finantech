package com.fint;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScripTradeInfoDetails;
import service.FinanTechService;

/**
 * Servlet implementation class ShowScripDeatils
 */
@WebServlet("/scripDeatils")
public class ShowScripDeatils extends HttpServlet {

    FinanTechService finanTechService = new FinanTechService();

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowScripDeatils() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String scripId = request.getParameter("scripId");
        ScripTradeInfoDetails scripTradeInfoDetails = finanTechService
                .getScripTradeInfoDetails(scripId);
        ScripInfo scripInfo = new ScripInfo();
        String data = scripInfo.getScripInfo(scripTradeInfoDetails);
        request.setAttribute("dataTable", data);
        request.setAttribute("scripName", scripId);
        request.setAttribute("date", util.FinanTechUtil
                .getFormattedDateString(new Date(), "dd-MMM"));
        request.setAttribute("data", scripInfo.getdataValues());
        request.setAttribute("labels", scripInfo.getScripLabels());
        request.setAttribute("scale_Max", scripInfo.getScaleMax());
        request.setAttribute("scale_Min", scripInfo.getScaleMin());

        RequestDispatcher reqDispatcher = getServletConfig()
                .getServletContext()
                .getRequestDispatcher("/ScripDetailsPageLine.jsp");
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
