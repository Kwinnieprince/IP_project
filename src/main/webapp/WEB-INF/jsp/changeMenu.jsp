<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
        <h2>
            Verander dagmenu
        </h2>
        <h3>
            <!--TODO-->
            <c:out value="${dateDay}"/> <c:out value="${date}"/>
        </h3>
    </header>
    <main>
        <h2>update <c:out value="${dish.name}"/></h2> a
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <form method="post" action="/dagmenu/add"> <!--TODO-->
            <p><label for="soup">Soep</label>
                <select name="soup" id="soup">
                    <c:forEach var="soup" items="${soups}">
                        <option value="${soup}"><c:out value="${soup}"/></option>
                    </c:forEach>
                </select>
            </p>
            <p><label for="dish">Dagschotel</label>
                <select name="dish" id="dish">
                    <c:forEach var="dish" items="${dayDishes}">
                        <option value="${dish}"><c:out value="${dish}"/></option>
                    </c:forEach>
                </select>
            </p>
            <p><label for="type">type</label><select name="type" id="type" required>
                <c:forEach items="${values}" var="value">
                    <option value="${value}">${value}</option>
                </c:forEach>
            </select></p>
            <p><input type="submit" id="addDayMenu" value="Voeg dagmenu toe"></p>

        </form>

    </main>
    <footer>
        &copy; kwinten
    </footer>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>