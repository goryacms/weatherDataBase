<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'/>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>${title}</title>
</head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header" style="color: white;">
            Формирование погоды в БД
        </div>
    </div>
</div>

<br><br><br>
<br><br><br>

<div class="container body-content">
    <form role="form" action="${pageContext.request.contextPath}/jms/save" method="POST">
        <button type="submit" class="btn btn-success">Сформировать запись</button>
    </form>

    <hr />
    <footer>
        <p>&copy; <%  Date date = new Date(); out.print( date.toString()); %> </p>
    </footer>
</div>

</body>
</html>