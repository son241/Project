<%@page import="DAL.*" %>
<%@page import="controllers.*" %>
<%@page import="models.*" %>
<%@page import="java.util.ArrayList" %>
<%@include file="./template/header.jsp" %>
<div id="content">
    <%
        Product p = null;
        if(request.getAttribute("add-cart-status") != null){
            p = (Product)request.getAttribute("p");
            request.setAttribute("p", (Product)request.getAttribute("p"));
        }else{
            p = new ProductDAO().getProduct(Integer.parseInt(request.getParameter("model")));
            request.setAttribute("p", p);
        }
    %>

    <div id="content-detail">
        <div id="content-title">
            <a href="<%=path%>/index.jsp">Home</a> >
            <a href="<%=path%>/product-list?category-id=${p.getCategoryID()}"><%=new CategoryDAO().getCategory(p.getCategoryID()).getCategoryName()%></a> >
            Model: ${p.getProductID()}
        </div>
        <div id="product">
            <div id="product-name">
                <h2>${p.getProductName()}</h2>
                <div id="product-detail">
                    <div id="product-detail-left">
                        <div id="product-img">
                            <img src="<%=path%>/img/1.jpg"/>
                        </div>
                        <div id="product-img-items">
                            <div><a href="#"><img src="<%=path%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=path%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=path%>/img/1.jpg"/></a></div>
                            <div><a href="#"><img src="<%=path%>/img/1.jpg"/></a></div>
                        </div>
                    </div>
                    <div id="product-detail-right">
                        <form action="account/addCart?productID=${p.getProductID()}">
                            <div id="product-detail-right-content">
                                <div id="product-price">$${p.getUnitPrice()}</div>
                                <div id="product-status">In stock</div>
                                <div id="product-detail-buttons">
                                    <div id="product-detail-button">
                                        <a style="background-color: brown;
                                           color: #fff;
                                           margin-bottom: 15px;
                                           line-height: 40px;
                                           border-radius: 5px;
                                           border: none;
                                           cursor: pointer;
                                           padding: 10px 80px;
                                           text-decoration: none" href="<%=path%>/account/cart?productID=${p.getProductID()}&buy=true">BY NOW</a>
                                        <a style="background-color: #fff;
                                           color: red;
                                           border: 1px solid gray;
                                           width: 49%;
                                           font-weight: bold;
                                           text-decoration: none;
                                           border-radius: 5px;
                                           padding: 9px 45px;" href="<%=path%>/account/cart?productID=${p.getProductID()}">ADD TO CART</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="info-detail">
            <div id="info-detail-title">
                <h2>Information deltail</h2>
                <div style="margin:10px auto;">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                </div>
            </div>
        </div>

        <%if(request.getAttribute("add-cart-status") != null){%>
        <%if((boolean)request.getAttribute("add-cart-status")){%>
        <div class="success-add-cart open">
            <p><span style='font-size:20px;'>&#128227;</span>Successfully Added<br/></p>                                          
        </div>
        <%}else{%>
        <div class="fail-add-cart open">
            <p><span style='font-size:20px;'>&#128227;</span>Fail, this item has already existed in the cart. Access your cart for more detail, please!<br/></p>                                          
        </div>
        <%}}%>
    </div>
</div>
<style>
    .fail-add-cart,
    .success-add-cart{
        position: fixed;
        right: 50%;
        top: 20px;
        color: black;
        width: 200px;
        border-radius: 8px;
        text-align: center;
        padding-bottom: 2px;
        animation: open ease-in-out 0.5s;
        display: block;
    }

    .success-add-cart{
        background-color: bisque;
    }

    .fail-add-cart{
        background-color: moccasin;
        width: 800px;
        right: 25%;
    }

    @keyframes open {
        from{
            opacity:0.1;
            transform: translateY(-180px);
        }

        to{
            opacity:1;
            transform: translateY(0);
        }
    }

</style>
<script>
    var openSuccess = document.querySelector(".success-add-cart.open");
    var openFail = document.querySelector(".fail-add-cart.open");
//    console.log(open);
//    console.log(openFail);
    function cartAdded() {
        if (openSuccess != null) {
            openSuccess.style.display = "block";
        }

        if (openFail != null) {
            openFail.style.display = "block";
        }
    }

    setTimeout(cartAdded(), 500);

    setTimeout(function () {
        if (openSuccess != null) {
            openSuccess.style.display = "none";
        }
    }, 1500);

    setTimeout(function () {
        if (openFail != null) {
            openFail.style.display = "none";
        }
    }, 8000);
</script>
<%@include file="./template/footer.jsp" %>