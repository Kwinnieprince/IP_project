<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
        <h2>
            Error
        </h2>
    </header>
    <main>
        <h3>Oops something went wrong!</h3>
        <p><a href="/home">go back to home</a></p>
        <p><c:out value="${pageContext.exception.message}" /> </p>
        <p>Something unexpected happened please try again and if the problem persists please contact us at <a href="mailto:noreply@ucll.be">support@ucll.be</a></p>
    </main>
</div>
</body>
</html>