<% 
    String cvName = (String) request.getAttribute("cvName"); 
    String cvUUID = (String) request.getAttribute("cvUUID"); 
%>
<div id="cv" class="flex p-5 rounded-xl bg-white shadow-xl border-2 border-gray-200 flex flex-row justify-between items-center hover:shadow-2xl transition-all">
  <a href="" id="cv-info" class="flex flex-col items-start">
    <div id="cv-name" class="text-[#1B6B93] font-semibold text-lg"><%=cvName%></div>
    <div id="cv-id" class="italic text-slate-400">ID: <%=cvUUID%></div>
  </a>
  <div id="function-buttons" class="flex gap-5">
    <button class="rounded text-slate-400 border-2 border-slate-400 bg-white hover:border-slate-600 hover:bg-green-500 hover:text-white p-3"><i class="fa-solid fa-pen-to-square"></i></button>
    <button class="rounded text-slate-400 border-2 border-slate-400 bg-white hover:border-slate-600 hover:bg-red-500 hover:text-white p-3"><i class="fa-solid fa-trash"></i></button>
  </div>
</div>