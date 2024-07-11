package com.parcodivertimenti.parcodivertimenti.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.rmi.ServerException;
import java.util.logging.Level;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;

@WebServlet(name = "Dispatcher", urlPatterns = {"/Dispatcher"})
public class Dispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String controllerAction=request.getParameter("controllerAction"); /*dobbiamo dire quale controller vogliamo chiamare*/

            if (controllerAction==null) controllerAction="ParkController.homePage"; /*HomeManagement è il controller che si occupa di gestire la schermata principale*/

            String[] splittedAction=controllerAction.split("\\.");
            Class<?> controllerClass=Class.forName("com.parcodivertimenti.parcodivertimenti.controller."+splittedAction[0]);
            Method controllerMethod=controllerClass.getMethod(splittedAction[1],HttpServletRequest.class,HttpServletResponse.class);
            //LogService.getApplicationLogger().log(Level.INFO,splittedAction[0]+" "+splittedAction[1]);
            Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            controllerMethod.invoke(controllerInstance,request,response);

            String viewUrl=(String)request.getAttribute("viewUrl");
            /*RequestDispatcher view=request.getRequestDispatcher(viewUrl+".jsp");
            view.forward(request,response);*/
            if (viewUrl != null) {
                RequestDispatcher view = request.getRequestDispatcher(viewUrl + ".jsp");
                view.forward(request, response);
            } else {
                throw new ServerException("viewUrl not set by controller");
            }


        } catch (Exception e) {
            e.printStackTrace(out);
            throw new ServerException("Dispacther Servlet Error",e);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

