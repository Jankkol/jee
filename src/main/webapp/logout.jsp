<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
</head>
<body>
  <p>
  <a href="login.jsp">Login again.</a>
  </p>
</body>
</html>
<%
    session.invalidate();
%>