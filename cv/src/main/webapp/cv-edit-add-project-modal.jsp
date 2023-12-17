<%@page import="model.dto.CVDTO"%>
<% CVDTO cv = (CVDTO) request.getAttribute("cv"); %>
<script>
    function close_project_modal() {
        document.getElementById("project-modal").style.display = "none";
    }
</script>
<div id="project-modal" class="hidden w-full h-screen fixed top-0 left-0 z-10 bg-gray-600 bg-opacity-50 p-32 flex justify-center">
    <form action="/cv/api/project" method="POST" class="rounded-xl bg-white shadow-xl h-fit w-1/2">
        <input name="cvid" value="<%=cv.getCvId().toString()%>" type="text" hidden>
        <div id="title" class="text-xl font-semibold p-3">
            Add Project
        </div>
        <hr>
        <div id="form-content" class="p-5">
            <div id="field" class="flex gap-5 items-center">
                <label for="projectName" class="text-lg">Project name:</label>
                <input type="text" class="text-lg border-2 border-slate-800 rounded p-1" name="projectName" placeholder="Project name">
            </div>
            <div id="field" class="flex gap-5 items-center">
                <label for="projectTime" class="text-lg">Time:</label>
                <input type="datetime-local" class="text-lg border-2 border-slate-800 rounded p-1" name="projectTime">
            </div>
            <div id="field" class="flex gap-5 items-center">
                <label for="projectNMembers" class="text-lg">Number of members:</label>
                <input type="number" class="text-lg border-2 border-slate-800 rounded p-1" name="projectNMembers">
            </div>
            <div id="field" class="flex gap-5 items-center">
                <label for="projectGithub" class="text-lg">Github link:</label>
                <input type="text" class="text-lg border-2 border-slate-800 rounded p-1" name="projectGithub">
            </div>
            <div id="button-group" class="flex justify-between mt-5 px-10">
                <button type="button" class="w-1/6 rounded-xl border-2 border-slate-800 hover:bg-[#1B6B93] hover:text-white" onclick="close_project_modal()">
                    Cancel
                </button>
                <button class="w-1/6 rounded-xl border-2 border-slate-800 hover:bg-[#1B6B93] hover:text-white">
                    Add
                </button>
            </div>
        </div>
    </form>
</div>