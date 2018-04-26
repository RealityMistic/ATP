<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.Player, 
    										AudioTrainerPlay.TopTenPointsController,
    										java.io.PrintWriter,
    										java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/materialize.min.css" rel="stylesheet" type="text/css">
<title>Hall-of-fame</title>
</head>
<body>
<nav>
<div class='nav-wrapper deep-purple blue accent-1'>
<a href="http://atlas.uned.es/" class='brand-logo left'>

<img src='images/logos/ATLASlogo-s.png'>

</a>
<a href="http://www.uned.es/" class='brand-logo center'>

<img src='images/logos/logo_uned.png'>

</a>
<a href="http://www.etsisi.upm.es/" class='brand-logo right'>
<img src='images/logos/ETSISIlogo.png'>
</a>

</div>
</nav>
    
<div class="container">
<div class="col s6">

 <div class="row">
<h1>Hall-of-fame</h1>
</div>
<div class="row">
<h2>Top 10 Explorers</h2>
</div>
<%
HttpSession s=request.getSession();
String path = (String) s.getAttribute("path");
TopTenPointsController ttpc = new TopTenPointsController(path);
response.setContentType("text/html");
PrintWriter dd=response.getWriter();
String topentry="<div class='container col s6'><table class='striped centered responsive-table'>"
				+"<thead><th data-field='User'>User's name</th><th data-field='Points'>Total Points</th>"
				+"</thead><tbody>";
ArrayList<Player> topplayers = ttpc.getTopTenPoints();

for (Player player : topplayers){
	topentry +="<tr><td>"+ player.getUser()+"</td><td>"+player.getExplopoints()+ "</td></tr>";
	//dd.println(player.getUser()+" --- "+player.getExplopoints());
}
topentry += "</tbody></table></div>";
%>
<%= topentry %>
<!-- 
<div align="right"><h2>Top 10 Explorers</h2></div>
 -->
 <div class='row' style="text-align:center">
<form action="main-menu.html">
    <input type="submit" class="btn btn-large btn-floating red" value="Menu"> 
</form>
</div>
</div>
</div>

</div>
</body>
</html>