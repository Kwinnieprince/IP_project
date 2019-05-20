<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
        <h2>
            Voeg gerecht toe
        </h2>
    </header>
    <main>
        <c:if test="${not empty dish.name}">
            <h2>update <c:out value="${dish.name}"/></h2>
        </c:if>
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <form method="post" action="/gerechten/add">
            <p style="display: flex"><label style="padding-right: 5px" for="name">Naam</label><input type="text" id="name" name="name" required > </p>
            <p style="display: flex"><label style="padding-right: 5px" for="price">Prijs</label><input type="text" id="price" name="price" required ></p>
            <p style="display: flex"><label style="padding-right: 5px" for="type">Type</label>
                <select name="type" id="type" required>
                    <option value="">Kies type</option>
                    <c:forEach items="${values}" var="value">
                        <option value="${value}">${value}</option>
                    </c:forEach>
                </select>
            </p>
            <p><input type="submit" id="update" value="Add"></p>
        </form>
        <form action="/home" method="get">
            <input type="submit" value="cancel">
        </form>

    </main>
    <footer>
        kwinten
    </footer>
</div>
</body>
</html>