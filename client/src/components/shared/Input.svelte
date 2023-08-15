<script>
    let className = '';
    export let type = "text";
    export let id = "";
    export let value = "";
    export { className as class };

    function typeAction (node) {
        node.type = type;
    }
</script>

<input use:typeAction class={`w-full rounded-xl bg-regColor text-white py-3 px-1 border-none focus\:outline-none ${className}`} id={id} bind:value={value} />