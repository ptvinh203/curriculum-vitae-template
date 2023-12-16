<script>
    function close_this_modal() {
        document.getElementById("skill-modal").style.display = "none";
    }
</script>
<div id="skill-modal" class="w-full h-screen fixed top-0 left-0 z-10 bg-gray-600 bg-opacity-50 p-32 flex justify-center">
    <form action="" method="POST" class="rounded-xl bg-white shadow-xl h-fit w-1/2">
        <div id="title" class="text-xl font-semibold p-3">
            Add Skill
        </div>
        <hr>
        <div id="form-content" class="p-5">
            <div id="field" class="flex gap-5 items-center">
                <label for="skillName" class="text-lg">Name:</label>
                <input type="text" class="text-lg border-2 border-slate-800 rounded p-1" name="skillName" placeholder="Skill name">
            </div>
            <div id="button-group" class="flex justify-between mt-5 px-10">
                <button type="button" class="w-1/6 rounded-xl border-2 border-slate-800 hover:bg-[#1B6B93] hover:text-white" onclick="close_this_modal()">
                    Cancel
                </button>
                <button type="button" class="w-1/6 rounded-xl border-2 border-slate-800 hover:bg-[#1B6B93] hover:text-white">
                    Add
                </button>
            </div>
        </div>
    </form>
</div>