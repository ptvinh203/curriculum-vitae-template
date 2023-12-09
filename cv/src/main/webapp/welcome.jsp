<%@page import="java.util.List"%>
<%@page import="model.dto.WifeDTO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
        </div>
    </nav>
    <form name="form" class="container d-flex flex-column justify-content-center align-items-center min-vh-100 p-5 mt-5">
        <div class="container-fluid m-1">
            <button type="button" class="btn btn-dark">
                Select all
            </button>
            <button type="button" class="btn btn-dark">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
            <button type="button" class="btn btn-dark">
                <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
        </div>
        <div class="table-responsive rounded shadow-lg h-vh-50 w-vh-50">
            <table class="table table-responsive-xl table-striped table-hover h-vh-5 w-vh-5">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Còn sống</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                        </td>
                        <td>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                        </td>
                        <td>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                        </td>
                        <td>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                        </td>
                        <td></td>
                    </tr>
                    <%List<WifeDTO> wifes = (List<WifeDTO>) request.getAttribute("wifes");%>
                        <% for(int i = 0; i < wifes.size(); i++) { %>
                            <tr>
                                <td>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            <%=wifes.get(i).getId()%>
                                        </label>
                                    </div>
                                </td>
                                <td><%=wifes.get(i).getName()%></td>
                                <td><%=wifes.get(i).getAddress()%></td>
                                <td><%=wifes.get(i).isAlive() ? "Còn" : "Ngủm"%></td>
                                <td>
                                    <button type="button" class="btn btn-dark">
                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-dark">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                        <% } %>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>