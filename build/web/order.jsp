<%@include file="./template/admin-header.jsp" %>
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form action="${applicationScope.path}/order/manage">
                    From: <input type="date" name="txtStartOrderDate" value="${fromDate}"/>
                    To: <input type="date" name="txtEndOrderDate" value="${toDate}"/>
                    <input type="submit" value="Filter">
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${list}" var="item">
                        <tr>
                            <td><a href="${path}/order-detail.jsp?id=${item.getOrderID()}">#${item.getOrderID()}</a></td>
                            <td>${item.getOrderDate()}</td>
                            <td>${item.getRequiredDate()}</td>
                            <td>${item.getShippedDate()}</td>
                            <td>${EmployeeDAO.getEmployee(item.getEmployeeID()).getFullName()}</td>
                            <td>${CustomerDAO.getCustomer(item.getCustomerID()).getContactName()}</td>
                            <td>${item.getFreight()}</td>
                            <c:choose>
                                <c:when test="${item.getShippedDate() ne null}">
                                    <td style="color: green;">Completed</td>
                                </c:when>
                                <c:when test="${item.getShippedDate() eq null and item.getRequiredDate() ne null}">
                                    <td style="color: blue;">Pending | <a href="${applicationScope.path}/account/cancel-order?order-id=${item.getOrderID()}&page=${currentPage}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}">Cancel</a></td>                               
                            </c:when>
                            <c:when test="${item.getRequiredDate() eq null}">
                                <td style="color: red;">Order canceled</td>
                            </c:when>
                        </c:choose>
                        </tr>
                    </c:forEach>           
                </table>
            </div>


            <div id="paging">
                <div class="pagination">
                    <c:choose>
                        <c:when test="${numberOfPage ne 0}">
                            <c:if test="${currentPage ne 1}">
                                <a href="${applicationScope.path}/order/manage?page=${currentPage-1}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}"><strong>&xlarr;</strong></a>
                            </c:if>
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <c:choose>
                                    <c:when test="${i eq currentPage}">
                                        <a href="${applicationScope.path}/order/manage?page=${i}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}" class="active">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${applicationScope.path}/order/manage?page=${i}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}">${i}</a>
                                    </c:otherwise>
                                </c:choose>                                  
                            </c:forEach>     
                            <c:if test="${currentPage ne numberOfPage}">
                                <a href="${applicationScope.path}/order/manage?page=${currentPage+1}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}"><strong>&xrarr;</strong></a>
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
<%@include file="./template/admin-footer.jsp" %>