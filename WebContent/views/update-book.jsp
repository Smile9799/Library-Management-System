<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="shared/nav.jsp"><c:param name="title" value="Add Book"></c:param></c:import>
<div class="container mt-5">
<div class="text-center">
  <img src="data:image/jpg;base64,<%=request.getAttribute("imgBase64Str").toString() %>" class="rounded">
</div>
<p><%request.getAttribute("book"); %></p>
<form action="${pageContext.request.contextPath }/Admin?action=update" method="post" enctype="multipart/form-data">
<div class="form-group">
	<input type="hidden" name="hiddenBase64Str" value="<%=request.getAttribute("imgBase64Str").toString() %>"/>
	<input type="hidden" name="id" value="<%=request.getAttribute("id").toString() %>"/>
    <label for="exampleInputEmail1">Enter ISBN</label>
    <input type="text" class="form-control" name="bookISBN" placeholder="Enter ISBN" required="required" value="<%=request.getAttribute("ISBN").toString() %>">
    <br>
    <label for="exampleInputEmail1">Enter Book Name</label>
    <input type="text" class="form-control" name="bookName" placeholder="Enter Book Name" required="required" value="<%=request.getAttribute("name").toString() %>">
    <br>
    <label for="exampleInputEmail1">Enter Author Name</label>
    <input type="text" class="form-control" name="authorName" placeholder="Enter Author Name" required="required" value="<%=request.getAttribute("author").toString() %>">
    <br>
    <div class="input-group mb-3">
		  <span class="input-group-text">R</span>
		  <span class="input-group-text">Book Price</span>
		  <input type="number" class="form-control" name="bookPrice" aria-label="Dollar amount (with dot and two decimal places)" required="required" min=0 value="<%=request.getAttribute("price").toString() %>">
	</div>
	<br>
	<div class="input-group">
		  <span class="input-group-text">Book Description</span>
		  <textarea class="form-control" name="bookDescription" aria-label="With textarea" required="required"><%=request.getAttribute("description").toString() %></textarea>
	</div>
	<br>
	<div class="input-group mb-3">
    	<label class="input-group-text" for="inputGroupFile01">Book Image</label>
  		<input type="file" class="form-control" name="bookImage" id="inputGroupFile01">
  	</div>
  </div>
  <button type="submit" class="btn btn-primary">Update Book</button>
</form>
</div>
</body>
</html>