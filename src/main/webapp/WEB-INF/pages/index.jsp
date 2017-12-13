<%@ page language="java" contentType="text/html; charset=utf8; application/json" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
  Created by IntelliJ IDEA.
  User: Yevhen Pohiba
  Date: 08.12.2017
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">
  <head>
    <title>Hello, Market!</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="application/json; text/html; charset=UTF-8" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
  </head>
  <body>
  <div class="container-fluid">
    <div class="alert alert-light" role="alert">
      <h1>Products Market</h1>
    </div>
    <div class="row">
      <div class="col-md-3">
        <form action="/" method="get">
          <div class="clearfix">
            <button type="submit" class="btn btn-secondary float-right">Clean all</button>
            <h3>Filters</h3>
          </div>
        </form>
        <form action="/getFilteredProducts" modelAttribute="product" modelattribute="minPrice" modelattribute="maxPrice" method="get">
          <div class="form-group">
            <label>Select Category:</label>
            <select class="form-control form-control-lg" id="idCategory" name="idCategory" title="Select a product category" onchange="">
              <option value="0">Все</option>
              <jsp:useBean id="categoriesList" scope="request" type="java.util.List"/>
              <c:forEach var="category" items="${categoriesList}">
                <option value=${category.id}>${category.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label>Select Manufacturer:</label>
            <select class="form-control form-control-lg" id="idManufacturer" name="idManufacturer" title="Selection of the manufacturer of products">
              <option value="0">Все</option>
              <jsp:useBean id="manufacturerList" scope="request" type="java.util.List"/>
              <c:forEach var="manufacturer" items="${manufacturerList}">
                <option value=${manufacturer.id}>${manufacturer.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label>Select Price Range:</label>
            <div class="row">
              <div class="col-md-6">
                <div class="input-group">
                  <span class="input-group-addon">$</span>
                  <input value=${minPrice} name="minPrice" type="text" class="form-control">
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group">
                  <span class="input-group-addon">$</span>
                  <input value=${maxPrice} name="maxPrice" type="text" class="form-control">
                </div>
              </div>
            </div>
          </div>
          <div class="form-check">
            <label class="form-check-label">
              <input name="instock" type="checkbox" class="form-check-input" <jsp:useBean id="instock" scope="request" type="java.lang.Boolean"/>
              <c:if test="${instock}"> checked</c:if>>
              <strong>In Stock</strong>
            </label>
          </div>
          <button type="submit" class="btn btn-primary float-right">Apply filters</button>
        </form>
      </div>
      <div class="col-md-9">
        <h3>Directory Manager</h3>
        <ul class="list-group">
          <li class="list-group-item">
            <div class="row py-lg-2">
              <div class="col-md-8"><h5><span class="badge badge-info">New Product</span></h5></div>
              <div class="col-md-4">
                <button class="btn btn-outline-info float-right"
                        aria-controls="collapseForNew"
                        href="#collapseForNew"
                        data-toggle="collapse"
                        aria-expanded="true">+</button>
              </div>
            </div>
            <div class="collapse" id="collapseForNew">
              <form:form class="card card-body" action="${pageContext.request.contextPath}/newProduct" modelAttribute="product" method="post">
                <div class="row">
                  <div class="form-group col-md-3">
                    <label>Name</label>
                    <input name="name" class="form-control" />
                  </div>
                  <div class="form-group col-md-4">
                    <label>Description</label>
                    <input name="description" class="form-control" />
                  </div>
                  <div class="form-group col-md-3">
                    <label>Price</label>
                    <input name="price" class="form-control" />
                  </div>
                </div>
                <div class="row">
                  <div class="form-group col-md-3">
                    <label>Category</label>
                    <select name="idCategory" id="idNewCategory" class="form-control form-control-lg">
                      <c:forEach var="category" items="${categoriesList}">
                        <option value=${category.id}>${category.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group col-md-3">
                    <label>Manufacturer</label>
                    <select name="idManufacturer" id="idNewManufacturer" class="form-control form-control-lg">
                      <c:forEach var="manufacturer" items="${manufacturerList}">
                        <option value=${manufacturer.id}>${manufacturer.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-check col-md-2">
                    <label class="form-check-label py-lg-5">
                      <input name="instock" type="checkbox" class="form-check-input" />
                      <strong>In Stock</strong>
                    </label>
                  </div>
                </div>
                <div class="clearfix"><button type="submit" class="btn btn-primary float-right">Add</button></div>
              </form:form>
            </div>
          </li>
          <li class="list-group-item">
            <div class="row py-lg-2">
              <div class="col-md-8"><h5><span class="badge badge-info">New Category</span></h5></div>
              <div class="col-md-4">
                <button class="btn btn-outline-info float-right"
                        aria-controls="collapseForNewCategory"
                        href="#collapseForNewCategory"
                        data-toggle="collapse"
                        aria-expanded="true">+</button>
              </div>
            </div>
            <div class="collapse" id="collapseForNewCategory">
              <form:form class="card card-body" action="${pageContext.request.contextPath}/newCategory" modelAttribute="category" method="post">
                <div class="row">
                  <div class="form-group col-md-3">
                    <label>Name</label>
                    <input name="name" class="form-control" />
                  </div>
                </div>
                <div class="clearfix"><button type="submit" class="btn btn-primary float-right">Add</button></div>
              </form:form>
            </div>
          </li>
          <li class="list-group-item">
            <div class="row py-lg-2">
              <div class="col-md-8"><h5><span class="badge badge-info">New Manufacturer</span></h5></div>
              <div class="col-md-4">
                <button class="btn btn-outline-info float-right"
                        aria-controls="collapseForNewManufacturer"
                        href="#collapseForNewManufacturer"
                        data-toggle="collapse"
                        aria-expanded="true">+</button>
              </div>
            </div>
            <div class="collapse" id="collapseForNewManufacturer">
              <form:form class="card card-body" action="${pageContext.request.contextPath}/newManufacturer" modelAttribute="Manufacturer" method="post">
                <div class="row">
                  <div class="form-group col-md-3">
                    <label>Name</label>
                    <input name="name" class="form-control" />
                  </div>
                </div>
                <div class="clearfix"><button type="submit" class="btn btn-primary float-right">Add</button></div>
              </form:form>
            </div>
          </li>
          <h3>Products</h3>
          <jsp:useBean id="productsList" scope="request" type="java.util.List"/>
          <c:forEach var="product" items="${productsList}">
            <li class="list-group-item">
              <div class="row py-lg-2">
                <div class="col-md-10">
                  <h5>
                    Product: <span class="badge badge-info">${product.name}</span>
                    Price: <span class="badge badge-info">${product.price}</span>
                  </h5>
                </div>
                <div class="col-md-2">
                  <button class="btn btn-outline-info float-right"
                        aria-controls="collapseFor${product.id}"
                        href="#collapseFor${product.id}"
                        data-toggle="collapse"
                        aria-expanded="true">+</button>
                </div>
              </div>
              <div class="collapse" id="collapseFor${product.id}">
                <form class="card card-body" action="${pageContext.request.contextPath}/changeProduct/${product.id}" modelAttribute="idButton" modelAttribute="product" method="post">
                  <div class="row">
                    <div class="form-group col-md-3">
                      <label>Name</label>
                      <input class="form-control" name="name" value="${product.name}"/>
                    </div>
                    <div class="form-group col-md-4">
                      <label>Description</label>
                      <input class="form-control" name="description" value="${product.description}"/>
                    </div>
                    <div class="form-group col-md-3">
                      <label>Price</label>
                      <input class="form-control" name="price" value="${product.price}"/>
                    </div>
                  </div>
                  <div class="row">
                    <div class="form-group col-md-3">
                      <label>Category</label>
                      <select id="idCategoryFor${product.id}" name="idCategory" class="form-control form-control-lg">
                        <c:forEach var="category" items="${categoriesList}">
                          <option value=${category.id}>${category.name}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="form-group col-md-3">
                      <label>Manufacturer</label>
                      <select id="idManufacturerFor${product.id}" name="idManufacturer" class="form-control form-control-lg">
                        <c:forEach var="manufacturer" items="${manufacturerList}">
                          <option value=${manufacturer.id}>${manufacturer.name}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="form-check col-md-2">
                      <label class="form-check-label py-lg-5">
                        <input name="instock" type="checkbox" class="form-check-input" <c:if test="${product.instock}"> checked</c:if> />
                        <strong>In Stock</strong>
                      </label>
                    </div>
                  </div>

                  <div class="clearfix">
                    <button name="" id="idUpdate" value="Update" type="submit" class="btn btn-primary float-right" onclick="setNameButton(this)">Update</button>
                    <button name="" id="idDelete" value="Delete" type="submit" class="btn-danger float-left" onclick="setNameButton(this)">Delete</button>
                  </div>
                </form>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script type="text/javascript">

      function setFieldById(idSelect, id)
      {
        for(var i=0; i < idSelect.options.length; i++)
        {
          if(idSelect.options[i].value === id.toString())
          {
            idSelect.selectedIndex = i;
            return;
          }
        }
      }

      function setNameButton(button){
        button.name = "idButton";
      }

      function forProductDetails(){
        <c:forEach var="product" items="${productsList}">
          setFieldById(document.getElementById('idCategoryFor${product.id}'), ${product.idCategory});
          setFieldById(document.getElementById('idManufacturerFor${product.id}'), ${product.idManufacturer});
        </c:forEach>
      }

      setFieldById(document.getElementById('idCategory'), ${filterIdCategory});
      setFieldById(document.getElementById('idManufacturer'), ${filterIdManufacturer});
      forProductDetails();

    </script>
  </body>
</html>
