<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
    </header>
    <main>
        <h2>Overzicht van de gerechten</h2>
        <c:choose>
        <c:when test="${dishes.size() != 0}">
        <table>
            <tr>
                <th>Naam</th>
                <th>Prijs</th>
                    </tr>
                    <c:forEach var ="dish" items = "${dishes}" >
                        <tr>
                            <td><c:out value="${dish.name}"/></td>
                            <td><c:out value="${dish.price}"/></td>
                            <c:url value="/gerechten/delete" var="deleteURL">
                                <c:param name="name" value="${dish.name}"/>
                            </c:url>
                            <c:url value="/gerechten/change" var="updateURL">
                                <c:param name="name" value="${dish.name}"/>
                            </c:url>
                            <td><a href="${updateURL}">update</a></td>
                            <td><a href="${deleteURL}">verwijder</a></td>
                        </tr>
                    </c:forEach>
        </table>
        </c:when>
            <c:otherwise>
                <p> Er staan geen gerechten op het menu. </p>
            </c:otherwise>
        </c:choose>
        <form action="gerechten/add" method="get">
            <input type="submit" value="voeg gerecht toe">
        </form>
    </main>
    <footer>
        kwinten
    </footer>
</div>
</body>
</html>