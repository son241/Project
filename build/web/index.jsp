<%@page import="DAL.*" %>
<%@page import="controllers.*" %>
<%@page import="models.*" %>
<%@page import="java.util.*" %>
<%@include file="./template/header.jsp" %>

<div id="content">   
    <div id="content-left">
        <h3>CATEGORY</h3>
        <ul>
            <%  
            int pageTh;
            try {
                pageTh = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                pageTh = 1;
            }
             
            String search = request.getParameter("txtSearch");               
            request.setAttribute("search", search);
            ArrayList<Category> categoryList = new CategoryDAO().getCategory();
                
            ArrayList<Product> allItem = new ProductDAO().getProductListBySqlQuery("select * from Products\n"
            +"where ProductName like '%"+search+"%'");

            ArrayList<Product> productlist = new MyGeneric<Product>(allItem).dataPaging(pageTh, 12);
            int numberOfPage = allItem.size() % 12 == 0 ? allItem.size() / 12 : allItem.size() / 12 + 1;
            request.setAttribute("numberOfPage", numberOfPage);
            request.setAttribute("currentPage", pageTh);
            request.setAttribute("productlist", productlist);
            for(Category item: categoryList){
            %>
            <a class="category-item" href="product-list?category-id=<%=item.getCategoryID()%>"><li><%=item.getCategoryName()%></li></a>          
                    <%
                        }
                    %>
        </ul>
    </div>
    <div id="content-right">
        <c:choose>
            <c:when test="${search ne null}">
                <div class="content-main">
                    <c:forEach items="${productlist}" var="p">
                        <div class="product" style="width: 21%; margin: 10px 10px">
                            <a href="detail.jsp?model=${p.getProductID()}"><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                            <div class="name" ><a href="detail.jsp?model=${p.getProductID()}">${p.getProductName()}</a></div>
                            <div class="price" >${p.getUnitPrice()}</div>
                            <div >
                                <div><a href="detail.jsp?model=${p.getProductID()}">Buy now</a></div>
                            </div>
                        </div>
                    </c:forEach>

                    <!--Paging-->
                </div>
                <div id="paging">
                    <div class="pagination">
                        <c:choose>
                            <c:when test="${numberOfPage ne 0}">
                                <c:if test="${currentPage ne 1}">
                                    <a href="index.jsp?page=${currentPage-1}&txtSearch=${search}"><strong>&xlarr;</strong></a>
                                </c:if>
                                <c:forEach begin="1" end="${numberOfPage}" var="i">
                                    <c:choose>
                                        <c:when test="${i eq currentPage}">
                                            <a href="index.jsp?page=${i}&txtSearch=${search}" class="active">${i}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="index.jsp?page=${i}&txtSearch=${search}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>                                  
                                </c:forEach>     
                                <c:if test="${currentPage ne numberOfPage}">
                                    <a href="index.jsp?page=${currentPage+1}&txtSearch=${search}"><strong>&xrarr;</strong></a>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <p style="font-weight: 500; margin-bottom: 8px">No Result <span style='font-size:16px;'>&#128577;</span></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <!--Paging-->
            </c:when>
            <c:otherwise>
                <%
                        request.removeAttribute("productlist");
                        productlist = new ProductDAO().getProductListBySqlQuery("select top 4 p.ProductID, p.CategoryID, p.ProductName, p.QuantityPerUnit, p.ReorderLevel, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\n"
                        + "from Products p, [Order Details] od\n"
                        + "where p.ProductID = od.ProductID\n"
                        + "order by od.Discount desc");
                        request.setAttribute("productlist", productlist);               
                %>
                <div class="path">Hot</b></div>      
                <div class="content-main">
                    <c:forEach items="${productlist}" var="p">
                        <div class="product" style="width: 21%; margin: 10px 10px">
                            <a href="detail.jsp?model=${p.getProductID()}"><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                            <div class="name" ><a href="detail.jsp?model=${p.getProductID()}">${p.getProductName()}</a></div>
                            <div class="price" >${p.getUnitPrice()}</div>
                            <div >
                                <div><a href="detail.jsp?model=${p.getProductID()}">Buy now</a></div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
                <div class="path">Best Sale</b></div>
                <div class="content-main">
                    <%
                        request.removeAttribute("productlist");
                        productlist = new ProductDAO().getProductListBySqlQuery("select top 4 p.ProductID, p.CategoryID, p.ProductName, p.QuantityPerUnit, p.ReorderLevel, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\n"
                        + "from Products p,\n"
                        + "(select top 4 od.ProductID, count(*) as NumberOfProduct\n"
                        + "from [Order Details] od\n"
                        + "group by od.ProductID\n"
                        + "order by NumberOfProduct desc) as od\n"
                        + "where p.ProductID = od.ProductID");
                        request.setAttribute("productlist", productlist);               
                    %>
                    <c:forEach items="${productlist}" var="p">
                        <div class="product" style="width: 21%; margin: 10px 10px">
                            <a href="detail.jsp?model=${p.getProductID()}"><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                            <div class="name" ><a href="detail.jsp?model=${p.getProductID()}">${p.getProductName()}</a></div>
                            <div class="price" >${p.getUnitPrice()}</div>
                            <div >
                                <div><a href="detail.jsp?model=${p.getProductID()}">Buy now</a></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="path">New Product</b></div>
                <div class="content-main">
                    <%
                        request.removeAttribute("productlist");
                        productlist = new ProductDAO().getProductListBySqlQuery("select top 4 p.ProductID, p.CategoryID, p.ProductName, p.QuantityPerUnit, p.ReorderLevel, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\n"
                        + "from Products p,\n"
                        + "(select top 4 od.ProductID, count(*) as NumberOfProduct\n"
                        + "from [Order Details] od\n"
                        + "group by od.ProductID\n"
                        + "order by NumberOfProduct desc) as od\n"
                        + "where p.ProductID = od.ProductID");
                        request.setAttribute("productlist", productlist);               
                    %>
                    <c:forEach items="${productlist}" var="p">
                        <div class="product" style="width: 21%; margin: 10px 10px">
                            <a href="detail.jsp?model=${p.getProductID()}"><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                            <div class="name" ><a href="detail.jsp?model=${p.getProductID()}">${p.getProductName()}</a></div>
                            <div class="price" >${p.getUnitPrice()}</div>
                            <div >
                                <div><a href="detail.jsp?model=${p.getProductID()}">Buy now</a></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="js/main.js"></script>
<%@include file="./template/footer.jsp" %>

