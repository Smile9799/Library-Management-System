<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="shared/nav.jsp"><c:param name="title" value="Add Book"></c:param></c:import>
<div class="container mt-5">
<div class="form-group">
<form action="${pageContext.request.contextPath }/Admin?action=add" method="post" enctype="multipart/form-data" >
    <label for="exampleInputEmail1">Enter ISBN</label>
    <input type="text" class="form-control" name="bookISBN"  required="required">
    <br>
    <label for="exampleInputEmail1">Enter Book Name</label>
    <input type="text" class="form-control" name="bookName" placeholder="Enter Book Name" required="required">
    <br>
    <label for="exampleInputEmail1">Enter Author Name</label>
    <input type="text" class="form-control" name="authorName" placeholder="Enter Author Name" required="required">
    <br>
    <div class="input-group mb-3">
		  <span class="input-group-text">R</span>
		  <span class="input-group-text">Book Price</span>
		  <input type="number" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" required="required" min=0 name="bookPrice">
	</div>
	<br>
	<div class="input-group">
		  <span class="input-group-text">Book Description</span>
		  <textarea class="form-control" aria-label="With textarea" required="required" name="bookDescription"></textarea>
	</div>
	<br>
	<div class="input-group mb-3">
    	<label class="input-group-text" for="inputGroupFile01">Book Image</label>
  		<input type="file" class="form-control" id="inputGroupFile01" required="required" name="bookImage">
  	</div>
 
  <button type="submit" class="btn btn-primary">Add Book</button>
</form>
</div>
</div>
</body>
</html>