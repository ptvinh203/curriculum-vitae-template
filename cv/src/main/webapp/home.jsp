<%@page import="model.dto.UserDTO"%>
<%@page import="model.dto.CVDTO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DMV - Home</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body class="h-screen">
    <jsp:include page="navbar.jsp" />
    <% UserDTO user = (UserDTO) session.getAttribute("current_user"); %>
    <!-- <div
      id="navbar"
      class="h-20 sticky top-0 justify-between flex items-center px-16"
    >
      <div class="flex items-center gap-20">
        <a
          href="/cv"
          id="logo"
          class="font-bold text-4xl font-sans items-center justify-center hover:drop-shadow transition-all flex"
        >
          <div class="text-[#4FC0D0]">D</div>
          <div class="text-[#1B6B93]">M</div>
          <div class="text-[#164B60]">V</div>
        </a>
      </div>
      <div id="navigation" class="w-5/6 max-h-full flex justify-end">
        <div
          id="navbar-item"
          class="w-40 flex items-center justify-center text-xl text-[#4FC0D0] font-semibold"
        >
          <a href="">Create CV</a>
        </div>
        <div
          id="navbar-item"
          class="w-40 flex items-center justify-center text-xl text-[#4FC0D0] font-semibold"
        >
          <a href="profile">My CV</a>
        </div>
      </div>
    </div> -->
    <div id="botton-navbar" class="h-[calc(100%-5rem)] flex flex-1 flex-row">
      <div id="sidebar" class="h-full w-72 flex-col flex gap-10 px-10 py-20">
        <div id="sidebar-item-name">Basic info</div>
        <a
          href="#basic-info"
          id="sidebar-item"
          class="text-xl text-slate-500 font-semibold hover:text-[#4FC0D0] transition-all flex items-center gap-5 justify-start"
        >
          <i class="fa fa-user w-1/4 justify-center flex"></i>
          <div id="sidebar-item-name">Basic info</div>
        </a>
      </div>
      <div
        id="content-container"
        class="w-[calc(100%-18rem)] h-full bg-gray-400 rounded-t-3xl bg-clip-padding backdrop-filter backdrop-blur-sm bg-opacity-10 border border-gray-100 mb-5 mr-5 p-10 overflow-y-auto scroll-smooth"
      >
        <div id="welcome-title" class="text-3xl font-semibold text-slate-500 mb-5">
            Welcome to your profile
        </div>
        <div id="welcome-name" class="text-4xl font-bold text-[#164B60]">
          <%=user.getFirstname()%> <%=user.getLastname()%>
        </div>
        <div id="user-id" class="text-slate-400">
            ID: <%=user.getUserid().toString()%>
        </div>
        <div id="row" class="flex gap-5">
            <div id="profile-info container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit min-h-[50vh] p-10 mt-10">
                <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                    Profile information
                </div>
                <div class="flex gap-10">
                    <img src="static/blank-avatar.jpg" alt="" width="300" height="300">
                    <div class="flex-wrap flex flex-col gap-5 justify-center">
                        <div id="field" class="flex gap-5 items-center">
                            <div id="label" class="font-bold text-lg text-[#164B60]">
                                Username:
                            </div>
                            <div id="value" class="text-lg text-slate-600 font-medium">
                                <%=user.getUsername()%>
                            </div>
                        </div>
                        <div id="field" class="flex gap-5 items-center">
                            <div id="label" class="font-bold text-lg text-[#164B60]">
                                Firstname:
                            </div>
                            <div id="value" class="text-lg text-slate-600 font-medium">
                                <%=user.getFirstname()%>
                            </div>
                        </div>
                        <div id="field" class="flex gap-5 items-center">
                            <div id="label" class="font-bold text-lg text-[#164B60]">
                                Lastname:
                            </div>
                            <div id="value" class="text-lg text-slate-600 font-medium">
                                <%=user.getLastname()%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="statistic-info container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit min-h-[50vh] p-10 mt-10">
                <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                    Statistic
                </div>
                <div class="flex gap-10">
                    <div class="flex-wrap flex flex-col gap-5 justify-center">
                        <div id="field" class="flex gap-5 items-center">
                            <div id="label" class="font-bold text-lg text-[#164B60]">
                                You have
                            </div>
                            <div id="label" class="font-bold text-lg text-slate-800">
                                <%=user.getCvs().size()%>
                            </div>
                            <div id="label" class="font-bold text-lg text-[#164B60]">
                                CV
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="cv-info container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit p-10 mt-10">
            <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                Your CV
            </div>
            <% if(user.getCvs().size() < 1) { %>
            <div class="flex gap-10 italic text-lg text-slate-800">
                It seems that you haven't created any CV yet.
            </div>
            <a href="cv/create" class="flex gap-10 font-semibold text-[#1B6B93] text-lg">
                Create one
            </a>
            <% } else { %>
              <a href="cv/create" class="flex gap-10 font-semibold text-[#1B6B93] text-lg">
                Create one
              </a>
              <div id="cvs-container" class="flex flex-col gap-5">
                <% for(CVDTO cv : user.getCvs()) { 
                  String name = cv.getCvName();
                  String uuid = cv.getCvId().toString();
                  request.setAttribute("cvName", name);
                  request.setAttribute("cvUUID", uuid);
                %>
                  <jsp:include page="home-cv.jsp" />
                <% } %>
              </div>
            <% } %>
        </div>
      </div>
    </div>
  </body>
</html>
