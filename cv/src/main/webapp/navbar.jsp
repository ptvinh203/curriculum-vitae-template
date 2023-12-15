<%@page import="model.dto.UserDTO"%>
<%@page import="controller.util.UserSessionUtil"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<% 
    UserSessionUtil.ensureUser(request);
    UserDTO user = (UserDTO) session.getAttribute("current_user"); 
%>
<div id="navbar" class="h-20 sticky top-0 justify-between flex items-center px-16 bg-white z-10">
    <div class="flex items-center gap-20">
        <a href="/cv" id="logo" class="font-bold text-4xl font-sans items-center justify-center hover:drop-shadow transition-all flex">
            <div class="text-[#4FC0D0] ">
                D
            </div>
            <div class="text-[#1B6B93] ">
                M
            </div>
            <div class="text-[#164B60] ">
                V
            </div>
        </a>
    </div>
    <div id="navigation" class="w-5/6 max-h-full flex justify-end">
        <div id="navbar-item" class="w-52 flex items-center justify-center text-xl text-[#4FC0D0] font-semibold">
            Item 1
        </div>
        <div id="navbar-item" class="w-52 flex items-center justify-center text-xl text-[#4FC0D0] font-semibold">
            Item 2
        </div>
        <div id="navbar-item" class="w-52 flex items-center justify-center text-xl text-[#4FC0D0] font-semibold">
            Item 3
        </div>
        <% if(user == null) { %>
            <a href="/cv/login" class="flex ml-5 justify-center items-center text-slate-500 font-semibold hover:text-[#4FC0D0] transition-all">Login</a>
        <%} else { %>
        <a href="/cv/home" id="navbar-profile" class="flex items-center gap-5">
            <img id="avatar" src="static/blank-avatar.jpg" alt="" height="60" width="60" class="rounded-full border-2 border-[#A2FF86]">
            <div id="navbar-username" class="font-semibold text-lg">
                <%=user.getUsername()%>
            </div>
        </a>
        <a href="/cv/logout" class="flex ml-5 justify-center items-center text-slate-500 font-semibold hover:text-[#4FC0D0] transition-all">Logout</a>
        <% } %>
    </div>
</div>