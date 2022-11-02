<%@include file="./template/admin-header.jsp" %>
<div id="content-right">
    <div class="path-admin">CREATE A NEW PRODUCT</b></div>
    <div class="content-main">
        <form id="content-main-product" action="crud" method="post">
            <div class="content-main-1">
                <label>Product name (*):</label><br/>
                <input type="text" name="txtProductName" value="${ProductName}"><br/>
                <c:if test="${emptyName ne null}"><span class="msg-error">${emptyName}</span><br/></c:if>

                    <label>Unit price:</label><br/>
                    <input type="text" name="txtUnitPrice" value="${UnitPrice}"><br/>
                <c:if test="${emptyUnitPrice ne null}"><span class="msg-error">${emptyUnitPrice}</span><br/></c:if>
                <c:if test="${emptyUnitPrice eq null and invalidUnitPrice ne null}"><span class="msg-error">${invalidUnitPrice}</span><br/></c:if>


                    <label>Quantity per unit:</label><br/>
                    <input type="text" name="txtQuantityPerUnit" value="${QuantityPerUnit}"><br/>
                <c:if test="${emptyQuantityPerUnit ne null}"><span class="msg-error">${emptyQuantityPerUnit}</span><br/></c:if>

                    <label>Units in stock (*):</label><br/>                 
                    <input type="text" name="txtUnitsInStock" value="${UnitsInStock}"><br/>
                <c:if test="${emptyUnitsInStock ne null}"><span class="msg-error">${emptyUnitsInStock}</span><br/></c:if>
                <c:if test="${invalidUnitsInStock ne null and emptyUnitsInStock eq null}"><span class="msg-error">${invalidUnitsInStock}</span><br/></c:if>

                </div>
                <div class="content-main-1">
                    <label>Category (*):</label><br/>
                    <select name="category">
                    <c:forEach items="${categoryList}" var="c">
                        <c:if test="${CategoryID == c.getCategoryID()}">
                            <option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                        </c:if>
                        <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                    </c:forEach>       
                </select>
                <br/>
                <!--<span class="msg-error">Product name is required.</span><br/>-->

                <label>Reorder level:</label><br/>
                <input type="text" name="txtReorderLevel" value="${ReorderLevel}"><br/>
                <c:if test="${emptyReorderLevel ne null}"><span class="msg-error">${emptyReorderLevel}</span><br/></c:if>
                <c:if test="${invalidReorderLevel ne null and emptyReorderLevel eq null}"><span class="msg-error">${invalidReorderLevel}</span><br/></c:if>


                    <label>Units on order:</label><br/>
                    <input type="text" name="txtUnitsOnOrder" disabled><br/>

                    <label>Discontinued:</label><br/>
                <c:choose>
                    <c:when test="${Discontinued eq true}">
                        <input type="checkbox" name="chkDiscontinued" checked><br/>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="chkDiscontinued"><br/>
                    </c:otherwise>
                </c:choose>
                <input type="submit" value="Save"/>
            </div>
        </form>
    </div>
</div>
<%if(request.getAttribute("success-msg") != null){%>
<div class="success-signup overlay">
    <div class="success-signup-content">
        <p><strong><%=request.getAttribute("success-msg")%></strong><br/></p>
        <button class="visit-shop" style="margin-right: 8px"><a href="manage">Back</a></button>
        <button class="visit-shop"><a href="crud?create=true">Add more</a></button>
    </div>
</div>  
<%}%>             
</div>
<%@include file="./template/admin-footer.jsp" %>