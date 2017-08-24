<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>TGW Token</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value="/resources/js/tgw.js" />"></script>

</head>
<body>


<div id="wrapper">
    <!-- header -->
    <div class="headermain">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="col-lg-6">
                <div class="col-xs-12"><img class="img-responsive" src="<c:url value="/resources/image/logo.svg" />"  style="width:85px;margin-top:8px;"></div>
                

                <!--<p style=""> My Wallet </p>-->

            </div>
            <div class="col-lg-4">
                <!-- <input type="text" placeholder="Search Symbol" style="width:55%;margin: 10px 0px;"/>
                <button class="btn-block">Search</button> -->
            </div>
            <div class="col-lg-2" style="padding-top: 10px;">
                <span>${currentUser.fullname}</span>
                <a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
            </div>
        </div>

    </div>
</div>
<!-- menu -->


<!-- /. leftside  -->
<section style="color:#2d313b; margin-top:5px">
    <div class="leftsidebar">
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse menubar">
                <ul class="nav ">


                    <li>
                        <a href="mywallet">My Wallet </a>
                    </li>


                    <li>
                        <a href="transactions">My Transactions </a>
                    </li>
              		<li>
                        <a href="buytoken">Buy TGW</a>
                    </li>
                    <li class="active">
                        <a href="#">Send TGW</a>
                    </li>
                    <li>
                        <a href="settings">Wallet Settings</a>
                    </li>

                </ul>
            </div>

            
        </nav>

    </div>
    <!-- /. leftside end  -->


    <div id="page-wrapper">
        <!-- <div id="page-inner">  -->
        <!-- /. ROW  -->
        
        <!-- left sidebar  -->
        <div class="col-lg-12">
            <div class="panel panel-default" style="height:600px;">
                <div class="panel-heading">Send TGW</div>
                <div class="panel-body">
                
	                <form class="form-horizontal" role="form" action="sendTokenToUser" method="post" id="sendTokenForm">
							<div class="errorMessageDiv"><script>showErrorMessage('<c:out value='${param.errormsg}'/>')</script></div>
							<div class="successMessageDiv"><script>showSuccessMessage('<c:out value='${param.successmsg}'/>')</script></div>
							<div class="form-group">
								<div class="col-sm-2">
									<span>To Address</span> 
								</div>
								<div class="col-sm-6">
									<input type="text" id="toaddress" name="toaddress"
											placeholder="e.g., 0x1234..." class="form-control" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<span>Amount</span>
								</div>
								<div class='col-sm-6'>
									<input type="number" id="tokenamount" name="tokenamount"
											placeholder="e.g., 100" class="form-control" required>
								</div>

							</div>
							<div class="form-group">
								<div class="col-sm-7">
									
								</div>
								<div class='col-sm-2'>
									<input type="button" value="Send" onclick="sendTokenConfirmation();">
									<!-- <button>Send</button> -->
								</div>
							</div>
						</form>
                </div>
            </div>
        </div>
    </div>

</section>
</body>
</html>