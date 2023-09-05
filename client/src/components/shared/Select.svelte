
<script>
    let className = '';
    export let id = "";
    export let value = "";
    export { className as class };
</script>

<select class={`w-full dark:bg-regColor bg-violet-700 text-white rounded py-3 px-1 border-none focus\:outline-none ${className}`} id={id} bind:value={value}>
    <slot />
</select>