<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../template/header.jsp" %>
<%@page  import="DAL.*"%>
<%@page  import="models.*"%>
<%@page  import="java.util.*"%>
<c:if test="${sessionScope.AccSession eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<%   
    int pageTh;
    try {
        pageTh = Integer.parseInt(request.getParameter("page"));
    } catch (Exception e) {
        pageTh = 1;
    }
    Customer c = new CustomerDAO().getCustomer(((Account)request.getSession().getAttribute("AccSession")).getCustomerID());  
    ArrayList<Order> allItem = new OrderDAO().getOrdersBySqlQurey("select * from Orders\n"
                                + "where RequiredDate is not null and CustomerID = '"+c.getCustomerID()+"'\n"
                                + "order by OrderID desc");
    ArrayList<Order> orderList = new MyGeneric<Order>(allItem).dataPaging(pageTh, 6);
    int numberOfPage = allItem.size() % 6 == 0 ? allItem.size() / 6 : allItem.size() / 6 + 1;
    request.setAttribute("c", c);  
    request.setAttribute("numberOfPage", numberOfPage);
    request.setAttribute("currentPage", pageTh);
    request.setAttribute("orders", orderList);
    request.setAttribute("oderDetailDAO", new OrderDetailDAO());
    request.setAttribute("productDAO", new ProductDAO());
%>

<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome, ${c.getContactName()}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<%=path%>/account/profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="profile1.jsp"><li>All orders</li></a>
            <a href="<%=path%>/profile2.jsp"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">LIST ORDERS</b></div>
        <div class="content-main">

            <div id="profile-content-order">

                <c:forEach items="${orders}" var="o">
                        <div>
                            <div onclick="show(this)" class="profile-order-title">
                                <div class="profile-order-title-left">
                                    <div>Order creation date: ${o.getOrderDate()}</div>
                                    <div>Order: <a style="cursor: pointer; text-decoration: underline; color: cornflowerblue">#${o.getOrderID()}</a></div>
                                </div>

                                <c:if test="${o.getShippedDate() eq null}">
                                    <div class="profile-order-title-right">
                                        <span>Pending</span>
                                    </div>
                                </c:if>

                                <c:if test="${o.getShippedDate() ne null}">
                                    <div class="profile-order-title-right">
                                        <span style="color: blue;">Completed</span>
                                    </div>
                                </c:if>


                            </div>
                            <c:set var="orderDetailList" value="${oderDetailDAO.getOrderDetails(o.getOrderID())}"/>
                            <!--<div class="order-detail">-->


                            <div class="profile-order-content" <c:if test="${orders.indexOf(o) ne 0}">style="display: none"</c:if>/>
                                <c:forEach items="${orderDetailList}" var="od"> 
                                    <div style="display: flex; align-content: space-around">
                                        <div class="profile-order-content-col1">
                                            <a href="detail.jsp?model=${od.getProductID()}"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                                        </div>
                                        <div class="profile-order-content-col2">${productDAO.getProduct(od.getProductID()).getProductName()}</div>
                                        <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                                        <div class="profile-order-content-col4">${od.getUnitPrice()} $</div>
                                    </div>
                                </c:forEach>    

                                <c:if test="${o.getShippedDate() == null}">
                                    <a class="cancel-order" onclick="ask(this)"  style="font-size: 18px;
                                       float: right;
                                       width: 100%;
                                       text-align: right;
                                       margin: 18px;
                                       color: blue;
                                       font-weight: bold;">Cancel Order</a> 

                                    <div class="success-signup overlay" onclick="hideAsk(this)" style="display: none">
                                        <div class="success-signup-content" style="background-color: brown">
                                            <p><strong>Are you sure to cancel this order?</strong><br/></p>
                                            <button class="visit-shop" style="margin-right: 8px; box-shadow: none; padding:0 8px; text-align: center"><a href="<%=path%>/account/cancel-order?order-id=${o.getOrderID()}">Yes</a></button>
                                            <button class="visit-shop no" style="margin-right: 10px; box-shadow: none; padding:0 10px; text-align: center; cursor: pointer" onclick="hideAsk1(this)">No</button>
                                        </div>
                                    </div>  
                                </c:if>                                      
                        </div>
                    </div>
            </c:forEach>
            <div id="paging">
                <div class="pagination">
                    <c:choose>
                        <c:when test="${numberOfPage ne 0}">
                            <c:if test="${currentPage ne 1}">
                                <a href="profile1.jsp?page=${currentPage-1}"><strong>&xlarr;</strong></a>
                            </c:if>
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <c:choose>
                                    <c:when test="${i eq currentPage}">
                                        <a href="profile1.jsp?page=${i}" class="active">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="profile1.jsp?page=${i}">${i}</a>
                                    </c:otherwise>
                                </c:choose>                                  
                            </c:forEach>     
                            <c:if test="${currentPage ne numberOfPage}">
                                <a href="profile1.jsp?page=${currentPage+1}"><strong>&xrarr;</strong></a>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <p style="font-weight: 500; margin-bottom: 8px">No Result <span style='font-size:16px;'>&#128577;</span></p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<style>
    /*    .success-signup.overlay{
            background-color: brown;
            width: 40%;
            margin: 16px auto;
            display: none;
        }*/
    .cancel-order:hover{
        opacity: 0.7;
        cursor: pointer;
    }

    .profile-order-content{
        animation: show ease-out 1s;
    }
    @keyframes show {
        from {
            opacity: 0.1;
        }

        to {
            opacity: 1;
        }
    }
</style>
<script>
    function show(o) {
        console.log(o)
        if (o.nextElementSibling.style.display === "flex") {
            o.nextElementSibling.style.display = "none";
        } else {
            o.nextElementSibling.style.display = "flex";
        }
    }

    function hideAsk1(i) {
        i.parentElement.parentElement.style.display = "none";
    }
    function hideAsk(i) {
        i.firstElementChild.onclick = function (e) {
            e.stopPropagation();
        }
        i.style.display = "none";
    }

    function ask(c) {
        if (c.nextElementSibling.style.display === "flex") {
            c.nextElementSibling.style.display = "none";
        } else {
            c.nextElementSibling.style.display = "flex";
        }
    }

</script>
<%@include file="./template/footer.jsp" %>