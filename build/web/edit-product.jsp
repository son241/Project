<%@include file="./template/admin-header.jsp" %>
                <div id="content-right">
                    <div class="path-admin">EDIT PRODUCT</b></div>
                    <div class="content-main">
                        <form id="content-main-product" action="crud" method="post">
                            <div class="content-main-1">
                                <label>ProductID:</label><br/>
                                <input type="text" name="txtProductID" value="<c:out value="${ProductID ne null ? ProductID : p.getProductID()}"/>" readonly><br/>

                                <label>Product name:</label><br/>
                                <input type="text" name="txtProductName" value="<c:out value="${ProductName ne null ? ProductName : p.getProductName()}"/>"><br/>
                                <c:if test="${emptyName ne null}"><span class="msg-error">${emptyName}</span><br/></c:if>

                                    <label>Unit price:</label><br/>
                                    <input type="text" name="txtUnitPrice" value="<c:out value="${UnitPrice ne null ? UnitPrice : p.getUnitPrice()}"/>"<br/>
                                <c:if test="${emptyUnitPrice ne null}"><span class="msg-error">${emptyUnitPrice}</span><br/></c:if>
                                <c:if test="${emptyUnitPrice eq null and invalidUnitPrice ne null}"><span class="msg-error">${invalidUnitPrice}</span><br/></c:if>


                                    <label>Quantity per unit:</label><br/>
                                    <input type="text" name="txtQuantityPerUnit" value="<c:out value="${QuantityPerUnit ne null ? QuantityPerUnit : p.getQuantityPerUnit()}"/>"<br/>
                                <c:if test="${emptyQuantityPerUnit ne null}"><span class="msg-error">${emptyQuantityPerUnit}</span><br/></c:if>

                                    <label>Units in stock (*):</label><br/>                 
                                    <input type="text" name="txtUnitsInStock" value="<c:out value="${UnitsInStock ne null ? UnitsInStock : p.getUnitsInStock()}"/>"><br/>
                                <c:if test="${emptyUnitsInStock ne null}"><span class="msg-error">${emptyUnitsInStock}</span><br/></c:if>
                                <c:if test="${invalidUnitsInStock ne null and emptyUnitsInStock eq null}"><span class="msg-error">${invalidUnitsInStock}</span><br/></c:if>

                                </div>
                                <div class="content-main-1">
                                    <label>Category (*):</label><br/>
                                    <select name="category">
                                    <c:forEach items="${categoryList}" var="c">
                                        <c:if test="${p.getCategoryID() == c.getCategoryID() or CategoryID == c.getCategoryID()}">
                                            <option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                        </c:if>
                                        <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                    </c:forEach>       
                                </select>
                                <br/>
                                <!--<span class="msg-error">Product name is required.</span><br/>-->

                                <label>Reorder level:</label><br/>
                                <input type="text" name="txtReorderLevel" value="<c:out value="${ReorderLevel ne null ? ReorderLevel : p.getReorderLevel()}"/>"><br/>
                                <c:if test="${emptyReorderLevel ne null}"><span class="msg-error">${emptyReorderLevel}</span><br/></c:if>
                                <c:if test="${invalidReorderLevel ne null and emptyReorderLevel eq null}"><span class="msg-error">${invalidReorderLevel}</span><br/></c:if>


                                    <label>Units on order:</label><br/>
                                    <input type="text" name="txtUnitsOnOrder" readonly value="<c:out value="${UnitsOnOrder ne null ? UnitsOnOrder : p.getUnitsOnOrder()}"/>"><br/>

                                <label>Discontinued:</label><br/>
                                <c:choose>
                                    <c:when test="${p.isDiscontinued() eq true or Discontinued eq true}">
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
                        <button class="visit-shop"><a href="crud?update=true&pid=${ProductID}">Edit again</a></button>
                    </div>
                </div>  
                <%}%>             
            </div>
<%@include file="./template/admin-footer.jsp" %>