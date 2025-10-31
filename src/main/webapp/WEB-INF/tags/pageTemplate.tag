<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<jsp:doBody/>
</body>
</html>