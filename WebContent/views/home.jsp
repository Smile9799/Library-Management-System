<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="shared/nav.jsp"><c:param name="title" value="Home"></c:param></c:import>
<main>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Album example</h1>
        <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>
        <p>
          <a href="#" class="btn btn-primary my-2">Main call to action</a>
          <a href="#" class="btn btn-secondary my-2">Secondary action</a>
        </p>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-light">
    <div class="container">

      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <c:forEach var="book" items="${books}">
      	<div class="col">
          <div class="card shadow-sm">
			<img src="data:image/jpg;base64,${book.imageBase64String}" class="bd-placeholder-img card-img-top" style="width: 100%; height: 255px;object-fit:cover">
            <div class="card-body">
              <p class="card-text">${book.bookName }</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                	<a href="${pageContext.request.contextPath }/Default?page=item&ISBN=${book.ISBN}" class="btn btn-sm btn-outline-secondary">View</a>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Add To Cart</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
    </div>
  </div>
</main>
</body>
</html>