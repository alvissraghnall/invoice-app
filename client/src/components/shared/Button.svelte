<script>
    let clazz = '';
    export let type = 'button';
    export { clazz as class };
</script>

<button on:click class={`cursor-pointer py-4 px-6 rounded-3xl border-none text-sm mr-2 text-white ${clazz}`} type={type}>
    <slot />
</button>