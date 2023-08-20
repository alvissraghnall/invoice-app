<script>
    let className = '';
    export let type = "text";
    export let id = "";
    export let value = "";
    export let label;
    export let name;
    export { className as class };
    export let labelClassNames;
    export let step = "0.01";

    function typeAction (node) {
      node.type = type;
      console.log(node.type);

      if(node.type === "number") {
        node.step = step;
      }
    }
</script>


{#if label}
  <label class="text-sm mb-1.5 {labelClassNames}" for={name}>{label}</label>
{/if}
<input use:typeAction {name} class={`w-full rounded-xl bg-regColor text-white py-3 px-1 border-none focus\:outline-none ${className}`} {id} bind:value={value} />