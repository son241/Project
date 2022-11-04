<%@include file="./template/admin-header.jsp" %>
<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form action="manage" method="get">
                        <select name="category">
                            <option value="-1" selected>None</option>
                            <c:forEach items="${categoryDAO.getCategory()}" var="c">
                                <c:choose>
                                    <c:when test="${c.getCategoryID() eq categoryFilter}">
                                        <option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>                                           
                        </select>
                        <input type="submit" value="Filter">
                        <!--</form>-->
                        </div>
                        <div id="product-title-2" style="width: 55%;">
                            <!--<form action="manage" method="get">-->
                            <input type="text" name="txtSearch" placeholder="Enter product name to search" value="${txtSearch}"/>
                            <input type="submit" value="Search"/>
                    </form>
                </div>
                <div id="product-title-3" style="width: 20%;">
                    <a href="crud?create=true">Create a new Product</a>
                    <form action="">
                        <label for="upload-file">Import .xls or .xlsx file</label>
                        <input type="file" name="file" id="upload-file" />
                    </form>
                </div>
            </div>
            <div id="order-table-admin">
                <table id="orders">
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>UnitPrice</th>
                        <th>Unit</th>
                        <th>UnitsInStock</th>
                        <th>Category</th>
                        <th>Discontinued</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${subList}" var="p">

                        <tr>
                            <td><a href="order-detail.html?id=5"><a>${p.getProductID()}</a></td>
                            <td>${p.getProductName()}</td>
                            <td>${p.getUnitPrice()}</td>
                            <td>${p.getQuantityPerUnit()}</td>
                            <td>${p.getUnitsInStock()}</td>
                            <td>${categoryDAO.getCategory(p.getCategoryID()).getCategoryName()}</td>
                            <td>${p.isDiscontinued()}</td>

                            <td>
                                <a href="crud?update=true&pid=${p.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                <a href="crud?delete=true&pid=${p.getProductID()}&page=${currentPage}">Delete</a>
                            </td>
                        </tr>

                    </c:forEach>

                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <c:choose>
                        <c:when test="${numberOfPage ne 0}">
                            <c:if test="${currentPage ne 1}">
                                <a href="manage?page=${currentPage-1}&category=${categoryFilter}&txtSearch=${txtSearch}"><strong>&xlarr;</strong></a>
                            </c:if>
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <c:choose>
                                    <c:when test="${i eq currentPage}">
                                        <a href="manage?page=${i}&category=${categoryFilter}&txtSearch=${txtSearch}" class="active">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="manage?page=${i}&category=${categoryFilter}&txtSearch=${txtSearch}">${i}</a>
                                    </c:otherwise>
                                </c:choose>                                  
                            </c:forEach>     
                            <c:if test="${currentPage ne numberOfPage}">
                                <a href="manage?page=${currentPage+1}&category=${categoryFilter}&txtSearch=${txtSearch}"><strong>&xrarr;</strong></a>
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
<c:if test="${deleteID ne null}">
    <div class="success-signup overlay">
        <div class="success-signup-content" style="background-color: brown">
            <p><strong>Do you want to delete this product?</strong><br/></p>
            <button class="visit-shop" style="margin-right: 8px; box-shadow: none; padding:0 8px; text-align: center"><a href="crud?delete=true&deleteID=${deleteID}">Yes</a></button>
            <button class="visit-shop no" style="margin-right: 10px; box-shadow: none; padding:0 10px; text-align: center; cursor: pointer">No</a></button>
        </div>
    </div>  
</c:if>
<%if(request.getAttribute("success-delete-msg") != null){%>
<div class="success-signup overlay">
    <div class="success-signup-content" style="background-color: green">
        <p><strong><%=request.getAttribute("success-delete-msg")%></strong><br/></p>                          
        <button class="visit-shop no" style="margin-right: 10px; box-shadow: none; padding:0 10px; text-align: center; cursor: pointer">Close</button>
    </div>
</div>  
<%}%>
</div>

<script>
    var overlay = document.querySelector(".success-signup.overlay");
    var content = document.querySelector(".success-signup-content");
    var no = document.querySelector(".no");
    function closeModel() {
        console.log(overlay);
        overlay.style.display = 'none';
    }
    overlay.addEventListener("click", closeModel);
    no.addEventListener("click", closeModel);
    content.onclick = function (e) {
        e.stopPropagation();
    }
</script>
<style>
    #product-link li{
        background-color: sienna;
        color: white;
        
    }
</style>
<%@include file="./template/footer.jsp" %>