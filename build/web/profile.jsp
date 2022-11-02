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
            <div id="profile-content">
                <div class="profile-content-col">
                    <div>Company name: <br/>${customer.getCompanyName()}</div>
                    <div>Contact name: <br/>${customer.getContactName()}</div>

                    <a style="background-color: sienna;
                       color: #fff;
                       line-height: 40px;
                       border-radius: 5px;
                       border: none;
                       cursor: pointer;
                       margin-top: 70px;
                       width: 40%;
                       text-decoration: none;
                       padding: 9px 42px;" href="editProfile">Edit</a>

                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/>${customer.getContactTitle()}</div>
                    <div>Address: <br/>${customer.getAddress()}</div>
                </div>
                <div class="profile-content-col">
                    <div>Email: <br/>${sessionScope.AccSession.getEmail()}</div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="./template/footer.jsp" %>