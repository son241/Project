<%@include file="./template/header.jsp" %>
<%@page  import="DAL.*"%>
<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome, ${customer.getContactName()}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<%=path%>/account/profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="${applicationScope.path}/profile1.jsp"><li>All orders</li></a>
            <a href="${applicationScope.path}/profile2.jsp"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Personal information</b></div>
        <div class="content-main">
            
            <!--edit-->
            <form action="editProfile" method="post" style=" width: 100%;
                  border: 1px solid saddlebrown;
                  margin: 0px 10px;
                  background-color: #fff;
                  display: flex;">
                <div class="profile-content-col">
                    <div>Company name: <br/><input type="text" name="company-name" value="${customer.getCompanyName()}" style="width: 65%"></div>
                    <div>Contact name: <br/><input type="text" name="contact-name" value="${customer.getContactName()}" style="width: 65%"></div>
                    <div>
                        <input style="background-color: sienna;
                               color: #fff;
                               line-height: 40px;
                               border-radius: 5px;
                               border: none;
                               cursor: pointer;
                               margin-top: 70px;
                               width: 35%;" type="submit" value="Save"/>
                        <a style="background-color: brown;
                           color: #fff;
                           line-height: 40px;
                           border-radius: 5px;
                           border: none;
                           cursor: pointer;
                           margin-top: 70px;
                           width: 50%;
                           text-decoration: none;
                           padding: 9px 42px;" href="<%=path%>/account/profile">Cancel</a>
                    </div>
                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/><input type="text" name="contact-title" value="${customer.getContactTitle()}" style="width: 65%"></div>
                    <div>Address: <br/><input type="text" name="address" value="${customer.getAddress()}" style="width: 65%"></div>
                </div>
            </form>
                <!--edit-->
                
        </div>
    </div>
</div>


<%@include file="./template/footer.jsp" %>