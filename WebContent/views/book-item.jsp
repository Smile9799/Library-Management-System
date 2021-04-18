<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="shared/nav.jsp"><c:param name="title" value="Home"></c:param></c:import>
<div class="container mt-5">
<div class="text-center">
<img src="data:image/jpg;base64,<%=request.getAttribute("imgBase64Str").toString() %>" class="rounded" style="max-width: 100%;height: auto;">
</div>
<br>
<div class="card">
  <div class="card-body">
    <h5 class="card-title"><strong>Book Name</strong>: <%=request.getAttribute("name").toString() %></h5>
    <h5 class="card-title"><strong>ISBN</strong>: <%=request.getAttribute("ISBN").toString() %></h5>
    <h5 class="card-title"><strong>Author Name</strong>: <%=request.getAttribute("author").toString() %></h5>
    <h5 class="card-title"><strong>Book Price</strong>: R<%=request.getAttribute("price").toString() %></h5>
    <p class="card-text"><strong>Book Description</strong>:<br><%=request.getAttribute("description").toString() %></p>
    <a href="#" class="btn btn-primary">Add To Cart</a>
  </div>
</div>
</div>	
</body>
</html>