<%@page import="model.dto.CVDTO"%>
<%@page import="model.bean.Skill"%>
<%@page import="model.bean.Education"%>
<%@page import="model.dto.ProjectDTO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body class="h-screen">
    <jsp:include page="navbar.jsp" />
    <% CVDTO cv = (CVDTO) request.getAttribute("cv"); %>
    <div id="botton-navbar" class="h-[calc(100%-5rem)] flex flex-1 flex-row">
        <div id="sidebar" class="h-full w-72 flex-col flex gap-10 px-10 py-20">
            <a href="#basic-info" id="sidebar-item" class="text-xl text-slate-500 font-semibold hover:text-[#4FC0D0] transition-all flex items-center gap-5 justify-start">
                <i class="fa fa-user w-1/4 justify-center flex"></i>
                <div id="sidebar-item-name">
                    Basic info
                </div>
            </a>
            <a href="#skill" id="sidebar-item" class="text-xl text-slate-500 font-semibold hover:text-[#4FC0D0] transition-all flex items-center gap-5 justify-start">
                <i class="fa fa-pen w-1/4 justify-center flex"></i>
                <div id="sidebar-item-name">
                    Skill
                </div>
            </a>
            <a href="#education" id="sidebar-item" class="text-xl text-slate-500 font-semibold hover:text-[#4FC0D0]  transition-all flex items-center gap-5 justify-start">
                <i class="fa fa-graduation-cap w-1/4 justify-center flex"></i>
                <div id="sidebar-item-name">
                    Education
                </div>
            </a>
            <a href="#project" id="sidebar-item" class="text-xl text-slate-500 font-semibold hover:text-[#4FC0D0]  transition-all flex items-center gap-5 justify-start">
                <i class="fa-solid fa-clipboard-list w-1/4 justify-center flex"></i>
                <div id="sidebar-item-name">
                    Project
                </div>
            </a>
        </div>
        <div id="content-container" class="w-[calc(100%-18rem)] h-full bg-gray-400 rounded-t-3xl bg-clip-padding backdrop-filter backdrop-blur-sm bg-opacity-10 border border-gray-100 mb-5 mr-5 p-10 overflow-y-scroll scroll-smooth">
            <div id="content" class="flex-col flex gap-10">
                <div id="cv-name" class="text-4xl font-bold text-[#164B60]">
                    <%=cv.getCvName()%>
                </div>
                <div id="basic-info container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit p-10">
                    <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                        Basic information
                    </div>
                    <div class="flex gap-10">
                        <img
                            src="static/blank-avatar.jpg"
                            alt=""
                            class="aspect-square w-1/4"
                        />
                        <div class="flex-wrap flex flex-col gap-5 justify-center w-1/3">
                            <div id="field" class="flex gap-5 items-center">
                                <div id="label" class="font-bold text-lg text-[#164B60]">
                                    Fullname:
                                </div>
                                <div id="value" class="text-lg text-slate-600 font-medium">
                                    <%=cv.getBasicInfo().getName()%>
                                </div>
                            </div>
                            <div id="field" class="flex gap-5 items-center">
                                <div id="label" class="font-bold text-lg text-[#164B60]">
                                    Email:
                                </div>
                                <div id="value" class="text-lg text-slate-600 font-medium">
                                    <%=cv.getBasicInfo().getEmail()%>
                                </div>
                            </div>
                            <div id="field" class="flex gap-5 items-center">
                                <div id="label" class="font-bold text-lg text-[#164B60]">
                                    Phone number:
                                </div>
                                <div id="value" class="text-lg text-slate-600 font-medium">
                                    <%=cv.getBasicInfo().getPhone()%>
                                </div>
                            </div>
                            <div id="field" class="flex gap-5 items-center">
                                <div id="label" class="font-bold text-lg text-[#164B60]">
                                    Address:
                                </div>
                                <div id="value" class="text-lg text-slate-600 font-medium">
                                    <%=cv.getBasicInfo().getAddress()%>
                                </div>
                            </div>
                        </div>

                        <div class="w-1/3 flex flex-col justify-between">
                            <div id="about-me">
                                <div id="title" class="text-xl font-semibold text-slate-800">
                                    About me
                                </div>
                                <p class="italic text-slate-600">
                                    <%=cv.getBasicInfo().getAboutMe()%>
                                </p>
                            </div>
                            <div id="refrence-links-container">
                                <div id="github" class="flex gap-5 items-center">
                                    <div id="github-label" class="text-lg font-semibold text-[#6e5494]">
                                        <i class="fa-brands fa-github"></i>
                                        Github:
                                    </div>
                                    <a href="<%=cv.getBasicInfo().getGithub()%>" target="_blank" id="github-link" class="text-lg text-slate-600 italic">
                                        <%=cv.getBasicInfo().getGithub()%>
                                    </a>
                                </div>
                                <!-- <div id="facebook" class="flex gap-5 items-center">
                                    <div id="facebook-label" class="text-lg font-semibold text-[#1778F2]">
                                        <i class="fa-brands fa-facebook"></i>
                                        Facebook:
                                    </div>
                                    <a href="https://www.facebook.com/blendminh/" target="_blank" id="github-link" class="text-lg text-slate-600 italic">
                                        https://www.facebook.com/blendminh/
                                    </a>
                                </div> -->
                            </div>
                        </div>
                    </div>
                </div>
                <div id="skill container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit p-10">
                    <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                        Skills
                    </div>
                    <div id="skills-container" class="flex-wrap flex gap-5">
                        <% if(cv.getSkills() == null || cv.getSkills().size() < 1) { %>
                            <div id="no-skill-issue" class="italic text-slate-800 text-lg">
                                This person has skill issue
                            </div>
                        <% } else { %>
                            <% for(Skill skill : cv.getSkills()) { 
                                request.setAttribute("skill", skill);
                            %>
                                <jsp:include page="cv-skill-slot.jsp" />
                            <% } %>
                        <% } %>
                    </div>
                </div>
                <div id="education container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit p-10">
                    <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                        Educations
                    </div>
                    <div id="educations-container" class="flex flex-col gap-5">
                        <% if(cv.getEducations() == null || cv.getEducations().size() < 1) { %>
                            <div id="no-skill-issue" class="italic text-slate-800 text-lg">
                                Never learn
                            </div>
                        <% } else { %>
                            <% for(Education edu : cv.getEducations()) { 
                                request.setAttribute("edu", edu);
                            %>
                                <jsp:include page="cv-education-slot.jsp" />
                            <% } %>
                        <% } %>
                    </div>
                </div>
                <div id="project container" class="bg-white rounded-2xl shadow-lg hover:shadow-xl transition-all w-full h-fit p-10">
                    <div id="title" class="text-2xl font-bold text-[#1B6B93] mb-10">
                        Projects
                    </div>
                    <% if(cv.getProjects() == null || cv.getProjects().size() < 1) { %>
                        <div id="no-skill-issue" class="italic text-slate-800 text-lg">
                            Totally have no contribution
                        </div>
                    <% } else { %>
                        <% for(ProjectDTO project : cv.getProjects()) { 
                            request.setAttribute("project", project);
                        %>
                            <jsp:include page="cv-education-slot.jsp" />
                        <% } %>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>