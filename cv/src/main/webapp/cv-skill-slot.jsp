<%@page import="model.bean.Skill"%>
<% Skill skill = (Skill) request.getAttribute("skill") %>
<div id="skill-item-container" class="bg-slate-100 rounded-lg p-2 w-32 flex items-center justify-center text-lg text-slate-800 font-bold hover:shadow-lg transition-all">
    <%=skill.getSkillName()%>
</div>