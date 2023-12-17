<%@page import="model.dto.ProjectDTO"%>
<%@page import="model.bean.ProjectDescription"%>
<% 
    ProjectDTO project = (ProjectDTO) request.getAttribute("project");
%>
<div id="education-item" class="flex flex-col gap-5 bg-slate-100 p-5 rounded-lg hover:shadow-lg transition-all">
    <div id="project-name" class="font-bold text-slate-700 text-xl">
        <%=project.getProjectName()%>
    </div>
    <a href="<%=project.getGithub()%>" target="_blank" class="text-slate-600 font-semibold">
        <i class="fa-brands fa-github"></i> <%=project.getGithub()%>
    </a>
    <div id="member-number" class="text-slate-700">
        Made by <%=project.getNumOfMember()%> members
    </div>
    <div id="descriptions" class="flex flex-col gap-5">
        <% for(ProjectDescription des : project.getDescriptions()) { %>
            <div>
                Description
            </div>
        <% } %>
    </div>
    <div id="project-time" class="text-slate-700 italic">
        <%=project.getTime()%>
    </div>
</div>