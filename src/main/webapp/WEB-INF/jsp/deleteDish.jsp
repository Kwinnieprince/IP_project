<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
        <h2>
            Delete dish
        </h2>
    </header>
    <main>
        <h2>Delete <c:out value="${dish.name}"/></h2>
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <p>
            Bent u zeker dat u ${dish.name} wilt verwijderen?
        </p>
        <div style="display: flex;">
            <form method="post" action="/gerechten/delete" novalidate="novalidate">
                <p>
                    <input type="hidden" name="name" value="${dish.name}">
                    <input type="hidden" name="id" value="${dish.id}">
                    <input type="hidden" name="price" value="${dish.price}">
                    <input type="hidden" name="type" value="${dish.type}">
                    <input type="submit" id="delete" value="Ja">
                </p>
            </form>
            <br>
            <form action="/" method="get">
                <p>
                    <input type="submit" id="no" value="Nee">
                </p>
            </form>
        </div>
    </main>
    <footer>
        kwinten
    </footer>
</div>
</body>
</html>