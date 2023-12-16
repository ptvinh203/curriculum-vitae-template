<%@page import="model.bean.Education"%>
<% 
    Education edu = (Education) request.getAttribute("edu");
%>
<div id="education-item" class="flex flex-col gap-5 bg-slate-100 p-5 rounded-lg hover:shadow-lg transition-all">
    <div id="education-time" class="font-bold text-slate-700 text-xl">
        <%=edu.getTime()%>
    </div>
    <div id="education-name" class="text-slate-700 text-xl">
        <%=edu.getEducationName()%>
    </div>
</div>