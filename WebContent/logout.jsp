<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/materialize.min.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
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

<%
HttpSession s = request.getSession(false);
if (s != null) {
    s.invalidate();
}
%>
<div class="container">
<div class="col s6">
<div style="text-align:center">
<div class='row'>
<br/>
<br/>
<a class='btn btn-large custom-btn blue-grey lighten-2' href="index.html">Go again!</a>
</div>
</div>
</div>
</div>
</body>
</html>