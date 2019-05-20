<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
        <h2>
            Update dish
        </h2>
    </header>
    <main>
        <h2>update <c:out value="${dish.name}"/></h2>
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <form method="post" action="/gerechten/change">
            <p style="display: flex"><label for="name">Name</label><input type="text" id="name" name="name" required readonly value="${dish.name}"> </p>
            <p style="display: flex"><label for="price">prijs</label><input type="text" id="price" name="price" required value="${dish.price}">&euro;</p>
            <p style="display: flex"><label for="type">type</label>
                <select name="type" id="type" required>
                    <option value="Soep" ${dish.type eq "Soep" ? 'selected' : ''}>Soep</option>
                    <option value="Dagschotel" ${dish.type eq "Dagschotel" ? 'selected' : ''}>Dagschotel</option>
                    <option value="Veggie" ${dish.type eq "Veggie" ? 'selected' : ''}>Veggie</option>
                </select>
            </p>
            <input type="hidden" name="id" value="${dish.id}">
            <p style="display: flex"><input type="submit" id="update" value="update product"></p>
        </form>
    </main>
    <footer>
        kwinten
    </footer>
</div>
</body>
</html>