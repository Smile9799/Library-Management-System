<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="shared/nav.jsp"><c:param name="title" value="Add Book"></c:param></c:import>
<div class="container">
	<table class="table">
  <thead>
    <tr>
      <th scope="col">ISBN#</th>
      <th scope="col">Book Name</th>
      <th scope="col">Book Author</th>
      <th scope="col">Functions</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="book" items="${books }">
  		<tr>
	      <th scope="row">${book.ISBN}</th>
	      <td>${book.bookName }</td>
	      <td>${book.bookAuthor }</td>
	      <td><a href="${pageContext.request.contextPath }/Admin?action=delete&ISBN=${book.ISBN}" class="btn btn-danger">Remove</a>
	       		<a href="${pageContext.request.contextPath }/Admin?action=update&ISBN=${book.ISBN}" class="btn btn-primary">Update</a></td>
    	</tr>
  	</c:forEach>
  </tbody>
</table>
</div>
</body>
</html>