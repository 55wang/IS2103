/*
 * SecondBankServlet.java
 */
package servlets;

import ejb.SHSManagerBeanRemote;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StarHotelServlet extends HttpServlet {

    @EJB
    private SHSManagerBeanRemote shsmb;
    private ArrayList data = null;
    private long paymentID;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    Date tempDate = new Date();
    private Date checkin, checkout;

    public void init() {
        System.out.println("SHS Servlet: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SHS Servlet: processRequest()");
        try {

            RequestDispatcher dispatcher;

            ServletContext servletContext = getServletContext();

            String page = request.getPathInfo();
            page = page.substring(1);
            ArrayList temp = new ArrayList();
               
            if ("register".equals(page)) {
                // no attributes to set
            } else if ("login".equals(page)) {
                temp.add("Enter your username and password");
                data = temp;

                request.setAttribute("data", data);
            } else if ("addStatus".equals(page)) {
                System.out.println("test");
                data = addCustomer(request);
                request.setAttribute("data", data);
            } else if ("index".equals(page)) {

            } else if ("loginStatus".equals(page)) {

                if (getCustomer(request) == true) {
                    System.out.println("LOGIN SUCCESS!");
                } else {
                    page = "login";
                    temp.add("Wrong username or password");
                    data = temp;
                    request.setAttribute("data", data);
                }
            } else if (shsmb.getLogin()) {
                if ("logout".equals(page)) {
                    shsmb.logout();

                    temp.add("successfully logout");
                    data = temp;
                    page = "login";
                    request.setAttribute("data", data);
                } else if ("result".equals(page)) {
                    checkin = sdf.parse(request.getParameter("checkin"));
                    checkout = sdf.parse(request.getParameter("checkout"));
                    temp = shsmb.searchRoomNumber(checkin, checkout);
                    data = temp;

                    request.setAttribute("data", data);
                    temp = shsmb.searchRoomType(data);
                    request.setAttribute("type", temp);
                    temp = shsmb.searchCapacity(data);
                    request.setAttribute("cap", temp);
                    temp = shsmb.searchPrice(data);
                    request.setAttribute("price", temp);

                } else if ("shoppingCart".equals(page)) {
                temp = shsmb.shoppingCartt();
                    data = temp;
System.out.println("reach here1"+temp.size());
                    request.setAttribute("data", data);
                    temp = shsmb.searchRoomType(data);
                    
                    System.out.println("reach here2"+temp.size());
                    request.setAttribute("type", temp);
                    temp = shsmb.searchCapacity(data);
                    
                    request.setAttribute("cap", temp);
                    temp = shsmb.searchPrice(data);
                    System.out.println("reach here3"+temp.size());
                    request.setAttribute("price", temp);
                    System.out.println("reach here4"+temp.size());
                
            } else if ("shopping".equals(page)) {
                    String[] selected = request.getParameterValues("shopping");
                    System.out.println(checkin+" "+checkout);
                    ArrayList temp2 =new ArrayList();
                    for (String s : selected) {
                        temp.add(s);
                        temp2.add(Integer.parseInt(s));
                        System.out.println("try:"+temp);
                    }

                    paymentID = shsmb.shoppingCart(temp, checkin, checkout);
                    System.out.println(paymentID);
                    data = temp2;

                    request.setAttribute("data", data);
                    temp2 = shsmb.searchRoomType(data);
                    request.setAttribute("type", temp2);
                    temp2 = shsmb.searchCapacity(data);
                    request.setAttribute("cap", temp2);
                    temp2 = shsmb.searchPrice(data);
                    request.setAttribute("price", temp2);
                     
                } else if ("viewBooking".equals(page)) {
                    temp = shsmb.viewBooking();
                    data= temp;
                    request.setAttribute("data", data);
                    temp = shsmb.viewBookingCheckin();
                    data= temp;
                    request.setAttribute("checkin", data);
                    temp = shsmb.viewBookingCheckout();
                    data= temp;
                    request.setAttribute("checkout", data);
                    temp = shsmb.viewBookingPayment();
                    data= temp;
                    request.setAttribute("payment", data);
                    temp = shsmb.viewBookingStatus();
                    data= temp;
                    request.setAttribute("status", data);
                    
                    
                } else if ("makePayment".equals(page)) {
                    shsmb.makePayment(paymentID);
                }
                
            } else {
                System.out.println("got error");
                page = "Error";
                
                
            }
            dispatcher = servletContext.getNamedDispatcher(page);
            // The dispatcher uses the Servlet Name in the web.xml
            // Therefore, the value of variable page must match
            // the Servlet Name in the web.xml

            if (dispatcher == null) {
                dispatcher = servletContext.getNamedDispatcher("Error");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            //log("Exception in SHS Servlet.processRequest()");
        } 
    }

    private boolean getCustomer(HttpServletRequest request) {
        ArrayList al = new ArrayList();
        String passport = request.getParameter("passport");
        String password = request.getParameter("password");
        password = hashpw(password);
        if (shsmb.loginMember(passport, password)) {
            request.getSession().setAttribute("passport", passport);
            request.getSession().setAttribute("password", password);
            return true;
        }
        return false;

    }
    
        public String hashpw (String pw) {
        String encrypted = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] passwordByte = pw.getBytes();
            digest.reset();
            digest.update(passwordByte);
            byte message[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<message.length;i++) {
                hexString.append(Integer.toHexString(0xFF & message[i]));
                
            }
            encrypted = hexString.toString();
            
        } catch (Exception e) {
            
        }
        return encrypted;
    }

    private ArrayList addCustomer(HttpServletRequest request) {
        ArrayList al = new ArrayList();
        String passport = request.getParameter("passport");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String postal = request.getParameter("postal");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String email = request.getParameter("email");

        if (passport.equals(null) || name.equals(null) || password.equals(null) || email.equals(null)) {
            return al;
        }
        
        password = hashpw(password);
        
        boolean ctl = shsmb.createMember(passport, name, password, postal, phone, email);
        if (ctl==true) {
           al.add(passport);
        al.add(name);
        al.add(password);
        al.add("Customer added successfully.");
        return al; 
        } else {
            al.add("nil");
        al.add("nil");
        al.add("nil");
        al.add("Registatration fail, passport is already registered. pls use another one.");
        return al;
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SHS Servlet: doGet()");

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SHS BankServlet: doPost()");

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        System.out.println("a");
        return "Short description";
    }

    @Override
    public void destroy() {
        System.out.println("SHS Servlet: destroy()");
    }
}
