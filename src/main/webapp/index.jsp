<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'/>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>${title}</title>

    <script>
        function getWeather(){
            var city = document.getElementById("cities").value;
            location.href="${pageContext.request.contextPath}/weather?city=" + city;
        }
    </script>

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
    <button class="btn btn-success" onClick='getWeather();'>Просмотреть запись</button>
    <br><hr /><br><br>
    <select name="cities" id="cities">
        <option >Перечень сохранённых городов</option>
        <c:forEach items="${cities}" var="city">
            <option value="${city}">${city}</option>
        </c:forEach>
    </select>

    <hr />
    <footer>
        <p>&copy; <%  Date date = new Date(); out.print( date.toString()); %> </p>
    </footer>
</div>

</body>
</html>