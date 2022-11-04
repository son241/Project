<%@page import="models.*" %>
<%@page import="controllers.*" %>
<%@page import="DAL.*" %>
<%@page import="java.util.*" %>
<%@include file="./template/header.jsp" %>
<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <ul>
            <c:forEach items="${categoryList}" var="item">
                <a class="category-item" href="product-list?category-id=${item.getCategoryID()}"><li <c:if test="${item.getCategoryID() eq c.getCategoryID()}">style="background-color: sienna; color: white"</c:if>>${item.getCategoryName()}</li></a>
            </c:forEach>


        </ul>
    </div>
    <div id="content-right">

        <div class="path">${c.getCategoryName()}</br></div> 

        <div class="content-main">
            <c:forEach items="${productList}" var="p">
                <div class="product" style="width: 20%; margin: 10px 10px;">
                    <a href="detail.jsp?model=${p.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail.jsp?model=${item.getProductID()}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <div><a href="">Buy now</a></div>
                </div>
            </c:forEach>

        </div>
        <c:if test="${numberOfPage ne 0}">
            <div id="paging">
                <div class="pagination">
                    <c:if test="${currentPage ne 1}">
                        <a href="product-list?category-id=${c.getCategoryID()}&page=${currentPage-1}"><strong>&xlarr;</strong></a>
                    </c:if>
                    <c:forEach begin="1" end="${numberOfPage}" var="i">
                        <c:choose>
                            <c:when test="${i eq currentPage}">
                                <a href="product-list?category-id=${c.getCategoryID()}&page=${i}" class="active">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="product-list?category-id=${c.getCategoryID()}&page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>                                  
                    </c:forEach>     
                    <c:if test="${currentPage ne numberOfPage}">
                        <a href="product-list?category-id=${c.getCategoryID()}&page=${currentPage+1}"><strong>&xrarr;</strong></a>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${numberOfPage eq 0}">
            <p>No result</p>
        </c:if>
    </div>
</div>
<script src="./js/main.js"></script>
<%@include file="./template/footer.jsp" %>
